package com.nokia.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nokia.application.model.Book;
import com.nokia.application.service.StackService;

@RestController()
@RequestMapping(value = "/api")
public class StackController {

	@Autowired
	StackService service;

	@PostMapping(path = "/{db}/push", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> push(@RequestBody Book book, @PathVariable String db) {
		book = service.push(book, db);
		if (book != null) {
			return new ResponseEntity<Object>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{db}/pop")
	public ResponseEntity<Object> pop(@PathVariable String db) {

		Book book = service.pop(db);
		if (book != null) {
			return new ResponseEntity<Object>(book, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}

	}

}
