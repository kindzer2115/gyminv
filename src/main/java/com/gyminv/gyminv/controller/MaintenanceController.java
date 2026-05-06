package com.gyminv.gyminv.controller;

import com.gyminv.gyminv.model.Maintenance;
import com.gyminv.gyminv.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    //wszystko wypluwa
    @GetMapping
    public List<Maintenance> getAll() {
        return maintenanceService.getAllMaintenances();
    }

    //create
    @PostMapping
    public Maintenance create(@RequestBody Maintenance maintenance) {
        return maintenanceService.saveMaintenance(maintenance);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> update(@PathVariable Long id, @RequestBody Maintenance details) {
        return maintenanceService.getMaintenanceById(id).map(m -> {
            m.setDescription(details.getDescription());
            m.setCost(details.getCost());
            m.setDate(details.getDate());
            m.setEquipment(details.getEquipment());
            return ResponseEntity.ok(maintenanceService.saveMaintenance(m));
        }).orElse(ResponseEntity.notFound().build());
    }

    //usuwanko
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
}