package com.hepsiburada.marsrover.service;

import com.hepsiburada.marsrover.exception.MarsRoverException;
import com.hepsiburada.marsrover.model.Instruction;
import com.hepsiburada.marsrover.service.impl.InstructionServiceImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class InstructionServiceTest {
    InstructionService instructionService;

    @BeforeEach
    void setUp() {
        instructionService = new InstructionServiceImpl();
    }

    @Test
    void shouldSuccess_whenGivenInstructionValid() {
        List<Instruction> instructions = instructionService.getInstructions("LMLMLMLMM");
        assertThat(instructions, Matchers.<Collection<Instruction>>allOf(
                hasSize(9), hasItem(not(Instruction.RIGHT)))
        );
    }

    @Test
    void shouldReturnError_whenGivenInstructionNotValid() {
        Throwable throwable = catchThrowable(() -> instructionService.getInstructions("LMRMMB"));
        assertNotNull(throwable);
        assertTrue(throwable instanceof MarsRoverException);
        assertEquals("Invalid instruction! It should be one of L(Left), " +
                "R(Right) or M(Move)", throwable.getMessage());
    }
}