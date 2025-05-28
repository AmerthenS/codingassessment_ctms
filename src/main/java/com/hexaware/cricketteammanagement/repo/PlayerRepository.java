package com.hexaware.cricketteammanagement.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.cricketteammanagement.entity.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
	

    List<Player> findByPlayerName(String playerName);
    
}
