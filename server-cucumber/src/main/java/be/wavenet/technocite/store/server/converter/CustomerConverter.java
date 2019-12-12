package be.wavenet.technocite.store.server.converter;

import be.wavenet.technocite.store.server.dto.CustomerDto;
import be.wavenet.technocite.store.server.model.Customer;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerConverter implements Function<Customer, CustomerDto> {

    @Override
    public CustomerDto apply(Customer customer) {
        return new CustomerDto(customer.getId(), customer.getName(), customer.getBalance(), customer.getRegistration());
    }
}
