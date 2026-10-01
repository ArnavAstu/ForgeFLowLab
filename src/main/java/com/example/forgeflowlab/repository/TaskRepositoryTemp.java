package com.example.forgeflowlab.repository;

import com.example.forgeflowlab.entity.Tasktemp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepositoryTemp extends JpaRepository<Tasktemp, Long> {

    List<Tasktemp> findByPriorityAndTitleContainingIgnoreCase(String priority, String title);


}
