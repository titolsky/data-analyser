package br.com.ntconsult.dataanalyser.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ntconsult.dataanalyser.data.Client;
import br.com.ntconsult.dataanalyser.data.FileData;
import br.com.ntconsult.dataanalyser.data.Sale;
import br.com.ntconsult.dataanalyser.data.Salesman;
import br.com.ntconsult.dataanalyser.exceptions.FileReadException;

@Service
public class FileImportService
{
	private static final String SALESMAN_LINE_ID = "001";
	private static final String CLIENT_LINE_ID = "002";
	private static final String SALE_LINE_ID = "003";
		
	@Autowired
	private SalesmanDataBinderService salesmanDataBinderService;
	@Autowired
	private ClientDataBinderService clientDataBinderService;
	@Autowired
    private SaleDataBinderService saleDataBinderService;
	
	public synchronized FileData importFile(Path filePath, String fileName)
	{
		List<String> fileLines = new ArrayList<>();	
		
		try (Stream<String> stream = Files.lines(filePath)) {					
			stream.forEach(line -> fileLines.add(line));					
		} catch (IOException e) {
			throw new FileReadException(String.format("Can't ready file from path '%s' error: %s",filePath.toString(), e.getMessage()));
		}
		
		return new FileData(fileName, 
							getSalesmans(getLinesById(fileLines, SALESMAN_LINE_ID)), 
							getClients(getLinesById(fileLines, CLIENT_LINE_ID)), 
							getSales(getLinesById(fileLines, SALE_LINE_ID)));
	}
	
	private List<String> getLinesById(List<String> fileLines, String id )
	{		
		return fileLines.stream()
						.filter(line -> line.startsWith(id))
						.collect(Collectors.toList());		  					
	}
	
	private List<Salesman> getSalesmans(List<String> SalesmanLines)
	{		
		List<Salesman> salesmans = new ArrayList<>();
		SalesmanLines.forEach(line -> salesmans.add(salesmanDataBinderService.getSalesman(line)));
		return salesmans;
	}
	
	private List<Client> getClients(List<String> clientLines)
	{
		List<Client> clients = new ArrayList<>();
		clientLines.forEach(line -> clients.add(clientDataBinderService.getClient(line)));
		return clients;
	}
	
	private List<Sale> getSales(List<String> SaleLines)
	{
		List<Sale> sales = new ArrayList<>();
		SaleLines.forEach(line -> sales.add(saleDataBinderService.getSale(line)));
		return sales;
	}			
}
