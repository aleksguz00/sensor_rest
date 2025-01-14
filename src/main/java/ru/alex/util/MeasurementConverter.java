package ru.alex.util;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alex.dto.MeasurementDto;
import ru.alex.model.Measurement;
import ru.alex.model.Sensor;
import ru.alex.service.SensorsService;

@Component
public class MeasurementConverter {

    private final ModelMapper modelMapper;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementConverter(ModelMapper modelMapper, SensorsService sensorsService) {
        this.modelMapper = modelMapper;
        this.sensorsService = sensorsService;
    }

    public MeasurementDto toMeasurementDto(Measurement measurement) {
        MeasurementDto measurementDto = new MeasurementDto();

        Hibernate.initialize(measurement.getSensor());

        measurementDto.setValue(measurement.getValue());
        measurementDto.setRaining(measurement.isRaining());
        measurementDto.setSensor(measurement.getSensor().getName());

        return measurementDto;
    }

    public Measurement toMeasurement(MeasurementDto measurementDto) {
        Measurement measurement = new Measurement();

        measurement.setValue(measurementDto.getValue());
        measurement.setRaining(measurementDto.isRaining());
        measurement.setSensor(sensorsService.findSensorByName(measurementDto.getSensor()));

        return measurement;
    }
}
