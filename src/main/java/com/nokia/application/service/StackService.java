package com.nokia.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nokia.application.h2repo.BookH2Repository;
import com.nokia.application.hsqlrepo.BookHSQLRepository;
import com.nokia.application.model.Book;

@Service
public class StackService {

	@Autowired
	BookH2Repository h2Repo;
	@Autowired
	BookHSQLRepository hsqlRepo;
	
	public Book push(Book book, String db) {
		if(db.equalsIgnoreCase("H2")) {
			return h2Repo.save(book);
		}
		else if(db.equalsIgnoreCase("HSQL")) {
			return hsqlRepo.save(book);
		}
		return null;
		
	}
	
	public Book pop(String db) {
		Book book = null;
		if(db.equalsIgnoreCase("H2")) {
			
			book = h2Repo.findTopByOrderByIdDesc();
			h2Repo.deleteById(book.getId());
			
		}
		else if(db.equalsIgnoreCase("HSQL")) {
			book = hsqlRepo.findTopByOrderByIdDesc();
			hsqlRepo.deleteById(book.getId());
			
		}
		return book;
	}
	
}
