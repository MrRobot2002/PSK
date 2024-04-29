package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "PLAYER")
@Getter @Setter
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Column(name = "JERSEY_NUMBER")
    private Integer jerseyNumber;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    @ManyToMany
    @JoinTable(
            name = "PLAYER_TOURNAMENT", // Name of the join table
            joinColumns = @JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID"), // Foreign key for Player in join table
            inverseJoinColumns = @JoinColumn(name = "TOURNAMENT_ID", referencedColumnName = "ID") // Foreign key for Tournament in join table
    )
    private List<Tournament> tournaments;


    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Player() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
