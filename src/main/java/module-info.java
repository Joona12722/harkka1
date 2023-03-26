module com.example.harkka1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.harkka1 to javafx.fxml;
    exports com.example.harkka1;
}