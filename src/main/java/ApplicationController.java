

import dao.JdbcTeamDao;
import dao.TeamDao;
import model.Team;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import util.BasicConsole;

import javax.sql.DataSource;
import java.util.List;

public class ApplicationController {

    private final String DB_NAME = "CrossCountry";

    private final BasicConsole console;
//    private final ApplicationView view;
    private TeamDao teamDao;


    public ApplicationController(BasicConsole console) {
        this.console = console;
        // Set View for controller
        //view = new ApplicationView(console);
        // Set DataSource for campground database and assign to all the JDBC-DAOs
        DataSource dataSource = setupDataSource(DB_NAME);
        teamDao = new JdbcTeamDao(dataSource);
        //campgroundDao = new JdbcCampgroundDao(dataSource);
        //parkDao = new JdbcParkDao(dataSource);
    }

    /**
     * This method is called to begin the main menu loop. It's usually called from the application's
     * entry-point method (e.g., main)
     */
    public void run() {
        Team firstTeam = teamDao.getTeamById(1);
        System.out.println(firstTeam);
        System.out.println();
        List<Team> allTeams = teamDao.getTeams();
        for (Team team : allTeams) {
            System.out.println(team);
        }

        Team newTeam = new Team ();
        newTeam.setSchoolName("Xavier");
        newTeam.setMascot("Gators");

        Team returnedTeam = teamDao.createTeam(newTeam);
        System.out.println("Added Team: ");
        System.out.println(returnedTeam);

        Team team4 = teamDao.getTeamById(4);
        team4.setSchoolName("Slytherin");
        team4.setMascot("Snakes");

        System.out.println("Updating team");
        teamDao.updateTeam(team4);

        allTeams = teamDao.getTeams();
        for (Team team : allTeams) {
            System.out.println(team);
        }

        System.out.println("Deleting team 8");
        teamDao.deleteTeamById(8);
        allTeams = teamDao.getTeams();
        for (Team team : allTeams) {
            System.out.println(team);
        }



       // displayMainMenu();
    }

    public void displayMainMenu() {
        final String PARK_CRUD = "Parks - create, read, update, delete";
        final String CAMPGROUND_CRUD = "Campground - create, read, update, delete";
        final String EXIT = "Exit the program";
        final String[] MENU_OPTIONS = {PARK_CRUD, CAMPGROUND_CRUD, EXIT};

        console.printBlankLine();
        console.printMessage("Welcome to the National Parks application.");

//        boolean finished = false;
//        while (!finished) {
//            console.printBlankLine();
//            String title = "Main Menu\n" + parkCampgroundBreadCrumbs();
//            String selection = view.getMenuSelection(title, MENU_OPTIONS);
//            if (selection.equals(PARK_CRUD)) {
//                parkMenu();
//            } else if (selection.equals(CAMPGROUND_CRUD)) {
//                if (currentPark != null) {
//                    campgroundMenu();
//                } else {
//                    console.printErrorMessage("No park selected.");
//                }
//            } else if (selection.equals(EXIT)) {
//                finished = true;
//            }
//        }
    }

    private void parkMenu() {
        final String SELECT = "Select park";
        final String VIEW = "View park information";
        final String CREATE = "Create new park";
        final String UPDATE = "Update selected park";
        final String DELETE = "Delete selected park";
        final String RETURN = "Return to Main Menu";
        final String[] MENU_OPTIONS = {SELECT, VIEW, CREATE, UPDATE, DELETE, RETURN};

        boolean finished = false;
//        while (!finished) {
//            console.printBlankLine();
//            String title = "Park Menu\n" + parkCampgroundBreadCrumbs();
//            String selection = view.getMenuSelection(title, MENU_OPTIONS);
//            console.printDivider();
//            console.printBlankLine();

//            try {
//                if (selection.equals(SELECT)) {
//                    List<Park> parks = parkDao.getParks();
//                    Park selectedPark = view.getParkSelection(parks);
//                    if (selectedPark == null) {
//                        // Deselect current park and campground
//                        currentPark = null;
//                        currentCampground = null;
//                    } else if (selectedPark.equals(currentPark) == false) {
//                        // Switch to selected park and deselect current campground
//                        currentPark = selectedPark;
//                        currentCampground = null;
//                    }
//                } else if (selection.equals(VIEW)) {
//                    if (currentPark != null) {
//                        view.displayPark(currentPark);
//                    } else {
//                        console.printErrorMessage("No park selected.");
//                    }
//                } else if (selection.equals(CREATE)) {
//                    Park newPark = view.createPark();
//                    if (newPark != null) {
//                        // Create new park, display success, then make it current park and deselect current campground
//                        newPark = parkDao.createPark(newPark);
//                        console.printBlankLine();
//                        console.printMessage(newPark.getName() + " created !!!");
//                        currentPark = newPark;
//                        currentCampground = null;
//                    }
//                } else if (selection.equals(UPDATE)) {
//                    if (currentPark != null) {
//                        Park updatedPark = view.updatePark(currentPark);
//                        if (updatedPark != null) {
//                            // Update park, display success and make it current if updated successfully
//                            if (parkDao.updatePark(updatedPark) != null) {
//                                console.printBlankLine();
//                                console.printMessage(updatedPark.getName() + " updated !!!");
//                                currentPark = updatedPark;
//                            }
//                        }
//                    } else {
//                        console.printErrorMessage("No park selected.");
//                    }
//                } else if (selection.equals(DELETE)) {
//                    if (currentPark != null) {
//                        if (view.deletePark(currentPark)) {
//                            // Delete park, display success, and deselect current park and campground
//                            parkDao.deleteParkById(currentPark.getParkId());
//                            console.printBlankLine();
//                            console.printMessage(currentPark.getName() + " deleted !!!");
//                            currentPark = null;
//                            currentCampground = null;
//                        }
//                    } else {
//                        console.printErrorMessage("No park selected.");
//                    }
//                } else if (selection.equals(RETURN)) {
//                    finished = true;
//                }
//            }
//            catch (DaoException e) {
//                console.printErrorMessage("DAO error - " + e.getMessage());
//            }
//        }
    }

    private void campgroundMenu() {
        final String SELECT = "Select campground";
        final String VIEW = "View campground information";
        final String CREATE = "Create new campground";
        final String UPDATE = "Update selected campground";
        final String DELETE = "Delete selected campground";
        final String RETURN = "Return to Main Menu";
        final String[] MENU_OPTIONS = {SELECT, VIEW, CREATE, UPDATE, DELETE, RETURN};

//        boolean finished = false;
//        while (!finished) {
//            console.printBlankLine();
//            String title = "Campground Menu\n"  + parkCampgroundBreadCrumbs();
//            String selection = view.getMenuSelection(title, MENU_OPTIONS);
//            console.printDivider();
//            console.printBlankLine();
//
//            try {
//                if (selection.equals(SELECT)) {
//                    List<Campground> campgrounds = campgroundDao.getCampgroundsByParkId(currentPark.getParkId());
//                    Campground selectedCampground = view.getCampgroundSelection(campgrounds);
//                    if (selectedCampground == null) {
//                        // Deselect campground
//                        currentCampground = null;
//                    } else if (selectedCampground.equals(currentCampground) == false) {
//                        // Switch to selected campground
//                        currentCampground = selectedCampground;
//                    }
//                }  else if (selection.equals(VIEW)) {
//                    if (currentCampground != null) {
//                        view.displayCampground(currentCampground);
//                    } else {
//                        console.printErrorMessage("No campground selected.");
//                    }
//                } else if (selection.equals(CREATE)) {
//                    Campground newCampground = view.createCampground(currentPark);
//                    if (newCampground != null) {
//                        // Create new campground, display success, then make it current campground
//                        newCampground = campgroundDao.createCampground(newCampground);
//                        console.printBlankLine();
//                        console.printMessage(newCampground.getName() + " created !!!");
//                        currentCampground = newCampground;
//                    }
//                } else if (selection.equals(UPDATE)) {
//                    if (currentCampground != null) {
//                        Campground updatedCampground = view.updateCampground(currentCampground);
//                        if (updatedCampground != null) {
//                            // Update park, display success and make it current if updated successfully
//                            if (campgroundDao.updateCampground(updatedCampground) != null) {
//                                console.printBlankLine();
//                                console.printMessage(updatedCampground.getName() + " updated !!!");
//                                currentCampground = updatedCampground;
//                            }
//                        }
//                    } else {
//                        console.printErrorMessage("No campground selected.");
//                    }
//                } else if (selection.equals(DELETE)) {
//                    if (currentCampground != null) {
//                        if (view.deleteCampground(currentCampground)) {
//                            // Delete campground, display success, and deselect current campground
//                            campgroundDao.deleteCampgroundById(currentCampground.getCampgroundId());
//                            console.printBlankLine();
//                            console.printMessage(currentCampground.getName() + " deleted !!!");
//                            currentCampground = null;
//                        }
//                    } else {
//                        console.printErrorMessage("No campground selected.");
//                    }
//                } else if (selection.equals(RETURN)) {
//                    finished = true;
//                }
//            }
//            catch (DaoException e) {
//                console.printErrorMessage("DAO error - " + e.getMessage());
//            }
 //       }
    }

    private DataSource setupDataSource(String databaseName) {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setUrl(String.format("jdbc:postgresql://localhost:5432/" + databaseName));
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");
        return dataSource;
    }

//    private String parkCampgroundBreadCrumbs() {
//        return "Selected: " + (currentPark != null ? currentPark.getName() : "---") + " >> " +
//                (currentCampground != null ? currentCampground.getName() : "---");
//    }
}
