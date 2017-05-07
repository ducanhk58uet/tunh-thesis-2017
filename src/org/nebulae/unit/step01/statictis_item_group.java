package org.nebulae.unit.step01;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class statictis_item_group extends BaseModule
{
	
	public static File fk = new File("C:/Users/Andy/Desktop/data-deploy/output3/select_users_similar_219.0.txt");
	
	public static File input = new File("C:/Users/Andy/Desktop/data-deploy/ouput3");
	
	public static void main(String[] args) 
	throws Exception
	{
		
		/*for(File f: input.listFiles()) 
		{
			List<Double[]> items = readLineFromFile(f);
			statisticWithUser(items, f);
		}*/
		
		List<Double[]> items = readLineFromFile(fk);
		
		statisticWithUser(items, fk);
		
	}
	
	public static void statisticWithUser(List<Double[]> items, File f0) 
	throws Exception
	{
		
		Map<Double, Double> group = List2.hist(items, x -> x[1]);
		
		String sname = f0.getName().replaceAll("select_users_similar", "");
		File f = fm.getDesktopFile("/data-deploy/output3.1/predict_for_user_" + sname);
		PrintWriter out = new PrintWriter(f);
		for(Double dk: group.keySet())
		{
			double avg = 0;
			for(Double[] dj: items) 
			{
				if(String.valueOf(dj[1]).equals(String.valueOf(dk)))
					avg += dj[2] / group.get(dk);
					
			}
			if(avg < 3) continue;
			
			for(Double[] dj: items) {
				if(String.valueOf(dk).equals(String.valueOf(dj[1])) && avg < dj[2])
					continue;
				out.println(dj[0] + " " + dj[1]  + " " + dj[2]);
			}
			
			break;
			
		}
		out.close();
	}
}
