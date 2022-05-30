package com.techathon.guesstheword.model;

import lombok.Data;

@Data
public class WordDTO {
	private String word;
	private String hints;
	private boolean isScrambledHint;
}
