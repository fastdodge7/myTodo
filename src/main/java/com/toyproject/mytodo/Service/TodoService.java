package com.toyproject.mytodo.Service;

import com.toyproject.mytodo.Entity.Todo;
import com.toyproject.mytodo.Entity.User;
import com.toyproject.mytodo.Repository.Todo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @Transactional
    public Todo saveTodo(Todo todo){
        todoRepository.save(todo);
        return todo;
    }

    @Transactional
    public Optional<Todo> findById(Long id){
        return todoRepository.findById(id);
    }

    @Transactional
    public List<Todo> findAll(){
        return todoRepository.findAll();
    }

    @Transactional
    public Long deleteTodoById(Long id){
        todoRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Long flipDoneFlag(Long id){
        Todo targetTodo= todoRepository.findById(id).orElseThrow(() -> new IllegalStateException("id에 해당하는 todo가 없습니다."));
        targetTodo.flipDoneFlag();
        return id;
    }
}
