package be.wavenet.technocite.store.server.controller;

import be.wavenet.technocite.store.server.dto.CheapestProductDto;
import be.wavenet.technocite.store.server.dto.InventoryProductDto;
import be.wavenet.technocite.store.server.model.Customer;
import be.wavenet.technocite.store.server.model.Market;
import be.wavenet.technocite.store.server.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface ApiController {

    @GetMapping(path = "/api/customers")
    @ResponseBody
    List<Customer> getCustomers();

    @GetMapping(path = "/api/localities")
    @ResponseBody
    List<String> getLocalities();

    @GetMapping(path = "/api/markets/{locality}")
    @ResponseBody
    List<Market> findMarketsByLocality(@PathVariable String locality);

    @GetMapping(path = "/api/inventoryProducts/{id}")
    @ResponseBody
    List<InventoryProductDto> findInventoryProductsByMarketId(@PathVariable String id);

    @GetMapping(path = "/api/buy/{customerId}")
    @ResponseBody
    double buyProducts(@PathVariable String customerId, @RequestBody List<String> inventoryIds);

    @GetMapping(path = "/api/products/search/{name}")
    @ResponseBody
    List<Product> findProductsByNameContaining(@PathVariable String name);

    @GetMapping(path = "/api/cheapestProducts/{name}")
    @ResponseBody
    List<CheapestProductDto> findCheapestProductsByNameContaining(@PathVariable String name);
}
