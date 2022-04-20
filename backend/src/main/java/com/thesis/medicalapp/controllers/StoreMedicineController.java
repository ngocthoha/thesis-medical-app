package com.thesis.medicalapp.controllers;

import com.thesis.medicalapp.models.StoreMedicine;
import com.thesis.medicalapp.services.StoreMedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StoreMedicineController {
    private final StoreMedicineService storeMedicineService;

    @GetMapping("/store-medicine")
    public List<StoreMedicine> getStoreMedicines() {
        return storeMedicineService.getStoreMedicines();
    }

    @PostMapping("/store-medicine")
    public StoreMedicine saveStoreMedicine(@RequestBody StoreMedicine storeMedicine) {
        return storeMedicineService.saveStoreMedicine(storeMedicine);
    }
}
