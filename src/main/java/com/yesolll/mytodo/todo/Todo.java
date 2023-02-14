package com.yesolll.mytodo.todo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(length = 250, nullable = false)
    private String title;

    @Column(name = "todo_order")
    private int order;

    private boolean completed;

    public Todo (String title, int order, boolean completed) {
        this.title = title;
        this.order = order;
        this.completed = completed;
        // this.completed = false;
    }

    public void update(Todo todo) {
        this.title = todo.getTitle();
        this.order = todo.getOrder();
        this.completed = todo.isCompleted();
    }

    public void changeCompleted() {
        this.completed = !completed;
    }
}
