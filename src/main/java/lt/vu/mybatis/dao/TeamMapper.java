package lt.vu.mybatis.dao;

import java.util.List;

import lt.vu.mybatis.model.Team;
import org.mybatis.cdi.Mapper;


@Mapper
public interface TeamMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Team record);
    Team selectByPrimaryKey(Integer id);
    List<Team> selectAll();
    int updateByPrimaryKey(Team record);
}
