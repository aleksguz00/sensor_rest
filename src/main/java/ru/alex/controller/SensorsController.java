package ru.alex.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alex.dto.SensorDto;
import ru.alex.model.Sensor;
import ru.alex.service.SensorsService;
import ru.alex.util.SensorConverter;
import ru.alex.util.SensorValidator;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sensors")
public class SensorsController {

    private final SensorsService sensorsService;

    private final SensorConverter sensorConverter;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorsController(SensorsService sensorsService, SensorConverter sensorConverter, SensorValidator sensorValidator) {
        this.sensorsService = sensorsService;
        this.sensorConverter = sensorConverter;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping
    @ResponseBody
    public List<SensorDto> findAll() {
        List<Sensor> sensors = sensorsService.getAllSensors();

        return sensors.stream().map(sensorConverter::toSensorDto).collect(Collectors.toList());
    }

    // TODO: Add custom exception
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDto sensorDto, BindingResult bindingResult) {
        sensorValidator.validate(sensorDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        sensorsService.saveSensor(sensorConverter.toSensor(sensorDto));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
