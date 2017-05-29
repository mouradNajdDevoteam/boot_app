package com.devoteam.guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;

@Controller
@RequestMapping("/guestbook")
@CrossOrigin(origins="*")
public class GuestbookController {
	@Autowired
	private GuestbookRepository repository;
	
	@RequestMapping(value="/",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEntries(){
	//1. Daten laden	
		List<GuestbookEntry> entries=repository.findAllByOrderByIdDesc();
		//Daten zurückgeben
		return ResponseEntity.ok(entries);
	}
	@RequestMapping(value="/",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<?>  create(@RequestBody GuestbookEntry entry){
		//1. Speichern
		entry=repository.save(entry);
		// 2. Zurückgeben
		return ResponseEntity.ok(entry);
	}
	}

