package carsharing;

import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImplement implements CompanyDao {

    List<Company> companies;
    List<Car> cars;


    public CompanyDaoImplement(){
        companies = new ArrayList<Company>();
        //Company Company1 = new Company("Pear",1);
        //Company Company2 = new Company("Microhard",2);
        //Companies.add(Company1);
        //Companies.add(Company2);
        cars = new ArrayList<Car>();
    }
    @Override
    public void deleteTable(){
        CompanyDao companyDao = new CompanyDaoImplement();
        for(Company company : companyDao.getAllCompanies()){
            deleteCompany(company);
        }
    }
    @Override
    public void deleteCompany(Company company) {
        companies.remove(company.getId());
        System.out.println("Company: id " + company.getId() + ", deleted from database");
    }
    @Override
    public void newCompany(String name){
        Company company1 = new Company(name, companies.size() + 1);
        companies.add(company1);
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

    @Override
    public List<Company> getAllCompanies() {
        return companies;
    }

    @Override
    public Company getCompany(int id) {
        return companies.get(id);
    }

    @Override
    public void updateCompany(Company company) {
        companies.get(company.getId()).setName(company.getName());
        System.out.println("Company: id " + company.getId() + ", updated in the database");
    }
    @Override
    public int getCompaniesSize(){ return companies.size();}
}