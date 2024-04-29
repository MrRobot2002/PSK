package lt.vu.usecases;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Tournament;
import lt.vu.mybatis.dao.PlayerMapper;
import lt.vu.mybatis.dao.TournamentMapper;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lt.vu.entities.Player;
import lt.vu.persistence.PlayersDAO;
import lt.vu.persistence.TournamnetsDAO;

import java.util.List;

@Named
@RequestScoped
public class TournamentBean {

    @Inject
    private PlayersForTournament tournamentService;
    @Inject
    private TournamentMapper tournamentMapper;

    @Inject
    private PlayerMapper playerMapper;

    @Inject
    private TournamnetsDAO tournamnetsDAO;
    @Inject
    private PlayersDAO playersDAO;

    private Tournament newTournament = new Tournament();
    private List<Tournament> tournaments;

    @Getter @Setter
    private Integer selectedPlayerId;
    @Getter @Setter
    private Integer selectedTournamentId;

    @Getter @Setter
    private List<Player> availablePlayers;
    @Getter @Setter
    private List<Tournament> allTournaments;

    @PostConstruct
    public void init() {
        refreshTournaments(); // Pre-load tournaments on bean initialization
        availablePlayers = playersDAO.findAll();
    }

    private void refreshTournaments() {
        tournaments = tournamnetsDAO.selectAll();
    }

    public Tournament getNewTournament() {
        return newTournament;
    }

    public void setNewTournament(Tournament newTournament) {
        this.newTournament = newTournament;
    }

    @Transactional
    public String createTournament() {
        try {
            tournamnetsDAO.persist(newTournament);
            refreshTournaments();
            return "tournaments?faces-redirect=true";
        } catch (Exception e) {
            // Log the error or handle it appropriately
            return "index";
        }

    }



    public List<Tournament> loadTournaments() {
        if (tournaments == null) {
            tournaments = tournamentMapper.selectAll();
        }

        for (Tournament tournament : tournaments) {
            System.out.println("Tournament: " + tournament.getName());
            for (Player player : tournament.getPlayers()) {
                System.out.println("Player: " + player.getName());
            }
        }


        return tournaments;
    }


    public String addPlayerToTournament() {
        tournamentService.addPlayerToTournament(selectedPlayerId, selectedTournamentId);
        refreshTournaments();
        return "tournaments?faces-redirect=true"; // Redirect to the tournament list page
    }

    // Other methods and logic as necessary


}
