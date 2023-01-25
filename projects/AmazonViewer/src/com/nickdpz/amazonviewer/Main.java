package com.nickdpz.amazonviewer;

import com.nickdpz.amazonviewer.model.Book;
import com.nickdpz.amazonviewer.model.Chapter;
import com.nickdpz.amazonviewer.model.Magazine;
import com.nickdpz.amazonviewer.model.Movie;
import com.nickdpz.amazonviewer.model.Serie;
import com.nickdpz.makereport.Report;
import com.nickdpz.util.AmazonUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Main {

  /**
   * The function showMenu() is called from the main() function
   */
  public static void main(String[] args) {
    showMenu();
  }

  public static void showMenu() {
    int exit = 0;
    do {
      System.out.println("\nBIENVENIDOS AMAZON VIEWER\n");
      System.out.println("Selecciona el número de la opción deseada");
      System.out.println("1. Movies");
      System.out.println("2. Series");
      System.out.println("3. Books");
      System.out.println("4. Magazines");
      System.out.println("5. Report");
      System.out.println("6. Report Today");
      System.out.println("0. Exit");

      //Leer la respuesta del usuario
      int response = AmazonUtil.validateUserResponseMenu(0, 6);

      switch (response) {
        case 0:
          //salir
          exit = 0;
          break;
        case 1:
          showMovies();
          break;
        case 2:
          showSeries();
          break;
        case 3:
          showBooks();
          break;
        case 4:
          showMagazines();
          break;
        case 5:
          makeReport();
          exit = 1;
          break;
        case 6:
          //Date date = new Date();
          makeReport(new Date());
          exit = 1;
          break;
        default:
          System.out.println("\n....¡¡Selecciona una opción!!....\n");
          exit = 1;
          break;
      }
    } while (exit != 0);
  }

  static ArrayList<Movie> movies = Movie.makeMoviesList();

  public static void showMovies() {
    int exit = 1;

    do {
      System.out.println("\n:: MOVIES ::\n");
      AtomicInteger atomicInteger = new AtomicInteger(1);

      movies.forEach((Movie movie) -> {
        System.out.println(
          atomicInteger.getAndIncrement() +
          1 +
          ". " +
          movie.getTitle() +
          " Visto: " +
          movie.isViewed()
        );
      });

      System.out.println("0. Regresar al Menu\n");

      //Leer Respuesta usuario
      int response = AmazonUtil.validateUserResponseMenu(0, movies.size());

      if (response == 0) {
        exit = 0;
        showMenu();
        break;
      }
      if (response > 0) {
        Movie movieSelected = movies.get(response - 1);
        movieSelected.view();
      }
    } while (exit != 0);
  }

  static ArrayList<Serie> series = Serie.makeSeriesList();

  public static void showSeries() {
    int exit = 1;

    do {
      System.out.println("\n:: SERIES ::\n");

      AtomicInteger atomicInteger = new AtomicInteger(1);

      series.forEach((Serie serie) -> {
        System.out.println(
          atomicInteger.getAndIncrement() +
          1 +
          ". " +
          serie.getTitle() +
          " Visto: " +
          serie.isViewed()
        );
      });

      for (int i = 0; i < series.size(); i++) {} //1. Serie 1

      System.out.println("0. Regresar al Menu\n");

      //Leer Respuesta usuario
      int response = AmazonUtil.validateUserResponseMenu(0, series.size());

      if (response == 0) {
        exit = 0;
        showMenu();
      }

      if (response > 0) {
        showChapters(series.get(response - 1).getChapters());
      }
    } while (exit != 0);
  }

  /**
   * @param chaptersOfSerieSelected
   */
  public static void showChapters(ArrayList<Chapter> chaptersOfSerieSelected) {
    int exit = 1;

    do {
      System.out.println("\n:: CHAPTERS ::\n");

      for (int i = 0; i < chaptersOfSerieSelected.size(); i++) { //1. Chapter 1
        System.out.println(
          i +
          1 +
          ". " +
          chaptersOfSerieSelected.get(i).getTitle() +
          " Visto: " +
          chaptersOfSerieSelected.get(i).isViewed()
        );
      }

      System.out.println("0. Regresar al Menu\n");

      //Leer Respuesta usuario
      int response = AmazonUtil.validateUserResponseMenu(
        0,
        chaptersOfSerieSelected.size()
      );

      if (response == 0) {
        exit = 0;
      }

      if (response > 0) {
        Chapter chapterSelected = chaptersOfSerieSelected.get(response - 1);
        chapterSelected.view();
      }
    } while (exit != 0);
  }

  static ArrayList<Book> books = Book.makeBookList();

  public static void showBooks() {
    int exit = 1;

    do {
      System.out.println("\n:: BOOKS ::\n");

      for (int i = 0; i < books.size(); i++) { //1. Book 1
        System.out.println(
          i +
          1 +
          ". " +
          books.get(i).getTitle() +
          " Leído: " +
          books.get(i).isReaded()
        );
      }

      System.out.println("0. Regresar al Menu\n");

      //Leer Respuesta usuario
      int response = AmazonUtil.validateUserResponseMenu(0, books.size());

      if (response == 0) {
        exit = 0;
        showMenu();
      }

      if (response > 0) {
        Book bookSelected = books.get(response - 1);
        bookSelected.view();
      }
    } while (exit != 0);
  }

  public static void showMagazines() {
    ArrayList<Magazine> magazines = Magazine.makeMagazineList();
    int exit = 0;
    do {
      System.out.println("\n:: MAGAZINES ::\n");

      for (int i = 0; i < magazines.size(); i++) { //1. Book 1
        System.out.println(i + 1 + ". " + magazines.get(i).getTitle());
      }

      System.out.println("0. Regresar al Menu\n");

      //Leer Respuesta usuario
      int response = AmazonUtil.validateUserResponseMenu(0, 0);

      if (response == 0) {
        exit = 0;
        showMenu();
      }
    } while (exit != 0);
  }

  public static void makeReport() {
    Report report = new Report();
    report.setNameFile("reporte");
    report.setExtension("txt");
    report.setTitle(":: VISTOS ::");

    StringBuilder contentReport = new StringBuilder();

    movies
      .stream()
      .filter(movie -> movie.getIsViewed())
      .forEach(movie -> {
        contentReport.append(movie.toString() + "\n");
      });

    Predicate<Chapter> getIsChapterViewed = (Chapter chapter) -> {
      return chapter.getIsViewed();
    };

    Consumer<Serie> serieEach = (Serie serie) -> {
      ArrayList<Chapter> chapters = serie.getChapters();
      chapters
        .stream()
        .filter(getIsChapterViewed)
        .forEach(chapter -> contentReport.append(chapter.toString() + "\n"));
    };

    series.stream().forEach(serieEach);

    books
      .stream()
      .filter(book -> book.getIsReaded())
      .forEach(book -> {
        contentReport.append(book.toString() + "\n");
      });

    report.setContent(contentReport.toString());
    report.makeReport();
    System.out.println("Reporte Generado\n");
  }

  /**
   * @param date
   */
  public static void makeReport(Date date) {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-h-m-s-S");
    String dateString = df.format(date);
    Report report = new Report();

    report.setNameFile("reporte" + dateString);
    report.setExtension("txt");
    report.setTitle(":: VISTOS ::");

    SimpleDateFormat dfNameDays = new SimpleDateFormat("E, W MMM Y");
    dateString = dfNameDays.format(date);
    StringBuilder contentReport = new StringBuilder(
      "Date: " + dateString + "\n\n\n"
    );

    movies
      .stream()
      .filter(movie -> movie.getIsViewed())
      .forEach(movie -> {
        contentReport.append(movie.toString() + "\n");
      });

    Predicate<Chapter> getIsChapterViewed = (Chapter chapter) -> {
      return chapter.getIsViewed();
    };

    Consumer<Serie> serieEach = (Serie serie) -> {
      ArrayList<Chapter> chapters = serie.getChapters();
      chapters
        .stream()
        .filter(getIsChapterViewed)
        .forEach(chapter -> contentReport.append(chapter.toString() + "\n"));
    };

    series.stream().forEach(serieEach);

    books
      .stream()
      .filter(book -> book.getIsReaded())
      .forEach(book -> {
        contentReport.append(book.toString() + "\n");
      });

    report.setContent(contentReport.toString());
    report.makeReport();

    System.out.println("Reporte Generado\n");
  }
}
