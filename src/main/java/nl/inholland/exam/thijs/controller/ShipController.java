package nl.inholland.exam.thijs.controller;

import nl.inholland.exam.thijs.dto.ShipDTO;
import nl.inholland.exam.thijs.dto.MissionHistoryDTO;
import nl.inholland.exam.thijs.model.Mission;
import nl.inholland.exam.thijs.model.Ship;
import nl.inholland.exam.thijs.repository.ShipRepository;
import nl.inholland.exam.thijs.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ships")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @Autowired
    private ShipRepository shipRepository;

    @GetMapping
    public ResponseEntity<List<ShipDTO>> getAllShips() {
        List<ShipDTO> ships = shipService.getAllShips();
        return ResponseEntity.ok(ships);
    }

    @PostMapping
    public ResponseEntity<ShipDTO> createShip(@RequestBody ShipDTO shipDTO) {
        ShipDTO createdShip = shipService.createShip(shipDTO);
        return ResponseEntity.ok(createdShip);
    }

    @GetMapping("/{shipId}/history")
    public ResponseEntity<List<MissionHistoryDTO>> getMissionHistory(@PathVariable long shipId) {
        Ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ship ID"));

        List<MissionHistoryDTO> missionHistory = ship.getMissions().stream()
                .map(this::convertToMissionHistoryDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(missionHistory);
    }

    private MissionHistoryDTO convertToMissionHistoryDTO(Mission mission) {
        long duration = mission.getEndDate() != null ?
                ChronoUnit.DAYS.between(mission.getStartDate(), mission.getEndDate()) : 0;

        return new MissionHistoryDTO(
                mission.getId(),
                mission.getName(),
                mission.getStartDate(),
                mission.getEndDate(),
                mission.getStatus(),
                duration,
                mission.getShip().getName()
        );
    }
}
