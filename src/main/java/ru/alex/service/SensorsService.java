package ru.alex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.model.Sensor;
import ru.alex.repository.SensorsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    @Transactional(readOnly = true)
    public List<Sensor> getAllSensors() {
        return sensorsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Sensor findSensorById(int id) {
        Optional<Sensor> sensor = sensorsRepository.findById(id);

        return sensor.orElse(null);
    }

    @Transactional(readOnly = true)
    public Sensor findSensorByName(String name) {
        Optional<Sensor> sensor = sensorsRepository.findByName(name);

        return sensor.orElse(null);
    }

    @Transactional
    public void saveSensor(Sensor sensor) {
        sensorsRepository.save(sensor);
    }
}
