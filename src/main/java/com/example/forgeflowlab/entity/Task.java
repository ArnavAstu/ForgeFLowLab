//package com.example.forgeflowlab.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//
//@Entity
//public class Task {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false)
//    private Long id;
//
//    private String title;
//    private String priority;
//
//    @ManyToOne
//    @JoinColumn(name = "project_id") // owning side
//    private Project project;
//
////    public String getTitle() {
////        return title;
////    }
////
////    public void setTitle(String title) {
////        this.title = title;
////    }
////
////    public String getPriority() {
////        return priority;
////    }
////
////    public void setPriority(String priority) {
////        this.priority = priority;
////    }
//
//}

package com.example.forgeflowlab.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Task{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String title;
    private String priority;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

}
