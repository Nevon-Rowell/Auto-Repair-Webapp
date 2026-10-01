package AutoReparShop.webapp.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Supplier")

public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "SupplierID")
    private int supplierID;

    @Column(name = "SupplierName")
    private String supplierName;

    @Column(name = "ContactNo")
    private String contactNumber;

    @Column(name = "Email")
    private String email;

    public Supplier() {
    }
    public Supplier(int id, String name, String pno, String email) {
        this.supplierID = id;
        this.supplierName = name;
        this.contactNumber = pno;
        this.email = email;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String address) {
        this.email = address;
    }
}