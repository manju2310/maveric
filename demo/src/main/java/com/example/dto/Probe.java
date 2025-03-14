package com.example.dto;

import java.util.*;

public class Probe {
    private int x, y;
    private Direction direction;
    private final Grid grid;
    private final List<Coordinate> visited;

    public Probe(int startX, int startY, Direction startDirection, Grid grid) {
        this.x = startX;
        this.y = startY;
        this.direction = startDirection;
        this.grid = grid;
        this.visited = new ArrayList<>();
        visited.add(new Coordinate(x, y));
    }

    public String executeCommands(String commands) {
        for (char command : commands.toCharArray()) {
            switch (command) {
            case 'F' -> move(1);
            case 'B' -> move(-1);
            case 'L' -> turnLeft();
            case 'R' -> turnRight();
            default -> throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
        return getStatus();
    }

    private void move(int step) {
        int newX = x + step * direction.dx;
        int newY = y + step * direction.dy;

        if (!grid.isWithinBounds(newX, newY) || grid.isObstacle(newX, newY)) {
            return; // Stay in place if out of bounds or obstacle present
        }

        x = newX;
        y = newY;
        visited.add(new Coordinate(x, y));
    }

    private void turnLeft() {
        direction = direction.left();
    }

    private void turnRight() {
        direction = direction.right();
    }

    public String getStatus() {
        return "Position: (" + x + ", " + y + "), Facing: " + direction + ", Visited: " + visited;
    }

}
