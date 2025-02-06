module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
    exports org.example.demo.controller;
    opens org.example.demo.controller;
    exports org.example.demo.dao;
    exports org.example.demo.dto;
    exports org.example.demo.dto.tm;
}