package com.example.carpark.service.impl;

import com.example.carpark.dto.TicketDTO;
import com.example.carpark.entity.Ticket;
import com.example.carpark.entity.User;
import com.example.carpark.repository.TicketRepository;
import com.example.carpark.service.BaseService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TicketService implements BaseService<TicketDTO> {

    private final TicketRepository ticketRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public Collection<TicketDTO> getAll() {
        return this.ticketRepository.findAll()
                .stream()
                .map(t -> modelMapper.map(t, TicketDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO create(TicketDTO seedDTO) {
        Ticket ticket = modelMapper.map(seedDTO, Ticket.class);
        ticket.setCreated(Instant.now());
        ticket.setModified(Instant.now());
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
                .withLocale(Locale.UK)
                .withZone(ZoneId.systemDefault());
        String dateAndTime = formatter.format(Instant.now());
        ticket.setDateAndTime(dateAndTime);
        ticket.setUser(getCurrentUser());
        ticketRepository.save(ticket);


        TicketDTO ticketDTO = modelMapper.map(ticket, TicketDTO.class);
        ticket.setDateAndTime(ticket.getDateAndTime());

        return ticketDTO;
    }

    private User getCurrentUser() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userService.getUserByUsername(username);
    }

    @Override
    public TicketDTO findById(String id) throws NotFoundException {
        return this.ticketRepository.findById(id)
                .map(ticket -> modelMapper.map(ticket, TicketDTO.class))
                .orElseThrow(() -> new NotFoundException("Ticket is not found!"));
    }

    @Override
    public boolean remove(String id) {
        return this.ticketRepository.findAll().remove(id);
    }

    @Override
    public TicketDTO update(String id, TicketDTO viewDto) {
        return null;
    }

    @Override
    public TicketDTO getByName(String name) {
        return null;
    }
}
