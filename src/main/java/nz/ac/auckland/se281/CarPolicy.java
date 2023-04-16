package nz.ac.auckland.se281;

public class CarPolicy extends Policy {

  private String makeAndModel;
  private String licensePlate;
  private boolean mechanicalBreakdown;
  private int carPremium;

  public CarPolicy(
      int sumInsured, String makeAndModel, String licensePlate, boolean mechanicalBreakdown) {
    super(sumInsured);
    this.makeAndModel = makeAndModel;
    this.licensePlate = licensePlate;
    this.mechanicalBreakdown = mechanicalBreakdown;
  }

  public void calculatePremium(int age, boolean breakdownStatus) {
    // check if breakDownStatus is true
    if (breakdownStatus == true) {
      // check if age is less than 25
      if (age < 25) {
        carPremium = (int) ((double) sumInsured * 0.15 + 80);
      } else {
        carPremium = (int) ((double) sumInsured * 0.1 + 80);
      }
    } else {
      // check if age is less than 25
      if (age < 25) {
        carPremium = (int) ((double) sumInsured * 0.15);
      } else {
        carPremium = (int) ((double) sumInsured * 0.1);
      }
    }
  }

  public int getSumInsured() {
    return sumInsured;
  }

  public String getMakeAndModel() {
    return makeAndModel;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public boolean isMechanicalBreakdown() {
    return mechanicalBreakdown;
  }

  public int getCarPremium() {
    return carPremium;
  }
}
