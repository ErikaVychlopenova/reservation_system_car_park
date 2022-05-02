package sk.stuba.fei.uim.vsa.pr1c.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "CAR_PARK")
public class CarPark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String carParkName;
    private String address;
    private Integer pricePerHour;

    @OneToMany(mappedBy = "carParkId", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CarParkFloor> carParkFloorList;

    public Long getCarParkId() {
        return id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarParkName() {
        return carParkName;
    }

    public void setCarParkName(String carParkName) {
        this.carParkName = carParkName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Integer pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public List<CarParkFloor> getCarParkFloorList() {
        return carParkFloorList;
    }

    public void setCarParkFloorList(CarParkFloor carParkFloor) {
        this.carParkFloorList.add(carParkFloor);
    }

//    @Override
//    public String toString() {
//        return "CarPark{" +
//                "id=" + id +
//                ", carParkName='" + carParkName + '\'' +
//                ", address='" + address + '\'' +
//                ", pricePerHour=" + pricePerHour +
//                ", carParkFloorList=" + carParkFloorList +
//                "}\n";
//    }
}
