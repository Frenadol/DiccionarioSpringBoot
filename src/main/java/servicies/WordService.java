package servicies;

import exception.WordNotFoundException;
import models.Palabra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.WordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public List<Palabra> getAllWords() {
        return wordRepository.findAll();
    }

    public Palabra getWordById(Long id) throws WordNotFoundException {
        Optional<Palabra> palabra = wordRepository.findById(id);
        if (palabra.isPresent()) {
            return palabra.get();
        } else {
            throw new WordNotFoundException("Word not found with id: " + id);
        }
    }

    public Palabra createWord(Palabra word) {
        return wordRepository.save(word);
    }

    public Palabra updateWord(Long id, Palabra updatedWord) throws WordNotFoundException {
        if (wordRepository.existsById(id)) {
            updatedWord.setId(id);
            return wordRepository.save(updatedWord);
        } else {
            throw new WordNotFoundException("Word not found with id: " + id);
        }
    }

    public void deleteWord(Long id) throws WordNotFoundException {
        if (wordRepository.existsById(id)) {
            wordRepository.deleteById(id);
        } else {
            throw new WordNotFoundException("Word not found with id: " + id);
        }
    }
}