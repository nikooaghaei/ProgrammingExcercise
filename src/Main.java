import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        Document doc = null;
        try {
          doc  = Jsoup.connect("https://stackoverflow.com/questions/tagged/android?sort=newest").get();
          //  System.out.println(doc);
        }
        catch (Exception e) {
            //TODO
        }

        ListIterator listIterator = doc.body().getElementsByClass("container").select("div#questions").listIterator();
        Elements questions = ((Element) listIterator.next()).children();
        ListIterator listIterator2 = questions.listIterator();
        for (int counter = 10; counter> 0; counter--){
            Element e = (Element) listIterator2.next();
            System.out.println(e.select("div.summary h3 a[href]"));
        }








    }
}
