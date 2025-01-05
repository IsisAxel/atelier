package mg.atelier.service;

import mg.atelier.exception.EntityNotFoundException;
import mg.atelier.model.ReparationTypePrice;
import mg.atelier.repository.ReparationTypePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReparationTypePriceService {

    @Autowired
    private ReparationTypePriceRepository reparationTypePriceRepository;

    public ReparationTypePrice createReparationTypePrice(ReparationTypePrice reparationTypePrice) {
        return reparationTypePriceRepository.save(reparationTypePrice);
    }

    public ReparationTypePrice getReparationTypePriceByIdReparationTypeAndDate(int idReparationType , LocalDateTime date) {
        return reparationTypePriceRepository.getReparationTypePriceByIdReparationTypeAndDate(idReparationType , date);
    }

    public List<ReparationTypePrice> getReparationTypePriceByDate(LocalDateTime date) {
        return reparationTypePriceRepository.getReparationTypePriceByDate(date);
    }

    public ReparationTypePrice getReparationTypePriceById(int id) {
        return reparationTypePriceRepository.findById(id).orElse(null);
    }

    public ReparationTypePrice updateReparationTypePrice(int id , double price) throws EntityNotFoundException {
        ReparationTypePrice reparationTypePriceToUpdate = reparationTypePriceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Type de reparation avec prix"));
        ReparationTypePrice newReparationTypePrice = new ReparationTypePrice();
        newReparationTypePrice.setPrice(price);
        newReparationTypePrice.setEffectiveDate(LocalDateTime.now());
        newReparationTypePrice.setReparationType(reparationTypePriceToUpdate.getReparationType());
        return reparationTypePriceRepository.save(newReparationTypePrice);
    }
}