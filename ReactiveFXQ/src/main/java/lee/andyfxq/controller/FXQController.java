package lee.andyfxq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lee.andyfxq.model.FXQuote;
import lee.andyfxq.service.FXQService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rFXQ")
public class FXQController {
	
	@Autowired
	FXQService fxqService;
	
	@GetMapping(value="/list")
	public Flux<FXQuote> requestFXQuote() {
		return fxqService.getAll();
	}
	
	@GetMapping(value="/symbol/{symbol}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<FXQuote> requestBySymbol(@PathVariable String symbol) {
		return fxqService.getBySymbol(symbol);
         
	}
	
	@GetMapping(value="/symbol/{symbol}/tenor/{tenor}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<FXQuote> requestByTenor(@PathVariable String symbol, @PathVariable String tenor) {
		return fxqService.getBySymbolTenor(symbol, tenor);
	}
	
	@GetMapping(value="/msymbol/{symbol}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Mono<FXQuote> requestRecentBySymbol(@PathVariable String symbol) {
		return fxqService.getMostRecentBySymbol(symbol);
	}
}
