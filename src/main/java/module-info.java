module com.unilabs.vehiclerental {
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
    requires javafx.graphics;

    // ¡Nuevo!
    opens com.unilabs.vehiclerental.ViewController to javafx.fxml;
    opens com.unilabs.vehiclerental to javafx.fxml;
    exports com.unilabs.vehiclerental;
    exports com.unilabs.vehiclerental.ViewController to javafx.fxml;
}