package dao;

import model.Coach;
import model.Runner;

import java.util.List;

public interface CoachDao {

    Coach getCoachById (int coachId);

    List<Coach> getAllCoaches();

    List<Coach> getCoachesByTeamId(int teamId);

    Coach createCoach (Coach coach);

    Runner updateCoach (Coach coach);

    int deleteCoachById (int coachId);

}
