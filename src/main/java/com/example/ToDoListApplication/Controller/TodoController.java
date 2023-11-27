package com.example.ToDoListApplication.Controller;

import com.example.ToDoListApplication.Entity.ToDo;
import com.example.ToDoListApplication.Services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class TodoController {
    @Autowired
    TodoService todoService;

    @RequestMapping({"/","/index"})
    public String index(Model model){
        todoService.index(model);
        return "index";
    }

    @RequestMapping("/index/{id}")
    public String page(@PathVariable Integer id, Model model){
        todoService.page(model, id);
        return "index";
    }

    @GetMapping("/add")
    public String add(){
        return "add";
        //return new RedirectView("/index");
    }

    @PostMapping("/add")
    public String add(@RequestParam String title){
        todoService.add(title);
        return "redirect:/index";
    }

    @RequestMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id, Model model){
        todoService.delete(id);
        return new RedirectView("/index");
    }

    @RequestMapping("index/completed")
    public String completed(Model model, ToDo todo){
        todoService.completed(model, todo);
        return "completed";
    }

    @RequestMapping("index/postponed")
    public String postponed(Model model, ToDo todo){
        todoService.postponed(model, todo);
        return "postponed";
    }

    @RequestMapping("index/active")
    public String active(Model model, ToDo todo){
        todoService.active(model, todo);
        return "active";
    }

    @RequestMapping("/changeOfStatusCompleted/{id}")
    public RedirectView changeOfStatusCompleted(@PathVariable Integer id) {
        todoService.changeOfStatusCompleted(id);
        return new RedirectView("/index/completed");
    }

    @RequestMapping("/changeOfStatusPostponed/{id}")
    public RedirectView changeOfStatusPostponed(@PathVariable Integer id){
        todoService.changeOfStatusPostponed(id);
        return new RedirectView("/index/postponed");
    }

    @RequestMapping("/changeOfStatusActive/{id}")
    public RedirectView changeOfStatusActive(@PathVariable Integer id){
        todoService.changeOfStatusActive(id);
        return new RedirectView("/index/active");
    }
}
