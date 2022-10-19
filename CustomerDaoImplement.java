package carsharing;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImplement implements CustomerDao {

    List<Customer> Customers;

    public CustomerDaoImplement(){
        Customers = new ArrayList<Customer>();
        //Customer Customer1 = new Customer("Pear",1);
        //Customer Customer2 = new Customer("Microhard",2);
        //Companies.add(Customer1);
        //Companies.add(Customer2);
    }
    @Override
    public void deleteTable(){
        CustomerDao customerDao = new CustomerDaoImplement();
        for(Customer customer : customerDao.getAllCustomers()){
            deleteCustomer(customer);
        }
    }
    @Override
    public void deleteCustomer(Customer Customer) {
        Customers.remove(Customer.getId());
        System.out.println("Customer: id " + Customer.getId() + ", deleted from database");
    }
    @Override
    public void newCustomer(String name){
        Customer Customer1 = new Customer(name, Customers.size() + 1, 0);
        Customers.add(Customer1);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return Customers;
    }

    @Override
    public Customer getCustomer(int id) {
        return Customers.get(id);
    }

    @Override
    public void updateCustomer(Customer Customer) {
        Customers.get(Customer.getId()).setName(Customer.getName());
        System.out.println("Customer: id " + Customer.getId() + ", updated in the database");
    }
    @Override
    public void deleteRentedCars(){
        CustomerDao customerDao = new CustomerDaoImplement();
        for(Customer customer : customerDao.getAllCustomers()){
            customer.setRented_car_id(0);
        }
    }
    @Override
    public int getCustomersSize(){ return Customers.size();}
}