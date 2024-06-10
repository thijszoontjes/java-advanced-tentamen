package nl.inholland.exam.thijs.model;

import jakarta.persistence.*;
import lombok.*;
import nl.inholland.exam.thijs.model.Ship;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrewMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String rank;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;
}
