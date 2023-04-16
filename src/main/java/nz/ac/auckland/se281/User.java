package nz.ac.auckland.se281;

import java.util.ArrayList;

public class User {
    //create a policyList field
    private ArrayList<String> policyList;

    public User() {
        this.policyList = new ArrayList<String>();

    }


    public ArrayList<String> getPolicyList() {
        return policyList;
    }
}


