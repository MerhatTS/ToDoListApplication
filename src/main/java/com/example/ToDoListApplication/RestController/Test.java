package com.example.ToDoListApplication.RestController;

import com.example.ToDoListApplication.Entity.ToDo;
import com.example.ToDoListApplication.Repositories.TodoRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class Test {
    @Autowired
    private TodoRepositories todoRepositories;

    @RequestMapping(path = "/test")
    public Iterable<ToDo> test(){
        Iterable<ToDo> todo = todoRepositories.findAll();
        return todo;
    }

}
