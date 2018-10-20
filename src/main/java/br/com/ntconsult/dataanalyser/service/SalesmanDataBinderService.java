package br.com.ntconsult.dataanalyser.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.ntconsult.dataanalyser.data.Salesman;
import br.com.ntconsult.dataanalyser.exceptions.SalesmanLineFormatException;

@Service
public class SalesmanDataBinderService
{
	@Autowired
	private Environment environment;

	public Salesman getSalesman(String line)
	{
		if (line != null)
		{
			// remove o id da linha
			line = line.substring(4);
			String[] attributes = line.split(environment.getProperty("br.com.ntconsult.dataanalyzer.data.file.separator", "ç"));
			String cpf = null;
			String name = null;
			BigDecimal salary = null;

			try
			{
				cpf = attributes[0];
				name = attributes[1];
				salary = new BigDecimal(attributes[2]);
			} catch (Exception e)
			{
				throw new SalesmanLineFormatException(e);
			}

			return new Salesman(cpf, name, salary);
		}		
		return null;
	}

}
