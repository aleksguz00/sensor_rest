package ru.alex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.alex.model.Sensor;

public class MeasurementDto {

    @Min(value = -100, message = "Temperature can't be less then -100")
    @Max(value = 100, message = "Temperature can't be greater then 100")
    @NotNull
    private float value;

    @NotNull
    @JsonProperty("isRaining")
    private boolean isRaining;

    @NotNull
    private String sensor;

    public MeasurementDto() {}

    public MeasurementDto(float value, boolean isRaining, String sensor) {
        this.value = value;
        this.isRaining = isRaining;
        this.sensor = sensor;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return isRaining;
    }

    public void setRaining(boolean raining) {
        isRaining = raining;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }
}
