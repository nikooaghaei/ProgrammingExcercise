import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) throws IOException {
        Document newest = null;
        Document mostVoted = null;
        Document web = null;
        try {
          newest = Jsoup.connect("https://stackoverflow.com/questions/tagged/android?sort=newest").get();
          mostVoted = Jsoup.connect("https://stackoverflow.com/questions/tagged/android?sort=votes").get();
        }
        catch (Exception e) {
            //TODO
        }

        try {
            File input = new File("/apply/Ahmed Hassan/Excercise/ProgrammingExcercise/src/input.html");
            web = Jsoup.parse(input, "UTF-8");   //TODO handle baseURi
        }
        catch (IOException e) {
            //TODO
        }

        //10 newest
        ListIterator listIterator = newest.body().getElementsByClass("container").select("div#questions").listIterator();
        Elements questions = ((Element) listIterator.next()).children();
        ListIterator listIterator2 = questions.listIterator();
        Element p = web.select("p").first();
        putLinks(p, listIterator2);

        //10 best-voted
        listIterator = mostVoted.body().getElementsByClass("container").select("div#questions").listIterator();
        questions = ((Element) listIterator.next()).children();
        listIterator2 = questions.listIterator();
        p = web.select("p#second").first();
        putLinks(p, listIterator2);

        /*
        //adding extra feature of showing question tags (other than android)
        listIterator2 = questions.listIterator();
        Elements divs = web.select("div#tag");
        for (int counter = 10; counter>0;counter--){
            Element e1 = (Element) listIterator2.next();
            Elements tags = e1.select("a[href].post-tag");
            //tags.addClass("tags t-android t-android-layout t-user-interface t-dimension t-units-of-measurement");
            String href = tags.attr("abs:href");
            tags.attr("href", href);
            divs.next().html("tags: " + tags.toString());
        }
*/

        //save changes
        File output = new File("/apply/Ahmed Hassan/Excercise/ProgrammingExcercise/src/output.html");
        PrintWriter writer = new PrintWriter(output, "UTF-8");
        writer.println(web);
        writer.close();
    }

    public static void  putLinks(Element element, ListIterator listIterator) {
        for (int counter = 10; counter> 0; counter--){
            Element e1 = (Element) listIterator.next();
            Element e2 = e1.select("div.summary h3 a[href]").first();
            e2.removeClass("question-hyperlink");
            String href = e2.attr("abs:href");
            e2.attr("href", href);
            element.html(e2.toString());
            //System.out.println(element);
            element = element.nextElementSibling();
        }
    }
}
