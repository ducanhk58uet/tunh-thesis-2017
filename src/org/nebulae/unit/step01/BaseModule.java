package org.nebulae.unit.step01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.formular.util.file.TomcatFileManager;

public class BaseModule 
{
	protected static TomcatFileManager fm = new TomcatFileManager();
	
	protected static List<Double[]> readLineFromFile(File fk) 
			throws Exception
			{
				List<Double[]> res = new ArrayList<Double[]>();
				for(String sk: fm.readLinesIntoList(fk))
				{
					String[] s = sk.split(" ");
					res.add(new Double[]{Double.parseDouble(s[0]),
											Double.parseDouble(s[1]),
											Double.parseDouble(s[2])});
				}
				return res;
			}
}
