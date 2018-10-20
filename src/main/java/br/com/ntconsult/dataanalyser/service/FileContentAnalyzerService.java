package br.com.ntconsult.dataanalyser.service;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ntconsult.dataanalyser.data.FileData;

@Service
public class FileContentAnalyzerService
{
	@Autowired
	private FileImportService fileImportService;
	
	public void analyze(Path filePath, String fileName)
	{
		FileData fileData = fileImportService.importFile(filePath, fileName);
	}	
}
