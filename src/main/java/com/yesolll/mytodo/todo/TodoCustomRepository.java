package com.yesolll.mytodo.todo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TodoCustomRepository {
    Page<Todo> findAll(Pageable pageable);
    Todo findById(long id);
    long update(Todo todo);
}
