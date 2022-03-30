package com.hepsiburada.marsrover.model;

import com.hepsiburada.marsrover.exception.MarsRoverException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Instruction {
    LEFT("L"), RIGHT("R"), MOVE("M");

    private final String command;

    public static Instruction getInstructionByCommand(String command) {
        for (Instruction instruction : Instruction.values()) {
            if (instruction.getCommand().equals(command))
                return instruction;
        }
        throw new MarsRoverException("Invalid instruction! It should be one of L(Left), R(Right) or M(Move)");
    }
}
