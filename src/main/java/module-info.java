module com.joshkupka.development {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.joshkupka.development to javafx.fxml;
    exports com.joshkupka.development;
}