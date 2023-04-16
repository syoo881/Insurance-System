package nz.ac.auckland.se281;

import java.util.ArrayList;
import nz.ac.auckland.se281.Main.PolicyType;

public class InsuranceSystem {
  private ArrayList<String> userNameList = new ArrayList<String>();
  private ArrayList<String> ageList = new ArrayList<String>();
  private ArrayList<Boolean> profileLoadStatus = new ArrayList<Boolean>();
  private ArrayList<Integer> policyCount = new ArrayList<Integer>();
  private ArrayList<User> userPolicyList = new ArrayList<User>();

  // Add a new arraylist here, to correspond to the amount of policy that each user has

  private String currentLoadedUserName = "";

  public InsuranceSystem() {
    // Only this constructor can be used (if you need to initialise fields).
  }

  public void printPolicy(int policyIndex) {
    ArrayList<String> policyList = userPolicyList.get(policyIndex).getPolicyList();
    int numberOfElements = policyList.size();

    if (policyCount.get(policyIndex) == 1) {
      // for the length of the policyList, everytime it finds a string "H", it will use the next 3
      // values to print out message.Cli.PRINT_DB_HOME_POLICY

      for (int i = 0; i < numberOfElements; i++) {
        if (policyList.get(i).equals("H")) {
          MessageCli.PRINT_DB_HOME_POLICY.printMessage(
              policyList.get(i + 1),
              policyList.get(i + 2),
              policyList.get(i + 3),
              policyList.get(i + 3));
        } else if (policyList.get(i).equals("C")) {
          MessageCli.PRINT_DB_CAR_POLICY.printMessage(
              policyList.get(i + 1),
              policyList.get(i + 2),
              policyList.get(i + 3),
              policyList.get(i + 3));
        } else if (policyList.get(i).equals("L")) {
          MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
              policyList.get(i + 1), policyList.get(i + 2), policyList.get(i + 2));
        }
      }
    } else if (policyCount.get(policyIndex) == 2) {
      for (int i = 0; i < numberOfElements; i++) {
        if (policyList.get(i).equals("H")) {
          MessageCli.PRINT_DB_HOME_POLICY.printMessage(
              policyList.get(i + 1),
              policyList.get(i + 2),
              policyList.get(i + 3),
              Integer.toString((int) (Double.parseDouble(policyList.get(i + 3)) * 0.9)));
        } else if (policyList.get(i).equals("C")) {
          MessageCli.PRINT_DB_CAR_POLICY.printMessage(
              policyList.get(i + 1),
              policyList.get(i + 2),
              policyList.get(i + 3),
              Integer.toString((int) (Double.parseDouble(policyList.get(i + 3)) * 0.9)));
        } else if (policyList.get(i).equals("L")) {
          MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
              policyList.get(i + 1),
              policyList.get(i + 2),
              Integer.toString((int) (Double.parseDouble(policyList.get(i + 2)) * 0.9)));
        }
      }

    } else if (policyCount.get(policyIndex) > 2) {
      for (int i = 0; i < numberOfElements; i++) {
        if (policyList.get(i).equals("H")) {
          MessageCli.PRINT_DB_HOME_POLICY.printMessage(
              policyList.get(i + 1),
              policyList.get(i + 2),
              policyList.get(i + 3),
              Integer.toString((int) (Double.parseDouble(policyList.get(i + 3)) * 0.8)));
        } else if (policyList.get(i).equals("C")) {
          MessageCli.PRINT_DB_CAR_POLICY.printMessage(
              policyList.get(i + 1),
              policyList.get(i + 2),
              policyList.get(i + 3),
              Integer.toString((int) (Double.parseDouble(policyList.get(i + 3)) * 0.8)));
        } else if (policyList.get(i).equals("L")) {
          MessageCli.PRINT_DB_LIFE_POLICY.printMessage(
              policyList.get(i + 1),
              policyList.get(i + 2),
              Integer.toString((int) (Double.parseDouble(policyList.get(i + 2)) * 0.8)));
        }
      }
    } else if (policyCount.get(policyIndex) == 0) {

    }
  }

  public int calculatePremium(int policyIndex) {
    ArrayList<String> policyList = userPolicyList.get(policyIndex).getPolicyList();
    int numberOfElements = policyList.size();
    int premium = 0;

    if (policyCount.get(policyIndex) == 1) {
      for (int i = 0; i < numberOfElements; i++) {
        if (policyList.get(i).equals("H")) {
          premium += Integer.parseInt(policyList.get(i + 3));
        } else if (policyList.get(i).equals("C")) {
          premium += Integer.parseInt(policyList.get(i + 3));
        } else if (policyList.get(i).equals("L")) {
          premium += Integer.parseInt(policyList.get(i + 2));
        }
      }
    } else if (policyCount.get(policyIndex) == 2) {
      for (int i = 0; i < numberOfElements; i++) {
        if (policyList.get(i).equals("H")) {
          premium += (int) (Double.parseDouble(policyList.get(i + 3)) * 0.9);
        } else if (policyList.get(i).equals("C")) {
          premium += (int) (Double.parseDouble(policyList.get(i + 3)) * 0.9);
        } else if (policyList.get(i).equals("L")) {
          premium += (int) (Double.parseDouble(policyList.get(i + 2)) * 0.9);
        }
      }

    } else if (policyCount.get(policyIndex) > 2) {
      for (int i = 0; i < numberOfElements; i++) {
        if (policyList.get(i).equals("H")) {
          premium += (int) (Double.parseDouble(policyList.get(i + 3)) * 0.8);
        } else if (policyList.get(i).equals("C")) {
          premium += (int) (Double.parseDouble(policyList.get(i + 3)) * 0.8);
        } else if (policyList.get(i).equals("L")) {
          premium += (int) (Double.parseDouble(policyList.get(i + 2)) * 0.8);
        }
      }
    } else if (policyCount.get(policyIndex) == 0) {

    }
    return premium;
  }

  public String policySuffix(int policyIndex) {
    String suffix = "";
    if (policyCount.get(policyIndex) == 1) {
      suffix = "y";
    } else {
      suffix = "ies";
    }
    return suffix;
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
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "*** ",
            "1",
            titleCase(userNameList.get(0)),
            ageList.get(0),
            Integer.toString(policyCount.get(0)),
            policySuffix(0),
            Integer.toString(calculatePremium(0)));

        // System.out.println("*** " + "1: " + titleCase(userNameList.get(0)) + ", " +
        // ageList.get(0));
        printPolicy(0);
      } else {
        MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
            "",
            "1",
            titleCase(userNameList.get(0)),
            ageList.get(0),
            Integer.toString(policyCount.get(0)),
            policySuffix(0),
            Integer.toString(calculatePremium(0)));
        printPolicy(0);
      }
    } else {
      MessageCli.PRINT_DB_POLICY_COUNT.printMessage(String.valueOf(numberOfProfiles), "s", ":");
      for (int i = 0; i < userNameList.size(); i++) {

        if (profileLoadStatus.get(i) == true) {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "*** ",
              Integer.toString(i + 1),
              titleCase(userNameList.get(i)),
              ageList.get(i),
              Integer.toString(policyCount.get(i)),
              policySuffix(i),
              Integer.toString(calculatePremium(i)));
          printPolicy(i);
        } else {
          MessageCli.PRINT_DB_PROFILE_HEADER_LONG.printMessage(
              "",
              Integer.toString(i + 1),
              titleCase(userNameList.get(i)),
              ageList.get(i),
              Integer.toString(policyCount.get(i)),
              policySuffix(i),
              Integer.toString(calculatePremium(i)));
          printPolicy(i);
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

  public boolean checkDuplicateUserName(String userName) {
    // Use loop to check through userNameList to see if the userName already exists
    for (int i = 0; i < userNameList.size(); i++) {
      if (userNameList.get(i).equals(titleCase(userName))) {
        return true;
      }
    }
    return false;
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
        } else if (checkDuplicateUserName(userName) == true) {
          MessageCli.INVALID_USERNAME_NOT_UNIQUE.printMessage(titleCase(userName));
        } else if ((Integer.parseInt(age) < 0)) {
          MessageCli.INVALID_AGE.printMessage(age, userName);
        } else {
          // Only adding username and age to array if they are valid.
          userNameList.add(titleCase(userName));
          ageList.add(age);
          MessageCli.PROFILE_CREATED.printMessage(titleCase(userName), age);
          profileLoadStatus.add(false);
          policyCount.add(0);
          User newUser = new User();
          userPolicyList.add(newUser);
          // For each new userName added, create a corresponding arraylist of policy for them, using
          // the constructor from the User class in the User.java file

        }
      }
    } catch (NumberFormatException exception) {
      MessageCli.INVALID_AGE.printMessage(age, titleCase(userName));
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
      MessageCli.PROFILE_UNLOADED.printMessage(titleCase(currentLoadedUserName));
      currentLoadedUserName = "";
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
            policyCount.remove(i);
            userPolicyList.remove(i);
            // After deleting the profile, reset the profileLoadStatus arraylist with the new loaded
            // userName

            // Maybe use a helper method here, written in the User.java class to help with deleting
            // the different types of policies (Polictylist)

            // Or alternatively, we can just delete the policycount list, and remove the
            // corresponding
            // one completely.

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

  // Write a method here, to check the profileLoadStatus, and set the corrresponding true
  // value of it and the policyAmount arraylist, to iterate the amount of policies
  // created for each profile.

  // e.g if profileLoadStatus = [true, false, false, false, false] and policyAmount = [1,0,0,0,0]

  // We will be using these helper methods in the createPolicy method, for each policy type.

  public void iteratePolicyCount() {
    for (int i = 0; i < profileLoadStatus.size(); i++) {
      if (profileLoadStatus.get(i) == true) {
        policyCount.set(i, policyCount.get(i) + 1);
      }
    }
  }

  public void initialisePolicyCount() {
    for (int i = 0; i < userNameList.size(); i++) {
      policyCount.add(0);
    }
  }

  public int findIndexOf(String userNameToFindIndex) {
    for (int i = 0; i < userNameList.size(); i++) {
      if (userNameList.get(i).equals(titleCase(userNameToFindIndex))) {
        return i;
      }
    }
    return 0;
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

          // The bottom part will not work, because if the user deletes a profile, then
          // We need to find a way to delete the corresponding policy in the arraylist.
          userPolicyList.get(findIndexOf(currentLoadedUserName)).getPolicyList().add("H");
          userPolicyList
              .get(findIndexOf(currentLoadedUserName))
              .getPolicyList()
              .add(homeInsurance.getAddress());
          userPolicyList
              .get(findIndexOf(currentLoadedUserName))
              .getPolicyList()
              .add(Integer.toString(homeInsurance.getSumInsured()));
          homeInsurance.calculatePremium(toBoolean(options[2]));
          userPolicyList
              .get(findIndexOf(currentLoadedUserName))
              .getPolicyList()
              .add(Integer.toString(homeInsurance.getHomePremium()));

          iteratePolicyCount();

          // Regarding the discounted premium, this can only be done after we figure out a
          // way to check how many policies are created for each profile.

          // Maybe could do something like if (userPolicyAmount>2){ homePolicyList[i+3] * 0.8}

          MessageCli.NEW_POLICY_CREATED.printMessage(
              type.toString().toLowerCase(), titleCase(currentLoadedUserName));

          /* System.out.println(
              userPolicyList.get(findIndexOf(currentLoadedUserName)).getPolicyList().toString());
          System.out.println(policyCount);
          System.out.println(profileLoadStatus);
          System.out.println(userNameList);
          System.out.println(ageList); */

          break;

        case CAR:
          CarPolicy carInsurance =
              new CarPolicy(
                  Integer.parseInt(options[0]), options[1], options[2], toBoolean(options[3]));
          // Using the same method as the home policy, but with the corresponding getters of the car
          // policy.
          userPolicyList.get(findIndexOf(currentLoadedUserName)).getPolicyList().add("C");
          userPolicyList
              .get(findIndexOf(currentLoadedUserName))
              .getPolicyList()
              .add(carInsurance.getMakeAndModel());
          userPolicyList
              .get(findIndexOf(currentLoadedUserName))
              .getPolicyList()
              .add(Integer.toString(carInsurance.getSumInsured()));
          carInsurance.calculatePremium(
              Integer.parseInt(ageList.get(findIndexOf(currentLoadedUserName))),
              toBoolean(options[3]));
          userPolicyList
              .get(findIndexOf(currentLoadedUserName))
              .getPolicyList()
              .add(Integer.toString(carInsurance.getCarPremium()));

          iteratePolicyCount();

          MessageCli.NEW_POLICY_CREATED.printMessage(
              type.toString().toLowerCase(), titleCase(currentLoadedUserName));

          /* System.out.println(
              userPolicyList.get(findIndexOf(currentLoadedUserName)).getPolicyList().toString());

          System.out.println(policyCount);
          System.out.println(profileLoadStatus);
          System.out.println(userNameList);
          System.out.println(ageList); */

          break;

        case LIFE:

          // If the user already has a life policy, then print message that the user already has a
          // life policy.

          if (userPolicyList
              .get(findIndexOf(currentLoadedUserName))
              .getPolicyList()
              .contains("L")) {
            MessageCli.ALREADY_HAS_LIFE_POLICY.printMessage(titleCase(currentLoadedUserName));
            break;
          } else if (Integer.parseInt(ageList.get(findIndexOf(currentLoadedUserName))) > 100) {
            // print message userName is over the age limit. No policy was created
            MessageCli.OVER_AGE_LIMIT_LIFE_POLICY.printMessage(titleCase(currentLoadedUserName));
            break;
          } else {
            LifePolicy lifeInsurance = new LifePolicy(Integer.parseInt(options[0]));

            userPolicyList.get(findIndexOf(currentLoadedUserName)).getPolicyList().add("L");
            userPolicyList
                .get(findIndexOf(currentLoadedUserName))
                .getPolicyList()
                .add(Integer.toString(lifeInsurance.getSumInsured()));

            lifeInsurance.calculatePremium(
                Integer.parseInt(ageList.get(findIndexOf(currentLoadedUserName))));

            userPolicyList
                .get(findIndexOf(currentLoadedUserName))
                .getPolicyList()
                .add(Integer.toString(lifeInsurance.getLifePremium()));

            iteratePolicyCount();

            MessageCli.NEW_POLICY_CREATED.printMessage(
                type.toString().toLowerCase(), titleCase(currentLoadedUserName));

            /* System.out.println(
                userPolicyList.get(findIndexOf(currentLoadedUserName)).getPolicyList().toString());

            System.out.println(policyCount);
            System.out.println(profileLoadStatus);
            System.out.println(userNameList);
            System.out.println(ageList); */

            break;
          }
      }
      // TODO: Complete this method.
    }
  }
}
