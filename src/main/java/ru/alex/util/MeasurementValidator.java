package ru.alex.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.dto.MeasurementDto;
import ru.alex.service.SensorsService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public MeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(MeasurementDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDto measurementDto = (MeasurementDto) target;

        if (sensorsService.findSensorByName(measurementDto.getSensor()) == null) {
            errors.rejectValue("sensor", null,
                    "It is forbidden to add measurements from an unknown sensor");
        }
    }
}
