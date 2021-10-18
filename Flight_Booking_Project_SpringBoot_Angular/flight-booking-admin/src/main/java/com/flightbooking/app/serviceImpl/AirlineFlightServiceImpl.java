package com.flightbooking.app.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flightbooking.app.entity.AirlineFlightEntity;
import com.flightbooking.app.repository.AirlineFlightRepository;
import com.flightbooking.app.service.AirlineFlightService;

@Service
public class AirlineFlightServiceImpl implements AirlineFlightService {
	// private static Logger log = Logger.getLogger(FlightServiceImpl.class);
	private static final Logger log = LogManager.getLogger(AirlineFlightServiceImpl.class);

	@Autowired
	AirlineFlightRepository flightRepository;

	@Override
	public int addUpdateFlight(AirlineFlightEntity flightEntity) {
		log.info("[FlightServiceImpl] method=addFlight IN: " + flightEntity);
		try {
			// flightEntity.setAirlineName(flightEntity.getAirlineName().toUpperCase());
			flightEntity = flightRepository.save(flightEntity);

		} catch (Exception e) {
			log.info("[FlightServiceImpl while calling addFlight ***exception occured***:" + e.getLocalizedMessage());
		}
		log.info("[FlightServiceImpl] method=addFlight out:" + flightEntity.getId());
		return flightEntity.getId();
	}

	@Override
	public List<AirlineFlightEntity> findFlights(String source, String destination, String date) {
		List<AirlineFlightEntity> entities = new ArrayList<>();
		try {
			List<AirlineFlightEntity> flightEntities = flightRepository.findFlights(source, destination, "1");

			if (flightEntities.size() > 0) {
				for (AirlineFlightEntity entity : flightEntities) {
					entity.setFlightDate(date);
					entities.add(entity);
				}
			}
		} catch (Exception e) {
			log.info("[AirlineFlightServiceImpl] exception occured while calling >>>findFligts:"
					+ e.getLocalizedMessage());
		}
		log.info("[AirlineFlightServiceImpl] loaded findFlights list:" + entities);
		return entities;
	}

	@Override
	public List<AirlineFlightEntity> getAllFlights() {
		log.info("[FlightServiceImpl] method=getAllFlights loading...");
		return flightRepository.findAll();
	}

	@Override
	@Transactional
	public void blockAirline(String name, String status) {
		log.info("[FlightServiceImpl] blockAirline in..." + name + "," + status);
		flightRepository.blockAirline(status, name);
	}

	@Override
	public boolean deleteById(int id) {
		log.info("[FlightServiceImpl] deleteById" + id);
		boolean flag = false;
		try {
			flightRepository.deleteById(id);
			flag = true;
		} catch (Exception e) {
			log.info("[FlightServiceImpl] exception occured calling>>>deleteById:" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return flag;
	}
}
