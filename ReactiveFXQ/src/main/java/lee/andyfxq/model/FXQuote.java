package lee.andyfxq.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="rfxq")
public class FXQuote implements Comparable<FXQuote> {
	static final DecimalFormat f = new DecimalFormat("##.00000000");
	
	@Id
	private String id;
	
	private String symbol;
	private String tenor;
	private String pxStr;
	private BigDecimal price;
	private long createdTime;

	public FXQuote () {}
	
	public FXQuote(String symbol,String tenor, BigDecimal price) {
		this.symbol = symbol;
		this.tenor = tenor;
		this.pxStr = f.format(price);
		this.price = price;
		this.createdTime = System.currentTimeMillis();
//		this.id = this.pxStr + this.createdTime;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getPxStr() {
		return pxStr;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
		this.pxStr = f.format(price);
	}

	public String getTenor() {
		return tenor;
	}

	public void setTenor(String tenor) {
		this.tenor = tenor;
	}
	
	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	

	@Override
	public String toString() {
		return "FXQuote [symbol=" + symbol + ", tenor=" + tenor + ", pxStr=" + pxStr + ", price="
				+ price + ", createdTime=" + createdTime + "]";
	}


	@Override
	public int compareTo(FXQuote o) {
		return this.symbol.compareTo(((FXQuote)o).getSymbol());
	}
	
	
}
