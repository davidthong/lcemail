package org.jboss.tools.examples.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.joda.time.LocalTime;

public class GigInformation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private String venue;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String artist;
	private String cost;
	private String amountDue;
	private LocalTime startTime; 
	private LocalTime endTime;
	private String production;
	private Boolean sound;
	private String additionalTerms;
	private String openArtist;
	private LocalTime openStart;
	private LocalTime openEnd;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public Boolean getSound() {
		return sound;
	}
	public void setSound(Boolean sound) {
		this.sound = sound;
	}
	public String getAdditionalTerms() {
		return additionalTerms;
	}
	public void setAdditionalTerms(String additionalTerms) {
		this.additionalTerms = additionalTerms;
	}
	public String getOpenArtist() {
		return openArtist;
	}
	public void setOpenArtist(String openArtist) {
		this.openArtist = openArtist;
	}
	public LocalTime getOpenStart() {
		return openStart;
	}
	public void setOpenStart(LocalTime openStart) {
		this.openStart = openStart;
	}
	public LocalTime getOpenEnd() {
		return openEnd;
	}
	public void setOpenEnd(LocalTime openEnd) {
		this.openEnd = openEnd;
	}
	
	
	
	
}
