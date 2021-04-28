package lee.andyfxq.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;

import lee.andyfxq.model.FXQuote;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FXQRepository extends ReactiveMongoRepository<FXQuote, String> {

	@Tailable
	Flux<FXQuote> findWithTailableCursorBy();
	@Tailable
	Flux<FXQuote> findBySymbol(String symbol);
	@Tailable
	Flux<FXQuote> findBySymbolAndTenor(String symbol, String tenor);
//	@Tailable
	Mono<FXQuote> findFirstBySymbolOrderByCreatedTimeDesc(String symbol);
//	Flux<FXQuote> saveAll(List<FXQuote> quotes);
	
}
