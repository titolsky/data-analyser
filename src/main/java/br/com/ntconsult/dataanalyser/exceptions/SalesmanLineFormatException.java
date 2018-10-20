package br.com.ntconsult.dataanalyser.exceptions;

public class SalesmanLineFormatException extends RuntimeException
{	
	
	private static final long serialVersionUID = -3195438711870831738L;
		
	public SalesmanLineFormatException(String message)
	{
		super(message);	
	}
	
	public SalesmanLineFormatException(Throwable throwable)
	{
		super(throwable);
	}
}
