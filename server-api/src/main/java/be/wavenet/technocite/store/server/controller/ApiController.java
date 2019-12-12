package be.wavenet.technocite.store.server.controller;

import be.wavenet.technocite.store.server.dto.CustomerDto;
import be.wavenet.technocite.store.server.dto.InventoryDto;
import be.wavenet.technocite.store.server.dto.MarketDto;
import be.wavenet.technocite.store.server.dto.ProductDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface ApiController {

    @GetMapping(path = "/api/customers")
    @ResponseBody
    List<CustomerDto> getCustomers();

    @GetMapping(path = "/api/localities")
    @ResponseBody
    List<String> getLocalities();

    @GetMapping(path = "/api/markets/{locality}")
    @ResponseBody
    List<MarketDto> findMarketsByLocality(@PathVariable String locality);

    @GetMapping(path = "/api/inventoryProducts/{id}")
    @ResponseBody
    List<InventoryDto> findInventoryProductsByMarketId(@PathVariable String id);

    @GetMapping(path = "/api/buy/{customerId}")
    @ResponseBody
    double buyProducts(@PathVariable String customerId, @RequestBody List<String> inventoryIds);

    @GetMapping(path = "/api/products/search/{name}")
    @ResponseBody
    List<ProductDto> findProductsByNameContaining(@PathVariable String name);

    @GetMapping(path = "/api/cheapestProducts/{name}")
    @ResponseBody
    List<InventoryDto> findCheapestProductsByNameContaining(@PathVariable String name);
}
