package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}


// TODO: 1) create 'spring_boot' database with (admin)root (password)root (see config for detail)
// TODO: 2) start 'DemoApplication'
// TODO: 3) run 'add_admin_to_db.sql'(resources) after (first) application start
