package co.com.sofka.cqrs.query.api.controller;

import co.com.sofka.cqrs.command.api.model.ProductRestModel;
import co.com.sofka.cqrs.query.api.queries.GetAllProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAllProducts(){

        GetAllProductsQuery getAllProductsQuery = new GetAllProductsQuery();

        return queryGateway.query(getAllProductsQuery, ResponseTypes
                        .multipleInstancesOf(ProductRestModel.class))
                .join();


    }
}
