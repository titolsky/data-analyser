package br.com.ntconsult.dataanalyser.data;

import java.math.BigDecimal;

public class Salesman
{	
	private String cpf;
	private String name;
	private BigDecimal salary;
		
	public Salesman(String cpf, String name, BigDecimal salary)
	{
		super();
		this.cpf = cpf;
		this.name = name;
		this.salary = salary;
	}
	
	public String getCpf()
	{
		return cpf;
	}
	
	public String getName()
	{
		return name;
	}
	
	public BigDecimal getSalary()
	{
		return salary;
	}	

	@Override
	public String toString()
	{
		return "Seller [cpf=" + cpf + ", name=" + name + ", salary=" + salary + "]";
	}
	
	
}
