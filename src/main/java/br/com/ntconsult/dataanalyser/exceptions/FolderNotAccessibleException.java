package br.com.ntconsult.dataanalyser.exceptions;

public class FolderNotAccessibleException extends RuntimeException
{	
	
	private static final long serialVersionUID = -3195438711870831738L;
		
	public FolderNotAccessibleException(String message)
	{
		super(message);	
	}
	
	public FolderNotAccessibleException(Throwable throwable)
	{
		super(throwable);
	}
}
