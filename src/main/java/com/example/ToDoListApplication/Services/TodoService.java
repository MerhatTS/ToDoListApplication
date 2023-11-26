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

//        List<ToDo> allToDo = todoRepositories.findAll();
//        model.addAttribute("todo", allToDo);
    }

    public void page(Model model, Integer id){
        Pageable pageable = PageRequest.of(id-1, 20, Sort.by("id"));
        Page<ToDo> todo = todoPageRepository.findAll2(pageable);

        model.addAttribute("number", todo.getNumber());
        model.addAttribute("totalPages", todo.getTotalPages());
        model.addAttribute("todo", todo.getContent());
    }
    public void addG(Model model, ToDo todo) {
        model.addAttribute("todo", todo);
    }
    public void addP(Model model, ToDo todo){
        boolean to = false;
        if (!to){
            if (todo.getTitle() !=null && !todo.getTitle().equals("")){
                ToDo addToDo = new ToDo();
                addToDo.setTitle(todo.getTitle());
                addToDo.setIsCompleted(todo.getIsCompleted() == null ? false : true);
                addToDo.setIsPostponed(todo.getIsPostponed() == null ? false : true);
                addToDo.setIsActive(todo.getIsActive() == null ? false : true);
                todoRepositories.save(addToDo);
                model.addAttribute("todo", addToDo);
            }
        }else {
            model.addAttribute("todo", todo);
        }
    }
    public void delete(Integer id) {
        ToDo todo = todoRepositories.findById(id).get();
        todoRepositories.delete(todo);
    }
    public void changeOfStatusCompleted(Integer id) {
        ToDo todo = todoRepositories.findById(id).get();
        System.out.println("id " + todo.getId());
        System.out.println("title " + todo.getTitle());
        System.out.println("isCompleted " + todo.getIsCompleted());
        System.out.println("isPostponed " + todo.getIsPostponed());
        System.out.println("isActive " + todo.getIsActive());

//        if (todo.getIsCompleted() == true){
//            todo.getTitle();
//        }else {
//            todo.setIsCompleted(false);
//        }


        if (todo.getIsCompleted() == true && todo.getIsPostponed() == true && todo.getIsActive() == false){
            todo.setIsCompleted(true);
            todo.setIsPostponed(false);
            todo.setIsActive(false);
        }else if (todo.getIsCompleted() == false && todo.getIsPostponed() == true && todo.getIsActive() == false){
            todo.setIsCompleted(true);
            todo.setIsPostponed(false);
            todo.setIsActive(false);
        }else if (todo.getIsCompleted() == false && todo.getIsPostponed() == false && todo.getIsActive() == false){
            todo.setIsCompleted(true);
            todo.setIsPostponed(false);
            todo.setIsActive(false);
        }
        todoRepositories.save(todo);
        System.out.println("output c");
        System.out.println("isCompleted " + todo.getIsCompleted());
        System.out.println("isPostponed " + todo.getIsPostponed());
        System.out.println("isActive " + todo.getIsActive());
    }
    public void changeOfStatusPostponed(Integer id) {
        ToDo todo = todoRepositories.findById(id).get();
        System.out.println("id " + todo.getId());
        System.out.println("title " + todo.getTitle());
        System.out.println("isCompleted " + todo.getIsCompleted());
        System.out.println("isPostponed " + todo.getIsPostponed());
        System.out.println("isActive " + todo.getIsActive());

        if (todo.getIsCompleted() == true && todo.getIsPostponed() == false && todo.getIsActive() == false){
            todo.setIsCompleted(true);
            todo.setIsPostponed(false);
            todo.setIsActive(false);
        }else if (todo.getIsCompleted() == false && todo.getIsPostponed() == false && todo.getIsActive() == false){
            todo.setIsCompleted(false);
            todo.setIsPostponed(true);
            todo.setIsActive(false);
        }else if (todo.getIsCompleted() == false && todo.getIsPostponed() == true && todo.getIsActive() == false){
            todo.setIsCompleted(false);
            todo.setIsPostponed(false);
            todo.setIsActive(true);
        }else if (todo.getIsCompleted() == false && todo.getIsPostponed() == false && todo.getIsActive() == true){
            todo.setIsCompleted(false);
            todo.setIsPostponed(true);
            todo.setIsActive(false);
        }else if (todo.getIsCompleted() == true && todo.getIsPostponed() == true && todo.getIsActive() == false){
            todo.setIsCompleted(true);
            todo.setIsPostponed(false);
            todo.setIsActive(false);
        }else if (todo.getIsCompleted() == true && todo.getIsPostponed() == false && todo.getIsActive() == true){
            todo.setIsCompleted(true);
            todo.setIsPostponed(false);
            todo.setIsActive(false);
        }

        todoRepositories.save(todo);
        System.out.println("output p");
        System.out.println("isCompleted " + todo.getIsCompleted());
        System.out.println("isPostponed " + todo.getIsPostponed());
        System.out.println("isActive " + todo.getIsActive());
    }

    public void changeOfStatusActive(Integer id) {
        ToDo todo = todoRepositories.findById(id).get();
        System.out.println("id " + todo.getId());
        System.out.println("title " + todo.getTitle());
        System.out.println("isCompleted " + todo.getIsCompleted());
        System.out.println("isPostponed " + todo.getIsPostponed());
        System.out.println("isActive " + todo.getIsActive());
        todo.setIsActive(true);
        if (todo.getIsPostponed() == true){
            todo.setIsActive(false);
        }else {
            todo.setIsPostponed(true);
        }
        todoRepositories.save(todo);
        System.out.println("isActive " + todo.getIsActive());
    }

    public void completed(Model model, ToDo todo) {
        List<ToDo> allToDo = todoRepositories.completed(true);
        model.addAttribute("todo", allToDo);
    }
    public void postponed(Model model, ToDo todo) {
        List<ToDo> allToDo = todoRepositories.findByisPostponed(true);
        model.addAttribute("todo", allToDo);
    }

    public void active(Model model, ToDo todo) {
        List<ToDo> allToDo = todoRepositories.findByisActive(false, false);
        model.addAttribute("todo", allToDo);
    }


}
