package br.com.ntconsult.dataanalyser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.ntconsult.dataanalyser.data.Sale;
import br.com.ntconsult.dataanalyser.exceptions.SaleLineFormatException;

@Service
public class SaleDataBinderService 
{	
	@Autowired
    private Environment environment;
	
	@Autowired
    private ItemDataBinderService itemDataBinderService;
	
	public Sale getSale(String line)
	{	
		if(line != null)
		{
			//remove o id da linha
			line = line.substring(4);		
			String[] attributes = line.split(environment.getProperty("br.com.ntconsult.dataanalyzer.data.file.separator", "ç"));
			Long id = null;
			String itemString = null;
			String name= null;
			
			try 
			{		
				id = new Long(attributes[0]);
				itemString = attributes[1].replace("[", "").replace("]", "");			
				name = attributes[2];
			}catch(Exception e)
			{
				throw new SaleLineFormatException(e);
			}		
			
			Sale sale = new Sale(id, itemDataBinderService.getItens(itemString) ,name);
			return sale;
		}		
		return null;
	}

}
