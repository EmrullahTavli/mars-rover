package com.hepsiburada.marsrover.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString(exclude = "plateau")
public class Rover {
    private String name;
    private Plateau plateau;
    private Position position;
}
