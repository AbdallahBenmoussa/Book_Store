module com.example.tpfinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.tpfinal to javafx.fxml;
    exports com.example.tpfinal;
    exports com.example.tpfinal.Controller;
    opens com.example.tpfinal.Controller to javafx.fxml;
}