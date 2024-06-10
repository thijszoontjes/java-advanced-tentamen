package nl.inholland.exam.thijs.controller;

import nl.inholland.exam.thijs.dto.MissionDTO;
import nl.inholland.exam.thijs.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @PostMapping
    public ResponseEntity<MissionDTO> createMission(@RequestBody MissionDTO missionDTO) {
        return ResponseEntity.ok(missionService.createMission(missionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MissionDTO> updateMission(@PathVariable long id, @RequestBody MissionDTO missionDTO) {
        return ResponseEntity.ok(missionService.updateMission(id, missionDTO));
    }
}
