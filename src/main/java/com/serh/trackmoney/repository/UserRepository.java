package com.serh.trackmoney.repository;

import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);

    boolean existsByEmail(String email);

    Page<User> findUsersByCategories(Category category, Pageable pageable);
}
