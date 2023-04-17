package nz.ac.auckland.se281;

public class LifePolicy extends Policy {

  private int lifePremium;

  public LifePolicy(int sumInsured) {
    super(sumInsured);
  }

  public void calculatePremium(int age) {
    lifePremium = (int) ((double) sumInsured * 0.01 * (1 + ((double) age / 100)));
  }

  public int getSumInsured() {
    return sumInsured;
  }

  public int getLifePremium() {
    return lifePremium;
  }
}
