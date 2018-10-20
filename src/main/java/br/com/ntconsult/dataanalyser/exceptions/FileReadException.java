package br.com.ntconsult.dataanalyser.exceptions;

public class FileReadException extends RuntimeException
{	
	
	private static final long serialVersionUID = -3195438711870831738L;
		
	public FileReadException(String message)
	{
		super(message);	
	}
	
	public FileReadException(Throwable throwable)
	{
		super(throwable);
	}
}
