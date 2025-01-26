package mg.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.atelier.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
