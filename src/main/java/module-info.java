module lab5.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires org.apache.logging.log4j;
    requires spring.context;
    requires java.sql;

    opens ubb.scs.mpp to javafx.fxml, spring.core;
    exports ubb.scs.mpp;
}