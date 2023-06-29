package dao;

import exception.DaoException;
import model.Runner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcRunnerDao implements RunnerDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcRunnerDao (DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Runner getRunnerById(int runnerId) {
        Runner runner = null;
        String sql = "SELECT runner_id, team_id, first_name, last_name" +
                " FROM runner WHERE runner_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, runnerId);
            if (results.next()) {
                runner = mapRowToRunner(results);
            }
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return runner;
    }

    @Override
    public List<Runner> getAllRunners() {
        List<Runner> allRunners = new ArrayList<>();
        String sql = "SELECT runner_id, team_id, first_name, last_name" +
                " FROM runner;";

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Runner runner = mapRowToRunner(results);
                allRunners.add(runner);
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data Integrity Violation", e);
        }
        return allRunners;
    }

    @Override
    public List<Runner> getRunnersByTeamId(int teamId) {
        List<Runner> runners = new ArrayList<>();
        String sql = "SELECT runner_id, team_id, first_name, last_name" +
                " FROM runner WHERE team_id = ?;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teamId);

            while (results.next()) {
                Runner runner = mapRowToRunner(results);
                runners.add(runner);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Cannot connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return runners;
    }

    @Override
    public Runner createRunner(Runner runner) {
        /*
        NOTE: You need to use queryForObject() (instead of update()) or you'll get
        .BadSqlGrammarException
         */

        Runner newRunner = null;
        String sql = "INSERT INTO runner (team_id, first_name, last_name)" +
                " VALUES (?, ?, ?) RETURNING runner_id;";
        System.out.println(sql );
        // Note w/o int.class, this triggers the DataIntegrityViolation Exception.
        try {
            int newRunnerId = jdbcTemplate.queryForObject(sql, int.class, runner.getTeamId(), runner.getFirstName(), runner.getLastName());
            newRunner = getRunnerById(newRunnerId);
        } catch (CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot connect to database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return newRunner;
    }

    @Override
    public Runner updateRunner(Runner runner) {
        Runner updatedRunner = null;
        String sql = "UPDATE runner SET runner_id=?, team_id=?, first_name=?, last_name=?" +
                " WHERE <condition>;";
    }

    @Override
    public int deleteRunner(int runnerId) {
        return 0;
    }

    private Runner mapRowToRunner (SqlRowSet results){
        Runner runner = new Runner();
        runner.setRunnerId(results.getInt("runner_id"));
        runner.setTeamId(results.getInt("team_id"));
        runner.setFirstName(results.getString("first_name"));
        runner.setLastName(results.getString("last_name"));

        return runner;
    }
}
