package dao;

import model.Team;

import java.util.List;

public interface TeamDao {

    Team getTeamById(int teamId);

    List<Team> getTeams();

    Team createTeam(Team team);

    Team updateTeam (Team team);

    int deleteTeamById(int teamId);

}
