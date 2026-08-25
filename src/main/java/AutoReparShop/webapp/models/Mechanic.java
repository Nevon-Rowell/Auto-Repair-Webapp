package AutoReparShop.webapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Mechanic{
    @Id
    private int mechID;
    private String name;
    private int contact;
    private int salary;

}