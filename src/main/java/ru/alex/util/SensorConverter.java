package ru.alex.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alex.dto.SensorDto;
import ru.alex.model.Sensor;

@Component
public class SensorConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public SensorConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SensorDto toSensorDto(Sensor sensor) {
        return modelMapper.map(sensor, SensorDto.class);
    }

    public Sensor toSensor(SensorDto sensorDto) {
        return modelMapper.map(sensorDto, Sensor.class);
    }
}
