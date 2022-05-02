package sk.stuba.fei.uim.vsa.pr1c.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CAR")
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String vehicleRegistrationPlate;

    private String brand;
    private String model;
    private String colour;

    @ManyToOne
    private User owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleRegistrationPlate() {
        return vehicleRegistrationPlate;
    }

    public void setVehicleRegistrationPlate(String vehicleRegistrationPlate) {
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

//    @Override
//    public String toString() {
//        return "Car{" +
//                "id=" + id +
//                ", vehicleRegistrationPlate='" + vehicleRegistrationPlate + '\'' +
//                ", brand='" + brand + '\'' +
//                ", model='" + model + '\'' +
//                ", colour='" + colour + '\'' +
//                ", owner=" + owner +
//                "}\n";
//    }
}
