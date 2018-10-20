package br.com.ntconsult.dataanalyser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.ntconsult.dataanalyser.util.FolderManagerUtil;

@SpringBootApplication
public class DataAnalyserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataAnalyserApplication.class, args);
	}
	
	@Bean 
	public	CommandLineRunner runner(){
		return args -> {
			FolderManagerUtil.initFolders();		
		};
	}
}
