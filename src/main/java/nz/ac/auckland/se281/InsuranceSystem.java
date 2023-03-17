package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Arrays;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printDatabase() {
    // TODO: Complete this method.
  }

  public void createNewProfile(String userName, String age) {

    ArrayList userNameList = new ArrayList();
    ArrayList ageList = new ArrayList();

    try {
      // put fully completed if statement here
      if (userName.length() < 3) {
        MessageCli.INVALID_USERNAME_TOO_SHORT.printMessage(userName);
      } else if (Arrays.asList(userNameList).contains(userName)) {
        MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(userName);
        // Need to make a way to filter out duplicate strings
      } else if ((Integer.parseInt(age) < 0)) {
        MessageCli.INVALID_AGE.printMessage(age, userName);
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
