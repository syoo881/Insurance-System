package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  private ArrayList userNameList = new ArrayList();
  private ArrayList ageList = new ArrayList();
  private ArrayList<Boolean> profileLoadStatus = new ArrayList<Boolean>();
  private String currentLoadedUserName = "";

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

  public void setProfileLoadStatus(String userName) {
    for (int i = 0; i < userNameList.size(); i++) {
      if (userNameList.get(i).equals(titleCase(userName))) {
        resetProfileLoadStatus();
        profileLoadStatus.set(i, true);
      }
    }
  }

  public void loadProfile(String userName) {
    // TODO: Complete this method.
    // set currentUserName to userName
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
    // TODO: Complete this method.
    // Set the boolean values of the arraylist to false
    // check if any profiles are loaded, and if not, print error message
    if (profileLoadStatus.contains(true)) {
      resetProfileLoadStatus();
      MessageCli.PROFILE_UNLOADED.printMessage(titleCase(currentLoadedUserName));
    } else {
      resetProfileLoadStatus();
      MessageCli.NO_PROFILE_LOADED.printMessage();
    }
  }

  public void deleteProfile(String userName) {
    // TODO: Complete this method.
    // Loop through the arraylist, and if the username matches, then remove the username and age
    // from the arraylist
    // If the username doesn't match, then print the error message
    // Loop through the Boolean, and if true, then print error, profile is loaded, and can't be
    // deleted
    // if username is not in the list, print no profile was deleted message
    if (String.join(", ", userNameList).toLowerCase().contains(userName.toLowerCase())) {
      for (int i = 0; i < userNameList.size(); i++) {
        if (userNameList.get(i).equals(titleCase(userName))) {
          if (profileLoadStatus.get(i) == true) {
            MessageCli.CANNOT_DELETE_PROFILE_WHILE_LOADED.printMessage(titleCase(userName));
          } else {
            userNameList.remove(i);
            ageList.remove(i);
            MessageCli.PROFILE_DELETED.printMessage(titleCase(userName));
            // After deleting the profile, reset the profileLoadStatus arraylist
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

  public void createPolicy(PolicyType type, String[] options) {
    // TODO: Complete this method.
  }
}
