package academy.digitallab.customerservice.service;

import academy.digitallab.customerservice.entity.Customer;
import academy.digitallab.customerservice.entity.Region;
import academy.digitallab.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer create(Customer customer) {
        Customer customerDB = customerRepository.findByNumberID(customer.getNumberID());
        if (customerDB != null) {
            return customerDB;
        }
        customer.setState("CREATED");
        customerDB = customerRepository.save(customer);
        return customerDB;
    }

    @Override
    public Customer update(Customer customer) {
        Customer productDB = getById(customer.getId());
        if (productDB == null) {
            return null;
        }

        productDB.setFirstName(customer.getFirstName());
        productDB.setLastName(customer.getLastName());
        productDB.setEmail(customer.getEmail());
        productDB.setPhotoUrl(customer.getPhotoUrl());
        return customerRepository.save(productDB);
    }

    @Override
    public Customer delete(Long id) {
        Customer customerDB = getById(id);
        if (customerDB == null)
            return null;

        customerDB.setState("DELETED");
        return customerRepository.save(customerDB);
    }

    @Override
    public List<Customer> getByRegion(Region region) {
        return customerRepository.findByRegion(region);
    }

}
