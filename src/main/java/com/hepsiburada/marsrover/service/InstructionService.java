package com.hepsiburada.marsrover.service;

import com.hepsiburada.marsrover.model.Instruction;

import java.util.List;

public interface InstructionService {
    List<Instruction> getInstructions(String input);
}
