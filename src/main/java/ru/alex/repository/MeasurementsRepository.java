package ru.alex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.model.Measurement;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
}
