package lt.vu.usecases;

import lt.vu.mybatis.dao.PlayerMapper;
import lt.vu.mybatis.model.Player;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class PlayerBean {

    @Inject
    private PlayerMapper playerMapper;

    private List<Player> players;

    public List<Player> getPlayers() {
        if (players == null) {
            players = playerMapper.selectAllWithTeam(); // Method to be implemented in PlayerMapper
        }
        return players;
    }

    // Other methods and logic as necessary
}
