package com.yesolll.mytodo.todo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public Todo findTodo(Long id) {
        Optional<Todo> findTodo = todoRepository.findById(id);
        return findTodo.orElseThrow();
    }
    
    @Transactional
    public Todo updateTodo(Todo todo) {
        // long updated = todoRepository.update(todo);
        // if (updated < 1) throw createruntimeException();
        Optional<Todo> findTodo = todoRepository.findById(todo.getId());
        Todo updateTodo = findTodo.orElseThrow(() -> createruntimeException());
        updateTodo.update(todo);
        return updateTodo;
    }

    public void deleteTodos() {
        todoRepository.deleteAll();
    }

    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    public RuntimeException createruntimeException() {
        return new RuntimeException("실패");
    }

    
}
