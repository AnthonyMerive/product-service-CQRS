package co.com.sofka.cqrs.query.api.projection;

import co.com.sofka.cqrs.command.api.data.ProductEntity;
import co.com.sofka.cqrs.command.api.data.ProductRepository;
import co.com.sofka.cqrs.command.api.model.ProductRestModel;
import co.com.sofka.cqrs.query.api.queries.GetAllProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository repository;

    public ProductProjection(ProductRepository repository) {
        this.repository = repository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetAllProductsQuery getAllProductsQuery){
        List<ProductEntity> products = repository.findAll();
        
        return products.stream()
                        .map(p -> ProductRestModel
                                .builder()
                                .quantity(p.getQuantity())
                                .price(p.getPrice())
                                .name(p.getName())
                                .build())
                        .collect(Collectors.toList());
    }
}
