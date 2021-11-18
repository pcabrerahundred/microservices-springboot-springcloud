package academy.digitallab.shoppingservice.client;

import academy.digitallab.shoppingservice.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient {

    @Override
    public ResponseEntity<Customer> getCustomer(long id) {
        Customer customer = Customer.builder()
                .firstName("None")
                .lastName("None")
                .email("None")
                .photoUrl("")
                .build();

        return ResponseEntity.ok(customer);
    }
}
