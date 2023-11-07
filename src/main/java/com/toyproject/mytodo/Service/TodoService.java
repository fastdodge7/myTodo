package com.toyproject.mytodo.Service;

import com.toyproject.mytodo.Dto.TodoFormDto;
import com.toyproject.mytodo.Entity.Todo;
import com.toyproject.mytodo.Entity.User;
import com.toyproject.mytodo.Repository.Todo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public Todo addTodo(TodoFormDto todoFormDto, User loginUser){
        Todo newTodo = Todo.builder()
                .task(todoFormDto.getTask())
                .createdDate(LocalDateTime.now())
                .dueDate(todoFormDto.getDueDate())
                .name(todoFormDto.getName())
                .owner(loginUser).build();

        return this.saveTodo(newTodo);
    }

    @Transactional
    public List<Todo> findByOwner(User user){
        return todoRepository.findByOwner(user);
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
    public Long deleteTodoById(Long id, User loginUser){
        Optional<Todo> targetTodo = todoRepository.findById(id);
        if(targetTodo.isEmpty() || loginUser.getId() != targetTodo.get().getId()) {
            throw new IllegalStateException();
        }
        todoRepository.deleteById(id);
        return id;
    }

    @Transactional
    public Todo updateTodoById(Long id, User loginUser, TodoFormDto updateDto){
        Optional<Todo> targetTodo = todoRepository.findById(id);
        if(targetTodo.isEmpty() || loginUser.getId() != targetTodo.get().getId()) {
            throw new IllegalStateException();
        }
        Todo modTarget = targetTodo.get();
        modTarget.updateTodo(updateDto);
        return modTarget;
    }

    @Transactional
    public Long flipDoneFlag(Long id){
        Todo targetTodo= todoRepository.findById(id).orElseThrow(() -> new IllegalStateException("id에 해당하는 todo가 없습니다."));
        targetTodo.flipDoneFlag();
        return id;
    }
}
