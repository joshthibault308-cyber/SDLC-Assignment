/**
 * Joshua Thibault
 * CEN 3024 - Software Development I
 * February 6, 2025
 * Main.java
 * This class will let the user to add a patron to the system, remove a patron from the system, and show the list of the patrons in the system.
 * It will use the Patron class to make new patrons and store them in the patronList.
 */

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static ArrayList<Patron> patronList = new ArrayList<Patron>();

    /**
     * method: addPatron
     * parameter: Scanner scanner
     * return: void
     * purpose: Adds a patron to the patronList with either a file or manually. Typing "Back" will return to the previous menu.
     */
    public static void addPatron(Scanner scanner) {

        boolean done = false;

        while (!done) {
            System.out.println(
                    "Adding a patron: \n Type \"File\" to add a patron using a file. \n Type \"Manual\" to add a patron manually. \n Type \"Back\" to go back to the previous menu.");

            String answer = scanner.nextLine();

            if (answer.equals("File")) {

                System.out.println("Please enter the file name of the file you want to use:");
                String fileName = scanner.nextLine();

                try (FileInputStream fileStream = new FileInputStream(fileName)) {
                    Scanner scannerFile = new Scanner(fileStream);
                    while (scannerFile.hasNextLine()) {

                        boolean validID = true;
                        String patronScanned = scannerFile.nextLine();
                        System.out.println(patronScanned);

                        int startIndex = 0;
                        int dashIndex = 0;

                        dashIndex = patronScanned.indexOf("-");
                        int ID = Integer.parseInt(patronScanned.substring(startIndex, dashIndex));
                        startIndex = dashIndex + 1;

                        dashIndex = patronScanned.indexOf("-", startIndex);
                        String Name = patronScanned.substring(startIndex, dashIndex);
                        startIndex = dashIndex + 1;

                        dashIndex = patronScanned.indexOf("-", startIndex);
                        String Address = patronScanned.substring(startIndex, dashIndex);
                        startIndex = dashIndex + 1;

                        float fineAmount = Float.parseFloat(patronScanned.substring(startIndex));

                        for (Patron patronItem : patronList) {

                            if (patronItem.ID == ID) {

                                validID = false;
                                break;

                            }

                        }

                        if (validID) {

                            if ((fineAmount >= 0) && (fineAmount <= 250)) {

                                if ((ID > 999999) && (ID < 10000000)) {

                                    patronList.add(new Patron(ID, Name, Address, fineAmount));

                                } else {

                                    System.out.println("The ID should be 7 digits, please try again.");

                                }

                            } else {

                                System.out.println("The fine amount should be between 0- 250, please try again.");

                            }

                        } else {

                            System.out.println("This ID is already used, please try again.");

                        }

                    }
                } catch (Exception e) {
                    System.out.println("An error happened when trying to read the file, please try again.");
                }

            } else if (answer.equals("Manual")) {

                System.out.println("Please enter the patron with the format: \"ID-Name-Adddress-FineAmount\".");
                try {

                    boolean validID = true;
                    String patronText = scanner.nextLine();
                    int startIndex = 0;
                    int dashIndex = 0;

                    dashIndex = patronText.indexOf("-");
                    int ID = Integer.parseInt(patronText.substring(startIndex, dashIndex));
                    startIndex = dashIndex + 1;

                    dashIndex = patronText.indexOf("-", startIndex);
                    String Name = patronText.substring(startIndex, dashIndex);
                    startIndex = dashIndex + 1;

                    dashIndex = patronText.indexOf("-", startIndex);
                    String Address = patronText.substring(startIndex, dashIndex);
                    startIndex = dashIndex + 1;

                    float fineAmount = Float.parseFloat(patronText.substring(startIndex));

                    for (Patron patronItem : patronList) {

                        if (patronItem.ID == ID) {

                            validID = false;
                            break;

                        }

                    }

                    if (validID) {

                        if ((fineAmount >= 0) && (fineAmount <= 250)) {

                            if ((ID > 999999) && (ID < 10000000)) {

                                patronList.add(new Patron(ID, Name, Address, fineAmount));

                            } else {

                                System.out.println("The ID should be 7 digits, please try again.");

                            }

                        } else {

                            System.out.println("The fine amount should be between 0-250, please try again.");

                        }

                    } else {

                        System.out.println("This ID is already used, please try again.");

                    }
                } catch (Exception e) {
                    System.out.println("The format is wrong, please try again.");
                }

            } else if (answer.equals("Back")) {

                done = true;

            }
        }
    }

    /**
     * method: removePatron
     * parameter: Scanner scanner
     * return: void
     * purpose: Removes a patron from the patronList using the patron's ID. Typing "Back" will return to the previous menu.
     */
    public static void removePatron(Scanner scanner) {

        boolean done = false;

        while (!done) {
            System.out.println(
                    "Please enter the patron's ID you want to remove. \n Type \"Back\" to go back to the previous menu.");

            try {

                String patronText = scanner.nextLine();

                if (patronText.equals("Back")) {

                    done = true;
                    return;

                }

                for (int i = 0; i < patronList.size(); i++) {
                    if (patronList.get(i).getID() == Integer.parseInt(patronText)) {
                        patronList.remove(i);
                        done = true;
                        break;

                    }

                }

                if (!done) {

                    System.out.println("There was no patron found with that ID, please try again.");
                    
                }

            } catch (Exception e) {

                System.out.println("The ID should be an Integer, please try again.");

            }

        }

    }

    /**
     * method: showList
     * parameter: none
     * return: void
     * purpose: Shows the list of all the patrons in the system.
     */
    public static void showList() {

        System.out.println("PatronID-Name-Address-Current Overdue Fine");

        for (Patron patronItem : patronList) {

            if (patronItem != null) {

                System.out
                        .println(patronItem.getID() + "     " + patronItem.name + "     " + patronItem.address + "     "
                                + patronItem.fineAmount);

            }

        }

    }

    /**
     * method: main
     * parameter: none
     * return: void
     * purpose: The main method of the program. This will let the user go to the add a patron method, remove a patron method, and show the list method. Typing "Done" will close the program.
     */
    public static void main(String[] args) {

        boolean done = false;
        Scanner scanner = new Scanner(System.in);

        while (!done) {
            System.out.println(
                    "Welcome to the Library System! \n Type \"Add a patron\" to add a patron to the system. \n Type \"Remove a patron\" to remove a patron from the system. \n Type \"Show the list\" to show the list of the current patrons in the system. \n Type \"Done\" to close the program.");
            String answer = scanner.nextLine();

            if (answer.equals("Add a patron")) {

                addPatron(scanner);

            } else if (answer.equals("Remove a patron")) {

                removePatron(scanner);

            } else if (answer.equals("Show the list")) {

                showList();

            } else if (answer.equals("Done")) {

                done = true;

            }
        }
    }

}
