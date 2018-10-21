package br.com.ntconsult.dataanalyser.exceptions;

public class FileMoveException extends RuntimeException
{	
	
	private static final long serialVersionUID = -3195438711870831738L;
		
	public FileMoveException(String message)
	{
		super(message);	
	}
	
	public FileMoveException(Throwable throwable)
	{
		super(throwable);
	}
}
