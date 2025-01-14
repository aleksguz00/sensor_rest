package ru.alex.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alex.dto.MeasurementDto;
import ru.alex.model.Measurement;
import ru.alex.service.MeasurementsService;
import ru.alex.util.MeasurementConverter;
import ru.alex.util.MeasurementValidator;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;

    private final MeasurementConverter measurementConverter;
    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, MeasurementConverter measurementConverter, MeasurementValidator measurementValidator) {
        this.measurementsService = measurementsService;
        this.measurementConverter = measurementConverter;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    @ResponseBody
    public List<MeasurementDto> findAll() {
        List<MeasurementDto> measurements =  measurementsService.findAllMeasurements()
                .stream().map(measurementConverter::toMeasurementDto)
                .toList();

        System.out.println(measurements);
        System.out.println(measurements.size());

        return measurements;
    }

    @GetMapping("/rainy_days")
    @ResponseBody
    public int getRainyDaysCount() {
        return measurementsService.countRainyDays();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDto measurementDto,
                                             BindingResult bindingResult) {
        measurementValidator.validate(measurementDto, bindingResult);

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Measurement measurement = measurementConverter.toMeasurement(measurementDto);

        measurementsService.save(measurement);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
