package br.com.ntconsult.dataanalyser.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ntconsult.dataanalyser.data.Item;
import br.com.ntconsult.dataanalyser.exceptions.ItemLineFormatException;

@Service
public class ItemDataBinderService
{
	private List<Item> itens;

	public List<Item> getItens(String lineItens)
	{
		if (lineItens != null)
		{
			List<String> stringItens = Arrays.asList(lineItens.split(","));
			itens = new ArrayList<>();
			stringItens.forEach(line -> itens.add(strToItem(line)));
			return itens;
		}
		return null;
	}

	private Item strToItem(String stringItem)
	{
		Long id = null;
		BigDecimal unitPrice = null;
		Integer soldAmount = null;

		String[] itemAttributes = stringItem.split("-");
		try
		{
			id = new Long(itemAttributes[0]);
			soldAmount = new Integer(itemAttributes[1]);
			unitPrice = new BigDecimal(itemAttributes[2]);			

		} catch (Exception e)
		{
			throw new ItemLineFormatException(e);
		}

		return new Item(id, unitPrice, soldAmount);
	}

}
