package dao;

import model.Runner;
import java.util.List;

public interface RunnerDao {

    Runner getRunnerById (int runnerId);

    List<Runner> getAllRunners();

    List<Runner> getRunnersByTeamId(int teamId);

    Runner createRunner (Runner runner);

    Runner updateRunner (Runner runner);

    int deleteRunner (int runnerId);

}
