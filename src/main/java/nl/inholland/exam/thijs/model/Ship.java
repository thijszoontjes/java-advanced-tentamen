package nl.inholland.exam.thijs.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String classType;
    private int crewCapacity;

    @OneToMany(mappedBy = "ship", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CrewMember> crewMembers = new ArrayList<>();

    @OneToMany(mappedBy = "ship", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Mission> missions = new ArrayList<>();
}
