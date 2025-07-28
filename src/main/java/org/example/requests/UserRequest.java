package org.example.requests;

//Data Transfer Object - things that will be shown publicly

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserRequest {
    //data that can be accessed on the outside
    @JsonIgnore
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long userId;
    private String username;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
