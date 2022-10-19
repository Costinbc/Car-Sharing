package carsharing;

import java.util.List;

public interface CarDao {
    public List<Car> getAllCars();
    public void newCar(String name, int company_id);
    public Car getCar(int id);
    public void updateCar(Car car);
    public void deleteCar(Car car);
    public int getCarsSize();
    public void deleteTable();
}
