package ru.alex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.model.Sensor;

import java.util.Optional;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {

    Optional<Sensor> findByName(String name);
}
