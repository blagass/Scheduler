module lagasse.scheduler {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens lagasse.scheduler to javafx.fxml;
    exports lagasse.scheduler;
    exports lagasse.scheduler.model;
    opens lagasse.scheduler.model to javafx.fxml;
    exports lagasse.scheduler.controller;
    opens lagasse.scheduler.controller to javafx.fxml;
}