package org.example.repositories;

import org.example.entities.Task;
import org.example.entities.User;
import org.example.requests.TaskRequest;
import org.example.types.SortType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
//    @Query("SELECT t FROM Task t")
//    List<Task> findByPriorityId(Sort sort);
//    @Query("SELECT t FROM Task t")
//    List<Task> findByCompleted(Sort sort);
//    @Query("SELECT t FROM Task t")
//    List<Task> findByDueDate(Sort sort);

    @Query("SELECT t FROM Task t WHERE t.title LIKE CONCAT('%', :query, '%') OR t.description LIKE CONCAT('%', :query, '%') " +
            " AND t.priority_id LIKE CONCAT('%', :priority_id, '%')" +
            " AND t.completed LIKE CONCAT('%', :completed, '%')" +
            " AND t.due_date LIKE CONCAT('%', :due_date, '%')")
    List<Task> findByQueryAndSort(@Param("query") String query,
                           @Param("priority_id") int priority_id,
                           @Param("completed") boolean completed,
                           @Param("due_date") String due_date);
}
