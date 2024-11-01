module org.example.javafx2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.javafx2 to javafx.fxml;
    exports org.example.javafx2;
}