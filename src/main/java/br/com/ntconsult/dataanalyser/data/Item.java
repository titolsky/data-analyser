package br.com.ntconsult.dataanalyser.data;

import java.math.BigDecimal;

public class Item
{
	private Long id;
	private BigDecimal unitPrice;
	private Integer soldAmount;
	
	public Item(Long id, BigDecimal unitPrice, Integer soldAmount)
	{
		super();
		this.id = id;
		this.unitPrice = unitPrice;
		this.soldAmount = soldAmount;
	}

	public Long getId()
	{
		return id;
	}
	
	public BigDecimal getUnitPrice()
	{
		return unitPrice;
	}

	public Integer getSoldAmount()
	{
		return soldAmount;
	}
	
	public BigDecimal getTotalPrice()
	{		
		return unitPrice.multiply(new BigDecimal(soldAmount));
	}

	@Override
	public String toString()
	{
		return "Item [id=" + id + ", unitPrice=" + unitPrice + ", soldAmount=" + soldAmount + ", totalSold="+getTotalPrice()+"]";
	}
	
}
