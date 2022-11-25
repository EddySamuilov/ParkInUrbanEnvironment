package com.example.carpark.web;

import com.example.carpark.dto.AddressDTO;
import com.example.carpark.service.impl.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/parking/spot")
@AllArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping
    public String showParkingPlaces(Model model) {
        List<String> cities = addressService.getAllDTOs();

        model.addAttribute("cities", cities);

        return "choose-town";
    }

    @GetMapping("/{townName}")
    public String showFreePlacesForTown(@PathVariable String townName, Model model) {
        List<AddressDTO> addresses = addressService.findAllFreeSpotsFor(townName);

        model.addAttribute("addresses", addresses);

        return "town";
    }
}



