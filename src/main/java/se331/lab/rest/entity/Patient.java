package se331.lab.rest.entity;

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

    @ManyToOne
    Doctor doctor;

    @ManyToMany(mappedBy = "patient")
    @Builder.Default
    List<Vaccine> vaccinelist = new ArrayList<>();

    @ManyToMany
    List<Admin> admin = new ArrayList<>();

    @OneToMany (mappedBy = "receiveComment")
    @Builder.Default
    List<Comment> commentList = new ArrayList<>();

    @OneToOne
    User user;

    String image;
}
