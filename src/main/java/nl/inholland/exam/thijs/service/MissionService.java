package nl.inholland.exam.thijs.service;

import nl.inholland.exam.thijs.dto.MissionDTO;
import nl.inholland.exam.thijs.model.Mission;
import nl.inholland.exam.thijs.model.MissionStatus;
import nl.inholland.exam.thijs.model.Ship;
import nl.inholland.exam.thijs.repository.MissionRepository;
import nl.inholland.exam.thijs.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private ShipRepository shipRepository;

    public MissionDTO createMission(MissionDTO missionDTO) {
        Mission mission = new Mission();
        mission.setName(missionDTO.getName());
        mission.setStartDate(missionDTO.getStartDate());
        mission.setEndDate(missionDTO.getEndDate());
        Ship ship = shipRepository.findById(missionDTO.getShipId()).orElseThrow(() -> new IllegalArgumentException("Invalid ship ID"));
        mission.setShip(ship);
        mission.setStatus(MissionStatus.valueOf(missionDTO.getStatus()));
        mission = missionRepository.save(mission);
        return convertToDTO(mission);
    }

    public MissionDTO updateMission(long id, MissionDTO missionDTO) {
        Mission mission = missionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid mission ID"));
        mission.setName(missionDTO.getName());
        mission.setEndDate(missionDTO.getEndDate());
        mission.setStatus(MissionStatus.valueOf(missionDTO.getStatus()));
        mission = missionRepository.save(mission);
        return convertToDTO(mission);
    }

    private MissionDTO convertToDTO(Mission mission) {
        MissionDTO missionDTO = new MissionDTO();
        missionDTO.setId(mission.getId());
        missionDTO.setName(mission.getName());
        missionDTO.setStartDate(mission.getStartDate());
        missionDTO.setEndDate(mission.getEndDate());
        missionDTO.setShipId(mission.getShip().getId());
        missionDTO.setStatus(mission.getStatus().name());
        return missionDTO;
    }
}
