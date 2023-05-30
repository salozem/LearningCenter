package com.acme.learning.platform.learning.domain.model;

import com.acme.learning.platform.shared.domain.model.AuditModel;
import com.acme.learning.platform.shared.exception.ResourceNotFoundException;
import com.acme.learning.platform.shared.exception.ResourceValidationException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "skills")
public class Skill extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String name;

    //Relationship
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "skill")
    private Set<Criterion> criteria = new HashSet<>();

    public Skill addCriterion(String criterionName){
        if (criteria == null){
            criteria = new HashSet<>();
        }
        //Validate Criterion Name Uniqueness for Skill
        if (!criteria.isEmpty()){
            if (criteria.stream().anyMatch(criterion -> criterion.getName().equals(criterionName)))
                throw new ResourceValidationException("Criterion", "A criterion with the same name alredy exists.");
        }

        //Add Criterion to Skill
        criteria.add(new Criterion()
                .withName(criterionName)
                .withSkill(this));

        return this;
    }
}