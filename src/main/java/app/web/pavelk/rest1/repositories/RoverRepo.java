package app.web.pavelk.rest1.repositories;

import app.web.pavelk.rest1.entities.Rover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoverRepo extends JpaRepository<Rover, Long> {
}
