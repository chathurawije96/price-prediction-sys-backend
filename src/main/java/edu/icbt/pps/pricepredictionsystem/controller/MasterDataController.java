package edu.icbt.pps.pricepredictionsystem.controller;

import edu.icbt.pps.pricepredictionsystem.dto.MasterDataResponse;
import edu.icbt.pps.pricepredictionsystem.exception.ServiceException;
import edu.icbt.pps.pricepredictionsystem.service.BikeBrandService;
import edu.icbt.pps.pricepredictionsystem.service.BikeModelService;
import edu.icbt.pps.pricepredictionsystem.service.BikeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/master-data")
public class MasterDataController {
    @Autowired
    private BikeTypeService bikeTypeService;
    @Autowired
    private BikeBrandService bikeBrandService;
    @Autowired
    private BikeModelService bikeModelService;

    @GetMapping("/bike-types")
    ResponseEntity<?> getAllBikeType(){
        try {
            return ResponseEntity.ok(bikeTypeService.getAllBikeTypes());
        } catch (ServiceException e) {
           return ResponseEntity.badRequest().body("No Recode Found");
        }
    }

    @GetMapping("/bike-brands/{id}")
    ResponseEntity<?> getAllBikeBrandsByType(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(bikeBrandService.getAllBikeBrands(id));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("No Recode Found");
        }
    }

    @GetMapping("/bike-models/{id}")
    ResponseEntity<?> getAllBikeModelsByType(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(bikeModelService.getAllBikeModels(id));
        } catch (ServiceException e) {
            return ResponseEntity.badRequest().body("No Recode Found");
        }
    }
}
