package br.com.ntconsult.dataanalyser.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.ntconsult.dataanalyser.data.Salesman;
import br.com.ntconsult.dataanalyser.exceptions.FileWriteException;

@Service
public class DataAnalyzeWriterService
{
	@Autowired
    private Environment environment;

	public void writeAnalyze(Integer amountClients, Integer amountSalesman, Long bestSaleId, Salesman worstSalesman, String fileName)
	{		
		
		try
		{
			String baseFolder = environment.getProperty("br.com.ntconsult.dataanalyzer.data.basefolder",".");
			String extension = environment.getProperty("br.com.ntconsult.dataanalyzer.data.file.extension",".");			
			File file = new File(baseFolder+"/data/out/"+fileName+".done"+extension);
			FileWriter fileWriter = new FileWriter(file);
			PrintWriter printWriter = new PrintWriter(fileWriter);					
			printWriter.println("Quantidade de clientes no arquivo de entrada: "+ amountClients);
			printWriter.println("Quantidade de vendedores no arquivo de entrada: "+ amountClients);
			printWriter.println("ID da venda mais cara: "+ bestSaleId);
			printWriter.println("Pior Vendedor: "+ worstSalesman);
			printWriter.close();
		} catch (IOException e)
		{
			throw new FileWriteException(e);
		}				
	}
}
