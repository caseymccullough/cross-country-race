package dao;

import exception.DaoException;
import model.Team;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import javax.sql.RowSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcTeamDao implements TeamDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTeamDao (DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Team getTeamById(int teamId) {
        Team team = null;

        String sql = "SELECT team_id, school_name, mascot" +
                " FROM team WHERE team_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);
            if (results.next()) {
                team = mapRowToTeam(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to the database", e);
        }
        return team;
    }

    @Override
    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<>();

        String sql = "SELECT team_id, school_name, mascot FROM team";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                Team team = mapRowToTeam(results);
                teams.add(team);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to the database", e);
        }

        return teams;

    }

    @Override
    public Team createTeam(Team team) {
        return null;
    }

    @Override
    public Team updateTeam(Team team) {
        return null;
    }

    @Override
    public int deleteTeamById(int teamId) {
        return 0;
    }

    private Team mapRowToTeam (SqlRowSet results){
        Team team = new Team();
        team.setTeamId(results.getInt("team_id"));
        team.setSchoolName(results.getString("school_name"));
        team.setMascot(results.getString("mascot"));
        return team;
    }
}
