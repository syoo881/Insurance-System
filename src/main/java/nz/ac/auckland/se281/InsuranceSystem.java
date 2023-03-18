package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Arrays;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  private ArrayList userNameList = new ArrayList();
  private ArrayList ageList = new ArrayList();
  // I'm not sure if this is supposed to be in here or the Class below (public insurnace system)
  // Might only need one arraylist that takes information of both
  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public String TitleCase(String unmodifieduserName) {
    String modifieduserName =
        unmodifieduserName.substring(0, 1).toUpperCase()
            + unmodifieduserName.substring(1).toLowerCase();
    return modifieduserName;
  }

  public void printDatabase() {
    // TODO: Complete this method.

    int numberofProfiles = userNameList.size();

    if (numberofProfiles == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    } else if (numberofProfiles == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(numberofProfiles), ":");
      System.out.println("1: " + TitleCase((String) userNameList.get(0)) + ", " + ageList.get(0));
      // "1: Jordan, 21"

    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(numberofProfiles), "s", ":");
      for (int i = 0; i < userNameList.size(); i++) {
        System.out.println(
            i + 1 + ": " + TitleCase((String) userNameList.get(i)) + ", " + ageList.get(i));
      }
    }
  }

  public void createNewProfile(String userName, String age) {

    try {
      // put fully completed if statement here
      if (userName.length() < 3) {
        MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
      } else if (Arrays.asList(userNameList).contains(userName)) {
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
        // Need to make a way to filter out duplicate strings
      } else if ((Integer.parseInt(age) < 0)) {
        MessageCli.INVALID_AGE.printMessage(age, userName);
      } else {
        userNameList.add(userName);
        ageList.add(age);
        MessageCli.PROFILE_CREATED.printMessage(userName, age);
      }

    } catch (NumberFormatException exception) {
      MessageCli.INVALID_AGE.printMessage(age, userName);
    }
    // TODO: Complete this method.

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
