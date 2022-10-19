package carsharing;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String name;
    private int id;

    private List<Car> cars = new ArrayList<>();
    Company(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCars(List <Car> cars){
        this.cars = cars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}