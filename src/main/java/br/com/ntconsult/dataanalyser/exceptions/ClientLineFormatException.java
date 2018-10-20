package br.com.ntconsult.dataanalyser.exceptions;

public class ClientLineFormatException extends RuntimeException
{	
	
	private static final long serialVersionUID = -3195438711870831738L;
		
	public ClientLineFormatException(String message)
	{
		super(message);	
	}
	
	public ClientLineFormatException(Throwable throwable)
	{
		super(throwable);
	}
}
