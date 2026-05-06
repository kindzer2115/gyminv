package com.gyminv.gyminv.controller;

import com.gyminv.gyminv.model.Equipment;
import com.gyminv.gyminv.service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    //wszystko wypluwa
    @GetMapping
    public List<Equipment> getAll() {
        return equipmentService.getAllEquipment();
    }

    //create
    @PostMapping
    public Equipment create(@RequestBody Equipment equipment) {
        return equipmentService.saveEquipment(equipment);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> update(@PathVariable Long id, @RequestBody Equipment details) {
        return equipmentService.getEquipmentById(id).map(equipment -> {
            equipment.setName(details.getName());
            equipment.setBrand(details.getBrand());
            equipment.setCategory(details.getCategory());
            return ResponseEntity.ok(equipmentService.saveEquipment(equipment));
        }).orElse(ResponseEntity.notFound().build());
    }

    //usuwanko
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}