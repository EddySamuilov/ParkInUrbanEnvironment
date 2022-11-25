package com.example.carpark.init;

import com.example.carpark.entity.*;
import com.example.carpark.repository.UserRoleRepository;
import com.example.carpark.service.impl.AddressService;
import com.example.carpark.service.impl.CarService;
import com.example.carpark.service.impl.ParkingSpaceService;
import com.example.carpark.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
public class DataInit implements CommandLineRunner {

    private final UserService userService;
    private final CarService carService;
    private final AddressService addressService;
    private final UserRoleRepository userRoleRepository;
    private final ParkingSpaceService parkingSpaceService;

    @Override
    public void run(String... args) {
        if (userRoleRepository.count() != 0) {
            return;
        }
        initUsers();

        Address sofia = initAddress("Sofia", "Mladost", "Alexander Malinov", 4);
        Address sofia1 = initAddress("Sofia", "Obelya", "6-ta", 7);
        Address sofia2 = initAddress("Sofia", "Vitosha", "Narcissus", 9);
        Address plovdiv = initAddress("Plovdiv", "Kamenitsa", "Rodopi", 11);
        Address plovdiv1 = initAddress("Plovdiv", "Kichuk Parij", "Peter Vaskov", 6);
        Address plovdiv2 = initAddress("Plovdiv", "Smirnenski", "Peshtersko shose", 123);
        Address plovdiv3 = initAddress("Plovdiv", "Smirnenski", "Orfei", 24);
        Address plovdiv4 = initAddress("Plovdiv", "Trakia", "Sankt Peterburg", 1);
        Address plovdiv5 = initAddress("Plovdiv", "Mladejki hulm", "Volga", 6);
        Address burgas = initAddress("Burgas", "Zornitsa", "Boycho Branzov", 1);;
        Address burgas1 = initAddress("Burgas", "Izgrev", "Petko Zadgorski", 12);;
        Address burgas2 = initAddress("Burgas", "Slaveykov", "Trakia", 3);;

        initParkingSpace(sofia, BigDecimal.valueOf(2.50));
        initParkingSpace(sofia1, BigDecimal.valueOf(2.50));
        initParkingSpace(sofia2, BigDecimal.valueOf(2.50));
        initParkingSpace(plovdiv, BigDecimal.valueOf(2.00));
        initParkingSpace(plovdiv1, BigDecimal.valueOf(2.00));
        initParkingSpace(plovdiv2, BigDecimal.valueOf(2.00));
        initParkingSpace(plovdiv3, BigDecimal.valueOf(2.00));
        initParkingSpace(plovdiv4, BigDecimal.valueOf(2.00));
        initParkingSpace(plovdiv5, BigDecimal.valueOf(2.00));
        initParkingSpace(burgas, BigDecimal.valueOf(1.50));
        initParkingSpace(burgas1, BigDecimal.valueOf(1.50));
        initParkingSpace(burgas2, BigDecimal.valueOf(1.50));
    }

    private void initParkingSpace(Address address, BigDecimal price) {
        ParkingSpace parkingSpace = new ParkingSpace();
        parkingSpace.setAddress(address);
        parkingSpace.setPrice(price);

        setCurrentTimestamps(parkingSpace);
        parkingSpaceService.create(parkingSpace);
    }

    private Address initAddress(String city, String neighbourhood, String street, int numberOfStreet) {
        Address address = new Address();
        address.setCity(city);
        address.setNeighbourhood(neighbourhood);
        address.setStreet(street);
        address.setNumberOfStreet(numberOfStreet);

        setCurrentTimestamps(address);
        addressService.createAddress(address);

        return address;
    }

    private void initCar(User user) {
        Car car = new Car();
        car.setRegistrationNumber("RA0000ND");
        car.setUser(user);
        car.setBrand("random brand");
        car.setModel("random car");
        setCurrentTimestamps(car);
        carService.create(car);
    }

    private <T extends BaseEntity> void setCurrentTimestamps(T entity) {
        entity.setCreated(Instant.now());
        entity.setModified(Instant.now());
    }

    private void initUsers() {
        RoleEntity userRoleAdmin = new RoleEntity();
        userRoleAdmin.setRole(RoleEnumType.ADMIN);
        RoleEntity userRoleUser = new RoleEntity();
        userRoleUser.setRole(RoleEnumType.USER);

        userRoleRepository.saveAll(List.of(userRoleAdmin, userRoleUser));

        User user = new User();

        user.setUsername("admin");
        user.setRoles(List.of(userRoleAdmin));
        user.setPassword("123");
        user.setEmail("admin@admin.com");
        user.setFirstName("admin");
        user.setLastName("adminov");
        user.setAge(12);

        userService.createV1(user);

        initCar(user);
    }
}
