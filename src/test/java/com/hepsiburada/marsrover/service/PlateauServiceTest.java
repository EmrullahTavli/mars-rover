package com.hepsiburada.marsrover.service;

import com.hepsiburada.marsrover.exception.MarsRoverException;
import com.hepsiburada.marsrover.model.Plateau;
import com.hepsiburada.marsrover.service.impl.PlateauServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlateauServiceTest {
    PlateauService plateauService;

    @BeforeEach
    void setUp() {
        plateauService = new PlateauServiceImpl();
    }

    @Test
    void shouldSuccess_whenGivenDimensionValid() {
        Plateau result = plateauService.create("1 3");

        assertNotNull(result);
        assertEquals(1, result.getPosition().getCoordinateX());
        assertEquals(3, result.getPosition().getCoordinateY());
    }

    @Test
    void shouldReturnError_whenGivenDimensionNotInUpperRight() {
        Throwable throwable = Assertions.catchThrowable(() -> plateauService.create("1 -1"));

        assertNotNull(throwable);
        assertTrue(throwable instanceof MarsRoverException);
        assertEquals("Dimension must be in upper right area!", throwable.getMessage());
    }

    @Test
    void shouldReturnError_whenGivenDimensionFormatNotValid() {
        Throwable throwable = Assertions.catchThrowable(() -> plateauService.create("55"));

        assertNotNull(throwable);
        assertTrue(throwable instanceof MarsRoverException);
        assertEquals("Invalid format! It should be x y", throwable.getMessage());
    }
}