package org.nebulae.unit.step01;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.formular.util.file.TomcatFileManager;

public class statistic_user_rating extends BaseModule
{
	static File fk = new File("C:/Users/Andy/Desktop/librec-2.0.0/data/filmtrust/rating/ratings_2.txt");
	static TomcatFileManager fm = new TomcatFileManager();
	
	public static void main(String[] args) 
	throws Exception
	{
		List<Double[]> items = readLineFromFile(fk);
		
		Map<Double, Double> es = List2.hist(items, x -> x[0]);
		
		for(Double dk: es.keySet())
		if(es.get(dk) < 5)
		{
			Double[] d = max(dk, items);
			
			//ket qua nhung nguoi trung danh gia voi user id 219
			List<Double[]> res = selectSimilarRate(items, d, 0.5);
			
			System.out.println("**** Query to: " + dk );
			
			File f = fm.getDesktopFile("/data-deploy/output3/select_users_similar_"+ dk + ".txt");
			PrintWriter out = new PrintWriter(f);
			for(int i = 0; i < res.size(); i++)
			{
				selectByUserId(items, res.get(i), out);
			}
			out.close();
			
			//break;
		}
	}

	private static void selectByUserId(List<Double[]> items, Double[] d, PrintWriter out) 
	throws Exception
	{
		
		for(Double[] dk: items) 
		{
			if(String.valueOf(d[0]).equals(String.valueOf(dk[0])))
				out.println(dk[0] + " " + dk[1] + " " + dk[2]);
		}
	}

	private static List<Double[]> selectSimilarRate(List<Double[]> items, Double[] d, double sigma)
	{
		List<Double[]> res = new ArrayList<Double[]>();
		for(Double[] dk: selectByItem(items, d))
			if((d[2] >= (dk[2] - sigma)) &&  (d[2] <= ((dk[2] + sigma)) ))
			{
				res.add(dk);
			}
		
		return res;
	}
	
	
	private static List<Double[]> selectByItem(List<Double[]> items, Double[] d) 
	{
		List<Double[]> res = new ArrayList<Double[]>();
		
		//tim tat ca cung danh gia bo phim x
		for(Double[] dk: items)
		{
			double d0 = (double) dk[1];
			double d1 = (double) d[1];
			if(d0 == d1) 
			{
				 res.add(dk);
			}
		}
		
		return res;
	}

	private static Double[] max(Double userId, List<Double[]> items)
	{
		Double max = 0D;
		for(Double[] d: items) //d[0]: userId, d[1] item, d[2]: rate
		{
			if(d[0] == userId)
				max = Math.max(d[2], max);
		}
		
		for(Double[] d: items)
		{
			if(d[0] == userId) {
				if(String.valueOf(d[2]).equals(String.valueOf(max))) {
					return d;
				}
			}
				
		}
		
		return null;
	}

}
