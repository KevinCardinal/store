package be.wavenet.technocite.store.server.controller;

import be.wavenet.technocite.store.server.converter.CustomerConverter;
import be.wavenet.technocite.store.server.dto.CustomerDto;
import be.wavenet.technocite.store.server.dto.InventoryDto;
import be.wavenet.technocite.store.server.dto.MarketDto;
import be.wavenet.technocite.store.server.dto.ProductDto;
import be.wavenet.technocite.store.server.model.Market;
import be.wavenet.technocite.store.server.repository.CustomerRepository;
import be.wavenet.technocite.store.server.repository.MarketRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
public class ApiControllerImpl implements ApiController {

    private CustomerRepository customerRepository;

    private MarketRepository marketRepository;

    private CustomerConverter customerConverter;

    public ApiControllerImpl(CustomerRepository customerRepository,
                             MarketRepository marketRepository,
                             CustomerConverter customerConverter) {
        this.customerRepository = customerRepository;
        this.marketRepository = marketRepository;
        this.customerConverter = customerConverter;
    }


    @Override
    public List<CustomerDto> getCustomers() {
        return customerRepository.findAll().stream().map(customerConverter).collect(toList());
    }

    @Override
    public List<String> getLocalities() {
        return marketRepository.findAll().stream().map(Market::getLocality).distinct().collect(toList());
    }

    @Override
    public List<MarketDto> findMarketsByLocality(String locality) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<InventoryDto> findInventoryProductsByMarketId(String id) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public double buyProducts(String customerId, List<String> inventoryIds) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<ProductDto> findProductsByNameContaining(String name) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<InventoryDto> findCheapestProductsByNameContaining(String name) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
