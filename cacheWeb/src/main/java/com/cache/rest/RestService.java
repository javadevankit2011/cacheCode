package com.cache.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.cache.common.SearchList;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

@Path("/service")
public class RestService {

	@Context
	private ServletContext context;
	private Cache cache;
	
	@PostConstruct
	public void init()
	{
		cache = (Cache) context.getAttribute("cache");
		}
	
	@POST
	@Path("{user}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addSearch(@PathParam("user")String user,String msg)
	{
		Element old = cache.get(user);
		SearchList oldList = null;
		if(old==null)
		{
			oldList = new SearchList(new ArrayList<String>());
		}
		else
		{
			oldList = (SearchList) old.getObjectValue();
		}
		
		SearchList newList = oldList.add(oldList, msg);
		Element newOne = new Element(user, newList);
		cache.put(newOne);
	}
	
	@GET
	@Path("{user}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getSearch(@PathParam("user")String user)
	{
		Element element = cache.get(user);
		if(element==null)
			return new ArrayList<String>();
		else
			return ((SearchList)element.getObjectValue()).getMessages();
	}
	
}
