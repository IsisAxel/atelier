package mg.atelier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.atelier.exception.EntityNotFoundException;
import mg.atelier.model.Brand;
import mg.atelier.repository.BrandRepository;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public List<Brand> getBrandByName(String name) {
        return brandRepository.findByName(name);
    }

    public Brand getBrandById(int id) throws EntityNotFoundException {
        return brandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Marque"));
    }
}