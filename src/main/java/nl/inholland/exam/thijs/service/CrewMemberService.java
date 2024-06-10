package nl.inholland.exam.thijs.service;

import nl.inholland.exam.thijs.dto.CrewMemberDTO;
import nl.inholland.exam.thijs.model.CrewMember;
import nl.inholland.exam.thijs.model.Ship;
import nl.inholland.exam.thijs.repository.CrewMemberRepository;
import nl.inholland.exam.thijs.repository.ShipRepository;
import nl.inholland.exam.thijs.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CrewMemberService {

    @Autowired
    private CrewMemberRepository crewMemberRepository;

    @Autowired
    private ShipRepository shipRepository;

    public CrewMemberDTO createCrewMember(CrewMemberDTO crewMemberDTO) {
        Ship ship = shipRepository.findById(crewMemberDTO.getShipId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid ship ID"));

        long currentCrewCount = crewMemberRepository.countByShip(ship);
        if (currentCrewCount >= ship.getCrewCapacity()) {
            throw new IllegalStateException("No available capacity on the ship");
        }

        CrewMember crewMember = new CrewMember();
        crewMember.setName(crewMemberDTO.getName());
        crewMember.setRank(crewMemberDTO.getRank());
        crewMember.setShip(ship);
        crewMember = crewMemberRepository.save(crewMember);
        return convertToDTO(crewMember);
    }

    private CrewMemberDTO convertToDTO(CrewMember crewMember) {
        CrewMemberDTO crewMemberDTO = new CrewMemberDTO();
        crewMemberDTO.setId(crewMember.getId());
        crewMemberDTO.setName(crewMember.getName());
        crewMemberDTO.setRank(crewMember.getRank());
        crewMemberDTO.setShipId(crewMember.getShip().getId());
        return crewMemberDTO;
    }
}
