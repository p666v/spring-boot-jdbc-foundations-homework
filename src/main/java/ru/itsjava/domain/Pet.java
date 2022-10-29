package ru.itsjava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Pet {
    private long id;
    private final String breed;


    @Override
    public String toString() {
        return "id: " + id + ", питомец: " + breed;
    }
}
