//package Web.scrapping;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.nodes.Element;
//
//import java.io.IOException;
//
//public class Stock {
//    public static void main(String[] args) throws Exception {
//        String symbol = "AAPL";
//        Stock run = new Stock(symbol);
//    }
//    public Stock(String symbol) throws IOException {
//        String url = "https://finance.yahoo.com/quote/GOOG?p="+symbol+"&.tsrc=fin-srch&guccounter=1";
//        Document web = Jsoup.connect(url).get();
//        Element symbolPrice = web.selectFirst("span[data-reactid='50'] > span");
//        if (symbolPrice != null){
//            String price = symbolPrice.text();
//            System.out.println("Current price of "+symbol+ " "+ price);
//        }
//
//    }
//}
