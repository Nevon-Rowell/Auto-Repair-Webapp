package AutoReparShop.webapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "SparePart")
public class SparePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "partID")
    private int PartID;

    @Column(name = "partName")
    private String PartName;

    @Column(name = "category")
    private String Category;

    @Column(name = "sellingPrice")
    private double SellingPrice;

    @Column(name = "costPrice")
    private double CostPrice;

    @Column(name = "stockQuantity")
    private int StockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplierID", nullable = false)
    private Supplier supplier;

    public SparePart() {}

    public SparePart(int partID, String partName, String category,
                     double costPrice, double sellingPrice, int stockQty, Supplier supplier) {
        this.PartID = partID;
        this.PartName = partName;
        this.Category = category;
        this.CostPrice = costPrice;
        this.SellingPrice = sellingPrice;
        this.StockQuantity = stockQty;
        this.supplier = supplier;
    }

    public int getPartID() { return PartID; }
    public void setPartID(int partID) { this.PartID = partID; }

    public String getPartName() { return PartName; }
    public void setPartName(String partName) { this.PartName = partName; }

    public String getCategory() { return Category; }
    public void setCategory(String category) { this.Category = category; }

    public double getCostPrice() { return CostPrice; }
    public void setCostPrice(double costPrice) { this.CostPrice = costPrice; }

    public double getSellingPrice() { return SellingPrice; }
    public void setSellingPrice(double sellingPrice) { this.SellingPrice = sellingPrice; }

    public int getStockQuantity() { return StockQuantity; }
    public void setStockQuantity(int stockQty) { this.StockQuantity = stockQty; }

    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
}