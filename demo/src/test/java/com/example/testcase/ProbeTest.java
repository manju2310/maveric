package com.example.testcase;

import static org.junit.jupiter.api.Assertions.*;

import com.example.dto.Direction;
import com.example.dto.Grid;
import com.example.dto.Probe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProbeTest {
    private Probe probe;
    private Grid grid;

    @BeforeEach
    void setUp() {
        grid = new Grid(5, 5);
        probe = new Probe(2, 2, Direction.NORTH, grid);
    }

    @Test
    void testForwardMovement() {
        probe.executeCommands("F");
        assertEquals("Position: (2, 3), Facing: NORTH, Visited: [(2, 2), (2, 3)]", probe.getStatus());

    }

    @Test
    void testBackwardMovement() {
        probe.executeCommands("B");
        assertEquals("Position: (2, 1), Facing: NORTH, Visited: [(2, 2), (2, 1)]", probe.getStatus());

    }

    @Test
    void testLeftTurn() {
        probe.executeCommands("L");
        assertEquals("Position: (2, 2), Facing: WEST, Visited: [(2, 2)]", probe.getStatus());

    }

    @Test
    void testRightTurn() {
        probe.executeCommands("R");
        assertEquals("Position: (2, 2), Facing: EAST, Visited: [(2, 2)]", probe.getStatus());

    }

    @Test
    void testObstaclePrevention() {
        grid.addObstacle(2, 3);
        probe.executeCommands("F");
        assertEquals("Position: (2, 2), Facing: NORTH, Visited: [(2, 2)]", probe.getStatus());

    }
}
