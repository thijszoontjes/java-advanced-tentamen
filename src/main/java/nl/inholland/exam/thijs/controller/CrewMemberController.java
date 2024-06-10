package nl.inholland.exam.thijs.controller;

import nl.inholland.exam.thijs.dto.CrewMemberDTO;
import nl.inholland.exam.thijs.service.CrewMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crewmembers")
public class CrewMemberController {

    @Autowired
    private CrewMemberService crewMemberService;

    @PostMapping
    public ResponseEntity<CrewMemberDTO> createCrewMember(@RequestBody CrewMemberDTO crewMemberDTO) {
        return ResponseEntity.ok(crewMemberService.createCrewMember(crewMemberDTO));
    }
}
