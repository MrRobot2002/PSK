package lt.vu.mybatis.dao;

import lt.vu.entities.Player;
import lt.vu.entities.Tournament;
import org.apache.ibatis.annotations.*;
import org.mybatis.cdi.Mapper;
import java.util.List;

@Mapper
public interface TournamentMapper {

    @Select("SELECT * FROM TOURNAMENT")
    List<Tournament> selectAll();

    @Select("SELECT p.* FROM PLAYER p " +
            "JOIN PLAYER_TOURNAMENT pt ON p.ID = pt.PLAYER_ID " +
            "JOIN TOURNAMENT t ON pt.TOURNAMENT_ID = t.ID " +
            "WHERE t.ID = #{tournamentId}")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            @Result(property = "jerseyNumber", column = "JERSEY_NUMBER"),
            // Include other Player properties if they exist
    })
    List<Player> selectPlayersByTournamentId(Integer tournamentId);
}
