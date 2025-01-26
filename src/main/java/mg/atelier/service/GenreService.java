
package mg.atelier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.atelier.exception.EntityNotFoundException;
import mg.atelier.model.Genre;
import mg.atelier.repository.GenreRepository;

@Service
public class GenreService {
    @Autowired
    private GenreRepository GenreRepository;

    public List<Genre> getAllGenres() {
        return GenreRepository.findAll();
    }
}