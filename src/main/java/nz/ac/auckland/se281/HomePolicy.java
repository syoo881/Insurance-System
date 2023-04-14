package nz.ac.auckland.se281;

public class HomePolicy extends Policy {

  private String address;
  private boolean rental;
  private int homePremium;

  public HomePolicy(int sumInsured, String address, boolean rental) {
    super(sumInsured);
    this.address = address;
    // TODO Auto-generated constructor stub
  }

  @Override
  public void calculatePremium() {
    if (rental = true) {
      homePremium = (int) (sumInsured * 0.02);
    } else {
      homePremium = (int) (sumInsured * 0.01);
    }
  }

  public int getSumInsured() {
    return sumInsured;
  }

  public String getAddress() {
    return address;
  }

  public boolean isRental() {
    return rental;
  }

  public int getHomePremium() {
    return homePremium;
  }
}
