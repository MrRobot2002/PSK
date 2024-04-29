package lt.vu.persistence;

import lt.vu.entities.Tournament;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class TournamnetsDAO {

    @Inject
    private EntityManager em;

    public void persist(Tournament tournament){
        this.em.persist(tournament);
    }

    public Tournament findOne(Integer id){
        return em.find(Tournament.class, id);
    }

    public Tournament update(Tournament tournament){
        return em.merge(tournament);
    }

    public void insert(Tournament tournament){
        this.em.persist(tournament);
    }

    public void delete(Tournament tournament){
        this.em.remove(tournament);
    }

    public List<Tournament> selectAll() {
        return em.createQuery("SELECT t FROM Tournament t", Tournament.class).getResultList();
    }
}
