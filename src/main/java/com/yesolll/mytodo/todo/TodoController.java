package com.yesolll.mytodo.todo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/todo")
@RestController
@CrossOrigin("*")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper todoMapper;

    public TodoController(TodoService todoService, TodoMapper todoMapper) {
        this.todoService = todoService;
        this.todoMapper = todoMapper;
    }
    
    @PostMapping
    public ResponseEntity<TodoDto.Response> postTodo(@RequestBody TodoDto.Post postDto) {
        Todo todo = todoMapper.todoPostDtoToTodo(postDto);
        Todo createdTodo = todoService.createTodo(todo);
        TodoDto.Response response = todoMapper.todoToTodoResponse(createdTodo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto.Response>> getTodos() {
        List<Todo> todos = todoService.findTodos();
        List<TodoDto.Response> response = todoMapper.todosToTodoResponseDtos(todos);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto.Response> getTodo(@PathVariable long id) {
        Todo todo = todoService.findTodo(id);
        TodoDto.Response response = todoMapper.todoToTodoResponse(todo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoDto.Response> patchTodo(@PathVariable long id, @RequestBody TodoDto.Patch patchDto) {
        Todo todo = todoMapper.todoPatchDtoToTodo(patchDto);
        todo.setId(id);

        Todo updatedTodo = todoService.updateTodo(todo);
        TodoDto.Response response = todoMapper.todoToTodoResponse(updatedTodo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteTodos() {
        todoService.deleteTodos();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable long id) {
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
