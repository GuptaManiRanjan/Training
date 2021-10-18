package com.flightbooking.app.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.app.entity.AirlineFlightEntity;
import com.flightbooking.app.serviceImpl.AirlineFlightServiceImpl;

@RestController
@RequestMapping(value = "/flight/airline")
@CrossOrigin(origins = "*")
public class AirlineFlightController 
{
	private static final Logger log = LogManager.getLogger(AirlineFlightController.class);
	
	@Autowired
	AirlineFlightServiceImpl flightService;
	
	   /***
	    * @param flightEntity
	    * @return add the flight & Update
	    */
	   @PostMapping(value = "/addFlight")
	   public String addFlight(@RequestBody AirlineFlightEntity flightEntity)
	   {
		   int id=0;
		   log.info("[FlightController] method=addFlight IN:"+flightEntity);
		   try {
			   log.info(">>>>calling addFlight service>>>>");
		    id=flightService.addUpdateFlight(flightEntity);
		   }catch (Exception e) {
			   log.info("[FlightController] exception occured while calling addFlight:"+e.getLocalizedMessage());
		}
		   return id>0?"Flight added successfully..":"Something went wrong!";
	   }
	   
	   /***
	    * @param flightEntity
	    * @return Delete flight
	    */
	   	@GetMapping("/deleteFlight")
		  public String deleteFlight(@RequestParam int id)
		  {
			  log.info("[FlightController] method=deleteFlight IN:"+id);
			  boolean flag=flightService.deleteById(id);
			  return flag==true?"Record deleted successfully..":"Something went wrong!";
		  }
	   
	   /***
	    * 
	    * @return loads all the flights
	    */
	   @GetMapping(value = "/flights")
	   public List<AirlineFlightEntity> getAllFlights()
	   {
		   log.info("[FlightController] method=getAllFlights Fething...");
		   return flightService.getAllFlights();
	   }
	   
	   /***
	    * 
	    * @param source
	    * @param destination
	    * @param date
	    * @return flights list to the user
	    */
	   @GetMapping("/search")
	   public List<AirlineFlightEntity> getFlightsDetail(@RequestParam String source,@RequestParam String destination,@RequestParam String date)
	   {
		   log.info("[FlightController] method=findFlights params>> source:"+source+", destination:"+destination+", date:"+date);
		   
		   return flightService.findFlights(source, destination, date);
	   }
	   
	   /**
	    * 
	    * @param flightEntity
	    * @note: this method block the airline
	    */
	   @GetMapping(value = "/block/{name}")
	   public String blockAirline(@PathVariable("name") String name)
	   {
		   boolean flag=false;
		   String blockFlag="0";
		   log.info("[FlightController] method=blockAirline IN:"+name+","+blockFlag);
		   try 
		   {
		    flightService.blockAirline(name.toUpperCase(),blockFlag);
		    flag=true;
		   }catch (Exception e) 
		   {
			   e.printStackTrace();
			   log.info("[FlightController] exception occured while calling blockAirline:"+e.getLocalizedMessage());
		   }
		   return flag==true?"Airline blocked successfully..":"Something went wrong!";
	   }
	   
	   @GetMapping(value = "/unblock/{name}")
	   public String unblockAirline(@PathVariable("name") String name)
	   {
		   boolean flag=false;
		   String blockFlag="1";
		   log.info("[FlightController] method=unblockAirline IN:"+name+","+blockFlag);
		   try 
		   {
		    flightService.blockAirline(name.toUpperCase(),blockFlag);
		    flag=true;
		   }catch (Exception e) 
		   {
			   e.printStackTrace();
			   log.info("[FlightController] exception occured while calling blockAirline:"+e.getLocalizedMessage());
		   }
		   //log.info("[FlightController] method=addFlight OUT:"+id);
		   return flag==true?"Airline unblocked successfully..":"Something went wrong!";
	   }
	  
	  
}
