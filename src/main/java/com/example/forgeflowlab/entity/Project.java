package com.example.forgeflowlab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter

public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long Id;

    private String name;

//    @OneToMany (mappedBy = "project",            // inverse side
//    cascade = CascadeType.ALL  // propagates certain operations from one entity to its related entities For example:
////    Delete Project
////      ↓
////    Delete related Tasks, CascadeType.ALL ensures this
//    , orphanRemoval = true // removes child entities that are no longer associated with the parent
//    )
//    private List<Task> tasks;

    @OneToMany (mappedBy = "project") // inverse side
    private List<Task> tasks;

}
