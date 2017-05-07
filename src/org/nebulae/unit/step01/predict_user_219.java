package org.nebulae.unit.step01;

import java.io.File;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class predict_user_219 extends BaseModule {

	public static File fk = new File("C:/Users/Andy/Desktop/data-deploy/output3.1/predict_for_user__219.0.txt");
	
	
	public static void main(String[] args)
	throws Exception
	{
		List<Double[]> items = readLineFromFile(fk);
		
		Map<Double, Double> group = List2.hist(items, x -> x[1]);
		
		
		
		Map<Double, Double> res = new LinkedHashMap<>();
		
		for(Double dk: group.keySet())
		for(Double[] d: items) 
		{
			if(String.valueOf(dk).equals(String.valueOf(d[1]))) 
			{
				Double vk = res.get(dk);
				res.put(dk, (vk == null) ? d[2] : (vk + d[2]) );
			}
		}
		
		DecimalFormat df = new DecimalFormat("#.##");
		PrintWriter out = new PrintWriter(fm.getDesktopFile("/data-deploy/output4/predict_user_219.txt"));
		for(Double sk: res.keySet())
		{
			
			out.println("219" + " " + sk + " " + df.format(res.get(sk) / group.get(sk) ) );
		}
		
		out.close();
	}
}
