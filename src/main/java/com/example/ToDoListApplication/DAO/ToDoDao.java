package com.example.ToDoListApplication.DAO;

import com.example.ToDoListApplication.Entity.ToDo;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class ToDoDao extends EntityDao<ToDo> {
    @Override
    public ToDo getById(int id) {
        return em.find(ToDo.class, id);
    }

    @Override
    public List<ToDo> getAll() {
        return null;
    }

    @Override
    public void update(ToDo entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void insert(ToDo entity) {

    }

    @Override
    public void delete(ToDo entity) {

    }

    @Override
    public void close() throws Exception {

    }
}
