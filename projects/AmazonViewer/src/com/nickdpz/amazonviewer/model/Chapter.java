package com.nickdpz.amazonviewer.model;

import java.util.ArrayList;

public class Chapter extends Movie {

  private int id;
  private int sessionNumber;
  private Serie serie;

  /**
   * Hereda de {@link Movie}
   * @see Film
   * */
  public Chapter(
    String title,
    String genre,
    String creator,
    int duration,
    short year,
    int sessionNumber,
    Serie serie
  ) {
    super(title, genre, creator, duration, year);
    
    this.setSessionNumber(sessionNumber);
    this.setSerie(serie);
  }

  
  /** 
   * @return int
   */
  @Override
  public int getId() {
    
    return this.id;
  }

  
  /** 
   * @return int
   */
  public int getSessionNumber() {
    return sessionNumber;
  }

  
  /** 
   * @param sessionNumber
   */
  public void setSessionNumber(int sessionNumber) {
    this.sessionNumber = sessionNumber;
  }

  
  /** 
   * @return Serie
   */
  public Serie getSerie() {
    return serie;
  }

  
  /** 
   * @param serie
   */
  public void setSerie(Serie serie) {
    this.serie = serie;
  }

  
  /** 
   * @return String
   */
  @Override
  public String toString() {
    
    return (
      "\n :: SERIE ::" +
      "\n Title: " +
      getSerie().getTitle() +
      "\n :: CHAPTER ::" +
      "\n Title: " +
      getTitle() +
      "\n Year: " +
      getYear() +
      "\n Creator: " +
      getCreator() +
      "\n Duration: " +
      getDuration()
    );
  }

  /**
   * It creates a list of chapters, each chapter has a title, genre, creator, duration, year, number
   * and a serie
   *
   * @param serie The serie that the chapters belong to.
   * @return An ArrayList of Chapter objects.
   */
  public static ArrayList<Chapter> makeChaptersList(Serie serie) {
    ArrayList<Chapter> chapters = new ArrayList<Chapter>();

    for (int i = 1; i <= 5; i++) {
      chapters.add(
        new Chapter(
          "Capituo " + i,
          "genero " + i,
          "creator" + i,
          45,
          (short) (2017 + i),
          i,
          serie
        )
      );
    }

    return chapters;
  }

  @Override
  public void view() {
    
    super.view();
    ArrayList<Chapter> chapters = getSerie().getChapters();
    int chapterViewedCounter = chapters
      .stream()
      .reduce(
        0,
        (acum, chapter) -> {
          if (chapter.getIsViewed()) {
            return acum + 1;
          }
          return acum;
        },
        Integer::sum
      );

    if (chapterViewedCounter == chapters.size()) {
      getSerie().view();
    }
  }
}
