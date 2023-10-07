package com.example.ToDoListApplication.Repositories;

import com.example.ToDoListApplication.Entity.ToDo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface TodoRepositories extends CrudRepository<ToDo, Integer> {
    List<ToDo> findByisPostponed(boolean value);
    @Query(value = "from ToDo t where t.isCompleted =:valCom and t.isPostponed =:valPos order by t.id desc")
    List<ToDo> findByisActive(@Param("valCom") Boolean valCom, @Param("valPos") Boolean valPos);

    @Query(value = "from ToDo t order by t.id desc limit 10")
    List<ToDo> allToDo();
    @Query(value = "from ToDo t where t.isCompleted = :value order by t.id desc")
    List<ToDo> completed(@Param("value") Boolean value);

    @Query(value = "from ToDo t order by t.isActive, t.isCompleted, t.isPostponed")
    List<ToDo> findAll();
}
