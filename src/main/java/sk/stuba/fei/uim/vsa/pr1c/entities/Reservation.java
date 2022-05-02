package sk.stuba.fei.uim.vsa.pr1c.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "RESERVATION")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private ParkingSpot parkingSpot;
    @OneToOne
    private Car car;

    private Date startDate;
    private Date endDate;
    private Double price;
    public Long getId() {
        return id;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    @Override
//    public String toString() {
//        return "Reservation{" +
//                "id=" + id +
//                ", parkingSpot=" + parkingSpot +
//                ", car=" + car +
//                ", startDate=" + startDate +
//                ", endDate=" + endDate +
//                "}\n";
//    }
}
