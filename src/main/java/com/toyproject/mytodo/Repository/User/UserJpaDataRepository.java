package com.toyproject.mytodo.Repository.User;

import com.toyproject.mytodo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserJpaDataRepository extends JpaRepository<User, Long> {
    public List<User> findByName(String name);
    public List<User> findByEmail(String email);
}
