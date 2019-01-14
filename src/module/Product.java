/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module;

/**
 *
 * @author Administrator
 */
public class Product {
    
    int     productID;
    String  productDesc;
    float   productUnitPrice;
    int     productUnitsInStock;

    public Product(int productID, String productDesc, float productUnitPrice, int productUnitsInStock) {
        this.productID = productID;
        this.productDesc = productDesc;
        this.productUnitPrice = productUnitPrice;
        this.productUnitsInStock = productUnitsInStock;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public float getProductUnitPrice() {
        return productUnitPrice;
    }

    public void setProductUnitPrice(float productUnitPrice) {
        this.productUnitPrice = productUnitPrice;
    }

    public int getProductUnitsInStock() {
        return productUnitsInStock;
    }

    public void setProductUnitsInStock(int productUnitsInStock) {
        this.productUnitsInStock = productUnitsInStock;
    }
    
    
    
}
