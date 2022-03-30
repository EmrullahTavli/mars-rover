package com.hepsiburada.marsrover.service.impl;

import com.hepsiburada.marsrover.exception.MarsRoverException;
import com.hepsiburada.marsrover.model.Plateau;
import com.hepsiburada.marsrover.service.PlateauService;
import com.hepsiburada.marsrover.model.Position;

public class PlateauServiceImpl implements PlateauService {

    @Override
    public Plateau create(String input) {
        try {
            String[] parts = input.split(" ");
            int coordinateX = Integer.parseInt(parts[0]);
            int coordinateY = Integer.parseInt(parts[1]);
            validate(coordinateX, coordinateY);
            return Plateau.builder()
                    .position(Position.builder()
                            .coordinateX(coordinateX)
                            .coordinateY(coordinateY)
                            .build())
                    .build();
        } catch (MarsRoverException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new MarsRoverException("Invalid format! It should be x y");
        }
    }

    private void validate(int coordinateX, int coordinateY) {
        if (coordinateX < 0 || coordinateY < 0) {
            throw new MarsRoverException("Dimension must be in upper right area!");
        }
    }
}
