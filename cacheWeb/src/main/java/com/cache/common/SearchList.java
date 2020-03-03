package com.cache.common;

import java.io.Serializable;
import java.util.List;

public class SearchList implements Serializable{

	private static final long serialVersionUID = 1L;
	private final int cache_size=10;
	private List<String> data;
	
	public SearchList(List<String> data) {
		this.data=data;
	}

	public SearchList add(SearchList list,String newData)
	{
		while(list.data.size()>cache_size)
		{
			list.data.remove(0);
		}
		list.data.add(newData);
		return list;
	}
	
	public List<String> getMessages()
	{
		return data;
	}
	
}
