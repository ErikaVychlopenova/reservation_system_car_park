package sk.stuba.fei.uim.vsa.pr1c.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CAR_PARK_FLOOR")
public class CarParkFloor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable=false)
    private String floorIdentifier;

    @ManyToOne
    private CarPark carParkId;

    @OneToMany(mappedBy = "carParkFloor", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ParkingSpot> parkingSpotList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloorIdentifier() {
        return floorIdentifier;
    }

    public void setFloorIdentifier(String floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }


    public CarPark getCarParkId() {
        return carParkId;
    }

    public void setCarParkId(CarPark carParkId) {
        this.carParkId = carParkId;
    }

    public List<ParkingSpot> getParkingSpotList() {
        return parkingSpotList;
    }

    public void setParkingSpotList(ParkingSpot parkingSpot) {
        this.parkingSpotList.add(parkingSpot);
    }

//    @Override
//    public String toString() {
//        return "CarParkFloor{" +
//                "id=" + id +
//                ", floorIdentifier='" + floorIdentifier + '\'' +
//                ", carParkId=" + carParkId +
//                ", parkingSpotList=" + parkingSpotList +
//                "}\n";
//    }
}
