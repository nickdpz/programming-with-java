package com.nickdpz.amazonviewer.model;

import java.util.ArrayList;
import java.util.Date;

public class Movie extends Film implements IDisplayable {

  private int id;
  private int timeViewed;

  public Movie(
    String title,
    String genre,
    String creator,
    int duration,
    short year
  ) {
    super(title, genre, creator, duration);
    setYear(year);
  }

  
  /** 
   * @return int
   */
  public int getId() {
    return id;
  }

  
  /** 
   * @return int
   */
  public int getTimeViewed() {
    return timeViewed;
  }

  
  /** 
   * @param timeViewed
   */
  public void setTimeViewed(int timeViewed) {
    this.timeViewed = timeViewed;
  }

  
  /** 
   * @return String
   */
  @Override
  public String toString() {
    
    return (
      "\n :: MOVIE ::" +
      "\n Title: " +
      getTitle() +
      "\n Genero: " +
      getGenre() +
      "\n Year: " +
      getYear() +
      "\n Creator: " +
      getCreator() +
      "\n Duration: " +
      getDuration()
    );
  }

  
  /** 
   * @param dateI
   * @return Date
   */
  @Override
  public Date startToSee(Date dateI) {
    
    return dateI;
  }

  
  /** 
   * @param dateI
   * @param dateF
   */
  @Override
  public void stopToSee(Date dateI, Date dateF) {
    

    if (dateF.getTime() > dateI.getTime()) {
      setTimeViewed((int) (dateF.getTime() - dateI.getTime()));
    } else {
      setTimeViewed(0);
    }
  }

  
  /** 
   * @return ArrayList<Movie>
   */
  public static ArrayList<Movie> makeMoviesList() {
    ArrayList<Movie> movies = new ArrayList<Movie>();

    for (int i = 1; i <= 5; i++) {
      movies.add(
        new Movie(
          "Movie " + i,
          "Genero " + i,
          "Creador " + i,
          120 + i,
          (short) (2017 + i)
        )
      );
    }

    return movies;
  }

  @Override
  public void view() {
    setViewed(true);
    Date dateI = startToSee(new Date());

    for (int i = 0; i < 100; i++) {
      System.out.println("..........");
    }

    //Termine de verla
    stopToSee(dateI, new Date());
    System.out.println("\nViste: " + toString());
    System.out.println("Por: " + getTimeViewed() + " milisegundos");
  }
}
