package com.hepsiburada.marsrover.service;

import com.hepsiburada.marsrover.exception.MarsRoverException;
import com.hepsiburada.marsrover.model.*;
import com.hepsiburada.marsrover.service.impl.RoverServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class RoverServiceTest {
    RoverService roverService;

    @BeforeEach
    void setUp() {
        roverService = new RoverServiceImpl();
    }

    @Test
    void shouldSuccess_whenCreateRoverPassedAllValidation() {
        Plateau plateau = buildPlateau();

        Rover result = roverService.create("Rover-1", "1 2 N", plateau);

        assertNotNull(result);
        assertEquals(1, result.getPosition().getCoordinateX());
        assertEquals(2, result.getPosition().getCoordinateY());
        assertEquals(Heading.NORTH, result.getPosition().getHeading());
        assertThat(plateau, is(result.getPlateau()));
    }

    @Test
    void shouldReturnError_whenGivenRoverCoordinatesFormatNotValid() {
        Throwable throwable = Assertions.catchThrowable(() -> roverService
                .create("Rover-1", "12N", buildPlateau()));

        assertNotNull(throwable);
        assertTrue(throwable instanceof MarsRoverException);
        assertEquals("Invalid format! It should be as x y h", throwable.getMessage());
    }

    @Test
    void shouldReturnError_whenGivenRoverCoordinatesNotInRangeOfNasaResearchField() {
        Throwable throwable = Assertions.catchThrowable(() -> roverService
                .create("Rover-1", "6 5 N", buildPlateau()));

        assertNotNull(throwable);
        assertTrue(throwable instanceof MarsRoverException);
        assertEquals("Rover coordinates must be in range of Nasa's research field!", throwable.getMessage());
    }

    @Test
    void shouldCompleteViewOfTheSurrounding_whenRoverMovingAccordingToInstructions() {
        Rover rover = Rover.builder()
                .name("Rover-1")
                .position(Position.builder()
                        .coordinateX(1)
                        .coordinateY(2)
                        .heading(Heading.NORTH)
                        .build())
                .plateau(buildPlateau())
                .build();

        List<Instruction> instructions = List.of(Instruction.LEFT, Instruction.MOVE, Instruction.LEFT,
                Instruction.MOVE, Instruction.LEFT, Instruction.MOVE,
                Instruction.RIGHT, Instruction.RIGHT, Instruction.RIGHT);

        instructions.forEach(instruction -> roverService.move(rover, instruction));

        assertEquals(1, rover.getPosition().getCoordinateX());
        assertEquals(1, rover.getPosition().getCoordinateY());
        assertEquals(Heading.NORTH, rover.getPosition().getHeading());
    }

    @Test
    void shouldSuccessfullyLanding_whenRoversSentToPlateau() {
        Plateau plateau = buildPlateau();

        Rover firstRover = Rover.builder()
                .name("Rover-1")
                .position(Position.builder()
                        .coordinateX(1)
                        .coordinateY(3)
                        .heading(Heading.WEST)
                        .build())
                .plateau(plateau)
                .build();

        Rover secondRover = Rover.builder()
                .name("Rover-2")
                .position(Position.builder()
                        .coordinateX(-3)
                        .coordinateY(2)
                        .heading(Heading.SOUTH)
                        .build())
                .plateau(plateau)
                .build();

        List<Rover> rovers = List.of(firstRover, secondRover);

        rovers.forEach(rover -> roverService.add(rover));

        assertThat(firstRover.getPlateau().getRovers().size(), is(2));
    }

    private Plateau buildPlateau() {
        return Plateau.builder()
                .position(Position.builder()
                        .coordinateX(5)
                        .coordinateY(5)
                        .build())
                .build();
    }
}