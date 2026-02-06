/**
 * Joshua Thibault
 * CEN 3024 - Software Development I
 * February 6, 2025
 * Patron.java
 * This class will be used to make a patron object that will be stored in the system.
 */

public class Patron {

    int ID;
    String name;
    String address;
    float fineAmount;

    /**
     * method: Patron
     * parameter: int IDNum, String patronName, String patronAddress, float
     * patronFineAmount
     * return: void
     * purpose: This will make a new Patron with the parameters.
     */
    public Patron(int IDNum, String patronName, String patronAddress, float patronFineAmount) {

        ID = IDNum;
        name = patronName;
        address = patronAddress;
        fineAmount = patronFineAmount;

    }

    /**
     * method: getID
     * parameter: none
     * return: int
     * purpose: Returns the ID of a patron.
     */
    public int getID() {
        return ID;
    }

}