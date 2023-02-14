package com.yesolll.mytodo;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yesolll.mytodo.todo.Todo;
import com.yesolll.mytodo.todo.TodoRepository;

import static com.yesolll.mytodo.todo.QTodo.todo;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QueryDslTest {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    JPAQueryFactory queryFactory;

    @BeforeAll
    public void setData() {
        Todo todo1 = new Todo("할일1", 1, false);
        Todo todo2 = new Todo("할일2", 2, false);
        Todo todo3 = new Todo("할일3", 3, false);

        todoRepository.saveAll(List.of(todo1, todo2, todo3));
    }

    @Test
    public void basicQueryTest() {
        List<Todo> todos = queryFactory
                            .selectFrom(todo)
                            .fetch();
        assertThat(todos.size()).isEqualTo(3);
    }
    
    @Test
    public void basicQueryWithConditionTest() {
        List<Todo> todos = queryFactory
                            .selectFrom(todo)
                            .where(todo.completed.eq(true))
                            .fetch();
        assertThat(todos.size()).isEqualTo(0);
    }
    
    @Test
    public void basicQueryWithConditionTest2() {
        List<Todo> todos = queryFactory
                            .selectFrom(todo)
                            .where(todo.order.goe(2))
                            .fetch();
        assertThat(todos.size()).isEqualTo(2);
    }
    
    @Test
    public void basicQueryWithConditionTest3() {
        List<Todo> todos = queryFactory
                            .selectFrom(todo)
                            .orderBy(todo.order.desc())
                            .fetch();
        assertThat(todos.size()).isEqualTo(3);
        assertThat(todos.get(0).getTitle()).isEqualTo("할일3");
    }
    
    @Test
    public void pagingQueryTest() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        List<Todo> todos = queryFactory
                            .selectFrom(todo)
                            .offset(pageRequest.getOffset())
                            .limit(pageRequest.getPageSize())
                            .fetch();

        assertThat(todos.size()).isEqualTo(2);
    }
}
