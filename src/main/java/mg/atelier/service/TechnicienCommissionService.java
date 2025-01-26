package mg.atelier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mg.atelier.model.TechnicienCommission;
import mg.atelier.repository.TechnicienCommissionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class TechnicienCommissionService {

    @Autowired
    private TechnicienCommissionRepository technicienCommissionRepository;

    public List<TechnicienCommission> getTechnicienCommissions(LocalDate startDate, LocalDate endDate) {
        return technicienCommissionRepository.findTechnicienCommissionBetweenDates(startDate, endDate);
    
}}
