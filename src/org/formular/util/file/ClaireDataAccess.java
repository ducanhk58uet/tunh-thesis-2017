package org.formular.util.file;

import java.io.File;

public class ClaireDataAccess {
	
	public static ClaireDataAccess start() {
		return new ClaireDataAccess();
	}
	
	public File getDesktopFile() 
	{
		return new File(System.getProperty("user.home") + "/Desktop"); 	
	}
	
	public File getDesktopFile(String fname) 
	{
		return new File(System.getProperty("user.home") + "/Desktop/" + fname); 	
	}
		
}