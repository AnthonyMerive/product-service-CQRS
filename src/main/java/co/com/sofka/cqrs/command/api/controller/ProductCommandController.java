package co.com.sofka.cqrs.command.api.controller;

import co.com.sofka.cqrs.command.api.commands.CreateProductCommand;
import co.com.sofka.cqrs.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRestModel productRestModelModel){

        CreateProductCommand createProductCommand =
                CreateProductCommand.builder()
                        .productId(UUID.randomUUID().toString())
                        .name(productRestModelModel.getName())
                        .price(productRestModelModel.getPrice())
                        .quantity(productRestModelModel.getQuantity())
                        .build();

        return commandGateway.sendAndWait(createProductCommand);
    }
}
