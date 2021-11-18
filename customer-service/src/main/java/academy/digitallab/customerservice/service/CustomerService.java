package academy.digitallab.customerservice.service;

import academy.digitallab.customerservice.entity.Customer;
import academy.digitallab.customerservice.entity.Region;

import java.util.List;

public interface CustomerService {

    List<Customer> getAll();
    Customer getById(Long id);
    Customer create(Customer product);
    Customer update(Customer product);
    Customer delete(Long id);
    List<Customer> getByRegion(Region region);
}
