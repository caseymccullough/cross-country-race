package model;

public class Team {
    private int teamId;
    private String schoolName;
    private String mascot;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getMascot() {
        return mascot;
    }

    public void setMascot(String mascot) {
        this.mascot = mascot;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", schoolName='" + schoolName + '\'' +
                ", mascot='" + mascot + '\'' +
                '}';
    }




}
