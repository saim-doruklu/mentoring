package com.designpatterns;

import java.util.Arrays;

public class Singleton {
  
  private static Singleton singleton=null;

  private Object[] args = new Object[0];

  private Singleton(Object[] args){
    this.args = args;
  }

  public String toString() {
    return this.hashCode() + " with args : " + Arrays.toString(args);
  }
  
  public static Singleton getInstance(Object... args){
    if(singleton == null) {
      synchronized (Singleton.class) {
        if(singleton == null) {
          singleton = new Singleton(args);
        }
      }
    }
    return singleton;
  }
  
  public static void main(String[] args) {
    for (int i = 0; i < 8; i++) {
      Singleton singleton = Singleton.getInstance("Arg 1","Arg 2", "Arg 3");
      System.out.println("Instance is "+singleton.toString());
    }
  }
}
