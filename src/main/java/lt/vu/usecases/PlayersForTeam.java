package lt.vu.usecases;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.inject.Model;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.PlayersDAO;
import lt.vu.persistence.TeamsDAO;
import lt.vu.entities.Player;
import lt.vu.entities.Team;

@Model
public class PlayersForTeam implements Serializable {

    @Inject
    private TeamsDAO teamsDAO;

    @Inject
    private PlayersDAO playersDAO;

    @Getter @Setter
    private Team team;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer teamId = Integer.parseInt(requestParameters.get("teamId"));
        this.team = teamsDAO.findOne(teamId);
    }

    @Transactional
    @LoggedInvocation
    public void createPlayer() {
        playerToCreate.setTeam(this.team);
        playersDAO.persist(playerToCreate);
    }
}
