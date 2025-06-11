package com.hexaware.cricketteammanagement.service;

import org.springframework.stereotype.Service;

import com.hexaware.cricketteammanagement.dto.PlayerDTO;
import com.hexaware.cricketteammanagement.entity.Player;
import com.hexaware.cricketteammanagement.exception.ResourceNotFoundException;
import com.hexaware.cricketteammanagement.repo.PlayerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepo;

    public PlayerServiceImpl(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

    private PlayerDTO mapToDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayerId(player.getPlayerId());
        dto.setPlayerName(player.getPlayerName());
        dto.setJerseyNumber(player.getJerseyNumber());
        dto.setRole(player.getRole());
        dto.setTotalMatches(player.getTotalMatches());
        dto.setTeamName(player.getTeamName());
        dto.setCountryName(player.getCountryName());
        dto.setDescription(player.getDescription());
        return dto;
    }

    private Player mapToEntity(PlayerDTO dto, Player player) {
        player.setPlayerName(dto.getPlayerName());
        player.setJerseyNumber(dto.getJerseyNumber());
        player.setRole(dto.getRole());
        player.setTotalMatches(dto.getTotalMatches());
        player.setTeamName(dto.getTeamName());
        player.setCountryName(dto.getCountryName());
        player.setDescription(dto.getDescription());
        return player;
    }

    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDTO getPlayerById(Long id) {
        Player player = playerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with ID " + id));
        return mapToDTO(player);
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDto) {
        Player player = mapToEntity(playerDto, new Player());
        player = playerRepo.save(player);
        return mapToDTO(player);
    }

    @Override
    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDto) {
        Player existing = playerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with ID " + id));
        Player updated = mapToEntity(playerDto, existing);
        return mapToDTO(playerRepo.save(updated));
    }

    @Override
    public void deletePlayer(Long id) {
        Player existing = playerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player not found with ID " + id));
        playerRepo.delete(existing);
    }
    
    
    @Override
    public List<PlayerDTO> getPlayersByPlayerNameContainingIgnoreCase(String playerName) {
        List<Player> players = playerRepo.findByPlayerNameContainingIgnoreCase(playerName);
        return players.stream()
                      .map(this::mapToDTO)
                      .collect(Collectors.toList());
    }
    
    @Override
    public PlayerDTO getPlayersByJerseyNumber(Integer jerseyNumber) {
        Player player = playerRepo.findByJerseyNumber(jerseyNumber)
            .orElseThrow(() -> new RuntimeException("Player not found with jersey number: " + jerseyNumber));
        return mapToDTO(player);
    }
    
    @Override
    public List<PlayerDTO> getPlayersByTeamNameContainingIgnoreCase(String teamName) {
        return playerRepo.findByTeamNameContainingIgnoreCase(teamName).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }
    
    @Override
    public List<PlayerDTO> getPlayersByCountryNameContainingIgnoreCase(String countryName) {
        return playerRepo.findByCountryNameContainingIgnoreCase(countryName).stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());
    }

}
