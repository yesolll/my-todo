package com.yesolll.mytodo.todo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TodoMapper {
    @Mapping(source = "todo_order", target = "order")
    Todo todoPostDtoToTodo(TodoDto.Post todoPostDto);
    @Mapping(source = "todo_order", target = "order")
    Todo todoPatchDtoToTodo(TodoDto.Patch todoPatchDto);
    @Mapping(source = "order", target = "todo_order")
    TodoDto.Response todoToTodoResponse(Todo todo);
    @Mapping(source = "order", target = "todo_order")
    List<TodoDto.Response> todosToTodoResponseDtos(List<Todo> todos);
}
