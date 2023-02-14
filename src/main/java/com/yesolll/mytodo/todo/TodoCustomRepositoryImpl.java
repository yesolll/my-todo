package com.yesolll.mytodo.todo;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.yesolll.mytodo.todo.QTodo.todo;

@RequiredArgsConstructor
public class TodoCustomRepositoryImpl implements TodoCustomRepository{
    
    private final JPAQueryFactory queryFactory;

    public Page<Todo> findAll(Pageable pageable) {
        List<Todo> findTodos = queryFactory
                                    .selectFrom(todo)
                                    .offset(pageable.getOffset())
                                    .limit(pageable.getPageSize())
                                    .fetch();

        // JPAQuery<Todo> countQuery = queryFactory.selectFrom(todo);
        
        return new PageImpl<>(findTodos, pageable, findTodos.size());
    }

    public Todo findById(long id) {
        Todo findTodo = queryFactory.selectFrom(todo).where(todo.id.eq(id)).fetchOne();
        return findTodo;
    }

    public long update(Todo updateTodo) {
        return  queryFactory
                    .update(todo)
                    .set(todo.order, updateTodo.getOrder())
                    .where(todo.id.eq(updateTodo.getId()))
                    .execute();
    }
}
