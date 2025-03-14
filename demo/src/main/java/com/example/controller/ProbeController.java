package com.example.controller;

import com.example.dto.Direction;
import com.example.dto.Grid;
import com.example.dto.Probe;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probe")
public class ProbeController {
    private Grid grid;
    private Probe probe;

    @PostMapping("/init")
    public String initialize(@RequestParam int width, @RequestParam int height,
                             @RequestParam int startX, @RequestParam int startY,
                             @RequestParam String startDirection) {
        grid = new Grid(width, height);
        probe = new Probe(startX, startY, Direction.valueOf(startDirection.toUpperCase()), grid);
        return "Probe initialized at (" + startX + ", " + startY + ") facing " + startDirection;
    }

    @PostMapping("/obstacle")
    public String addObstacle(@RequestParam int x, @RequestParam int y) {
        grid.addObstacle(x, y);
        return "Obstacle added at (" + x + ", " + y + ")";
    }

    @PostMapping("/move")
    public String move(@RequestBody String commands) {
        return probe.executeCommands(commands);
    }

    @GetMapping("/status")
    public String getStatus() {
        return probe.getStatus();
    }
}
