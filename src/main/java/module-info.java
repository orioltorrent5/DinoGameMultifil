module com.example.dinogamemultifil {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dinogamemultifil to javafx.fxml;
    exports com.example.dinogamemultifil;
}