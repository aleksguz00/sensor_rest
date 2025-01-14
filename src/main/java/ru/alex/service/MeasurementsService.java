package ru.alex.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.model.Measurement;
import ru.alex.repository.MeasurementsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    @Transactional(readOnly = true)
    public List<Measurement> findAllMeasurements() {
        return measurementsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Measurement findMeasurementById(int id) {
        Optional<Measurement> measurement = measurementsRepository.findById(id);

        return measurement.orElse(null);
    }

    @Transactional(readOnly = true)
    public int countRainyDays() {
        List<Measurement> rainyMeasurements = measurementsRepository.findByIsRainingTrue();
        return rainyMeasurements.size();
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setMeasuredAt(LocalDateTime.now());

        measurementsRepository.save(measurement);
    }
}
