package com.hepsiburada.marsrover.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Position {
    private int coordinateX;
    private int coordinateY;
    private Heading heading;
}
