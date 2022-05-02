package sk.stuba.fei.uim.vsa.pr1c.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PARKING_SPOT")
public class ParkingSpot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private String spotIdentifier;

    private boolean isOccupied;

    @ManyToOne
    private CarParkFloor carParkFloor;

//    private String floorIdentifier;
//    private Long carParkId;

    public Long getId() {
        return id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotIdentifier() {
        return spotIdentifier;
    }

    public void setSpotIdentifier(String spotIdentifier) {
        this.spotIdentifier = spotIdentifier;
    }

    public CarParkFloor getCarParkFloor() {
        return carParkFloor;
    }

    public void setCarParkFloor(CarParkFloor carParkFloor) {
        this.carParkFloor = carParkFloor;
    }

//    @Override
//    public String toString() {
//        return "ParkingSpot{" +
//                "id=" + id +
//                ", spotIdentifier='" + spotIdentifier + '\'' +
//                ", isOccupied=" + isOccupied +
//                ", carParkFloor=" + carParkFloor +
//                "}\n";
//    }
}
