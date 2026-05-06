package com.gyminv.gyminv.service;

import com.gyminv.gyminv.model.Equipment;
import com.gyminv.gyminv.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    // konstruktur ciagnie z repository
    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    // pobieranko calego sprzetu
    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    // pobieranko po id
    public Optional<Equipment> getEquipmentById(Long id) {
        return equipmentRepository.findById(id);
    }

    // zapisywanie + walidacja
    public Equipment saveEquipment(Equipment equipment) {
        if (equipment.getName() == null || equipment.getName().isEmpty()) {
            throw new IllegalArgumentException("Nazwa sprzętu nie może być pusta!");
        }
        return equipmentRepository.save(equipment);
    }

    // usuwanko
    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }
}