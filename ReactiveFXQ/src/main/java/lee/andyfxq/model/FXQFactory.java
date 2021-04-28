package lee.andyfxq.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import lee.andyfxq.repository.FXQRepository;
import reactor.core.publisher.Mono;

public class FXQFactory {

	private static final Logger logger = LoggerFactory.getLogger(FXQFactory.class);
	
	@Autowired
	FXQRepository fxqRepo;
		
	private List<String> symbolList = new ArrayList<>();
	private List<BigDecimal> priceList = new ArrayList<>();
	private List<String> tenorList = new ArrayList<>();
	
	public FXQFactory() {
		symbolList.add("EURUSD");  priceList.add(new BigDecimal("1.1805"));
		symbolList.add("USDCAD");  priceList.add(new BigDecimal("1.3225"));
		symbolList.add("CADJPY");  priceList.add(new BigDecimal("105.8860"));
		symbolList.add("USDJPY");  priceList.add(new BigDecimal("80.0670"));
		
		tenorList.add("ON");  tenorList.add("TN");  tenorList.add("SN");  
		tenorList.add("1W");  tenorList.add("2W");  
		tenorList.add("1M");  tenorList.add("2M");  tenorList.add("3M");  tenorList.add("6M");  tenorList.add("9M"); 
		tenorList.add("1Y");
	}
	
	// generate n random quotes
	public List<FXQuote> generate(int n) {
		List<FXQuote> quotes = new ArrayList<>();
			
		for (int i=0; i<n; i++) {
			int ind = (int) (Math.random() * symbolList.size());
			String symbol = symbolList.get(ind);
			
			int pluse = (int) (Math.random() * 2);
			int range = (int) (Math.random() * 350);
			BigDecimal price = priceList.get(ind);
			BigDecimal value = price.multiply( new BigDecimal(range* 0.000001));
			price = (pluse == 0 ? price.subtract(value) : price.add(value)).setScale(8, BigDecimal.ROUND_DOWN);
			
			priceList.remove(ind);
			priceList.add(ind, price);

			String tenor = tenorList.get((int) (Math.random() * tenorList.size()));
			
			FXQuote _fxq = new FXQuote(symbol, tenor, price);
			quotes.add(_fxq);
//			Mono<FXQuote> _fxq = fxqRepo.save(new FXQuote(symbol, tenor, price));
//			quotes.add(_fxq.block());
			
			logger.info(_fxq.toString());
		}
		return quotes;
	}
	
}
