package com.serh.trackmoney.repository;

import com.serh.trackmoney.model.Category;
import com.serh.trackmoney.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);

    @Query(value = "SELECT * FROM categories WHERE id < ?1",
            nativeQuery = true)
    List<Category> findAllDefault(Long id);

    List<Category> findByIdGreaterThanAndUsersIs(Long specificStarts, User user);
}
