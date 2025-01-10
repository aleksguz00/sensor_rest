package ru.alex.dto;

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
    private boolean isRaining;

    @NotNull
    private Sensor sensor;

    public MeasurementDto() {}

    public MeasurementDto(float value, boolean isRaining, Sensor sensor) {
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
