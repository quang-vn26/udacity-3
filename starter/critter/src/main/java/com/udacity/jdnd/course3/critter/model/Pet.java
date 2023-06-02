package com.udacity.jdnd.course3.critter.model;


import com.udacity.jdnd.course3.critter.enums.PetType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    @Getter
    @Setter
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @Getter
    @Setter
    private PetType type;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "birth_date")
    @Getter
    @Setter
    private LocalDate birthDate;

    @Column(name = "notes")
    @Getter
    @Setter
    private String notes;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id")
    @Getter
    @Setter
    private Customer customer;

    @ManyToMany(mappedBy = "pets", targetEntity = Schedule.class)
    @Getter
    @Setter
    private List<Schedule> schedules;

}
