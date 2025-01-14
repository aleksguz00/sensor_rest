package ru.alex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alex.model.Measurement;

import java.util.List;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {

    List<Measurement> findByIsRainingTrue();
}
