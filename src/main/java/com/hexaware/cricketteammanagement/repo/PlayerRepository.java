package com.hexaware.cricketteammanagement.repo;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.cricketteammanagement.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	

	List<Player> findByPlayerNameContainingIgnoreCase(String playerName); 
    Optional<Player> findByJerseyNumber(Integer jerseyNumber); 
    List<Player> findByTeamNameContainingIgnoreCase(String teamName); 
    List<Player> findByCountryNameContainingIgnoreCase(String countryName);
}
