package sk.stuba.fei.uim.vsa.pr1c;

import sk.stuba.fei.uim.vsa.pr1c.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.*;


public class CarParkService extends AbstractCarParkService{
    @Override
    public Object createCarPark(String name, String address, Integer pricePerHour) {
        try {
            EntityManager em = emf.createEntityManager();
            if (name != null && address != null && pricePerHour != null) {
                // find CarPark with that name
                TypedQuery q = em.createQuery("SELECT cp FROM CarPark cp WHERE cp.carParkName = :name", Object.class).setParameter("name", name);
                if(q.getResultList().isEmpty()) {
                    // create new CarPark and set attributes
                    CarPark carPark = new CarPark();
                    carPark.setCarParkName(name);
                    carPark.setAddress(address);
                    carPark.setPricePerHour(pricePerHour);
                    // persist (upload) CarPark to database
                    em.getTransaction().begin();
                    em.persist(carPark);
                    em.getTransaction().commit();
                    em.close();
                    // return the new CarPark
                    return carPark;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getCarPark(Long carParkId) {
        try {
            if(carParkId != null) {
                EntityManager em = emf.createEntityManager();
                return em.find(CarPark.class, carParkId);
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getCarPark(String carParkName) {
        try {
            EntityManager em = emf.createEntityManager();
            if(carParkName != null) {
                TypedQuery<Object> q = em.createQuery("SELECT cp FROM CarPark cp WHERE cp.carParkName = :carParkName", Object.class).setParameter("carParkName", carParkName);
                if(q.getResultList().isEmpty()){
                    return null;
                }
                return q.getSingleResult();
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getCarParks() {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Object> q = em.createQuery("SELECT cp FROM CarPark cp", Object.class);
            return q.getResultList();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    @Override
    public Object updateCarPark(Object carPark) {
        try {
            if(carPark != null) {
                EntityManager em = emf.createEntityManager();
                CarPark oldCarPark = em.find(CarPark.class, ((CarPark) carPark).getCarParkId());
                if (oldCarPark.getCarParkId() != null) {
                    em.getTransaction().begin();
                    em.merge(carPark);
                    em.getTransaction().commit();
                    em.close();
                    return carPark;
                }
            }
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object deleteCarPark(Long carParkId) {
        try {
            if(carParkId != null) {
                EntityManager em = emf.createEntityManager();
                CarPark carPark = em.find(CarPark.class, carParkId);
                if(carPark != null) {
                    em.getTransaction().begin();
                    TypedQuery<Reservation> q = em.createQuery("SELECT r FROM Reservation r WHERE r.parkingSpot.carParkFloor.carParkId.id= :id", Reservation.class).setParameter("id", carParkId);
                    setReservationToNull(q, em);
                    em.remove(carPark);
                    em.getTransaction().commit();
                    em.close();
                    return carPark;
                }
            }
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object createCarParkFloor(Long carParkId, String floorIdentifier) {
        try {
            if(carParkId != null && floorIdentifier != null) {
                EntityManager em = emf.createEntityManager();
                CarPark carPark = em.find(CarPark.class, carParkId);
                if (carPark != null) {
                    List<Object> list = getCarParkFloors(carParkId);
                    for (Object object : list) {
                        if (((CarParkFloor) object).getFloorIdentifier().equals(floorIdentifier)) {
                            return null;
                        }
                    }
                    CarParkFloor carParkFloor = new CarParkFloor();
                    carParkFloor.setCarParkId(carPark);
                    carPark.setCarParkFloorList(carParkFloor);
                    carParkFloor.setFloorIdentifier(floorIdentifier);
                    em.getTransaction().begin();
                    em.persist(carParkFloor);
                    em.getTransaction().commit();
                    em.close();
                    return carParkFloor;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getCarParkFloor(Long carParkFloorId) {
        try {
            if(carParkFloorId != null) {
                EntityManager em = emf.createEntityManager();
                return em.find(CarParkFloor.class, carParkFloorId);
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getCarParkFloors(Long carParkId) {
        try {
            if(carParkId != null) {
                EntityManager em = emf.createEntityManager();
                TypedQuery<Object> q = em.createQuery("SELECT cpf FROM CarParkFloor cpf WHERE cpf.carParkId.id = :carParkId", Object.class);
                q.setParameter("carParkId", carParkId);
                return q.getResultList();
            }
            return new ArrayList<>();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }



    @Override
    public Object updateCarParkFloor(Object carParkFloor) {
        try {
            if(carParkFloor != null) {
                EntityManager em = emf.createEntityManager();
                CarParkFloor oldCarParkFloor = em.find(CarParkFloor.class, ((CarParkFloor) carParkFloor).getId());
                if (oldCarParkFloor != null) {
                    em.getTransaction().begin();
                    em.merge(carParkFloor);
                    em.getTransaction().commit();
                    em.close();
                    return carParkFloor;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Object deleteCarParkFloor(Long carParkFloorId) {
        try {
            if(carParkFloorId != null) {
                EntityManager em = emf.createEntityManager();
                CarParkFloor carParkFloor = em.find(CarParkFloor.class, carParkFloorId);
                if(carParkFloor != null) {
                    em.getTransaction().begin();
                    TypedQuery<Reservation> q = em.createQuery("SELECT r FROM Reservation r WHERE r.parkingSpot.carParkFloor.id = :id", Reservation.class).setParameter("id", carParkFloorId);
                    setReservationToNull(q, em);
                    em.remove(carParkFloor);
                    em.getTransaction().commit();
                    em.close();
                    return carParkFloor;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void setReservationToNull(TypedQuery<Reservation> q, EntityManager em) {
        List<Reservation> reservationList = q.getResultList();
        for(Reservation reservation : reservationList){
            reservation.setParkingSpot(null);
            em.merge(reservation);
        }
    }

    @Override
    public Object createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier) {
        try {
            if (carParkId != null && floorIdentifier != null && spotIdentifier != null) {
                EntityManager em = emf.createEntityManager();
                CarPark carPark = em.find(CarPark.class, carParkId);
                if (carPark != null) {
                    List<Object> floors = getCarParkFloors(carParkId);
                    for (Object object : floors) {
                        List<ParkingSpot> spots = ((CarParkFloor) object).getParkingSpotList();
                        for (ParkingSpot spot : spots) {
                            if (spot.getSpotIdentifier().equals(spotIdentifier)) {
                                return null;
                            }
                        }
                    }
                    ParkingSpot parkingSpot = new ParkingSpot();
                    TypedQuery<Object> q = em.createQuery("SELECT cpf FROM CarParkFloor cpf WHERE cpf.floorIdentifier = :floorIdentifier and cpf.carParkId.id = :carParkId", Object.class);
                    q.setParameter("floorIdentifier", floorIdentifier);
                    q.setParameter("carParkId", carParkId);
                    if(q.getResultList().isEmpty()){
                        return null;
                    }
                    CarParkFloor floor = (CarParkFloor) q.getSingleResult();

                    parkingSpot.setSpotIdentifier(spotIdentifier);
                    parkingSpot.setCarParkFloor(floor);
                    floor.setParkingSpotList(parkingSpot);

//                    em.getTransaction().begin();
//                    em.persist(parkingSpot);
//                    em.persist(floor);
//                    em.getTransaction().commit();
//                    em.close();
                    return parkingSpot;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getParkingSpot(Long parkingSpotId) {
        try {
            if (parkingSpotId != null) {
                EntityManager em = emf.createEntityManager();
                return em.find(ParkingSpot.class, parkingSpotId);
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getParkingSpots(Long carParkId, String floorIdentifier) {
        try {
            if(carParkId != null && floorIdentifier != null) {
                EntityManager em = emf.createEntityManager();
                TypedQuery q = em.createQuery("SELECT ps FROM ParkingSpot ps WHERE ps.carParkFloor.carParkId.id = :carParkId AND ps.carParkFloor.floorIdentifier = :floorIdentifier", CarParkFloor.class);
                q.setParameter("carParkId", carParkId);
                q.setParameter("floorIdentifier", floorIdentifier);
                return (List<Object>) q.getResultList();
            }
            return new ArrayList<>();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Map<String, List<Object>> getParkingSpots(Long carParkId) {
        try {
            Map<String, List<Object>> map = new HashMap<>();
            if(carParkId != null) {
                EntityManager em = emf.createEntityManager();
                CarPark cp = em.find(CarPark.class, carParkId);
                if (cp != null) {
                    for (CarParkFloor cpf : cp.getCarParkFloorList()) {
                        map.put(cpf.getFloorIdentifier(), new ArrayList<>(cpf.getParkingSpotList()));
                    }
                }
            }
            return map;
        }
        catch (Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public Map<String, List<Object>> getAvailableParkingSpots(String carParkName) {
        try {
            Map<String, List<Object>> map = new HashMap<>();
            if(carParkName != null) {
                CarPark carPark = (CarPark) getCarPark(carParkName);
                if (carPark != null) {
                    List<CarParkFloor> carParkFloorList = carPark.getCarParkFloorList();
                    List<Object> availableParkingSpots = new ArrayList<>();
                    for (CarParkFloor objectCPF : carParkFloorList) {
                        for (ParkingSpot objectPS : objectCPF.getParkingSpotList()) {
                            if (!objectPS.isOccupied()) {
                                availableParkingSpots.add(objectPS);
                            }
                        }
                        map.put(objectCPF.getFloorIdentifier(), availableParkingSpots);
                        availableParkingSpots = new ArrayList<>();
                    }
                }
            }
            return map;
        }
        catch (Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public Map<String, List<Object>> getOccupiedParkingSpots(String carParkName) {
        try {
            Map<String, List<Object>> map = new HashMap<>();
            if(carParkName != null) {
                CarPark carPark = (CarPark) getCarPark(carParkName);
                if (carPark != null) {
                    List<CarParkFloor> carParkFloorList = carPark.getCarParkFloorList();
                    List<Object> availableParkingSpots = new ArrayList<>();
                    for (CarParkFloor objectCPF : carParkFloorList) {
                        for (ParkingSpot objectPS : objectCPF.getParkingSpotList()) {
                            if (objectPS.isOccupied()) {
                                availableParkingSpots.add(objectPS);
                            }
                        }
                        map.put(objectCPF.getFloorIdentifier(), availableParkingSpots);
                        availableParkingSpots = new ArrayList<>();
                    }
                }
            }
            return map;
        }
        catch (Exception e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    @Override
    public Object updateParkingSpot(Object parkingSpot) {
        try {
            if(parkingSpot != null) {
                EntityManager em = emf.createEntityManager();
                ParkingSpot ps = em.find(ParkingSpot.class, ((ParkingSpot) parkingSpot).getId());
                if (ps != null) {
                    em.getTransaction().begin();
                    em.merge(parkingSpot);
                    em.getTransaction().commit();
                    em.close();
                    return parkingSpot;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object deleteParkingSpot(Long parkingSpotId) {
        try {
            if (parkingSpotId != null) {
                EntityManager em = emf.createEntityManager();
                ParkingSpot parkingSpot = em.find(ParkingSpot.class, parkingSpotId);
                if(parkingSpot != null) {
                    em.getTransaction().begin();
                    TypedQuery<Reservation> q = em.createQuery("SELECT r FROM Reservation r WHERE r.parkingSpot.id = :id", Reservation.class).setParameter("id", parkingSpotId);
                    setReservationToNull(q, em);
                    em.remove(parkingSpot);
                    em.getTransaction().commit();
                    em.close();
                    return parkingSpot;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate) {
        try {
            if (userId != null && vehicleRegistrationPlate != null) {
                EntityManager em = emf.createEntityManager();
                User user = em.find(User.class, userId);
                if (user != null) {
                    TypedQuery<Car> q = em.createQuery("SELECT c FROM Car c WHERE c.vehicleRegistrationPlate = :plate", Car.class).setParameter("plate", vehicleRegistrationPlate);
                    if(q.getResultList().isEmpty()) {
                        Car car = new Car();
                        car.setOwner(user);
                        car.setBrand(brand);
                        car.setModel(model);
                        car.setColour(colour);
                        car.setVehicleRegistrationPlate(vehicleRegistrationPlate);
                        em.getTransaction().begin();
                        em.persist(car);
                        em.getTransaction().commit();
                        em.close();
                        return car;
                    }
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getCar(Long carId) {
        try {
            if (carId != null) {
                EntityManager em = emf.createEntityManager();
                return em.find(Car.class, carId);
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getCar(String vehicleRegistrationPlate) {
        try {
            if (vehicleRegistrationPlate != null) {
                EntityManager em = emf.createEntityManager();
                TypedQuery<Object> q = em.createQuery("SELECT car FROM Car car WHERE car.vehicleRegistrationPlate = :regPlate", Object.class);
                q.setParameter("regPlate", vehicleRegistrationPlate);
                if(q.getResultList().isEmpty()){
                    return null;
                }
                return q.getSingleResult();
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getCars(Long userId) {
        try {
            if (userId != null) {
                EntityManager em = emf.createEntityManager();
                TypedQuery<Object> q = em.createQuery("SELECT cars FROM Car cars WHERE cars.owner.id = :userId", Object.class).setParameter("userId", userId);
                return q.getResultList();
            }
            return new ArrayList<>();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Object updateCar(Object car) {
        try {
            EntityManager em = emf.createEntityManager();
            if (car != null) {
                Car oldCar = em.find(Car.class, ((Car) car).getId());
                if (oldCar != null) {
                    em.getTransaction().begin();
                    em.merge(car);
                    em.getTransaction().commit();
                    em.close();
                    return car;
                } else return null;
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object deleteCar(Long carId) {
        try {
            if (carId != null) {
                EntityManager em = emf.createEntityManager();
                Car car = em.find(Car.class, carId);
                if(car != null) {
                    em.getTransaction().begin();
                    TypedQuery<Reservation> q = em.createQuery("SELECT r FROM Reservation r WHERE r.car.id = :id", Reservation.class).setParameter("id", carId);
                    setReservationToNullWithCar(q, em);
                    em.remove(car);
                    em.getTransaction().commit();
                    em.close();
                    return car;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void setReservationToNullWithCar(TypedQuery<Reservation> q, EntityManager em) {
        List<Reservation> reservationList = q.getResultList();
        for(Reservation reservation : reservationList){
            endReservation(reservation.getId());
            reservation.setCar(null);
            em.merge(reservation);
        }
    }

    @Override
    public Object createUser(String firstname, String lastname, String email) {
        try {
            if (email != null) {
                EntityManager em = emf.createEntityManager();
                TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class).setParameter("email", email);
                if(q.getResultList().isEmpty()) {
                    User user = new User();
                    user.setFirstname(firstname);
                    user.setLastname(lastname);
                    user.setEmail(email);
                    em.getTransaction().begin();
                    em.persist(user);
                    em.getTransaction().commit();
                    em.close();
                    return user;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getUser(Long userId) {
        try {
            if (userId != null) {
                EntityManager em = emf.createEntityManager();
                return em.find(User.class, userId);
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getUser(String email) {
        try {
            if (email != null) {
                EntityManager em = emf.createEntityManager();
                TypedQuery<Object> q = em.createQuery("SELECT user FROM User user WHERE user.email = :email", Object.class);
                q.setParameter("email", email);
                if(q.getResultList().isEmpty()){
                    return null;
                }
                return q.getSingleResult();
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getUsers() {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Object> q = em.createQuery("SELECT user FROM User user", Object.class);
            return q.getResultList();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Object updateUser(Object user) {
        try {
            EntityManager em = emf.createEntityManager();
            if (user != null) {
                User oldUser = em.find(User.class, ((User) user).getId());
                if (oldUser != null) {
                    em.getTransaction().begin();
                    em.merge(user);
                    em.getTransaction().commit();
                    em.close();
                    return user;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object deleteUser(Long userId) {
        try {
            if (userId != null) {
                EntityManager em = emf.createEntityManager();
                User user = em.find(User.class, userId);
                if(user != null) {
                    em.getTransaction().begin();
                    TypedQuery<Car> q = em.createQuery("SELECT c FROM Car c WHERE c.owner.id = :id", Car.class).setParameter("id", userId);
                    List<Car> carList = q.getResultList();
                    for(Car car : carList){
                        deleteCar(car.getId());
                    }
                    em.remove(user);
                    em.getTransaction().commit();
                    em.close();
                    return user;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public Object createReservation(Long parkingSpotId, Long cardId) {
        try {
            if (parkingSpotId != null && cardId != null) {
                EntityManager em = emf.createEntityManager();
                ParkingSpot ps = em.find(ParkingSpot.class, parkingSpotId);
                Car car = em.find(Car.class, cardId);
                TypedQuery<Reservation> q = em.createQuery("SELECT r FROM Reservation r WHERE r.car.id = :id AND r.endDate = null ", Reservation.class).setParameter("id", cardId);
                if(!q.getResultList().isEmpty()){
                    return null;
                }
                if (ps != null && car != null && !(ps.isOccupied())) {

                    Reservation reservation = new Reservation();
                    reservation.setStartDate(new Date());
                    reservation.setParkingSpot(ps);
                    reservation.setCar(car);
                    ps.setOccupied(true);

                    em.getTransaction().begin();
                    em.persist(reservation);
                    em.merge(ps);
                    em.getTransaction().commit();
                    em.close();

                    return reservation;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object endReservation(Long reservationId) {
        try {
            if (reservationId != null) {
                EntityManager em = emf.createEntityManager();
                Reservation reservation = em.find(Reservation.class, reservationId);
                if (reservation != null) {
                    reservation.setEndDate(new Date());

                    Date start = reservation.getStartDate();
                    Date end = reservation.getEndDate();

                    TypedQuery<Holiday> q = em.createQuery("SELECT h FROM Holiday h WHERE h.date BETWEEN :start AND :end ", Holiday.class);
                    q.setParameter("start", start);
                    q.setParameter("end", end);
                    List<Holiday> holidays = q.getResultList();
                    CarPark carPark = em.find(CarPark.class, reservation.getParkingSpot().getCarParkFloor().getCarParkId().getCarParkId());
                    Integer price = carPark.getPricePerHour();
                    Double discount = price * 0.25;
                    Double totalPrice = 0.0;

                    while(start.getTime() < end.getTime()){
                        totalPrice += price;
                        if(!holidays.isEmpty()){
                            for(Holiday holiday : holidays){
                                if (holiday.getDate().getDay() == start.getDay() && holiday.getDate().getMonth() == start.getMonth()){
                                    totalPrice -= discount;
                                }
                            }
                        }
                        start.setTime(start.getTime() + (60*60*1000));
                    }

                    reservation.setPrice(totalPrice);
                    ParkingSpot parkingSpot = em.find(ParkingSpot.class, reservation.getParkingSpot().getId());
                    parkingSpot.setOccupied(false);
                    em.getTransaction().begin();
                    em.merge(reservation);
                    em.merge(parkingSpot);
                    em.getTransaction().commit();
                    em.close();
                    return reservation;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getReservations(Long parkingSpotId, Date date) {
        try {
            if (parkingSpotId != null && date != null) {
                EntityManager em = emf.createEntityManager();
                TypedQuery q = em.createQuery("SELECT r FROM Reservation r WHERE r.parkingSpot.id = :id", Object.class).setParameter("id", parkingSpotId);
                List<Reservation> reservations = q.getResultList();
                List<Object> printList = new ArrayList<>();
                for (Reservation object : reservations) {
                    if (object.getStartDate().getDay() == date.getDay() && object.getStartDate().getMonth() == date.getMonth() && object.getStartDate().getYear() == date.getYear()) {
                        printList.add(object);
                    }
                }
                return printList;
            }
            return new ArrayList<>();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public List<Object> getMyReservations(Long userId) {
        try {
            if (userId != null) {
                EntityManager em = emf.createEntityManager();
                TypedQuery<Object> q = em.createQuery("SELECT r FROM Reservation r WHERE r.car.owner.id = :id AND r.endDate = null ", Object.class).setParameter("id", userId);
                return q.getResultList();
            }
            return new ArrayList<>();
        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Object updateReservation(Object reservation) {
        try{
            if(reservation != null) {
                EntityManager em = emf.createEntityManager();
                Reservation oldReservation = em.find(Reservation.class, ((Reservation) reservation).getId());
                if (oldReservation != null) {
                    em.getTransaction().begin();
                    em.merge(oldReservation);
                    em.getTransaction().commit();
                    em.close();
                    return oldReservation;
                }
            }
            return null;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object createHoliday(String name, Date date) {
        try {
            EntityManager em = emf.createEntityManager();
            if (name != null && date != null) {
                Holiday holiday = new Holiday();
                holiday.setTitle(name);
                holiday.setDate(date);
                em.getTransaction().begin();
                em.persist(holiday);
                em.getTransaction().commit();
                em.close();
                return holiday;
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getHoliday(Date date) {
        try {
            if (date != null) {
                EntityManager em = emf.createEntityManager();
                TypedQuery<Object> q = em.createQuery("SELECT holiday FROM Holiday holiday WHERE holiday.date = :date", Object.class).setParameter("date", date);
                if(q.getResultList().isEmpty()){
                    return null;
                }
                return q.getSingleResult();
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Object> getHolidays() {
        try {
            EntityManager em = emf.createEntityManager();
            TypedQuery<Object> q = em.createQuery("SELECT holiday FROM Holiday holiday", Object.class);
            return q.getResultList();

        }
        catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Object deleteHoliday(Long holidayId) {
        try {
            if (holidayId != null) {
                EntityManager em = emf.createEntityManager();
                Holiday holiday = em.find(Holiday.class, holidayId);
                if(holiday != null) {
                    em.getTransaction().begin();
                    em.remove(holiday);
                    em.getTransaction().commit();
                    em.close();
                    return holiday;
                }
            }
            return null;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
