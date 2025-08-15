package org.example.mappers;

import org.example.entities.Task;
import org.example.requests.TaskRequest;
import org.example.types.SortType;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskRequest toDto(Task task);
    Task toEntity(TaskRequest task);

}
