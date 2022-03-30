package com.hepsiburada.marsrover.service;

import com.hepsiburada.marsrover.model.Instruction;
import com.hepsiburada.marsrover.model.Plateau;
import com.hepsiburada.marsrover.model.Rover;

public interface RoverService {
    void add(Rover rover);

    void move(Rover rover, Instruction instruction);

    Rover create(String name, String input, Plateau plateau);
}
