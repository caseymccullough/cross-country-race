package dao;

import exception.DaoException;
import model.Team;
import org.springframework.dao.DataIntegrityViolationException;
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

        Team newTeam = null;
        // notice int.class is 2nd parameter
        String sql = "INSERT INTO team(school_name, mascot) VALUES (?, ?) RETURNING team_id;";

        try {
            int newTeamId = jdbcTemplate.queryForObject(sql, int.class, team.getSchoolName(), team.getMascot());
            newTeam  = getTeamById(newTeamId);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot connect to the database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newTeam;
    }

    @Override
    public Team updateTeam(Team team) {

        Team updatedTeam = null;
        String sql = "UPDATE team SET school_name=?, mascot=? WHERE team_id=?;";
        try {
            //
            int rowsModified = jdbcTemplate.update(sql, team.getSchoolName(), team.getMascot(), team.getTeamId());
            if (rowsModified == 0) {
                throw new DaoException("Zero rows affected. Expected at least one.");
            }
            updatedTeam = getTeamById(team.getTeamId());

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        return updatedTeam;

    }

    @Override
    public int deleteTeamById(int teamId) {

        /*
            NOTE: Need to first delete coach and runners who are on each team.
        */

        int numTeamsDeleted = 0;
        String deleteRunnersSql = "DELETE FROM runner WHERE team_id = ?";
        String deleteCoachesSql = "DELETE FROM coach WHERE team_id = ?";
        String deleteTeamSql = "DELETE FROM team WHERE team_id = ?";

        try {
            // First delete the runners who are on the team
            int runnersDeleted = jdbcTemplate.update(deleteRunnersSql, teamId);
            System.out.println("Runners deleted: " + runnersDeleted);

            // Also delete the coaches who are on the team
            int coachesDeleted = jdbcTemplate.update(deleteCoachesSql, teamId);
            System.out.println("Coaches deleted: " + coachesDeleted);

            // Delete the team itself
            numTeamsDeleted = jdbcTemplate.update(deleteTeamSql, teamId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation");
        }
        return numTeamsDeleted;

    }

    private Team mapRowToTeam (SqlRowSet results){
        Team team = new Team();
        team.setTeamId(results.getInt("team_id"));
        team.setSchoolName(results.getString("school_name"));
        team.setMascot(results.getString("mascot"));
        return team;
    }
}
