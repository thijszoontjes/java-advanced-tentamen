package nl.inholland.exam.thijs.service;

import nl.inholland.exam.thijs.dto.CrewMemberDTO;
import nl.inholland.exam.thijs.dto.MissionDTO;
import nl.inholland.exam.thijs.dto.ShipDTO;
import nl.inholland.exam.thijs.model.Ship;
import nl.inholland.exam.thijs.model.CrewMember;
import nl.inholland.exam.thijs.model.Mission;
import nl.inholland.exam.thijs.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;

    public List<ShipDTO> getAllShips() {
        return shipRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ShipDTO createShip(ShipDTO shipDTO) {
        Ship ship = new Ship();
        ship.setName(shipDTO.getName());
        ship.setClassType(shipDTO.getClassType());
        ship.setCrewCapacity(shipDTO.getCrewCapacity());
        ship = shipRepository.save(ship);
        return convertToDTO(ship);
    }

    private ShipDTO convertToDTO(Ship ship) {
        ShipDTO shipDTO = new ShipDTO();
        shipDTO.setId(ship.getId());
        shipDTO.setName(ship.getName());
        shipDTO.setClassType(ship.getClassType());
        shipDTO.setCrewCapacity(ship.getCrewCapacity());

        List<CrewMemberDTO> crewMembers = ship.getCrewMembers().stream()
                .map(this::convertToCrewMemberDTO)
                .collect(Collectors.toList());
        shipDTO.setCrewMembers(crewMembers);

        List<MissionDTO> missions = ship.getMissions().stream()
                .map(this::convertToMissionDTO)
                .collect(Collectors.toList());
        shipDTO.setMissions(missions);

        return shipDTO;
    }

    private CrewMemberDTO convertToCrewMemberDTO(CrewMember crewMember) {
        CrewMemberDTO crewMemberDTO = new CrewMemberDTO();
        crewMemberDTO.setId(crewMember.getId());
        crewMemberDTO.setName(crewMember.getName());
        crewMemberDTO.setRank(crewMember.getRank());
        crewMemberDTO.setShipId(crewMember.getShip().getId());
        return crewMemberDTO;
    }

    private MissionDTO convertToMissionDTO(Mission mission) {
        MissionDTO missionDTO = new MissionDTO();
        missionDTO.setId(mission.getId());
        missionDTO.setName(mission.getName());
        missionDTO.setStartDate(mission.getStartDate());
        missionDTO.setEndDate(mission.getEndDate());
        missionDTO.setStatus(mission.getStatus().name());
        missionDTO.setShipId(mission.getShip().getId());
        return missionDTO;
    }
}
