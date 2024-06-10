package nl.inholland.exam.thijs.model;

import jakarta.persistence.*;
import lombok.*;
import nl.inholland.exam.thijs.model.Ship;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;
}
