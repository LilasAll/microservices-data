package fr.formation.inti;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;



import fr.formation.inti.repository.PriceRepository;
import fr.formation.inti.repository.model.Price;
import fr.formation.inti.rest.bean.PriceRequest;
import fr.formation.inti.rest.exception.InternalServerException;
import fr.formation.inti.rest.exception.ValidationParameterException;
import fr.formation.inti.service.IPriceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v2/shop")
@Slf4j
public class Endpoint {

	@Autowired
	IPriceService priceservice;
	@Autowired
	PriceRepository pricerepository;
	 @ExceptionHandler(ValidationParameterException.class)
	    public Mono<ResponseEntity<String>> handlerValidationParameterException(ValidationParameterException e) {
	     return Mono.just(
	                badRequest().body("Missing parameter: "+ e.getMessage()));
	    }

	    @ExceptionHandler(InternalServerException.class)
	    public Mono<ResponseEntity<String>> handlerInternalServerException() {
	        return Mono.just(status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error server has occurred "));
	    }

	    @PostMapping(value = "/register" , headers = "Accept=application/json; charset=utf-8")
	    @ResponseStatus( value  = HttpStatus.CREATED, reason="Price is registered" )
	    public Mono<String> create(@RequestBody PriceRequest price) {
	        // Vérification des paramètres
	        if( ObjectUtils.anyNotNull(price)  && !ObjectUtils.allNotNull(price.getMontant(), price.getCode(), price.getDate())){
	            log.error("Validation error: one of attributes is not found");
	            return Mono.error(new ValidationParameterException("(Validation error message): one of attributes is not found" ));
	        }
	        return Mono.just(price)
	        .map(data->
	                {

	                    return priceservice.register( data).subscribe().toString();

	                });
	    }
	    @GetMapping
	    @RequestMapping(value = "/prices/")
	    public Flux<Price> getPrices() {
	        log.info("All prices searching");
	      return priceservice.getPrices()
	              // uses of map
	                .switchIfEmpty(Flux.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
	                .map( data-> data);
	    }
	    @PostMapping(value = "/update" , headers = "Accept=application/json; charset=utf-8")
	    @ResponseStatus( value  = HttpStatus.CREATED, reason="Price is update" )
	    public Mono<String> update(@RequestBody PriceRequest price) {
	        // Vérification des paramètres
	        if( ObjectUtils.anyNotNull(price)  && !ObjectUtils.allNotNull(price.getIdPrix(), price.getMontant(), price.getCode(), price.getDate())){
	            log.error("Validation error: one of attributes is not found");
	            return Mono.error(new ValidationParameterException("(Validation error message): one of attributes is not found" ));
	        }
	        return Mono.just(price)
	        .map(data->
	                {

	                    return priceservice.update( data).subscribe().toString();

	                });
	    }
	    @PostMapping(value = "/active" , headers = "Accept=application/json; charset=utf-8")
	    @ResponseStatus( value  = HttpStatus.CREATED, reason="Price is active" )
	    public Mono<String> active(@RequestBody PriceRequest price) {
	        // Vérification des paramètres
	        if( ObjectUtils.anyNotNull(price)  && !ObjectUtils.allNotNull(price.getIdPrix(), price.getMontant(), price.getCode(), price.getDate())){
	            log.error("Validation error: one of attributes is not found");
	            return Mono.error(new ValidationParameterException("(Validation error message): one of attributes is not found" ));
	        }
	        return Mono.just(price)
	        .map(data->
	                {

	                    return priceservice.active( data).subscribe().toString();

	                });
	    }
	    @PostMapping(value = "/desactive" , headers = "Accept=application/json; charset=utf-8")
	    @ResponseStatus( value  = HttpStatus.CREATED, reason="Price is active" )
	    public Mono<String> desactive(@RequestBody PriceRequest price) {
	        // Vérification des paramètres
	        if( ObjectUtils.anyNotNull(price)  && !ObjectUtils.allNotNull(price.getIdPrix(), price.getMontant(), price.getCode(), price.getDate())){
	            log.error("Validation error: one of attributes is not found");
	            return Mono.error(new ValidationParameterException("(Validation error message): one of attributes is not found" ));
	        }
	        return Mono.just(price)
	        .map(data->
	                {

	                    return priceservice.desactive( data).subscribe().toString();

	                });
	    }
	    @GetMapping
	    @RequestMapping(value = "/prices/{date}")

	    public Flux<Price> getActiveByDate(@RequestParam(required = true, name = "date")
	    	@DateTimeFormat(pattern = "yyy-MM-dd'T'HH:mm:ss.SSSZZZZ") Date date ) {
	        log.info("Searching  {} ",date );
	        return pricerepository.findByDateAndActive(date)

	                // uses of doNext

	        		.doOnNext(price -> log.info(price.getDate()+ " is found"));

	    }
}
