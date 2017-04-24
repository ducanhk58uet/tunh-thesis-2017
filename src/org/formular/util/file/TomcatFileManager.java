package org.formular.util.file;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class TomcatFileManager 
{
	protected SimpleDateFormat fmt_log = new SimpleDateFormat("yyMMddHH");
	
	public File getDesktopFile(String url) 
	{
		return new File( System.getProperty("user.home") + "/Desktop/" + url );
	}
	
	public File getDropboxFile(String url) 
	{
		return new File( System.getProperty("user.home") + "/Dropbox/" + url );
	}
	
	public URL getResourceUrl(Class<?> cl, String fname) 
	{
		return cl.getResource(fname);
	}	
	
	public File getResourceFile(Class<?> cl, String fname) 
	{
		URL u = cl.getResource(fname);
		if(u==null) return null;
		return new File(u.getFile());
	}

	public List<String> readLinesIntoList(Class<?> cl, String fname)
	throws Exception
	{
		return readLinesIntoList(getResourceFile(cl, fname));
	}	
	
	public List<String> readLinesIntoList(File f)
	throws Exception
	{
		List<String> res = new ArrayList<String>();
		
		BufferedReader rd = new BufferedReader(
				   new InputStreamReader(new FileInputStream(f), "UTF-8"));

		
		while(true)
		{
			String l = rd.readLine();
			if(l==null) break;
			res.add(l);
		}
		
		rd.close();		
		
		return res;
	}	
	
	public Set<String> readSetOfLines(File f)
	throws Exception
	{
		Set<String> res = new LinkedHashSet<String>();
		
		BufferedReader rd = new BufferedReader(
				   new InputStreamReader(new FileInputStream(f), "UTF-8"));

		
		while(true)
		{
			String l = rd.readLine();
			if(l==null) break;
			res.add(l);
		}
		
		rd.close();		
		
		return res;
	}	

	
	public Set<String> readTreeSetOfLines(File f)
	throws Exception
	{
		Set<String> res = new TreeSet<String>();
		
		BufferedReader rd = new BufferedReader(
				   new InputStreamReader(new FileInputStream(f), "UTF-8"));

		
		while(true)
		{
			String l = rd.readLine();
			if(l==null) break;
			res.add(l);
		}
		
		rd.close();		
		
		return res;
	}		
	public String readLinesAsText(Class<?> cl, String fname) 
	throws Exception
	{
		File f = new File( cl.getResource(fname).getFile() );
		System.out.println(f.getAbsolutePath());
		
		return readLinesAsText(f);
	}
	
	public String readLinesAsText(File f) 
	throws Exception
	{
		String res = new String();
		
		BufferedReader reader = new BufferedReader(new FileReader(f));
		while(true)
		{
			String line = reader.readLine();
			if(line == null) break;
			if(res.length() > 0) res += "\r\n";
			res += line;
		}
		reader.close();
		
		return res;
	}	
	
	public String readLinesIntoString(File f)
	throws Exception
	{
		StringBuilder res = new StringBuilder();
		
		BufferedReader rd = new BufferedReader(new FileReader(f));
		
		while(true)
		{
			String l = rd.readLine();
			if(l==null) break;
			res.append(l + "\r\n");
		}
		
		rd.close();		
		
		return res.toString();
	}		
	public Set<String> readLinesIntoSet(File f)
	throws Exception
	{
		Set<String> res = new LinkedHashSet<String>();
		
		BufferedReader rd = new BufferedReader(new FileReader(f));
		
		while(true)
		{
			String l = rd.readLine();
			if(l==null) break;
			res.add(l);
		}
		
		rd.close();		
		
		return res;
	}

	public void showFile(File f) 
	throws Exception
	{
		Desktop.getDesktop().open(f);
	}
	


	public List<File> readSiblingFiles(Class<?> cl) 
	{
		File f = this.getResourceFile(cl, '/' + cl.getName().replace('.', '/') + ".class");
		System.out.println("Exploring " + f.getAbsolutePath());
		
		return readInnerFiles(f.getParentFile());
	}

	public List<File> readInnerFilesNR(File f0) 
	{
		List<File> res = new ArrayList<File>();
		
		File[] files = f0.listFiles();
		if(files != null)  for(File fk: files) res.add(fk);

		return res;
	}
	

	public List<File> readInnerFiles(File f0) 
	{
		List<File> res = new ArrayList<File> ();
		
		Stack<File> todo = new Stack<File>();
		todo.add(f0);
		
		while(!todo.isEmpty())
		{
			File fk = todo.pop();
			
			File[] files = fk.listFiles();
			if(files == null) continue;
			
			for(File fj: files)
			if( fj.isFile() ) res.add(fj);
			else if( fj.isDirectory() ) todo.add(fj);
		}
		
		return res;
	}

	
}
