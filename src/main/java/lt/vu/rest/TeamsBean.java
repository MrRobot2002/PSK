package lt.vu.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lt.vu.persistence.TeamsDAO;
import lt.vu.entities.Team;

import java.util.List;

@Named // Marks the bean as managed by JSF
@RequestScoped // Defines the scope of the bean
public class TeamsBean {

    @Inject
    private TeamsDAO teamsDAO;

    private String searchQuery;
    private List<Team> searchResults;

    // Getters and setters for searchQuery and searchResults
    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<Team> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Team> searchResults) {
        this.searchResults = searchResults;
    }

    public List<Team> getAllTeams() {
        return teamsDAO.loadAll();
    }

    @Transactional // This annotation handles the transaction boundary
    public String searchTeams() {
        searchResults = teamsDAO.findTeamsByNameFragment(searchQuery);
        return null; // Stay on the same page and update the search results
    }

    // Implement other methods similar to PlayersController if needed
}