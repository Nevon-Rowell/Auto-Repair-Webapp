package AutoReparShop.webapp.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "SparePart")
public class SparePart {
    @Id
    private int PartID;
    private String PartName;
    private String Category;
    private String Description;
    private double CostPrice;
    private double SellingPrice;
    private int StockQuantity;
    private int SupplierID;

    public SparePart() {}

    public SparePart(int partID, String partName, String category, String description,
                     double costPrice, double sellingPrice, int stockQty, int supplierID) {
        this.PartID = partID;
        this.PartName = partName;
        this.Category = category;
        this.Description = description;
        this.CostPrice = costPrice;
        this.SellingPrice = sellingPrice;
        this.StockQuantity = stockQty;
        this.SupplierID = supplierID;
    }

    public int getPartID() { return PartID; }
    public void setPartID(int partID) { this.PartID = partID; }

    public String getPartName() { return PartName; }
    public void setPartName(String partName) { this.PartName = partName; }

    public String getCategory() { return Category; }
    public void setCategory(String category) { this.Category = category; }

    public String getDescription() { return Description; }
    public void setDescription(String description) { this.Description = description; }

    public double getCostPrice() { return CostPrice; }
    public void setCostPrice(double costPrice) { this.CostPrice = costPrice; }

    public double getSellingPrice() { return SellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.SellingPrice = sellingPrice; }

    public int getStockQuantity() { return StockQuantity; }
    public void setStockQuantity(int stockQty) { this.StockQuantity = stockQty; }

    public int getSupplierID() { return SupplierID; }
    public void setSupplierID(int supplierID) { this.SupplierID = supplierID; }
}