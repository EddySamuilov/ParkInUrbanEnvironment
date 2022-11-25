package com.example.carpark.service.impl;

import com.example.carpark.dto.AddressDTO;
import com.example.carpark.dto.ParkingSpaceDTO;
import com.example.carpark.dto.TicketDTO;
import com.example.carpark.entity.ParkingSpace;
import com.example.carpark.repository.ParkingSpaceRepository;
import com.example.carpark.service.BaseService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ParkingSpaceService implements BaseService<ParkingSpaceDTO> {

    private final ParkingSpaceRepository parkingSpaceRepository;
    private final AddressService addressService;
    private final ModelMapper modelMapper;

    @Override
    public Collection<ParkingSpaceDTO> getAll() {
        return this.parkingSpaceRepository.findAll()
                .stream()
                .map(p -> modelMapper.map(p, ParkingSpaceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ParkingSpaceDTO create(ParkingSpaceDTO seedDTO) {
        ParkingSpace parkingSpace=modelMapper.map(seedDTO,ParkingSpace.class);
        parkingSpaceRepository.save(parkingSpace);
        return modelMapper.map(parkingSpace, ParkingSpaceDTO.class);
    }

    public ParkingSpace create(ParkingSpace parkingSpace) {
        return parkingSpaceRepository.save(parkingSpace);
    }

    @Override
    public ParkingSpaceDTO findById(String id) throws NotFoundException {
        return this.parkingSpaceRepository.findById(id)
                .map(p -> modelMapper.map(p, ParkingSpaceDTO.class))
                .orElseThrow(()->new NotFoundException("Parking space not found!"));
    }

    @Override
    public boolean remove(String id) {
        return this.parkingSpaceRepository.findAll().remove(id);
    }

    @Override
    public ParkingSpaceDTO update(String id, ParkingSpaceDTO viewDto) {
        return null;
    }

    @Override
    public ParkingSpaceDTO getByName(String name) {
        return null;
    }

    public ParkingSpaceDTO findAddressByAddressId(String id) throws NotFoundException {
        AddressDTO address = addressService.findByAddressId(id);

        if (address != null) {
            address.setOccupied(true);
            addressService.update(id, address);
        }
        ParkingSpaceDTO parkingSpaceDTO = new ParkingSpaceDTO();
        parkingSpaceDTO.setAddress(address);
        parkingSpaceDTO.setTicket(new TicketDTO());
        return parkingSpaceDTO;
    }
}
