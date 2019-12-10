package be.wavenet.technocite.store.server.controller.impl;

import be.wavenet.technocite.store.server.controller.ApiController;
import be.wavenet.technocite.store.server.dto.CheapestProductDto;
import be.wavenet.technocite.store.server.dto.InventoryProductDto;
import be.wavenet.technocite.store.server.model.Customer;
import be.wavenet.technocite.store.server.model.Market;
import be.wavenet.technocite.store.server.model.Product;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ApiControllerImpl implements ApiController {



    @Override
    public List<Customer> getCustomers() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<String> getLocalities() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<Market> findMarketsByLocality(String locality) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<InventoryProductDto> findInventoryProductsByMarketId(String id) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public double buyProducts(String customerId, List<String> inventoryIds) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<Product> findProductsByNameContaining(String name) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    @Override
    public List<CheapestProductDto> findCheapestProductsByNameContaining(String name) {
        throw new UnsupportedOperationException("Not supported yet");
    }
}
