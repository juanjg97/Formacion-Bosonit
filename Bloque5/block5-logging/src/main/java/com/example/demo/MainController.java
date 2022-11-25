package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class MainController {
    Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/")
    public String index() {
        logger.trace("Mensaje a nivel de TRACE");
        logger.debug("Mensaje a nivel de DEBUG");
        logger.info("Mensaje a nivel de INFO");
        logger.warn("Mensaje a nivel de WARNING");
        logger.error("Mensaje a nivel de ERROR");

        return "Hola! Mira los logs para ver los resultados";
    }
}
