package lt.vu.persistence;

import lt.vu.entities.Player;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class PlayersDAO {

    @Inject
    private EntityManager em;

    public void persist(Player player){
        this.em.persist(player);
    }

    public Player findOne(Integer id){
        return em.find(Player.class, id);
    }

    public List<Player> findAll() {
        return em.createQuery("SELECT p FROM Player p", Player.class).getResultList();
    }

    public Player update(Player player){
        return em.merge(player);
    }
}
