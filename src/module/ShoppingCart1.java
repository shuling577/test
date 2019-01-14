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
public class ShoppingCart1 {
    
    int productID;
    String  productDesc;
    float   productUnitPrice;
    int numberUnitsOrdered;

    public ShoppingCart1(int productID, String productDesc, float productUnitPrice, int numberUnitsOrdered) {
        this.productID = productID;
        this.productDesc = productDesc;
        this.productUnitPrice = productUnitPrice;
        this.numberUnitsOrdered = numberUnitsOrdered;
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

    public int getNumberUnitsOrdered() {
        return numberUnitsOrdered;
    }

    public void setNumberUnitsOrdered(int numberUnitsOrdered) {
        this.numberUnitsOrdered = numberUnitsOrdered;
    }
    
}
