package academy.digitallab.customerservice.repository;

import academy.digitallab.customerservice.entity.Customer;
import academy.digitallab.customerservice.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByNumberID(String numberID);
    List<Customer> findByLastName(String lastName);
    List<Customer> findByRegion(Region region);
}
