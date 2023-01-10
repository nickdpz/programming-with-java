
package com.nickdpz.amazonviewer.model;

public abstract class Film {

	private String title;
	private String genre;
	private String creator;
	private int duration;
	private short year;
	private boolean viewed;

	public Film(String title, String genre, String creator, int duration) {
		super();
		this.title = title;
		this.genre = genre;
		this.creator = creator;
		this.duration = duration;
	}

	
	/** 
	 * @return String
	 */
	public String getTitle() {
		return title;
	}

	
	/** 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	
	/** 
	 * @return String
	 */
	public String getGenre() {
		return genre;
	}

	
	/** 
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	
	/** 
	 * @return String
	 */
	public String getCreator() {
		return creator;
	}

	
	/** 
	 * @param creator
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	
	/** 
	 * @return int
	 */
	public int getDuration() {
		return duration;
	}

	
	/** 
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	
	/** 
	 * @return short
	 */
	public short getYear() {
		return year;
	}

	
	/** 
	 * @param year
	 */
	public void setYear(short year) {
		this.year = year;
	}

	
	/** 
	 * @return String
	 */
	public String isViewed() {
		return viewed ? "Si" : "No";
	}

	
	/** 
	 * @return boolean
	 */
	public boolean getIsViewed() {
		return viewed;
	}

	
	/** 
	 * @param viewed
	 */
	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}

	public abstract void view();

}