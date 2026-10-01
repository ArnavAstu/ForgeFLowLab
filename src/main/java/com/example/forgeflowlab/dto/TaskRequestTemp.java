package com.example.forgeflowlab.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TaskRequestTemp {

    @NotBlank(message = "title should not be left blank")
    @Size(max=100, message = "size should not exceed 100 characters")
    private String title;

    @NotBlank(message = "priority should not be left blank")
    private String priority;

}
