package com.example.ToDoListApplication.Repositories;

import com.example.ToDoListApplication.Entity.ToDo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
public interface TodoPageRepository extends PagingAndSortingRepository<ToDo, Integer> {
    @Query(value = "from ToDo t order by t.isActive desc, t.isCompleted, t.isPostponed")
    Page<ToDo> findAll2(Pageable pageable);
}
