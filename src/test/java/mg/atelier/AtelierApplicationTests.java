package mg.atelier;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mg.atelier.model.Genre;
import mg.atelier.service.GenreService;

@SpringBootTest
class AtelierApplicationTests {
	@Autowired
	GenreService genreService;

	@Test
	void contextLoads() {
	}

}
