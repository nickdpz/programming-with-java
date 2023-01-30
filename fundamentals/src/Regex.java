import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    String string = "documento (1).docx";
    String regex = "^(.+?)(?:\\s\\((\\d+)\\))?\\.(.+)$";

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(string);

    if (matcher.find()) {
      System.out.println(matcher.group(1)); // "documento"
      System.out.println(matcher.group(2)); // "1"
      System.out.println(matcher.group(3)); // "docx"
    }
  }
}
