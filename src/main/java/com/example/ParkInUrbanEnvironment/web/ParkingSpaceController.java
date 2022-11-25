package com.example.carpark.web;

import com.example.carpark.dto.CarDTO;
import com.example.carpark.service.impl.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/parking/spot")
@AllArgsConstructor
public class ParkingSpaceController {

    private final CarService carService;


    @GetMapping("/choose-car")
    public String showUserCars(Model model) {
        List<CarDTO> cars = carService.getAllCarsForTheLoggedInUser();

        model.addAttribute("cars", cars);

        return "choose-car";
    }




}
