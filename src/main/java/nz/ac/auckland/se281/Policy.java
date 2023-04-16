package nz.ac.auckland.se281;

public abstract class Policy extends Object {

  protected int sumInsured;
  protected int insurancePremium;

  public Policy(int sumInsured) {
    this.sumInsured = sumInsured;
  }
}
