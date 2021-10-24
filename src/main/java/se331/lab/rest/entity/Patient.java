package se331.lab.rest.entity;

import ch.qos.logback.core.BasicStatusManager;
import lombok.*;
import se331.lab.rest.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String surename;
    Long age;
    String gender;
    String hometown;
    String status;

    @ManyToOne
    Doctor doctor;

    @ManyToMany (mappedBy = "patient")
    List<Vaccine> vaccinelist = new ArrayList<>();

    @ManyToMany
    List<Admin> admin = new ArrayList<>();

    @OneToOne
    User user;

    @ElementCollection
    List<String> image = new ArrayList<>();

}
