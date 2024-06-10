package nl.inholland.exam.thijs.dto;

import nl.inholland.exam.thijs.model.MissionStatus;
import java.time.LocalDate;

public class MissionHistoryDTO {
    private long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private MissionStatus status;
    private long duration;
    private String shipName;

    // Constructor
    public MissionHistoryDTO(long id, String name, LocalDate startDate, LocalDate endDate,
                             MissionStatus status, long duration, String shipName) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.duration = duration;
        this.shipName = shipName;
    }

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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }
}
