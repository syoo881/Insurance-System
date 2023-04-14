package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  private ArrayList userNameList = new ArrayList();
  private ArrayList ageList = new ArrayList();
  private ArrayList<Boolean> profileLoadStatus = new ArrayList<Boolean>();
  private String currentLoadedUserName = "";
  private ArrayList<String> homePolicyList = new ArrayList<String>();

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

        if (profileLoadStatus.get(i) == true) {
          System.out.println(
              "*** " + (i + 1) + ": " + titleCase(userNameList.get(i)) + ", " + ageList.get(i));
        } else {
          System.out.println(i + 1 + ": " + titleCase(userNameList.get(i)) + ", " + ageList.get(i));
        }
      }
    }
  }

  private String titleCase(Object unmodifieduserName) {
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

        MessageCli.CANNOT_CREATE_WHILE_LOADED.printMessage(titleCase(currentLoadedUserName));
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

  private void initialiseProfileLoadStatus() {

    for (int i = 0; i < userNameList.size(); i++) {
      profileLoadStatus.add(false);
    }
  }

  private void resetProfileLoadStatus() {

    for (int i = 0; i < userNameList.size(); i++) {
      profileLoadStatus.set(i, false);
    }
  }

  private void setProfileLoadStatus(String userName) {
    for (int i = 0; i < userNameList.size(); i++) {
      if (userNameList.get(i).equals(titleCase(userName))) {
        resetProfileLoadStatus();
        profileLoadStatus.set(i, true);
      }
    }
  }

  public void loadProfile(String userName) {

    currentLoadedUserName = userName;
    setProfileLoadStatus(userName);
    // If the username doesn't match, then print the error message
    if (!String.join(", ", userNameList).toLowerCase().contains(userName.toLowerCase())) {
      MessageCli.NO_PROFILE_FOUND_TO_LOAD.printMessage(titleCase(userName));
    } else {
      MessageCli.PROFILE_LOADED.printMessage(titleCase(userName));
    }
  }

  public void unloadProfile() {
    // Unloading the profile, only if it is loaded. After loading, the profileLoadStatus is reset.
    if (profileLoadStatus.contains(true)) {
      resetProfileLoadStatus();
      currentLoadedUserName = "";
      MessageCli.PROFILE_UNLOADED.printMessage(titleCase(currentLoadedUserName));
    } else {
      resetProfileLoadStatus();
      MessageCli.NO_PROFILE_LOADED.printMessage();
    }
  }

  public void deleteProfile(String userName) {
    // If the userName matches, delete the profile unless it is already loaded.
    if (String.join(", ", userNameList).toLowerCase().contains(userName.toLowerCase())) {
      for (int i = 0; i < userNameList.size(); i++) {
        // Checking if the profile is loaded before deleting it
        if (userNameList.get(i).equals(titleCase(userName))) {
          if (profileLoadStatus.get(i) == true) {
            MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(titleCase(userName));
          } else {
            userNameList.remove(i);
            ageList.remove(i);
            MessageCli.PROFILE_DELETED.printMessage(titleCase(userName));
            // After deleting the profile, reset the profileLoadStatus arraylist with the new loaded
            // userName
            if (profileLoadStatus.contains(true)) {
              resetProfileLoadStatus();
              for (int j = 0; j < userNameList.size(); j++) {
                if (userNameList.get(j).equals(titleCase(currentLoadedUserName))) {
                  profileLoadStatus.set(j, true);
                }
              }
            } else {
              resetProfileLoadStatus();
            }
          }
        }
      }
    } else {
      MessageCli.NO_PROFILE_FOUND_TO_DELETE.printMessage(titleCase(userName));
    }
  }

  private boolean toBoolean(String toBeBoolean) {

    // if toBeBoolean = "yes" return true. If toBeBoolean = "no" return false.
    if (toBeBoolean.contains("y")) {
      return true;
    } else {
      return false;
    }
  }

  public void createPolicy(PolicyType type, String[] options) {

    if (currentLoadedUserName.equals("")) {
      System.out.println("Need to load a profile in order to create a policy.");
    } else {

      switch (type) {
        case HOME:
          HomePolicy homeInsurance =
              new HomePolicy(Integer.parseInt(options[0]), options[1], toBoolean(options[2]));
          // Separate the arraylist with the alphabet of the policy command, and use
          // a loop to check through the arraylist, and every time the corresponding H,C,L
          // is found, add the corresponding getters.
          homePolicyList.add("H");
          homePolicyList.add(homeInsurance.getAddress());
          homePolicyList.add(Integer.toString(homeInsurance.getSumInsured()));
          homeInsurance.calculatePremium();
          homePolicyList.add(Integer.toString(homeInsurance.getHomePremium()));
          // Regarding the discounted premium, this can only be done after we figure out a
          // way to check how many policies are created for each profile.

          // Maybe could do something like if (userPolicyAmount>2){ homePolicyList[i+3] * 0.8}

          MessageCli.NEW_POLICY_CREATED.printMessage(
              type.toString().toLowerCase(), titleCase(currentLoadedUserName));

          break;

        case CAR:
          break;

        case LIFE:
          break;
      }
    }
    // TODO: Complete this method.
  }
}
