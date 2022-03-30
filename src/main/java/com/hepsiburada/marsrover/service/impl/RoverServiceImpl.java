package com.hepsiburada.marsrover.service.impl;

import com.hepsiburada.marsrover.base.DoublyLinkedList;
import com.hepsiburada.marsrover.base.Node;
import com.hepsiburada.marsrover.exception.MarsRoverException;
import com.hepsiburada.marsrover.model.*;
import com.hepsiburada.marsrover.service.RoverService;
import com.hepsiburada.marsrover.util.HeadingUtil;

public class RoverServiceImpl implements RoverService {
    private static final DoublyLinkedList<Heading> headings = HeadingUtil.headings;

    @Override
    public void add(Rover rover) {
        rover.getPlateau().getRovers().add(rover);
    }

    @Override
    public void move(Rover rover, Instruction instruction) {
        Position position = rover.getPosition();

        if (Instruction.LEFT.getCommand().equals(instruction.getCommand())) {
            Heading heading = headings.traverseBackward(build(
                    rover.getPosition().getHeading())
            ).getData();
            position.setHeading(heading);
        }

        if (Instruction.RIGHT.getCommand().equals(instruction.getCommand())) {
            Heading heading = headings.traverseForward(build(
                    rover.getPosition().getHeading())
            ).getData();
            position.setHeading(heading);
        }

        if (Instruction.MOVE.getCommand().equals(instruction.getCommand())) {
            switch (position.getHeading()) {
                case EAST:
                    position.setCoordinateX(position.getCoordinateX() + 1);
                    break;
                case NORTH:
                    position.setCoordinateY(position.getCoordinateY() + 1);
                    break;
                case SOUTH:
                    position.setCoordinateY(position.getCoordinateY() - 1);
                    break;
                case WEST:
                    position.setCoordinateX(position.getCoordinateX() - 1);
                    break;
            }
        }
    }

    @Override
    public Rover create(String name, String input, Plateau plateau) {
        try {
            String[] parts = input.split(" ");
            int coordinateX = Integer.parseInt(parts[0]);
            int coordinateY = Integer.parseInt(parts[1]);
            Heading heading = Heading.getHeadingByDirection(parts[2]);
            validate(plateau, coordinateX, coordinateY);
            return Rover.builder()
                    .name(name)
                    .position(Position.builder()
                            .coordinateX(coordinateX)
                            .coordinateY(coordinateY)
                            .heading(heading)
                            .build())
                    .plateau(plateau)
                    .build();
        } catch (MarsRoverException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new MarsRoverException("Invalid format! It should be as x y h");
        }
    }


    private void validate(Plateau plateau, int coordinateX, int coordinateY) {
        Position position = plateau.getPosition();
        if (coordinateX > position.getCoordinateX() || coordinateY > position.getCoordinateY()) {
            throw new MarsRoverException("Rover coordinates must be in range of Nasa's research field!");
        }
    }

    private Node<Heading> build(Heading heading) {
        return Node.<Heading>builder()
                .data(heading)
                .build();
    }
}
