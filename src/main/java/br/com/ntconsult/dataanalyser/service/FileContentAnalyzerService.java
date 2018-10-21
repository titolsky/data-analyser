package br.com.ntconsult.dataanalyser.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.ntconsult.dataanalyser.data.FileData;
import br.com.ntconsult.dataanalyser.data.Sale;
import br.com.ntconsult.dataanalyser.data.Salesman;
import br.com.ntconsult.dataanalyser.exceptions.FileMoveException;

@Service
public class FileContentAnalyzerService
{
	@Autowired
	private FileImportService fileImportService;
	
	@Autowired
	private DataAnalyzeWriterService analyzeWriterService;
	
	@Autowired
    private Environment environment;

	private static final Logger LOGGER = Logger.getLogger(FileContentAnalyzerService.class.getSimpleName());
	public void analyze(Path filePath)
	{	
		filePath = moveFileToProcessedPath(filePath);
		FileData fileData = fileImportService.importFile(filePath);
		Integer amountClients = fileData.getClients().size();
		Integer amountSalesman = fileData.getSellers().size();

		Long bestSale = 0L;		
		if (fileData.getSales().size() > 0)
		{
			fileData.getSales().sort((p1, p2) -> p1.compareTo(p2));
			bestSale = fileData.getSales().get(0).getId();
		}		
		String extension = environment.getProperty("br.com.ntconsult.dataanalyzer.data.file.extension", ".dat");
		String fileName = fileData.getFilename().replace(extension, "");
		analyzeWriterService.writeAnalyze(amountClients, amountSalesman, bestSale, getWorstSalesman(fileData.getSales(), fileData.getSellers()), fileName);
		LOGGER.log(Level.INFO, String.format("File '%s' imported successfully", filePath.getFileName().toString()));
	}
	
	private Path moveFileToProcessedPath(Path filePath)
	{
		String baseFolder = environment.getProperty("br.com.ntconsult.dataanalyzer.data.basefolder",".");
		Path target = Paths.get(baseFolder+"/data/processed");
		try
		{
			target = target.resolve(filePath.getFileName());
		
			if(Files.exists(target))
			{
				Files.delete(target);				
			}
				
			Files.move(filePath, target);
		} catch (IOException e)
		{
			throw new FileMoveException(e);
		}
		return target;
	}

	private Salesman getWorstSalesman(List<Sale> sales, List<Salesman> sellers)
	{
		//procura se algum vendedor não vendeu
		for (Salesman seller : sellers)
		{
			Optional<Sale> optionalSale = sales.stream()
				 .filter(sale -> sale.getSalesmanName().startsWith(seller.getName()))
				 .findAny();
			
			if(!optionalSale.isPresent())
			{
				return seller;
			}
		}
		
		
		//Procura o pior vendedor na lista de vendas, caso todos os vendedores tenham vendido algum valor
		Map<String, BigDecimal> saleSumary = new HashMap<>();				
		for (Sale sale : sales)
		{
			if (saleSumary.containsKey(sale.getSalesmanName().trim()))
			{
				BigDecimal curValue = saleSumary.get(sale.getSalesmanName().trim());
				saleSumary.put(sale.getSalesmanName(), curValue.add(sale.getTotalSale()));
			} else
			{
				saleSumary.put(sale.getSalesmanName(), sale.getTotalSale());
			}
		}
		
		Map.Entry<String, BigDecimal> minEntry = null;
		for (Map.Entry<String, BigDecimal>entry : saleSumary.entrySet())
		{
		    if (minEntry == null || entry.getValue().compareTo(minEntry.getValue()) <= 0)
		    {
		        minEntry = entry;		        		       
		    }
		}	
		
		String worstSellerName = minEntry.getKey();
		Salesman worstSeller = sellers.stream()
				.filter(salesman -> salesman.getName().startsWith(worstSellerName))
				.findFirst().get();		
		return worstSeller;
	}

}
