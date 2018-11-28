package com.designpatterns;

import java.util.ArrayList;
import java.util.List;

public class Factory {
  public interface BaseProduct {
    int getPrice();
  }
  public static class ProductOne implements BaseProduct{
    @Override
    public int getPrice() {
      return 10;
    }
  }
  public static class ProductTwo implements BaseProduct{
    @Override
    public int getPrice() {
      return 20;
    }
  }
  public static abstract class AbstractProductFactory {
    public List<BaseProduct> getNProducts(int n){
      List<BaseProduct> products = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        products.add(createProduct());
      }
      return products;
    }
    abstract BaseProduct createProduct();
  }
  
  public static class ProductOneFactory extends AbstractProductFactory{
    @Override
    BaseProduct createProduct() {
      return new ProductOne();
    }
  }
  
  public static class ProductTwoFactory extends AbstractProductFactory {
    @Override
    BaseProduct createProduct() {
      return new ProductTwo();
    }
  }

  public static void main(String[] args) {
    AbstractProductFactory productOneFactory = new ProductOneFactory();
    AbstractProductFactory productTwoFactory = new ProductTwoFactory();
    
    BaseProduct productOne = productOneFactory.createProduct();
    BaseProduct productTwo = productTwoFactory.createProduct();

    System.out.println("Product one price is : "+productOne.getPrice());
    System.out.println("Product two price is : "+productTwo.getPrice());
  }
}
