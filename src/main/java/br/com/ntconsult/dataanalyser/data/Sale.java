package br.com.ntconsult.dataanalyser.data;

import java.math.BigDecimal;
import java.util.List;

public class Sale implements Comparable<Sale>
{			
	private Long id;
	private List<Item> itens;
	private String salesmanName;
	private BigDecimal total;
	
	public Sale(Long id, List<Item> itens,String salesmanName)
	{
		super();
		this.id = id;		
		this.itens = itens;
		this.salesmanName = salesmanName;
		this.total = BigDecimal.ZERO;
	}

	public Long getId()
	{
		return id;
	}

	public List<Item> getItens()
	{
		return itens;
	}

	public String getSalesmanName()
	{
		return salesmanName;
	}
	
	public void addItem(Item item)
	{
		this.itens.add(item);
	}
	
	public BigDecimal getTotalSale()
	{
		for (Item item : itens)
		{ 
			total = total.add(item.getTotalPrice());		
		}	
		return total;
	}
	
	@Override
	public int compareTo(Sale sale)
	{		
		if(this.getTotalSale().compareTo(sale.getTotalSale()) > 0)
		{
			return -1;
		}else if (this.getTotalSale().compareTo(sale.getTotalSale()) < 0)
		{
			return 1;
		}
		return 0;
	}

	@Override
	public String toString()
	{
		return "Sale [id=" + id +", salesmanName=" + salesmanName + ", totalSale="+getTotalSale()+"]";
	}

	

	
}
