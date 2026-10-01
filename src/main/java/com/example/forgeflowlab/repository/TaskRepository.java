//package com.example.forgeflowlab.repository;
//
//import com.example.forgeflowlab.entity.Task;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface TaskRepository extends JpaRepository<Task, Long> {
//
//}

package com.example.forgeflowlab.repository;
import com.example.forgeflowlab.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {

    // Derived Qeueries

    List<Task> findByPriority(String priority);

    List<Task> findByPriorityAndTitle(
            String priority,
            String title
    );

    List<Task> findByPriorityOrTitle(
            String priority,
            String title
    );

    List<Task> findByTitleContaining(String title);

    List<Task> findByTitleStartingWith(String title);

    List<Task> findByTitleEndingWith(String title);

    List<Task> findByPriorityIgnoreCase(String priority);

    List<Task> findByPriorityAndTitleContainingIgnoreCase(String priority, String title);

    @Query("SELECT t FROM Task t WHERE t.priority = :priority")
    List<Task> findTasksByPriority(@Param("priority") String priority);

    // Find tasks by priority and title
    @Query("""
           SELECT t FROM Task t
           WHERE t.priority = :priority
           AND t.title LIKE %:title%
           """)
    List<Task> searchTasks(
            @Param("priority") String priority,
            @Param("title") String title
    );

}
