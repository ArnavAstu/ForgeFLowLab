package com.example.forgeflowlab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Tasktemp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String priority;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Projecttemp project;


}
