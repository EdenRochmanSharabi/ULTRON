module com.example.ultron {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ultron to javafx.fxml;
    exports com.example.ultron;
}