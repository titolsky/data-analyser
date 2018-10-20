package br.com.ntconsult.dataanalyser.exceptions;

public class FileWriteException extends RuntimeException
{	
	
	private static final long serialVersionUID = -3195438711870831738L;
		
	public FileWriteException(String message)
	{
		super(message);	
	}
	
	public FileWriteException(Throwable throwable)
	{
		super(throwable);
	}
}
