package com.example.carpark.web;

import com.example.carpark.dto.ParkingSpaceDTO;
import com.example.carpark.dto.TicketDTO;
import com.example.carpark.service.impl.ParkingSpaceService;
import com.example.carpark.service.impl.TicketService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {

    private final ParkingSpaceService parkingSpaceService;
    private final TicketService ticketService;

    @GetMapping("/buy")
    public String showTicket() {
        return "ticket-bought-successful";
    }
    @GetMapping("/buy-error")
    public String addressOccupied() {
        return "buy-error";
    }

    @PostMapping("/buy")
    public String buyTicket(@ModelAttribute(value = "id") String id, Model model) {
        ParkingSpaceDTO parkingSpace = null;
        try {
            parkingSpace = parkingSpaceService.findAddressByAddressId(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        TicketDTO ticket = ticketService.create(parkingSpace.getTicket());

        model.addAttribute("address", parkingSpace.getAddress());
        model.addAttribute("ticket", ticket);

        return "ticket-bought-successful";
    }
}
