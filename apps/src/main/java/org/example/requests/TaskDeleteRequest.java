package org.example.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TaskDeleteRequest {
    private Long id;
    private String title;
    public Long getTaskId() {
        return id;
    }
}
