package br.com.ntconsult.dataanalyser.exceptions;

public class SaleLineFormatException extends RuntimeException
{	
	
	private static final long serialVersionUID = -3195438711870831738L;
		
	public SaleLineFormatException(String message)
	{
		super(message);	
	}
	
	public SaleLineFormatException(Throwable throwable)
	{
		super(throwable);
	}
}
