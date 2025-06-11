package com.hexaware.cricketteammanagement.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.hexaware.cricketteammanagement.dto.PlayerDTO;
import com.hexaware.cricketteammanagement.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/api/players")
@CrossOrigin(origins = "http://localhost:4200")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable Long playerId) {
        return ResponseEntity.ok(playerService.getPlayerById(playerId));
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> createPlayer(@Valid @RequestBody PlayerDTO playerDto) {
        return new ResponseEntity<>(playerService.createPlayer(playerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long playerId, @Valid @RequestBody PlayerDTO playerDto) {
        return ResponseEntity.ok(playerService.updatePlayer(playerId, playerDto));
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long playerId) {
        playerService.deletePlayer(playerId);
        return ResponseEntity.ok("Player deleted successfully");
    }
    
    
    @GetMapping("/name/{playerName}")
    public ResponseEntity<List<PlayerDTO>> getPlayersByName(@PathVariable String playerName) {
        return ResponseEntity.ok(playerService.getPlayersByPlayerNameContainingIgnoreCase(playerName));
    }
    
    @GetMapping("/jersey/{jerseyNumber}")
    public ResponseEntity<PlayerDTO> getPlayerByJerseyNumber(@PathVariable Integer jerseyNumber) {
        return ResponseEntity.ok(playerService.getPlayersByJerseyNumber(jerseyNumber));
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<List<PlayerDTO>> getPlayersByTeamName(@PathVariable String teamName) {
        return ResponseEntity.ok(playerService.getPlayersByTeamNameContainingIgnoreCase(teamName));
    }

    @GetMapping("/country/{countryName}")
    public ResponseEntity<List<PlayerDTO>> getPlayersByCountryName(@PathVariable String countryName) {
        return ResponseEntity.ok(playerService.getPlayersByCountryNameContainingIgnoreCase(countryName));
    }


}
