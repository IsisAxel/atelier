package mg.atelier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.atelier.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    List<Brand> findByName(String name);
}
