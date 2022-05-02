package sk.stuba.fei.uim.vsa.pr1c;

import sk.stuba.fei.uim.vsa.pr1c.entities.*;
import sk.stuba.fei.uim.vsa.pr1c.helpers.KeyboardInput;

import java.util.Date;
import java.util.Locale;

public class Project1C {

    public static void main(String[] args) {
        CLI();
    }

    private static void CLI() {
        try {
            CarParkService service = new CarParkService();
            boolean end = true;
            while (end) {
                System.out.println("Enter number of Entity or enter 'x' to end the program.");
                String input = KeyboardInput.readString(
                        "1 -> Car\n" +
                                "2 -> CarParkFloor\n" +
                                "3 -> ParkingSpot\n" +
                                "4 -> Car\n" +
                                "5 -> User\n" +
                                "6 -> Reservation\n" +
                                "7 -> Holiday\n");
                switch (input.toLowerCase(Locale.ROOT)) {
                    case "1":
                        input = KeyboardInput.readString("\nEnter the number of method: \n" +
                                "1 -> createCarPark(String name, String address, Integer pricePerHour)\n" +
                                "2 -> getCarPark(Long carParkId)\n" +
                                "3 -> getCarPark(String carParkName)\n" +
                                "4 -> getCarParks()\n" +
                                "5 -> updateCarPark(Object carPark)\n" +
                                "6 -> deleteCarPark(Long carParkId)\n");
                        System.out.println("Your input is " + input + "\n");
                        switch (input) {
                            case "1":
                                String name = KeyboardInput.readString("Enter name");
                                String address = KeyboardInput.readString("Enter address");
                                Integer price = Integer.valueOf(KeyboardInput.readString("Enter pricePerHour"));
                                System.out.println("Method called: createCarPark(" + name + ", " + address + ", " + price + ")");
                                System.out.println("---- Returned value: " + service.createCarPark(name, address, price) + "\n");
                                break;
                            case "2":
                                Long id = Long.valueOf(KeyboardInput.readString("Enter carParkId"));
                                System.out.println("Method called: getCarPark(" + id + ")");
                                System.out.println("---- Returned value: " + service.getCarPark((id)) + "\n");
                                break;
                            case "3":
                                name = KeyboardInput.readString("Enter carParkName");
                                System.out.println("Method called: getCarPark(" + name + ")");
                                System.out.println("---- Returned value: " + service.getCarPark((name)) + "\n");
                                break;
                            case "4":
                                System.out.println("Method called: getCarParks()");
                                System.out.println("---- Returned value: " + service.getCarParks() + "\n");
                                break;
                            case "5":
                                id = Long.valueOf(KeyboardInput.readString("Enter id"));
                                name = KeyboardInput.readString("Enter name");
                                address = KeyboardInput.readString("Enter address");
                                price = Integer.valueOf(KeyboardInput.readString("Enter pricePerHour"));
                                CarPark carPark = new CarPark();
                                carPark.setId(id);
                                carPark.setCarParkName(name);
                                carPark.setAddress(address);
                                carPark.setPricePerHour(price);
                                System.out.println("Method called: updateCarPark(" + carPark + ")");
                                System.out.println("---- Returned value: " + service.updateCarPark(carPark) + "\n");
                                break;
                            case "6":
                                id = Long.valueOf(KeyboardInput.readString("Enter carParkId"));
                                System.out.println("Method called: deleteCarPark(" + id + ")");
                                System.out.println("---- Returned value: " + service.deleteCarPark((id)) + "\n");
                                break;
                            default:
                                System.out.println("Wrong value\n");
                                break;
                        }
                        break;
                    case "2":
                        input = KeyboardInput.readString("\nEnter the number of method: \n" +
                                "1 -> createCarParkFloor(Long carParkId, String floorIdentifier)\n" +
                                "2 -> getCarParkFloor(Long carParkFloorId)\n" +
                                "3 -> getCarParkFloors(Long carParkId)\n" +
                                "4 -> updateCarParkFloor(Object carParkFloor)\n" +
                                "5 -> deleteCarParkFloor(Long carParkFloorId)\n");
                        System.out.println("Your input is " + input + "\n");
                        switch (input) {
                            case "1":
                                Long carParkId = Long.valueOf(KeyboardInput.readString("Enter carParkId"));
                                String floorIdentifier = KeyboardInput.readString("Enter floorIdentifier");
                                System.out.println("Method called: createCarParkFloor(" + carParkId + ", " + floorIdentifier + ")");
                                System.out.println("---- Returned value: " + service.createCarParkFloor(carParkId, floorIdentifier) + "\n");
                                break;
                            case "2":
                                Long carParkFloorId = Long.valueOf(KeyboardInput.readString("Enter carParkFloorId"));
                                System.out.println("Method called: createCarParkFloor(" + carParkFloorId + ")");
                                System.out.println("---- Returned value: " + service.getCarParkFloor(carParkFloorId) + "\n");
                                break;
                            case "3":
                                carParkId = Long.valueOf(KeyboardInput.readString("Enter CarParkId"));
                                System.out.println("Method called: getCarParkFloors(" + carParkId + ")");
                                System.out.println("---- Returned value: " + service.getCarParkFloors(carParkId) + "\n");
                                break;
                            case "4":
                                Long id = Long.valueOf(KeyboardInput.readString("Enter CarParkFloorId"));
                                floorIdentifier = KeyboardInput.readString("Enter floorIdentifier");
                                CarParkFloor carParkFloor = new CarParkFloor();
                                carParkFloor.setId(id);
                                carParkFloor.setFloorIdentifier(floorIdentifier);
                                System.out.println("Method called: updateCarParkFloor(" + carParkFloor + ")");
                                System.out.println("---- Returned value: " + service.updateCarParkFloor(carParkFloor) + "\n");
                                break;
                            case "5":
                                carParkFloorId = Long.valueOf(KeyboardInput.readString("Enter carParkFloorId"));
                                System.out.println("Method called: deleteCarParkFloor(" + carParkFloorId + ")");
                                System.out.println("---- Returned value: " + service.deleteCarParkFloor(carParkFloorId) + "\n");
                                break;
                            default:
                                System.out.println("Wrong value\n");
                                break;
                        }
                        break;
                    case "3":
                        input = KeyboardInput.readString("\nEnter the number of method: \n" +
                                "1 -> createParkingSpot(Long carParkId, String floorIdentifier, String spotIdentifier)\n" +
                                "2 -> getParkingSpot(Long parkingSpotId)\n" +
                                "3 -> getParkingSpots(Long carParkId, String floorIdentifier) \n" +
                                "4 -> getParkingSpots(Long carParkId)\n" +
                                "5 -> getAvailableParkingSpots(String carParkName)\n" +
                                "6 -> getOccupiedParkingSpots(String carParkName)\n" +
                                "7 -> updateParkingSpot(Object parkingSpot)\n" +
                                "8 -> deleteParkingSpot(Long parkingSpotId)\n");
                        System.out.println("Your input is " + input + "\n");
                        switch (input) {
                            case "1":
                                Long carParkId = Long.valueOf(KeyboardInput.readString("Enter CarParkId"));
                                String floorIdentifier = KeyboardInput.readString("Enter floorIdentifier");
                                String spotIdentifier = KeyboardInput.readString("Enter spotIdentifier");
                                System.out.println("Method called: createParkingSpot(" + carParkId + ", " + floorIdentifier + ", " + spotIdentifier + ")");
                                System.out.println("---- Returned value: " + service.createParkingSpot(carParkId, floorIdentifier, spotIdentifier) + "\n");
                                break;
                            case "2":
                                Long parkingSpotId = Long.valueOf(KeyboardInput.readString("Enter parkingSpotId"));
                                System.out.println("Method called: getParkingSpot(" + parkingSpotId + ")");
                                System.out.println("---- Returned value: " + service.getParkingSpot(parkingSpotId) + "\n");
                                break;
                            case "3":
                                carParkId = Long.valueOf(KeyboardInput.readString("Enter CarParkId"));
                                floorIdentifier = KeyboardInput.readString("Enter floorIdentifier");
                                System.out.println("Method called: getParkingSpots(" + carParkId + ", " + floorIdentifier + ")");
                                System.out.println("---- Returned value: " + service.getParkingSpots(carParkId, floorIdentifier) + "\n");
                                break;
                            case "4":
                                carParkId = Long.valueOf(KeyboardInput.readString("Enter CarParkId"));
                                System.out.println("Method called: getParkingSpots(" + carParkId + ")");
                                System.out.println("---- Returned value: " + service.getParkingSpots(carParkId) + "\n");
                                break;
                            case "5":
                                String carParkName = KeyboardInput.readString("Enter carParkName");
                                System.out.println("Method called: getAvailableParkingSpots(" + carParkName + ")");
                                System.out.println("---- Returned value: " + service.getAvailableParkingSpots(carParkName) + "\n");
                                break;
                            case "6":
                                carParkName = KeyboardInput.readString("Enter carParkName");
                                System.out.println("Method called: getOccupiedParkingSpots(" + carParkName + ")");
                                System.out.println("---- Returned value: " + service.getOccupiedParkingSpots(carParkName) + "\n");
                                break;
                            case "7":
                                Long id = Long.valueOf(KeyboardInput.readString("Enter parkingSpotId"));
                                spotIdentifier = KeyboardInput.readString("Enter spotIdentifier");
                                ParkingSpot parkingSpot = new ParkingSpot();
                                parkingSpot.setId(id);
                                parkingSpot.setSpotIdentifier(spotIdentifier);
                                System.out.println("Method called: updateParkingSpot(" + parkingSpot + ")");
                                System.out.println("---- Returned value: " + service.updateParkingSpot(parkingSpot) + "\n");
                                break;
                            case "8":
                                parkingSpotId = Long.valueOf(KeyboardInput.readString("Enter parkingSpotId"));
                                System.out.println("Method called: deleteParkingSpot(" + parkingSpotId + ")");
                                System.out.println("---- Returned value: " + service.deleteParkingSpot(parkingSpotId) + "\n");
                                break;
                            default:
                                System.out.println("Wrong value\n");
                                break;
                        }
                        break;
                    case "4":
                        input = KeyboardInput.readString("\nEnter the number of method: \n" +
                                "1 -> createCar(Long userId, String brand, String model, String colour, String vehicleRegistrationPlate)\n" +
                                "2 -> getCar(Long carId)\n" +
                                "3 -> getCar(String vehicleRegistrationPlate)\n" +
                                "4 -> getCars(Long userId)\n" +
                                "5 -> updateCar(Object car) n" +
                                "6 -> deleteCar(Long carId)\n");
                        System.out.println("Your input is " + input + "\n");
                        switch (input) {
                            case "1":
                                Long userId = Long.valueOf(KeyboardInput.readString("Enter userId"));
                                String brand = KeyboardInput.readString("Enter brand");
                                String model = KeyboardInput.readString("Enter model");
                                String colour = KeyboardInput.readString("Enter colour");
                                String vehicleRegistrationPlate = KeyboardInput.readString("Enter vehicleRegistrationPlate");
                                System.out.println("Method called: createCar(" + userId + ", " + brand + ", " + model + ", " + colour + ", " + vehicleRegistrationPlate + ")");
                                System.out.println("---- Returned value: " + service.createCar(userId, brand, model, colour, vehicleRegistrationPlate) + "\n");
                                break;
                            case "2":
                                Long carId = Long.valueOf(KeyboardInput.readString("Enter carId"));
                                System.out.println("Method called: getCar(" + carId + ")");
                                System.out.println("---- Returned value: " + service.getCar(carId) + "\n");
                                break;
                            case "3":
                                vehicleRegistrationPlate = KeyboardInput.readString("Enter vehicleRegistrationPlate");
                                System.out.println("Method called: getCar(" + vehicleRegistrationPlate + ")");
                                System.out.println("---- Returned value: " + service.getCar(vehicleRegistrationPlate) + "\n");
                                break;
                            case "4":
                                userId = Long.valueOf(KeyboardInput.readString("Enter userId"));
                                System.out.println("Method called: getCars(" + userId + ")");
                                System.out.println("---- Returned value: " + service.getCars(userId) + "\n");
                                break;
                            case "5":
                                Long id = Long.valueOf(KeyboardInput.readString("Enter carId"));
                                brand = KeyboardInput.readString("Enter brand");
                                model = KeyboardInput.readString("Enter model");
                                colour = KeyboardInput.readString("Enter colour");
                                vehicleRegistrationPlate = KeyboardInput.readString("Enter vehicleRegistrationPlate");
                                Car car = new Car();
                                car.setId(id);
                                car.setBrand(brand);
                                car.setModel(model);
                                car.setColour(colour);
                                car.setVehicleRegistrationPlate(vehicleRegistrationPlate);
                                System.out.println("Method called: updateCar(" + car + ")");
                                System.out.println("---- Returned value: " + service.updateCar(car) + "\n");
                                break;
                            case "6":
                                carId = Long.valueOf(KeyboardInput.readString("Enter carId"));
                                System.out.println("Method called: deleteCar(" + carId + ")");
                                System.out.println("---- Returned value: " + service.deleteCar(carId) + "\n");
                                break;
                            default:
                                System.out.println("Wrong value\n");
                                break;
                        }
                        break;
                    case "5":
                        input = KeyboardInput.readString("\nEnter the number of method: \n" +
                                "1 -> createUser(String firstname, String lastname, String email)\n" +
                                "2 -> getUser(Long userId) \n" +
                                "3 -> getUser(String email)\n" +
                                "4 -> getUsers()\n" +
                                "5 -> updateUser(Object user)\n" +
                                "6 -> deleteUser(Long userId)\n");
                        System.out.println("Your input is " + input + "\n");
                        switch (input) {
                            case "1":
                                String firstname = KeyboardInput.readString("Enter firstname");
                                String lastname = KeyboardInput.readString("Enter lastname");
                                String email = KeyboardInput.readString("Enter email");
                                System.out.println("Method called: createUser(" + firstname + ", " + lastname + ", " + email + ")");
                                System.out.println("---- Returned value: " + service.createUser(firstname, lastname, email) + "\n");
                                break;
                            case "2":
                                Long userId = Long.valueOf(KeyboardInput.readString("Enter userId"));
                                System.out.println("Method called: getUser(" + userId + ")");
                                System.out.println("---- Returned value: " + service.getUser(userId) + "\n");
                                break;
                            case "3":
                                email = KeyboardInput.readString("Enter email");
                                System.out.println("Method called: getUser(" + email + ")");
                                System.out.println("---- Returned value: " + service.getUser(email) + "\n");
                                break;
                            case "4":
                                System.out.println("Method called: getUsers()");
                                System.out.println("---- Returned value: " + service.getUsers() + "\n");
                                break;
                            case "5":
                                Long id = Long.valueOf(KeyboardInput.readString("Enter userId"));
                                firstname = KeyboardInput.readString("Enter firstname");
                                lastname = KeyboardInput.readString("Enter lastname");
                                email = KeyboardInput.readString("Enter email");
                                User user = new User();
                                user.setId(id);
                                user.setFirstname(firstname);
                                user.setLastname(lastname);
                                user.setEmail(email);
                                System.out.println("Method called: updateCar(" + user + ")");
                                System.out.println("---- Returned value: " + service.updateUser(user) + "\n");
                                break;
                            case "6":
                                userId = Long.valueOf(KeyboardInput.readString("Enter userId"));
                                System.out.println("Method called: deleteUser(" + userId + ")");
                                System.out.println("---- Returned value: " + service.deleteUser(userId) + "\n");
                            default:
                                System.out.println("Wrong value\n");
                                break;
                        }
                        break;
                    case "6":
                        input = KeyboardInput.readString("\nEnter the number of method: \n" +
                                "1 -> createReservation(Long parkingSpotId, Long cardId) \n" +
                                "2 -> endReservation(Long reservationId)\n" +
                                "3 -> getReservations(Long parkingSpotId, Date date)\n" +
                                "4 -> getMyReservations(Long userId) \n" +
                                "5 -> updateReservation(Object reservation)\n");
                        System.out.println("Your input is " + input + "\n");
                        switch (input) {
                            case "1":
                                Long parkingSpotId = Long.valueOf(KeyboardInput.readString("Enter parkingSpotId"));
                                Long cardId = Long.valueOf(KeyboardInput.readString("Enter cardId"));
                                System.out.println("Method called: createReservation(" + parkingSpotId + ", " + cardId + ")");
                                System.out.println("---- Returned value: " + service.createReservation(parkingSpotId, cardId) + "\n");
                                break;
                            case "2":
                                Long reservationId = Long.valueOf(KeyboardInput.readString("Enter reservationId"));
                                System.out.println("Method called: endReservation(" + reservationId + ")");
                                System.out.println("---- Returned value: " + service.endReservation(reservationId) + "\n");
                                break;
                            case "3":
                                parkingSpotId = Long.valueOf(KeyboardInput.readString("Enter parkingSpotId"));
                                Integer day = Integer.valueOf(KeyboardInput.readString("Enter date - day"));
                                Integer month = Integer.valueOf(KeyboardInput.readString("Enter date - month"));
                                Integer year = Integer.valueOf(KeyboardInput.readString("Enter date - year"));
                                Date date = new Date();
                                date.setDate(day);
                                date.setMonth(month);
                                date.setYear(year);
                                System.out.println("Method called: getReservations(" + parkingSpotId + ", " + date + ")");
                                System.out.println("---- Returned value: " + service.getReservations(parkingSpotId, date) + "\n");
                                break;
                            case "4":
                                Long userId = Long.valueOf(KeyboardInput.readString("Enter userId"));
                                System.out.println("Method called: getMyReservations(" + userId + ")");
                                System.out.println("---- Returned value: " + service.getMyReservations(userId) + "\n");
                                break;
                            case "5":
                                Long id = Long.valueOf(KeyboardInput.readString("Enter reservationId"));
                                Double price = KeyboardInput.readDouble("Enter price");
                                Reservation reservation = new Reservation();
                                reservation.setId(id);
                                reservation.setPrice(price);
                                System.out.println("Method called: updateCar(" + reservation + ")");
                                System.out.println("---- Returned value: " + service.updateReservation(reservation) + "\n");
                                break;

                            default:
                                System.out.println("Wrong value\n");
                                break;
                        }
                        break;
                    case "7":
                        input = KeyboardInput.readString("\nEnter the number of method: \n" +
                                "1 -> createHoliday(String name, Date date)\n" +
                                "2 -> getHoliday(Date date) \n" +
                                "3 -> getHolidays()\n" +
                                "4 -> deleteHoliday(Long holidayId)\n");
                        System.out.println("Your input is " + input + "\n");
                        switch (input) {
                            case "1":
                                String name = KeyboardInput.readString("Enter name");
                                Integer day = Integer.valueOf(KeyboardInput.readString("Enter date - day"));
                                Integer month = Integer.valueOf(KeyboardInput.readString("Enter date - month (0 - 11)"));
                                Integer year = Integer.valueOf(KeyboardInput.readString("Enter date - year"));
                                Date date = new Date();
                                date.setDate(day);
                                date.setMonth(month);
                                date.setYear(year);
                                System.out.println("Method called: createHoliday(" + name + ", " + date + ")");
                                System.out.println("---- Returned value: " + service.createHoliday(name, date) + "\n");
                                break;
                            case "2":
                                day = Integer.valueOf(KeyboardInput.readString("Enter date - day"));
                                month = Integer.valueOf(KeyboardInput.readString("Enter date - month (0 - 11)"));
                                year = Integer.valueOf(KeyboardInput.readString("Enter date - year"));
                                date = new Date();
                                date.setDate(day);
                                date.setMonth(month);
                                date.setYear(year);
                                System.out.println(date);
                                System.out.println("Method called: getHoliday(" + date + ")");
                                System.out.println("---- Returned value: " + service.getHoliday(date) + "\n");
                                break;
                            case "3":
                                System.out.println("Method called: getHolidays()");
                                System.out.println("---- Returned value: " + service.getHolidays() + "\n");
                                break;
                            case "4":
                                Long holidayId = Long.valueOf(KeyboardInput.readString("Enter holidayId"));
                                System.out.println("Method called: deleteHoliday(" + holidayId + ")");
                                System.out.println("---- Returned value: " + service.deleteHoliday(holidayId) + "\n");
                                break;
                            default:
                                System.out.println("Wrong value\n");
                                break;
                        }
                        break;
                    case "x":
                        System.out.println("\n...Ending...\n");
                        break;
                    default:
                        System.out.println("Wrong input\n");
                        break;
                }

                if (input.equals("x") || input.equals("X")) {
                    end = false;
                }
            }
        }
        catch (Exception e){
            System.out.println("Wrong input\n");
            CLI();
        }
    }

}
