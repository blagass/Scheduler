module lagasse.scheduler {
    requires javafx.controls;
    requires javafx.fxml;


    opens lagasse.scheduler to javafx.fxml;
    exports lagasse.scheduler;
}