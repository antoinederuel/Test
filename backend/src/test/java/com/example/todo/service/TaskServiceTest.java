package com.example.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void shouldSaveTask() {
        // 1. Given (Préparation)
        Task taskToSave = new Task();
        taskToSave.setTitle("Apprendre MongoDB");

        Task savedTask = new Task();
        savedTask.setId("64c1f8a2e4b0"); // <-- ICI : On simule un ID Mongo (String), pas un Long (1L)
        savedTask.setTitle("Apprendre MongoDB");

        // On configure le Mock
        when(taskRepository.save(taskToSave)).thenReturn(savedTask);

        // 2. When (Action)
        Task result = taskService.saveTask(taskToSave);

        // 3. Then (Vérification)
        assertEquals("64c1f8a2e4b0", result.getId()); // On vérifie une String
        assertEquals("Apprendre MongoDB", result.getTitle());
        
        verify(taskRepository).save(taskToSave);
    }
}

// COUCOUu