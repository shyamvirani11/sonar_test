package com.techathon.guesstheword.service.impl;

import com.techathon.guesstheword.entity.WordEntity;
import com.techathon.guesstheword.model.WordDTO;
import com.techathon.guesstheword.repository.WordRepository;
import com.techathon.guesstheword.service.GuessWordService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GuessWordServiceImpl implements GuessWordService {

    @Autowired
    WordRepository wordRepository;

    @Override
    public ResponseEntity<WordDTO> getChosenWord() {
        WordEntity wordEntity = wordRepository.chooseWord();
        WordDTO wordDTO =getWordDTOFromEntity(wordEntity);
        wordDTO.setHints("");
        return new ResponseEntity<>(wordDTO, HttpStatus.OK);
    }

	@Override
	public ResponseEntity<WordDTO> checkValidWord(String word) {
		Optional<WordEntity> wordEntity = wordRepository.findByWord(word);

		if(wordEntity.isPresent()) {
	        return new ResponseEntity<>(HttpStatus.FOUND);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<WordDTO> getHints(String word) {
		Optional<WordEntity> wordEntity = wordRepository.findByWord(word);
		WordDTO wordDTO =getWordDTOFromEntity(wordEntity.get());
		if(wordDTO.getHints().isEmpty()) {
			wordDTO = getScrambledHint(word);
		}
        return new ResponseEntity<>(wordDTO, HttpStatus.OK);
	}
	
	private WordDTO getScrambledHint(String word) {
		WordDTO wordDTO = new WordDTO();
		List<Character> chars = new ArrayList<>(word.length());
		for ( char c : word.toCharArray() ) {
		   chars.add(c);
		}
		Collections.shuffle(chars);
		char[] shuffled = new char[chars.size()];
		for ( int i = 0; i < shuffled.length; i++ ) {
		   shuffled[i] = chars.get(i);
		}
		String shuffledWord = new String(shuffled);
		wordDTO.setWord(word);
		wordDTO.setHints(shuffledWord);
		wordDTO.setScrambledHint(true);
		return wordDTO;
	}

	private WordDTO getWordDTOFromEntity(WordEntity wordEntity) {
		WordDTO wordDTO = new WordDTO();
        BeanUtils.copyProperties(wordEntity, wordDTO);
        wordDTO.setWord(wordEntity.getWord().toLowerCase());
        return wordDTO;
	}

	@Override
	public ResponseEntity<WordDTO> scrambledHint(String word) {
		WordDTO wordDTO = getScrambledHint(word);
		return new ResponseEntity<>(wordDTO, HttpStatus.OK);
	}
}
