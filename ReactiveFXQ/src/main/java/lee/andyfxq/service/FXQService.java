package lee.andyfxq.service;

import lee.andyfxq.model.FXQuote;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FXQService {

	Flux<FXQuote> getAll();
	Flux<FXQuote> getBySymbol(String symbol);
	Flux<FXQuote> getBySymbolTenor(String symbol, String tenor);
	Mono<FXQuote> getMostRecentBySymbol(String symbol);

}
