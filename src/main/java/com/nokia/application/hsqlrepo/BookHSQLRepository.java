package com.nokia.application.hsqlrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nokia.application.model.Book;

@Repository
public interface BookHSQLRepository extends JpaRepository<Book, Long> {
	Book findTopByOrderByIdDesc();
}
