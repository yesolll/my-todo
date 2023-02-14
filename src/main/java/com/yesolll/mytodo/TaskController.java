package com.yesolll.mytodo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    
    @GetMapping("/")
    public String main() {
        return "To-do Application!";
    }
}
