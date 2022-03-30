
package com.hepsiburada.marsrover;

import com.hepsiburada.marsrover.model.*;
import com.hepsiburada.marsrover.service.InstructionService;
import com.hepsiburada.marsrover.service.PlateauService;
import com.hepsiburada.marsrover.service.RoverService;
import com.hepsiburada.marsrover.service.impl.InstructionServiceImpl;
import com.hepsiburada.marsrover.service.impl.PlateauServiceImpl;
import com.hepsiburada.marsrover.service.impl.RoverServiceImpl;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.System.in;
import static java.lang.System.out;

public class MarsRoverApplication {
    private static final PlateauService plateauService;
    private static final RoverService roverService;
    private static final InstructionService instructionService;

    static {
        roverService = new RoverServiceImpl();
        plateauService = new PlateauServiceImpl();
        instructionService = new InstructionServiceImpl();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);

        out.print("Enter dimensions of the plateau (in the form x y):");
        String dimensions = scanner.nextLine();
        Plateau plateau = plateauService.create(dimensions);

        int i = 0; String command;
        do {
            ++i;
            String name = "Rover-" + i;
            out.print("Enter rover information for " + name + " (in the form x y h):");
            String roverPosition = scanner.nextLine();
            try {
                Rover rover = roverService.create(name, roverPosition, plateau);
                roverService.add(rover);

                out.print("Enter instructions for " + name + " (in the form LMLMLMLMM):");
                String instructionDetails = scanner.nextLine();
                List<Instruction> instructions = instructionService.getInstructions(instructionDetails);
                instructions.forEach(instruction -> roverService.move(rover, instruction));

                out.println("Report: " + rover.toString());
            } catch (Exception exception) {
                --i;
                out.println(exception.getMessage());
            }
            out.println("Enter any character to continue or q to quit");
            command = scanner.nextLine();
        } while (!command.equalsIgnoreCase("q"));

        scanner.close();
    }
}

