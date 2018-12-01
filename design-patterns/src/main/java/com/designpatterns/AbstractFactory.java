package com.designpatterns;

import java.util.ArrayList;
import java.util.List;

public class AbstractFactory {
  public interface Product {
    int getPrice();
    default String print() {
      return "Product price is "+this.getPrice();
    }
  }
  public interface ProductWithDiscount extends Product{
    int getDiscountedPrice();
    @Override
    default String print() {
      return "Product with discount price is "+this.getPrice()+", discounted price is "+getDiscountedPrice();
    }
  }
  public static class ProductOne implements Product {
    @Override
    public int getPrice() {
      return 100;
    }
  }
  public static class ProductTwo implements Product {
    @Override
    public int getPrice() {
      return 200;
    }
  }
  public static class ProductWithDiscountOne implements ProductWithDiscount {
    @Override
    public int getPrice() {
      return 100;
    }

    @Override
    public int getDiscountedPrice() {
      return 80;
    }
  }
  public static class ProductWithDiscountTwo implements ProductWithDiscount{
    @Override
    public int getPrice() {
      return 200;
    }

    @Override
    public int getDiscountedPrice() {
      return 160;
    }
  }
  public static abstract class AbstractProductFactory {
    public List<Product> createNProducts(int n) {
      List<Product> products = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        products.add(getProduct());
      }
      return products;
    } 
    public List<ProductWithDiscount> createNProductsWithDiscount(int n) {
      List<ProductWithDiscount> productWithDiscount = new ArrayList<>();
      for (int i = 0; i < n; i++) {
        productWithDiscount.add(getProductWithDiscount());
      }
      return productWithDiscount;
    }
    abstract Product getProduct();
    abstract ProductWithDiscount getProductWithDiscount();
  }
  public static class FactoryOne extends AbstractProductFactory {
    @Override
    Product getProduct() {
      return new ProductOne();
    }

    @Override
    ProductWithDiscount getProductWithDiscount() {
      return new ProductWithDiscountOne();
    }
  }
  public static class FactoryTwo extends AbstractProductFactory {
    @Override
    Product getProduct() {
      return new ProductTwo();
    }

    @Override
    ProductWithDiscount getProductWithDiscount() {
      return new ProductWithDiscountTwo();
    }
  }

  public static void main(String[] args) {
    AbstractProductFactory factoryOne = new FactoryOne();
    AbstractProductFactory factoryTwo = new FactoryTwo();
    
    Product productOne = factoryOne.getProduct();
    System.out.println("Product one : " + productOne.print());
    ProductWithDiscount productWithDiscountOne = factoryOne.getProductWithDiscount();
    System.out.println("Product with discount one : " + productWithDiscountOne.print());
    
    Product productTwo = factoryTwo.getProduct();
    System.out.println("Product two : " + productTwo.print());
    ProductWithDiscount productWithDiscountTwo = factoryTwo.getProductWithDiscount();
    System.out.println("Product with discount two : " + productWithDiscountTwo.print());
  }
}
