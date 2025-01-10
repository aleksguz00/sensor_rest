package ru.alex.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDto {

    @NotEmpty(message = "Sensor name can't be empty")
    @Size(min = 3, max = 30, message = "Name length should be between 3 and 30")
    private String name;

    public SensorDto() {}

    public SensorDto(String name) {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
