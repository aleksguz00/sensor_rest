package ru.alex.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.dto.SensorDto;
import ru.alex.service.SensorsService;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SensorDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDto sensorDto = (SensorDto) target;

        if (sensorsService.findSensorByName(sensorDto.getName()) != null) {
            errors.rejectValue("name", null, "Such sensor already exists");
        }
    }
}
