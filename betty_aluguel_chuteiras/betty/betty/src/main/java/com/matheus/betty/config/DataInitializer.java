package com.matheus.betty.config;

import org.javalite.activejdbc.Base;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Ligar à base de dados SQLite...");
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:aluguel_chuteiras.db", "", "");

            String sqlCreate = "CREATE TABLE IF NOT EXISTS chuteiras (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "modelo VARCHAR(100), " +
                    "tamanho VARCHAR(10), " +
                    "cliente VARCHAR(100), " +
                    "valor_diaria VARCHAR(30), " +
                    "status VARCHAR(30))";
            Base.exec(sqlCreate);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Base.close();
            System.out.println("Ligação encerrada.");
        }
    }
}
