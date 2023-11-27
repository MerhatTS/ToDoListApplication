package com.example.ToDoListApplication.Services;

import com.example.ToDoListApplication.Entity.ToDo;
import com.example.ToDoListApplication.Repositories.TodoPageRepository;
import com.example.ToDoListApplication.Repositories.TodoRepositories;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;
@Service
@Getter
@Setter
public class TodoService {
    @Autowired
    TodoRepositories todoRepositories;
    @Autowired
    TodoPageRepository todoPageRepository;


    public void index(Model model){
        Pageable pageable = PageRequest.of(0, 20, Sort.by("id"));
        Page<ToDo> todo = todoPageRepository.findAll2(pageable);

        model.addAttribute("number", todo.getNumber());
        model.addAttribute("totalPages", todo.getTotalPages());
        model.addAttribute("todo", todo.getContent());
    }

    public void page(Model model, Integer id){
        Pageable pageable = PageRequest.of(id-1, 20, Sort.by("id"));
        Page<ToDo> todo = todoPageRepository.findAll2(pageable);

        model.addAttribute("number", todo.getNumber());
        model.addAttribute("totalPages", todo.getTotalPages());
        model.addAttribute("todo", todo.getContent());
    }
    public void add(String title) {
        if (title != null && !title.equals("")) {
            ToDo addToDo = new ToDo();
            addToDo.setTitle(title);
            addToDo.setIsCompleted(false);
            addToDo.setIsPostponed(false);
            addToDo.setIsActive(false);
            todoRepositories.save(addToDo);
        }
    }
    public void delete(Integer id) {
        ToDo todo = todoRepositories.findById(id).get();
        todoRepositories.delete(todo);
    }
    public void changeOfStatusCompleted(Integer id) {
        ToDo todo = todoRepositories.findById(id).get();
            if(!todo.getIsPostponed()){
            todo.setIsCompleted(true);
            todo.setIsActive(false);
            todoRepositories.save(todo);
        }
    }
    public void changeOfStatusPostponed(Integer id) {
        ToDo todo = todoRepositories.findById(id).get();
        if(!todo.getIsCompleted()){
            todo.setIsPostponed(!todo.getIsPostponed());
            todo.setIsActive(false);
            todoRepositories.save(todo);
        }
    }
    public void changeOfStatusActive(Integer id) {
        ToDo todo = todoRepositories.findById(id).get();
        if(!todo.getIsCompleted() && !todo.getIsPostponed()){
            todo.setIsActive(true);
            todoRepositories.save(todo);
        }
    }

    public void completed(Model model, ToDo todo) {
        List<ToDo> allToDo = todoRepositories.findByIsPostponedAndIsActiveAndIsCompletedOrderById(false, false, true);
        model.addAttribute("todo", allToDo);
    }
    public void postponed(Model model, ToDo todo) {
        List<ToDo> allToDo = todoRepositories.findByIsPostponedAndIsActiveAndIsCompletedOrderById(true, false, false);
        model.addAttribute("todo", allToDo);
    }

    public void active(Model model, ToDo todo) {
        List<ToDo> allToDo = todoRepositories.findByIsPostponedAndIsCompletedOrderByIsActiveDesc(false, false);
        model.addAttribute("todo", allToDo);
    }


}
