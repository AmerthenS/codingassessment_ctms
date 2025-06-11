package com.hexaware.cricketteammanagement.service;

import java.util.List;

import com.hexaware.cricketteammanagement.dto.PlayerDTO;

public interface PlayerService {
    List<PlayerDTO> getAllPlayers();
    PlayerDTO getPlayerById(Long id);
    PlayerDTO createPlayer(PlayerDTO playerDto);
    PlayerDTO updatePlayer(Long id, PlayerDTO playerDto);
    void deletePlayer(Long id);
    
    List<PlayerDTO> getPlayersByPlayerNameContainingIgnoreCase(String playerName);
    PlayerDTO getPlayersByJerseyNumber(Integer jerseyNumber);
    List<PlayerDTO> getPlayersByTeamNameContainingIgnoreCase(String teamName);
    List<PlayerDTO> getPlayersByCountryNameContainingIgnoreCase(String countryName);
    
}
