package lee.andyfxq.service.impl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.andyfxq.model.FXQFactory;
import lee.andyfxq.model.FXQuote;
import lee.andyfxq.repository.FXQRepository;
import lee.andyfxq.service.FXQService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service("fxqService")
public class FXQServiceImpl implements FXQService {
	private static final Logger logger = LoggerFactory.getLogger(FXQServiceImpl.class);
	
	@Autowired
	FXQRepository fxqRepo;
	
	
	@Override
	public Flux<FXQuote> getAll() {
		return fxqRepo.findWithTailableCursorBy();
	}
	
	@Override
	public Flux<FXQuote> getBySymbol(String symbol) {
		return fxqRepo.findBySymbol(symbol);
	}

	@Override
	public Flux<FXQuote> getBySymbolTenor(String symbol, String tenor) {
		return fxqRepo.findBySymbolAndTenor(symbol, tenor);
	}
	
	@Override
	public Mono<FXQuote> getMostRecentBySymbol(String symbol) {
		return fxqRepo.findFirstBySymbolOrderByCreatedTimeDesc(symbol);
	}



}
