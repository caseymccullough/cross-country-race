package dao;

import model.Coach;
import model.Runner;

import java.util.List;

public class JdbcCoachDao implements CoachDao {
    @Override
    public Coach getCoachById(int coachId) {
        return null;
    }

    @Override
    public List<Coach> getAllCoaches() {
        return null;
    }

    @Override
    public List<Coach> getCoachesByTeamId(int teamId) {
        return null;
    }

    @Override
    public Coach createCoach(Coach coach) {
        return null;
    }

    @Override
    public Runner updateCoach(Coach coach) {
        return null;
    }

    @Override
    public int deleteCoachById(int coachId) {
        return 0;
    }
}
