package org.example.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class TaskRequest {
    private Long id;
    private String categoryId;
    private Boolean completed;
    private String description;
    private Date dueDate;
    private Long priorityId;
    private String title;
}
