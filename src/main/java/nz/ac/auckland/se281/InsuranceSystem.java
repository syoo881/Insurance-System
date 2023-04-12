package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  private ArrayList userNameList = new ArrayList();
  private ArrayList ageList = new ArrayList();
  private ArrayList<Boolean> profileLoadStatus = new ArrayList<Boolean>();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {
    int numberOfProfiles = userNameList.size();
    // If statement for three separate cases - when numberOfProfiles=0, = 1, > 1
    // Using the MessageCli file to print the appropriate message for each case
    if (numberOfProfiles == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    } else if (numberOfProfiles == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(numberOfProfiles), ":", "");
      // * */ If index 0 of profileLoadStatus is true, then add *** to the start of the string
      if (profileLoadStatus.get(0) == true) {
        System.out.println("*** 1: " + titleCase(userNameList.get(0)) + ", " + ageList.get(0));
      } else {
        System.out.println("1: " + titleCase(userNameList.get(0)) + ", " + ageList.get(0));
      }
    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(numberOfProfiles), "s", ":");
      for (int i = 0; i < userNameList.size(); i++) {
        // *Check the i position of the profileLoadStatus arraylist, and if it is true, then add ***
        // * */ to the start of the userName with the index i
        if (profileLoadStatus.get(i) == true) {
          System.out.println(
              "*** " + (i + 1) + ": " + titleCase(userNameList.get(i)) + ", " + ageList.get(i));
        } else {
          System.out.println(i + 1 + ": " + titleCase(userNameList.get(i)) + ", " + ageList.get(i));
        }
      }
    }
  }

  public String titleCase(Object unmodifieduserName) {
    // Converting the Object input into a string.
    String stringUnmodifiedUserName = unmodifieduserName.toString();
    // Converting string to lowercase, and uppercasing only the first letter in one line of code.
    String modifiedUserName =
        stringUnmodifiedUserName.substring(0, 1).toUpperCase()
            + stringUnmodifiedUserName.substring(1).toLowerCase();
    return modifiedUserName;
  }

  public void createNewProfile(String userName, String age) {
    // Using try-catch to remove the error when string is inserted into age integer.
    try {
      // If statement to check if profileLoadStatus only has false values
      if (profileLoadStatus.contains(true)) {
        // *print cannot create new profile if profile is loaded, for userName
        MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(titleCase(userName));
      } else {
        if (userName.length() < 3) {
          MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(titleCase(userName));
        } else if (String.join(", ", userNameList).toLowerCase().contains(userName.toLowerCase())) {
          MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(titleCase(userName));
        } else if ((Integer.parseInt(age) < 0)) {
          MessageCli.INVALID_AGE.printMessage(age, userName);
        } else {
          // Only adding username and age to array if they are valid.
          userNameList.add(titleCase(userName));
          ageList.add(age);
          MessageCli.PROFILE_CREATED.printMessage(titleCase(userName), age);
          initialiseProfileLoadStatus();
        }
      }
    } catch (NumberFormatException exception) {
      MessageCli.INVALID_AGE.printMessage(age, titleCase(userName));
    }
  }

  public void initialiseProfileLoadStatus() {

    // * */ For the size of userNameList, set all values of profileLoadStatus to false
    for (int i = 0; i < userNameList.size(); i++) {
      profileLoadStatus.add(false);
    }
  }

  public void resetProfileLoadStatus() {

    // *For the size of userNameList, set all values of profileLoadStatus to false
    for (int i = 0; i < userNameList.size(); i++) {
      profileLoadStatus.set(i, false);
    }
  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.

    // call the method initialiseProfileLoadStatus() to initialise the boolean arraylist

    // * */ Loop through the userNameList arraylist, and if the username matches, then set the
    // * */ corresponding boolean value to true
    for (int i = 0; i < userNameList.size(); i++) {
      if (userNameList.get(i).equals(titleCase(userName))) {
        resetProfileLoadStatus();
        profileLoadStatus.set(i, true);
        MessageCli.PROFILE_LOADED.printMessage(titleCase(userName));
      }
    }

    // If the username doesn't match, then print the error message
    if (!String.join(", ", userNameList).toLowerCase().contains(userName.toLowerCase())) {
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(titleCase(userName));
    }
  }

  public void unloadProfile() {
    // TODO: Complete this method.
    // Set the boolean values of the arraylist to false

  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
    // Loop through the arraylist, and if the username matches, then remove the username and age
    // from the arraylist
    // If the username doesn't match, then print the error message

    // Loop through the Boolean, and if true, then print error, profile is loaded, and can't be
    // deleted

  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}
