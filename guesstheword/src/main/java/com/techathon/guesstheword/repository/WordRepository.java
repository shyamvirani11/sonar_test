package com.techathon.guesstheword.repository;

import com.techathon.guesstheword.entity.WordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<WordEntity, Integer> {

    @Query(value = "SELECT * FROM words ORDER BY RAND() LIMIT 1", nativeQuery = true)
    public WordEntity chooseWord();

	 Optional<WordEntity> findByWord(String word);
}
