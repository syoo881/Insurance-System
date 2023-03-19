package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Arrays;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  private ArrayList userNameList = new ArrayList();
  private ArrayList ageList = new ArrayList();

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {
    // TODO: Complete this method.

    int numberofProfiles = userNameList.size();

    if (numberofProfiles == 0) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage("0", "s", ".");
    } else if (numberofProfiles == 1) {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(numberofProfiles), ":", "");
      System.out.println("1: " + titleCase(userNameList.get(0)) + ", " + ageList.get(0));
    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(numberofProfiles), "s", ":");
      for (int i = 0; i < userNameList.size(); i++) {
        System.out.println(i + 1 + ": " + titleCase(userNameList.get(i)) + ", " + ageList.get(i));
      }
    }
  }

  public String titleCase(Object unmodifieduserName) {
    String strunmodifieduserName = unmodifieduserName.toString();
    String modifieduserName =
        strunmodifieduserName.substring(0, 1).toUpperCase()
            + strunmodifieduserName.substring(1).toLowerCase();
    return modifieduserName;
  }

  public void createNewProfile(String userName, String age) {

    try {
      if (userName.length() < 3) {
        MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
      } else if (Arrays.asList(userNameList).contains(userName)) {
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
      } else if ((Integer.parseInt(age) < 0)) {
        MessageCli.INVALID_AGE.printMessage(age, userName);
      } else {
        userNameList.add(userName);
        ageList.add(age);
        MessageCli.PROFILE_CREATED.printMessage(titleCase(userName), age);
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
