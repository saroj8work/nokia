package com.nokia.application.h2repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nokia.application.model.Book;

@Repository
public interface BookH2Repository extends JpaRepository<Book, Long> {
	
	Book findTopByOrderByIdDesc();

}
