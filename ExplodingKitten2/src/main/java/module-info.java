module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.app to javafx.fxml;
    exports com.example.app;
    exports com.example.controllers.multi;
    opens com.example.controllers.multi to javafx.fxml;
}