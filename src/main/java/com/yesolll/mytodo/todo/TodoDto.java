package com.yesolll.mytodo.todo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;


public class TodoDto {
    
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        private String title;
        private int todo_order;
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {
        private String title;
        private int todo_order;
        private boolean completed;
    }
    
    @Getter
    @AllArgsConstructor
    public static class Response {
        private long id;
        private String title;
        private int todo_order;
        private boolean completed;
    }
}
