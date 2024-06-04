package com.jyujyu.springtest;

public class MyCalculatorApplication {
  public static void main(String[] args) {
    MyCalculator myCalculator = new MyCalculator();
    myCalculator.add(10.0).sub(2.0).mul(2.0).div(4.0);

    myCalculator.div(2.0);
    System.out.println("result: " + myCalculator.getResult());
  }
}
