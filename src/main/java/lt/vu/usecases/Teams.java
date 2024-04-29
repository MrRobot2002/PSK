package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.persistence.TeamsDAO;
import lt.vu.entities.Team;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@Model
public class Teams {

    @Inject
    private TeamsDAO teamsDAO;

    @Getter @Setter
    private Team teamToCreate = new Team();

    @Getter
    private List<Team> allTeams;

    @PostConstruct
    public void init(){
        loadAllTeams();
    }

    @Transactional
    public void createTeam(){
        this.teamsDAO.persist(teamToCreate);
    }

    private void loadAllTeams(){
        this.allTeams = teamsDAO.loadAll();
    }
}
