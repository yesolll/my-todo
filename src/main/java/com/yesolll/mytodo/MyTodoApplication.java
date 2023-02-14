package com.yesolll.mytodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MyTodoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MyTodoApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { // (2)
        return builder.sources(MyTodoApplication.class);
    }

}
