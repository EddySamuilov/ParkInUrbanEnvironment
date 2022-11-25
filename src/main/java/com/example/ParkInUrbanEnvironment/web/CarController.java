package com.example.carpark.web;

import com.example.carpark.dto.CarDTO;
import com.example.carpark.service.impl.CarService;
import com.example.carpark.service.impl.UserService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/add")
    public String showAll(Model model) {
        if (!model.containsAttribute("carDTO")) {
            model.addAttribute("carDTO", new CarDTO());
        }
        return "add-car";
    }

    @PostMapping("/add")
    public String addCar(
            @Valid @ModelAttribute("carDTO") CarDTO carDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("carDTO", carDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.carDTO", bindingResult);
            return "register";
        }
        try {
            carService.addCarToUser(carDTO);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
