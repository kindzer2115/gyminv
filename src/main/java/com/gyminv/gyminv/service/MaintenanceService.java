package com.gyminv.gyminv.service;

import com.gyminv.gyminv.model.Maintenance;
import com.gyminv.gyminv.repository.MaintenanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    // konstruktur ciagnie z repository
    public MaintenanceService(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    // pobieranko calego sprzetu
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAll();
    }

    // pobieranko po id
    public Optional<Maintenance> getMaintenanceById(Long id) {
        return maintenanceRepository.findById(id);
    }

    // zapisywanie + walidacja
    public Maintenance saveMaintenance(Maintenance maintenance) {
        if (maintenance.getDescription() == null || maintenance.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Opis naprawy nie może być pusty!");
        }
        return maintenanceRepository.save(maintenance);
    }

    // Usuwa wpis serwisowy
    public void deleteMaintenance(Long id) {
        maintenanceRepository.deleteById(id);
    }
}