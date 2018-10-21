package br.com.ntconsult.dataanalyser.data;

import java.util.List;

public class FileData
{
	private String filename;
	private List<Salesman> sellers;
	private List<Client> clients;
	private List<Sale> sales;
	
	
	public FileData(String filename, List<Salesman> sellers, List<Client> clients, List<Sale> sales)
	{
		this.filename = filename;
		this.sellers = sellers;
		this.clients = clients;
		this.sales = sales;
	}

	public String getFilename()
	{
		return filename;
	}

	public List<Salesman> getSellers()
	{
		return sellers;
	}

	public List<Client> getClients()
	{
		return clients;
	}

	public List<Sale> getSales()
	{
		return sales;
	}

	@Override
	public String toString()
	{
		return "FileData [filename=" + filename + ",\n sellers=" + sellers + ",\n clients=" + clients + ",\n sales=" + sales + "]";
	}
	
	
	
	
}
