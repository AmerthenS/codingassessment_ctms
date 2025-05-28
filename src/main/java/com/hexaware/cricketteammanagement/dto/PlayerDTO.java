package com.hexaware.cricketteammanagement.dto;

import jakarta.validation.constraints.*;


public class PlayerDTO {

    private Long playerId;

    @NotBlank(message = "Player name is mandatory")
    @Size(min = 2, max = 100)
    private String playerName;

    @NotNull(message = "Jersey number is required")
    @Min(value = 0)
    private Integer jerseyNumber;

    @NotBlank(message = "Role is required")
    @Pattern(regexp = "Batsman|Bowler|Keeper|All Rounder", message = "Role must be Batsman, Bowler, Keeper, or All Rounder")
    private String role;
    
    private Integer totalMatches;
    private String teamName;
    private String countryName;
    private String description;
    

	public Integer getTotalMatches() {
		return totalMatches;
	}

	public void setTotalMatches(Integer totalMatches) {
		this.totalMatches = totalMatches;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getJerseyNumber() {
		return jerseyNumber;
	}

	public void setJerseyNumber(Integer jerseyNumber) {
		this.jerseyNumber = jerseyNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    
}
