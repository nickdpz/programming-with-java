package com.nickdpz.amazonviewer.model;

import com.nickdpz.util.AmazonUtil;
import java.util.ArrayList;
import java.util.Date;

public class Book extends Publication implements IDisplayable {

  private int id;
  private String isbn;
  private boolean readed;
  private int timeReaded;
  private ArrayList<Page> pages;

  public Book(
    String title,
    Date edititionDate,
    String editorial,
    String[] authors,
    ArrayList<Page> pages
  ) {
    super(title, edititionDate, editorial);
    setAuthors(authors);
    this.pages = pages;
  }

  public int getId() {
    return id;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String isReaded() {
    String leido = "";
    if (readed == true) {
      leido = "Sí";
    } else {
      leido = "No";
    }

    return leido;
  }

  public void setReaded(boolean readed) {
    this.readed = readed;
  }

  public boolean getIsReaded() {
    return readed;
  }

  public int getTimeReaded() {
    return timeReaded;
  }

  public void setTimeReaded(int timeReaded) {
    this.timeReaded = timeReaded;
  }

  public ArrayList<Page> getPages() {
    return this.pages;
  }

  public void setPages(ArrayList<Page> timeReaded) {
    this.pages = timeReaded;
  }

  @Override
  public String toString() {
    String detailBook =
      "\n :: BOOK ::" +
      "\n Title: " +
      getTitle() +
      "\n Editorial: " +
      getEditorial() +
      "\n Edition Date: " +
      getEdititionDate() +
      "\n Authors: ";
    for (int i = 0; i < getAuthors().length; i++) {
      detailBook += "\t" + getAuthors()[i] + " ";
    }
    return detailBook;
  }

  @Override
  public Date startToSee(Date dateI) {
    return dateI;
  }

  @Override
  public void stopToSee(Date dateI, Date dateF) {
    if (dateF.getTime() > dateI.getTime()) {
      setTimeReaded((int) (dateF.getTime() - dateI.getTime()));
    } else {
      setTimeReaded(0);
    }
  }

  public int getLastPageReaded() {
    int lastPageReaded = 0;
    for (int i = 0; i < pages.size(); i++) {
      if (pages.get(i).getReaded()) {
        lastPageReaded = pages.get(i).getNumber() - 1;
        if (i != 0) {
          lastPageReaded--;
        }
        break;
      } else if (i == pages.size() - 1) {
        lastPageReaded = pages.size() - 1;
      }
    }
    return lastPageReaded;
  }

  public void view() {
    Date dateI = startToSee(new Date());
    int lastPageReaded = getLastPageReaded();
    System.out.println(lastPageReaded);
    do {
      System.out.println("\n :: " + getTitle() + " :: ");
      System.out.println(
        "\n...Page " + pages.get(lastPageReaded).getNumber() + "..."
      );
      System.out.println(".........");
      System.out.println(pages.get(lastPageReaded).getContent());
      System.out.println(".........\n");
      pages.get(lastPageReaded).setReaded(true);
      if (lastPageReaded != 0) {
        System.out.println("1. Anterior Página");
      }
      System.out.println("2. Siguiente Página");
      System.out.println("0. Cerrar Libro");
      int option = AmazonUtil.validateUserResponseMenu(0, 2);
      switch (option) {
        case 2:
          lastPageReaded++;
          break;
        case 1:
          if (lastPageReaded != 0) {
            lastPageReaded--;
          } else {
            System.out.println("\nSeleccione una opción valida.\n");
          }
          break;
        case 0:
          lastPageReaded = pages.size();
          break;
      }
    } while (lastPageReaded < pages.size());
    

    stopToSee(dateI, new Date());
    setReaded(true);
    System.out.println("Leíste: " + toString());
    System.out.println("Por: " + getTimeReaded() + " milisegundos");
  }

  public static ArrayList<Book> makeBookList() {
    ArrayList<Book> books = new ArrayList<Book>();
    String[] authors = new String[3];
    for (int i = 0; i < 3; i++) {
      authors[i] = "author " + i;
    }

    ArrayList<Page> pages = new ArrayList<Page>();
    int pagina = 0;
    for (int i = 0; i < 3; i++) {
      pagina = i + 1;
      pages.add(new Book.Page(pagina, "El contenido de la página " + pagina));
    }

    for (int i = 1; i <= 5; i++) {
      books.add(
        new Book("Book " + i, new Date(), "editorial " + i, authors, pages)
      );
    }

    return books;
  }

  public static class Page {

    private int id;
    private int number;
    private String content;
    private boolean readed;

    public Page(int number, String content) {
      super();
      this.number = number;
      this.content = content;
    }

    public static ArrayList<Page> makePagesForBook() {
      ArrayList<Page> pages = new ArrayList<>();
      for (int i = 1; i < 7; i++) {
        pages.add(
          new Page(i, "Content...\nContent...\nContent...\nContent...")
        );
      }
      return pages;
    }

    public String isReaded() {
      return (this.readed) ? "Yes" : "No";
    }

    public boolean getReaded() {
      return this.readed;
    }

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public int getNumber() {
      return number;
    }

    public void setNumber(int number) {
      this.number = number;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public void setReaded(boolean readed) {
      this.readed = readed;
    }
  }
}
