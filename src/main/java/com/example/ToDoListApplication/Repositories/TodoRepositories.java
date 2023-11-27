package com.example.ToDoListApplication.Repositories;

import com.example.ToDoListApplication.Entity.ToDo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface TodoRepositories extends CrudRepository<ToDo, Integer> {
    List<ToDo> findByIsPostponedAndIsActiveAndIsCompletedOrderById(boolean isPostponed, boolean isActive, boolean isCompleted);
    List<ToDo> findByIsPostponedAndIsCompletedOrderByIsActiveDesc(boolean isPostponed, boolean isCompleted);

}
