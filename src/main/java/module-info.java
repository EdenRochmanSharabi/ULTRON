module com.example.ultron {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires ical4j;
    requires org.json;


    opens com.example.ultron to javafx.fxml;
    exports com.example.ultron;
}