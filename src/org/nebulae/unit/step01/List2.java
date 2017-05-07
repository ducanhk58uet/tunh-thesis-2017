package org.nebulae.unit.step01;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class List2 
{

	public static<T1, T2> Map<T2, Double> hist(List<T1> items, TypedMapper<T1, T2> lf) 
	{
		Map<T2, Double> res = new LinkedHashMap<T2, Double>();
		
		for(T1 ik: items)  
		{
			T2 vk = lf.invokeMapperAction(ik);
			Double ck = res.get(vk);
			res.put(vk, ck==null ? 1 : ck+1);
		}
		
		return res;
	}
	
}
