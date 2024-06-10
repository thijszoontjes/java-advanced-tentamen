package nl.inholland.exam.thijs.service;

import nl.inholland.exam.thijs.dto.CrewMemberDTO;
import nl.inholland.exam.thijs.model.CrewMember;
import nl.inholland.exam.thijs.model.Ship;
import nl.inholland.exam.thijs.repository.CrewMemberRepository;
import nl.inholland.exam.thijs.repository.ShipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CrewMemberServiceTest {

    @Mock
    private CrewMemberRepository crewMemberRepository;

    @Mock
    private ShipRepository shipRepository;

    @InjectMocks
    private CrewMemberService crewMemberService;

    private Ship ship;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ship = new Ship();
        ship.setId(1L);
        ship.setName("USS Enterprise");
        ship.setClassType("Explorer");
        ship.setCrewCapacity(5);
    }

    @Test
    void createCrewMember_Success() {
        // Arrange
        CrewMemberDTO crewMemberDTO = new CrewMemberDTO();
        crewMemberDTO.setName("John Doe");
        crewMemberDTO.setRank("Captain");
        crewMemberDTO.setShipId(ship.getId());

        when(shipRepository.findById(ship.getId())).thenReturn(Optional.of(ship));
        when(crewMemberRepository.countByShip(ship)).thenReturn(4L); // Under capacity

        CrewMember savedCrewMember = new CrewMember();
        savedCrewMember.setId(1L);
        savedCrewMember.setName(crewMemberDTO.getName());
        savedCrewMember.setRank(crewMemberDTO.getRank());
        savedCrewMember.setShip(ship);

        when(crewMemberRepository.save(any(CrewMember.class))).thenReturn(savedCrewMember);

        // Act
        CrewMemberDTO result = crewMemberService.createCrewMember(crewMemberDTO);

        // Assert
        assertNotNull(result);
        assertEquals(crewMemberDTO.getName(), result.getName());
        assertEquals(crewMemberDTO.getRank(), result.getRank());
        verify(crewMemberRepository, times(1)).save(any(CrewMember.class));
    }

    @Test
    void createCrewMember_ThrowsIllegalStateException() {
        // Arrange
        CrewMemberDTO crewMemberDTO = new CrewMemberDTO();
        crewMemberDTO.setName("John Doe");
        crewMemberDTO.setRank("Captain");
        crewMemberDTO.setShipId(ship.getId());

        when(shipRepository.findById(ship.getId())).thenReturn(Optional.of(ship));
        when(crewMemberRepository.countByShip(ship)).thenReturn(5L); // At capacity

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            crewMemberService.createCrewMember(crewMemberDTO);
        });

        assertEquals("No available capacity on the ship", exception.getMessage());
        verify(crewMemberRepository, never()).save(any(CrewMember.class));
    }
}
