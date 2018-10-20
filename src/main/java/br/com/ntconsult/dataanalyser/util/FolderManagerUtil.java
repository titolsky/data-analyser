package br.com.ntconsult.dataanalyser.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.com.ntconsult.dataanalyser.exceptions.FolderNotAccessibleException;

public class FolderManagerUtil
{
	public static void initFolders()
	{
		List<Path> paths = new ArrayList<>();

		Path pathIn = Paths.get("./data/in");
		Path pathOut = Paths.get("./data/out");
		paths.add(pathIn);
		paths.add(pathOut);

		paths.forEach(path -> createFolder(path));
	}

	private static void createFolder(Path path)
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
}
