package lt.vu.usecases;

import lt.vu.entities.Player;
import lt.vu.entities.Tournament;
import lt.vu.persistence.PlayersDAO;
import lt.vu.persistence.TournamnetsDAO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lt.vu.persistence.TournamnetsDAO;

import java.util.List;

public class PlayersForTournament {

    @Inject
    private TournamnetsDAO tournamentsDAO;

    @Inject
    private PlayersDAO playersDAO;

    @Transactional
    public void addPlayerToTournament(Integer playerId, Integer tournamentId) {
        System.out.println("Attempting to add player to tournament");
        Player player = playersDAO.findOne(playerId);
        Tournament tournament = tournamentsDAO.findOne(tournamentId);
        if (player != null && tournament != null) {
            System.out.println("Found player and tournament");
            player.getTournaments().add(tournament); // Since Player owns the relationship
            playersDAO.update(player); // Persist changes
            System.out.println("Player added to tournament");
        } else {
            System.out.println("Player or tournament not found");
        }
    }


    public List<Player> getPlayersForTournament(Integer tournamentId) {
        return tournamentsDAO.findOne(tournamentId).getPlayers();
    }

    // Other methods and logic related to tournaments
}
