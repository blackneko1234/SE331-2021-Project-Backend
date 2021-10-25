package se331.lab.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String description;
    String commentDate;

    @ManyToOne
    Doctor sendComment;

    @ManyToOne
    Patient receiveComment;
}
