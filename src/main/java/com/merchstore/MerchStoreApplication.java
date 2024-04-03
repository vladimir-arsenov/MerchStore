package com.merchstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MerchStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MerchStoreApplication.class, args);
    }

}

//insert into product (category, description, image_url, price, quantity, title)
//values ('apparel', 'T-shirt descriptoin', 'images/led_zeppelin_gray_tshirt.jpg', 50, 100, 'Led Zeppelin T-shirt gray')