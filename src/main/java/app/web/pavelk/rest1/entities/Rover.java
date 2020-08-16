package app.web.pavelk.rest1.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Rover {

    @Id
    @GeneratedValue
    Long id;

    String name;
    String model;

    public Rover(String name, String model) {
        this.name = name;
        this.model = model;
    }
}
