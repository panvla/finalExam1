package com.vladimirpandurov.finalExam1.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class TaskDTO {

    private Long id;
    @NotBlank
    @Length(min = 0, max=40)
    private String name;
    private String subscriber;
    @Min(0) @Max(20)
    private Integer points;
    private Long sprintId;
    private String sprintName;
    private Long stateId;
    private String stateName;

}
