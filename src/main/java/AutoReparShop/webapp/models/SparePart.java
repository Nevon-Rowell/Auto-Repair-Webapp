package AutoReparShop.webapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "SparePart")
public class SparePart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partID")
    private Integer partID;

    @Column(name = "partName")
    private String partName;

    @JsonProperty("category")
    private String category;

    @Column(name = "sellingPrice")
    private Double sellingPrice;

    @Column(name = "costPrice")
    private Double costPrice;

    @JsonProperty("stockQty")
    @Column(name = "stockQuantity")
    private Integer stockQuantity;

    @Column(name = "isActive")
    private Boolean isActive = true;

    public SparePart() {}

    public SparePart(Integer partID, String partName, String category,
                     Double costPrice, Double sellingPrice, Integer stockQuantity) {
        this.partID = partID;
        this.partName = partName;
        this.category = category;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.stockQuantity = stockQuantity;
    }

    public Integer getPartID() { return partID; }
    public void setPartID(Integer partID) { this.partID = partID; }

    public String getPartName() { return partName; }
    public void setPartName(String partName) { this.partName = partName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getCostPrice() { return costPrice; }
    public void setCostPrice(Double costPrice) { this.costPrice = costPrice; }

    public Double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(Double sellingPrice) { this.sellingPrice = sellingPrice; }

    public Integer getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(Integer stockQuantity) { this.stockQuantity = stockQuantity; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}