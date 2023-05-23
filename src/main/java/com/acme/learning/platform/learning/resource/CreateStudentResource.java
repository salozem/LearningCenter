package com.acme.learning.platform.learning.resource;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentResource {
    @NotBlank
    @NotNull
    @Size(max = 60)
    private String name;

    @Min(18)
    private int age;

    @Size(max = 240)
    private String address;
}
