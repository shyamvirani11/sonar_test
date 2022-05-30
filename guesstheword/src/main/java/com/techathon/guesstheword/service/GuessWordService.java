package com.techathon.guesstheword.service;

import com.techathon.guesstheword.model.WordDTO;
import org.springframework.http.ResponseEntity;

public interface GuessWordService {

    ResponseEntity<WordDTO> getChosenWord();

	ResponseEntity<WordDTO> checkValidWord(String word);

	ResponseEntity<WordDTO> getHints(String word);

	ResponseEntity<WordDTO> scrambledHint(String word);
}
