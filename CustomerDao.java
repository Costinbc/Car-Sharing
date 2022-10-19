package carsharing;

import java.util.List;

public interface CustomerDao {
    public List<Customer> getAllCustomers();
    public void newCustomer(String name);
    public Customer getCustomer(int id);
    public void updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
    public int getCustomersSize();
    public void deleteRentedCars();
    public void deleteTable();
}
