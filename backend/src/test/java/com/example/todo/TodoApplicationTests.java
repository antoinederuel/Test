package com.example.todo;

import org.junit.jupiter.api.Disabled; // <--- Import nécessaire
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled // <--- ON DÉSACTIVE CE TEST. Le compilateur va l'ignorer.
class TodoApplicationTests {

    @Test
    void contextLoads() {
        // Ce test ne se lancera plus, donc plus d'erreur de connexion DB.
    }

}