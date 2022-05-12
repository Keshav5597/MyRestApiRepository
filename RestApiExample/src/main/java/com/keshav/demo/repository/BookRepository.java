package com.keshav.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keshav.demo.model.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
