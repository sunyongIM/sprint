package com.example.sprint.repository;

import com.example.sprint.entity.Book;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book ORDER BY id", nativeQuery = true)
    PageImpl<Book> findAllOrderById(Pageable pageable);

    @Query(value = "SELECT * FROM book ORDER BY publish_date DESC", nativeQuery = true)
    PageImpl<Book> findAllOrderByPublishDate(Pageable pageable);

}
