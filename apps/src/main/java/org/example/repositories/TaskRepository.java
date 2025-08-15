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
    List<Task> findByTitleContainingOrDescriptionContaining(String title, String description, Sort sort);
}
