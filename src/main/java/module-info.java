module com.example.signin {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.naming;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.oracle.database.jdbc;
    requires org.apache.commons.lang3;

    opens com.example.signin to javafx.fxml;
    exports com.example.signin;
}