package com.example.todo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.todo.model.Task;
import com.example.todo.repository.TaskRepository;

@ExtendWith(MockitoExtension.class) // On active Mockito
public class TaskServiceTest {

    @Mock // On crée un faux Repository (Mock)
    private TaskRepository taskRepository;

    @InjectMocks // On injecte ce faux repository dans le vrai Service
    private TaskService taskService;

    @Test
    public void shouldSaveTask() {
        // 1. Given (Préparation)
        Task taskToSave = new Task();
        taskToSave.setTitle("Apprendre la CI/CD");

        Task savedTask = new Task();
        savedTask.setId("1");
        savedTask.setTitle("Apprendre la CI/CD");

        // On dit au Mock : "Si on t'appelle avec taskToSave, retourne savedTask"
        when(taskRepository.save(taskToSave)).thenReturn(savedTask);

        // 2. When (Action)
        Task result = taskService.saveTask(taskToSave);

        // 3. Then (Vérification)
        assertEquals(1L, result.getId()); // On vérifie que l'ID est bien là
        assertEquals("Apprendre la CI/CD", result.getTitle());
        
        // On vérifie que le service a bien appelé le repository
        verify(taskRepository).save(taskToSave);
    }
}