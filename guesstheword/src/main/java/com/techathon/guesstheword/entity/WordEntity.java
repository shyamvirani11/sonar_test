package com.techathon.guesstheword.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="words")
@Data
public class WordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Word")
    private String word;

    @Column(name = "Hints")
    private String hints;

}
