package br.com.ntconsult.dataanalyser.exceptions;

public class ItemLineFormatException extends RuntimeException
{	
	
	private static final long serialVersionUID = -3195438711870831738L;
		
	public ItemLineFormatException(String message)
	{
		super(message);	
	}
	
	public ItemLineFormatException(Throwable throwable)
	{
		super(throwable);
	}
}
