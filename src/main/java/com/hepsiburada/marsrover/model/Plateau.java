package com.hepsiburada.marsrover.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Plateau {
    private Position position;
    @Builder.Default
    private List<Rover> rovers = new ArrayList<>();
}
