package carsharing;

import java.util.ArrayList;
import java.util.List;

public class CarDaoImplement implements CarDao {

    List<Car> cars;

    public CarDaoImplement(){
        cars = new ArrayList<Car>();
        //Car Car1 = new Car("Pear",1);
        //Car Car2 = new Car("Microhard",2);
        //Companies.add(Car1);
        //Companies.add(Car2);
    }
    @Override
    public void deleteTable(){
        CarDao carDao = new CarDaoImplement();
        for(Car car : carDao.getAllCars()){
            deleteCar(car);
        }
    }
    @Override
    public void deleteCar(Car Car) {
        cars.remove(Car.getId());
        System.out.println("Car: id " + Car.getId() + ", deleted from database");
    }
    @Override
    public void newCar(String name, int company_id){
        Car Car1 = new Car(name, cars.size() + 1, company_id);
        cars.add(Car1);
    }

    @Override
    public List<Car> getAllCars() {
        return cars;
    }

    @Override
    public Car getCar(int id) {
        return cars.get(id);
    }

    @Override
    public void updateCar(Car Car) {
        cars.get(Car.getId()).setName(Car.getName());
        System.out.println("Car: id " + Car.getId() + ", updated in the database");
    }
    @Override
    public int getCarsSize(){ return cars.size();}
}