package com.hepsiburada.marsrover.model;

import com.hepsiburada.marsrover.exception.MarsRoverException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Heading {
    NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

    private final String direction;

    public static Heading getHeadingByDirection(String direction) {
        for (Heading heading : Heading.values()) {
            if (heading.getDirection().equals(direction))
                return heading;
        }
        throw new MarsRoverException("Invalid heading! It should be one of N(North), E(East), S(South), W(West)");
    }
}
