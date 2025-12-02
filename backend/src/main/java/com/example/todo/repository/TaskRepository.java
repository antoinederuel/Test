package com.example.todo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.todo.model.Task;

@Repository

public interface TaskRepository extends MongoRepository<Task, String> {
}