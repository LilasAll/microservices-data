package com.inti.formation.shop.api;



import com.inti.formation.shop.api.repository.model.Customer;

import com.inti.formation.shop.api.repository.model.Product;
import com.inti.formation.shop.api.repository.model.Stockinit;
import com.inti.formation.shop.api.rest.bean.CustomerRequest;
import com.inti.formation.shop.api.rest.exception.InternalServerException;
import com.inti.formation.shop.api.rest.exception.ValidationParameterException;
import com.inti.formation.shop.api.service.CustomerService;
import com.inti.formation.shop.api.service.ProductService;
import com.inti.formation.shop.api.service.StockinitService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;



@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/shop")
@Slf4j
// Controller , Roote
public class Endpoint {
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    StockinitService stockinitService;
    

    @ExceptionHandler(ValidationParameterException.class)
    public Mono<ResponseEntity<String>> handlerValidationParameterException(ValidationParameterException e) {
     return Mono.just(
                badRequest().body("Missing parameter: "+ e.getMessage()));
    }

    @ExceptionHandler(InternalServerException.class)
    public Mono<ResponseEntity<String>> handlerInternalServerException() {
        return Mono.just(status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error server has occurred "));
    }
    
    
    // **************************
    // **************  Customer : 
    // **************************
    @PostMapping(value = "/register" , headers = "Accept=application/json; charset=utf-8")
    @ResponseStatus( value  = HttpStatus.CREATED, reason="Customer is registered" )
    public Mono<String> create(@RequestBody CustomerRequest customer) {

        if( ObjectUtils.anyNotNull(customer)  && !ObjectUtils.allNotNull(customer.getEmail(),customer.getName(), customer.getFirstname() )){
            log.error("Validation error: one of attributes is not found");
            return Mono.error(new ValidationParameterException("(Validation error message): one of attributes is not found" ));
        }
        return Mono.just(customer)
        .map(data->
                {

                    return customerService.register( data).subscribe().toString();

                });
    }

    @GetMapping
    @RequestMapping(value = "/customers{customername}")

    public Flux<Customer> getCustomers(@RequestParam(required = true, name = "customername") String customername ) {
        log.info("Searching  {} ",customername );
        return customerService.searchName(customername)

                // uses of doNext

                .doOnNext(customer -> log.info(customer.getEmail()+ " is found"));

    }



    @GetMapping
    @RequestMapping(value = "/customers/")
    public Flux<Customer> getCustomers() {
        log.info("All customers searching");
      return customerService.getCustomers()
              // uses of map
                .switchIfEmpty(Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map( data-> data);
    }
    
    // **************************
    // **************   Product : 
    // **************************
    @PostMapping(value = "/register_product" , headers = "Accept=application/json; charset=utf-8")
    @ResponseStatus( value  = HttpStatus.CREATED, reason="Product is created" )
    public Mono<String> create(@RequestBody Product product) {

        if( ObjectUtils.anyNotNull(product)  && !ObjectUtils.allNotNull( product.getLibelle(), product.getDescription(), product.getOrigine(), product.getCouleur() )) {
            log.error("Validation error: one of parameter is not found");
            return Mono.error(new ValidationParameterException("Validation error" ));
        }
        return Mono.just(product)
                .map(data->
                {
                     return productService.create(data).subscribe().toString();
                });
    }
    
    @GetMapping
    @RequestMapping(value = "/products/")
    public Flux<Product> getProducts() {
        log.info("All products searching");
      return productService.getProducts()
              // uses of map
                .switchIfEmpty(Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map( product-> product);
    }
    
    // **************************
    // ************** Stockinit : 
    // **************************
    
    @PostMapping(value = "/registerProduct" , headers = "Accept=application/json; charset=utf-8")
    @ResponseStatus( value  = HttpStatus.CREATED, reason="Stock is created" )
    public Mono<String> createStock(@RequestBody Stockinit stockinit) {

        if( ObjectUtils.anyNotNull(stockinit)  && !ObjectUtils.allNotNull( stockinit.getDate(), stockinit.getMagasin(), stockinit.getQuantite())) {
            log.error("Validation error: one of parameter is not found");
            return Mono.error(new ValidationParameterException("Validation error" ));
        }
        return Mono.just(stockinit)
                .map(data->
                {
                     return stockinitService.createStock(data).subscribe().toString();
                });
    }
    
    @GetMapping
    @RequestMapping(value = "/stockinits/")
    public Flux<Stockinit> getStockinits() {
        log.info("All stockinits searching");
      return stockinitService.getStockinits()
              // uses of map
                .switchIfEmpty(Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .map( stockinit-> stockinit);
    }
    
    
    
    
    

}

