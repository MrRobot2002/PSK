package lt.vu.mybatis.dao;

import java.util.List;


import lt.vu.mybatis.model.Player;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mybatis.cdi.Mapper;


@Mapper
public interface PlayerMapper {

    int deleteByPrimaryKey(Integer id);
    int insert(Player record);
    Player selectByPrimaryKey(Integer id);
    List<Player> selectAll();
    int updateByPrimaryKey(Player record);

    // A new method to select all players with their associated teams
    @Select("SELECT p.*, t.ID as team_id, t.NAME as team_name " +
            "FROM PLAYER p " +
            "LEFT JOIN TEAM t ON p.TEAM_ID = t.ID")
    @Results(value = {
            @Result(property = "id", column = "ID"),
            @Result(property = "name", column = "NAME"),
            // ... map other player properties ...
            @Result(property = "team", column = "team_id",
                    one = @One(select = "lt.vu.mybatis.dao.TeamMapper.selectByPrimaryKey"))
    })
    List<Player> selectAllWithTeam();
}