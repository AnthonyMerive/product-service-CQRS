package co.com.sofka.cqrs.command.api.events;

import co.com.sofka.cqrs.command.api.data.ProductEntity;
import co.com.sofka.cqrs.command.api.data.ProductRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private ProductRepository repository;

    public ProductEventsHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        ProductEntity product = new ProductEntity();

        BeanUtils.copyProperties(productCreatedEvent, product);

        repository.save(product);
    }
}
