package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  private ArrayList userNameList = new ArrayList();
  private ArrayList ageList = new ArrayList();

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
      System.out.println("1: " + titleCase(userNameList.get(0)) + ", " + ageList.get(0));
    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(numberOfProfiles), "s", ":");
      for (int i = 0; i < userNameList.size(); i++) {
        System.out.println(i + 1 + ": " + titleCase(userNameList.get(i)) + ", " + ageList.get(i));
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
      }
    } catch (NumberFormatException exception) {
      MessageCli.INVALID_AGE.printMessage(age, titleCase(userName));
    }
  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.
  }

  public void unloadProfile() {
    // TODO: Complete this method.
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
  }

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}
