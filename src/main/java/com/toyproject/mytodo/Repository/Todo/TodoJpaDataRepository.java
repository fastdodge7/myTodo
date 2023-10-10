package com.toyproject.mytodo.Repository.Todo;

import com.toyproject.mytodo.Entity.Todo;
import com.toyproject.mytodo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoJpaDataRepository extends JpaRepository<Todo, Long> {
    public List<Todo> findByOwner(User owner);
}
