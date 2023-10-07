package com.example.ToDoListApplication.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "todo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @Column(name = "is_completed")
    private Boolean isCompleted;
    @Column(name = "is_postponed")
    private Boolean isPostponed;
    @Column(name = "is_active")
    private Boolean isActive;
}