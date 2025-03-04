package com.frenadol.diccionariofernandospringboot;

import com.frenadol.diccionariofernandospringboot.servicies.WordService;

public class main {

    public static void main(String[] args) {
        WordService wordService = new WordService();
        System.out.println(wordService.getAllWords());
    }
}
