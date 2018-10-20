package br.com.ntconsult.dataanalyser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.ntconsult.dataanalyser.data.Client;
import br.com.ntconsult.dataanalyser.exceptions.ClientLineFormatException;

@Service
public class ClientDataBinderService
{
	@Autowired
	private Environment environment;

	public Client getClient(String line)
	{
		if (line != null)
		{
			// remove o id da linha
			line = line.substring(4);
			String[] attributes = line.split(environment.getProperty("br.com.ntconsult.dataanalyzer.data.file.separator", "ç"));
			String cnpj = null;
			String name = null;
			String businessArea = null;

			try
			{
				cnpj = attributes[0];
				name = attributes[1];
				businessArea = attributes[2];
			} catch (Exception e)
			{
				throw new ClientLineFormatException(e);
			}

			return new Client(cnpj, name, businessArea);
		}
		return null;
	}

}
