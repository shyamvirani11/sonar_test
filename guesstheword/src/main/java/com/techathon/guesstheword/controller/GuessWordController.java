package com.techathon.guesstheword.controller;

import com.techathon.guesstheword.model.WordDTO;
import com.techathon.guesstheword.service.GuessWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GuessWordController {

	@Autowired
	GuessWordService guessWordService;

	@GetMapping("/chooseWord")
	public ResponseEntity<WordDTO> chooseWord() {
		log.info("In to the GuessWordController");
		return guessWordService.getChosenWord();
	}
	
	@GetMapping("/checkValidWord")
	public ResponseEntity<WordDTO> checkValidWord(@RequestParam(name="word") String word) {
		return guessWordService.checkValidWord(word);
	}
	
	@GetMapping("/hints")
	public ResponseEntity<WordDTO> getHints(@RequestParam(name="word") String word) {
		return guessWordService.getHints(word);
	}
	
	@GetMapping("/scrambledHint")
	public ResponseEntity<WordDTO> scrambledHint(@RequestParam(name="word") String word) {
		return guessWordService.scrambledHint(word);
	}
	
	

}
