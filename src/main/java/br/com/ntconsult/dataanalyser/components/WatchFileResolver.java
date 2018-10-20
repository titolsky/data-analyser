package br.com.ntconsult.dataanalyser.components;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import br.com.ntconsult.dataanalyser.exceptions.FolderNotAccessibleException;

@Component
public class WatchFileResolver implements InitializingBean
{	
	@Autowired
    private Environment environment;
	
	private static final Logger LOGGER = Logger.getLogger(WatchFileResolver.class.getName());
		
	private void initWatchService(String baseFolder) throws IOException, InterruptedException
	{
		WatchService watchService = FileSystems.getDefault().newWatchService();

		Path path = Paths.get(baseFolder + "/data/in");

		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		WatchKey key;
		while ((key = watchService.take()) != null)
		{
			for (WatchEvent<?> event : key.pollEvents())
			{
				String extension = environment.getProperty("br.com.ntconsult.dataanalyzer.data.file.extension", ".dat");
				if(event.context().toString().endsWith(extension))
				{				
					Path dir = (Path) key.watchable();
					Path fullPath = dir.resolve((Path) event.context());			
					LOGGER.log(Level.INFO, "File affected: '" + fullPath.toString() + "'");
				}
			}
			key.reset();
		}
	}
	
	private void initFolders(String baseFolder)
	{			
		List<Path> paths = new ArrayList<>();
		Path pathIn = Paths.get(baseFolder+"/data/in");
		Path pathOut = Paths.get(baseFolder+"/data/out");
		paths.add(pathIn);
		paths.add(pathOut);

		paths.forEach(path -> createFolder(path));
	}

	private void createFolder(Path path)
	{
		if (!Files.exists(path))
		{
			try
			{
				Files.createDirectories(path);
			} catch (IOException e)
			{
				throw new FolderNotAccessibleException("Can't create folders... " + e.getMessage());
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		String baseFolder = environment.getProperty("br.com.ntconsult.dataanalyzer.data.basefolder", ".");
		initFolders(baseFolder);
		initWatchService(baseFolder);
	}
}
