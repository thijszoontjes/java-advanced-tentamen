package nl.inholland.exam.thijs.dto;

import java.util.List;

public class ShipDTO {
    private long id;
    private String name;
    private String classType;
    private int crewCapacity;
    private List<CrewMemberDTO> crewMembers;
    private List<MissionDTO> missions;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getCrewCapacity() {
        return crewCapacity;
    }

    public void setCrewCapacity(int crewCapacity) {
        this.crewCapacity = crewCapacity;
    }

    public List<CrewMemberDTO> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(List<CrewMemberDTO> crewMembers) {
        this.crewMembers = crewMembers;
    }

    public List<MissionDTO> getMissions() {
        return missions;
    }

    public void setMissions(List<MissionDTO> missions) {
        this.missions = missions;
    }
}
