package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "TOURNAMENT")
@Getter @Setter
public class Tournament implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //For auto-increment primary key
    private Integer id;

    @Size(max = 100) //Max length of the name
    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tournaments")
    private List<Player> players;


    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Tournament() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }




}
