package com.toyproject.mytodo.Repository.Todo;

import com.toyproject.mytodo.Entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaDataRepository extends JpaRepository<Todo, Long> {

}
