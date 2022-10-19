package carsharing;

import java.util.List;

public interface CompanyDao {
    public List<Company> getAllCompanies();
    public void newCompany(String name);
    public List<Car> getAllCars();
    public void newCar(String name, int company_id);
    public Car getCar(int id);
    public void updateCar(Car car);
    public void deleteCar(Car car);
    public int getCarsSize();
    public Company getCompany(int id);
    public void updateCompany(Company company);
    public void deleteCompany(Company company);
    public int getCompaniesSize();
    public void deleteTable();
}
