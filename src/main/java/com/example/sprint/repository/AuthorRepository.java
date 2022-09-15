package com.example.sprint.repository;

import com.example.sprint.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByName(String name);

    Optional<Author> findByNameAndBirth(String name, LocalDate birth);
}
