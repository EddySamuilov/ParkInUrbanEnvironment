package com.example.carpark.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User extends BaseEntity {

    @Size(min = 3, message = "Username length must be at least 3 symbols!")
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Size(min = 3, message = "Password length must be at least 3 symbols!")
    @Column(name = "password", unique = true, nullable = false)
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private int age;

    @Email
    @Column
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<Car> cars;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user")
    private Set<Ticket> tickets;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RoleEntity> roles;
}
