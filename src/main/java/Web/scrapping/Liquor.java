//package Web.scrapping;
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//import org.jsoup.select.Elements;
//
//import java.io.IOException;
//
//public class Liquor {
//    public static void main(String[] args) throws IOException {
//        Liquor run = new Liquor("Jack Daniels");
//
//    }
//    public Liquor(String query) throws IOException {
//        String url = "https://drankdozijn.nl/merk/" + query.replaceAll(" ", "-");
//        Document web = Jsoup.connect(url).get();
//        String cssSelector = "section.col-6.col-sm-6.col-md-4.col-lg-02.gutter[data-v-0a20bec4]";
//        Element sectionElement = web.select(cssSelector).first();
//        assert sectionElement != null;
//        Elements productElements = sectionElement.select("div.product-item");
//        for (Element element : productElements) {
//            Element nameElement = element.selectFirst("h3.product-title a");
//            Element priceElement = element.selectFirst("div.price span");
//            if (nameElement != null && priceElement != null) {
//                String name = nameElement.text();
//                String price = priceElement.text();
//                System.out.println(name + ": " + price);
//            }
//        }
//    }
//}
////col-6 col-sm-6 col-md-4 col-lg-02 gutter
////col-6 col-sm-6 col-md-4 col-lg-02 gutter