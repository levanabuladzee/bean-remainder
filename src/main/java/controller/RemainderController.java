package controller;

import dao.RemainderDAO;
import model.Location;
import model.Remainder;
import util.Valid;
import view.RemainderView;

import javax.validation.ConstraintViolation;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.exit;

public class RemainderController {
    public void getInput() {
        RemainderDAO dao = new RemainderDAO();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");
        String input = scanner.next();

        switch (input) {
            case "show":
                dao.showRemainder();
                break;
            case "add":
                try {
                    System.out.print("Enter name of the remainder: ");
                    String addName = scanner.next();
                    System.out.print("Enter hour of the remainder: ");
                    int addHour = scanner.nextInt();
                    System.out.print("Enter minute of the remainder: ");
                    int addMinute = scanner.nextInt();
                    System.out.print("Enter second of the remainder: ");
                    int addSecond = scanner.nextInt();
                    LocalTime addTime = LocalTime.of(addHour, addMinute, addSecond);
                    System.out.print("Enter city of the remainder: ");
                    String addCity = scanner.next();
                    System.out.print("Enter zip code of the remainder: ");
                    String addZip = scanner.next();
                    Location addLocation = new Location(addCity, addZip);
                    Remainder addRemainder = new Remainder(addName, addTime, addLocation);

                    Set<ConstraintViolation<Remainder>> violations = Valid.validate(addRemainder);
                    if (violations.size() != 0) {
                        for (ConstraintViolation<Remainder> violation : violations) {
                            System.out.println(violation.getMessage());
                        }
                    } else {
                        dao.addRemainder(addRemainder);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input is invalid");
                    break;
                }

                break;
            case "update":
                try {
                    System.out.print("Enter name of the remainder: ");
                    String updateName = scanner.next();
                    System.out.print("Enter hour of the remainder: ");
                    int updateHour = scanner.nextInt();
                    System.out.print("Enter minute of the remainder: ");
                    int updateMinute = scanner.nextInt();
                    System.out.print("Enter second of the remainder: ");
                    int updateSecond = scanner.nextInt();
                    LocalTime updateTime = LocalTime.of(updateHour, updateMinute, updateSecond);
                    System.out.print("Enter city of the remainder: ");
                    String updateCity = scanner.next();
                    System.out.print("Enter zip code of the remainder: ");
                    String updateZip = scanner.next();
                    System.out.print("Enter id of the remainder: ");
                    int updateId = scanner.nextInt();
                    Location updateLocation = new Location(updateCity, updateZip);
                    Remainder updateRemainder = new Remainder(updateName, updateTime, updateLocation);

                    Set<ConstraintViolation<Remainder>> updateViolations = Valid.validate(updateRemainder);
                    if (updateViolations.size() != 0) {
                        for (ConstraintViolation<Remainder> violation : updateViolations) {
                            System.out.println(violation.getMessage());
                        }
                    } else {
                        dao.updateRemainder(updateRemainder, updateId);
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input is invalid");
                    break;
                }
                break;
            case "remove":
                try {
                    System.out.print("Enter id of the remainder: ");
                    int removeId = scanner.nextInt();
                    dao.removeRemainder(removeId);
                } catch (InputMismatchException e) {
                    System.out.println("Input is invalid");
                    break;
                }
                break;
            case "check":
                dao.getRemainders();
                break;
            case "menu":
                RemainderView display = new RemainderView();
                display.getMenu();
                break;
            case "quit":
                exit(0);
                break;
            default:
        }
    }
}
