package br.com.ntconsult.dataanalyser.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import br.com.ntconsult.dataanalyser.data.FileData;
import br.com.ntconsult.dataanalyser.data.Item;
import br.com.ntconsult.dataanalyser.data.Sale;
import br.com.ntconsult.dataanalyser.data.Salesman;
import br.com.ntconsult.dataanalyser.exceptions.FileReadException;

@Service
public class FileImportService
{
	private static final String SALESMAN_LINE_ID = "001";
	private static final String CLIENT_LINE_ID = "002";
	private static final String SALE_LINE_ID = "003";

	public synchronized FileData importFile(Path filePath, String fileName)
	{
		FileData fileData = new FileData(fileName);		
		try (Stream<String> stream = Files.lines(filePath)) {			
			List<String> salesmanLines = stream.filter(line -> line.startsWith(SALESMAN_LINE_ID))
										  	   .collect(Collectors.toList());		  
			
			List<String> clientsLines = stream.filter(line -> line.startsWith(CLIENT_LINE_ID))
					  						  .collect(Collectors.toList());
			
			List<String> saleLines = stream.filter(line -> line.startsWith(SALE_LINE_ID))
					  					   .collect(Collectors.toList());
			
			fileData.
			
		} catch (IOException e) {
			throw new FileReadException(String.format("Can't ready file from path '%s' error: %s",filePath.toString(), e.getMessage()));
		}
		
		return fileData;
	}
	
	private List<Salesman> getSalesmans(List<String> SalesmanLines)
	{
		return null;
	}
	
	private List<Sale> getSales(List<String> SalesLines)
	{
		return null;
	}
	
	private List<Item> getSalesman(List<String> ItensLines)
	{
		return null;
	}
	
		
}
