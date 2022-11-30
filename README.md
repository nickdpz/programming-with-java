# programming-with-java-sprint

This project is a compilation of examples code written in java.

Update jvm

```sh
$ sudo update-alternatives --config java
```

## Examples

- Create a array

```java
Foo f[] = new Foo[20]
F[2]= new Foo("Papa")
```

- create a list

```java
ArrayList<Serie> series = new ArrayList<Serie>();
series.add(new Serie(""));
```

- Declare a attribute readonly

```java
class Serie extends Film {
    public final int CONSTANTS_NAME;
}
```

- Inheritance and call methods of class father

```java
public class Magazine extends Publication {

	private int id;

	public String getId() {
		String title = super.getTitle();
		return title + Integer.toString(id);
	}
}
```

- Apply polymorphism


```java
public class Publication {
	
	private String title;
	private Date editionDate;
	private String editorial;
	private String[] authors;
	
	
	public Publication(String title, Date edititionDate, String editorial) {
		super();
		this.title = title;
		this.editionDate = edititionDate;
		this.editorial = editorial;
	}

    public String toPrint() {
        return "This is a new publication"
    }

}
```


```java
public class Book extends Publication implements IDisplayable {
	private int id;
	private String isbn;
	private boolean readed;
	private int timeReaded;
	
	
	public Book(String title, Date edititionDate, String editorial, String[] authors) {
		super(title, edititionDate, editorial);
		// TODO Auto-generated constructor stub
		setAuthors(authors);
	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		String detailBook = "\n :: BOOK ::" + 
							"\n Title: " + getTitle() +
							"\n Editorial: " + getEditorial() + 
							"\n Edition Date: " + getEdititionDate() +
							"\n Authors: ";
		for (int i = 0; i < getAuthors().length; i++) {
			detailBook += "\t" + getAuthors()[i] + " ";
		}
		return  detailBook;
	}
}
```

- Create interface, apply functionality without inheritance 

```java
import java.util.Date;

public interface IDisplayable {
	Date startToSee(Date dateI);
	void stopToSee(Date dateI, Date dateF);
	
}
```