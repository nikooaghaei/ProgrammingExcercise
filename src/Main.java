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
            File input = new File("D:\\apply\\Ahmed Hassan\\Excercise\\ProgrammingExcercise\\src\\input.html");
            web = Jsoup.parse(input, "UTF-8");   //TODO handle baseURi
        }
        catch (IOException e) {
            //TODO
        }

        ListIterator listIterator = newest.body().getElementsByClass("container").select("div#questions").listIterator();
        Elements questions = ((Element) listIterator.next()).children();
        ListIterator listIterator2 = questions.listIterator();
        //10 newest
        Element p = web.select("p").first();
        putLinks(p, listIterator2);

        //10 best-voted
        listIterator = mostVoted.body().getElementsByClass("container").select("div#questions").listIterator();
        questions = ((Element) listIterator.next()).children();
        listIterator2 = questions.listIterator();
        p = web.select("p#second").first();
        putLinks(p, listIterator2);


//        for (int counter = 20; counter> 0; counter--){
//            Element e = (Element) listIterator2.next();
//            //System.out.println("<p> https://stackoverflow.com" + e.select("div.summary h3 a[href]").toString());
//            //h2.html("<p> https://stackoverflow.com" + e.select("div.summary h3 a[href]").toString());
//            Element e2 = e.select("div.summary h3 a[href]").first();
//            e2.removeClass("question-hyperlink");
//            String href = e2.attr("abs:href");
//            e2.attr("href", href);
//            p.html(e2.toString());
//            System.out.println(p);
//            p = p.nextElementSibling();
            //most voted
//            if (p == null) p = web.select("p#second").first();
//            System.out.println(p);
           // System.out.println(e.select("div.summary h3 a[href]").toString());


        //save changes
        File output = new File("D:\\apply\\Ahmed Hassan\\Excercise\\ProgrammingExcercise\\src\\output.html");
        PrintWriter writer = new PrintWriter(output, "UTF-8");
        writer.println(web);
        writer.close();

        }

    public static void  putLinks(Element element, ListIterator listIterator) {
        for (int counter = 10; counter> 0; counter--){
            Element e1 = (Element) listIterator.next();
            //System.out.println("<p> https://stackoverflow.com" + e.select("div.summary h3 a[href]").toString());
            //h2.html("<p> https://stackoverflow.com" + e.select("div.summary h3 a[href]").toString());
            Element e2 = e1.select("div.summary h3 a[href]").first();
            e2.removeClass("question-hyperlink");
            String href = e2.attr("abs:href");
            e2.attr("href", href);
            element.html(e2.toString());
            System.out.println(element);
            element = element.nextElementSibling();
        }
    }
}
