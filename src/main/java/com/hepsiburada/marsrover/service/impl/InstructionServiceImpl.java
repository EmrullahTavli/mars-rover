package com.hepsiburada.marsrover.service.impl;

import com.hepsiburada.marsrover.model.Instruction;
import com.hepsiburada.marsrover.service.InstructionService;

import java.util.List;
import java.util.stream.Collectors;

public class InstructionServiceImpl implements InstructionService {

    @Override
    public List<Instruction> getInstructions(String input) {
        return input.chars()
                .mapToObj(command -> (char) command)
                .map(character -> Instruction.getInstructionByCommand(character.toString()))
                .collect(Collectors.toList());
    }
}
