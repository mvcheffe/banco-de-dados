package com.matheus.betty.controllers;

import com.matheus.betty.models.Chuteira;
import org.javalite.activejdbc.Base;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ChuteirasController {

    @GetMapping("/")
    public String home() {
        return "redirect:/chuteiras/index";
    }

    @GetMapping("/chuteiras/index")
    public String index(ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:aluguel_chuteiras.db", "", "");

            List<Chuteira> chuteiras = new ArrayList<>(Chuteira.findAll());
            model.addAttribute("chuteiras", chuteiras);
            model.addAttribute("size", Chuteira.count());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }

        return "chuteiras/index";
    }

    @PostMapping("/chuteiras/salvar")
    public String salvar(@RequestParam String modelo,
                         @RequestParam String tamanho,
                         @RequestParam String cliente,
                         @RequestParam String valor_diaria,
                         @RequestParam String status) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:aluguel_chuteiras.db", "", "");

            Chuteira c = new Chuteira();
            c.set("modelo", modelo,
                  "tamanho", tamanho,
                  "cliente", cliente,
                  "valor_diaria", valor_diaria,
                  "status", status);
            c.saveIt();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }

        return "redirect:/chuteiras/index";
    }

    @GetMapping("/chuteiras/editar/{id}")
    public String editar(@PathVariable("id") Long id, ModelMap model) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:aluguel_chuteiras.db", "", "");

            Chuteira chuteira = Chuteira.findById(id);
            model.addAttribute("chuteira", chuteira);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }

        return "chuteiras/editar";
    }

    @PostMapping("/chuteiras/atualizar/{id}")
    public String atualizar(@PathVariable("id") Long id,
                            @RequestParam String modelo,
                            @RequestParam String tamanho,
                            @RequestParam String cliente,
                            @RequestParam String valor_diaria,
                            @RequestParam String status) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:aluguel_chuteiras.db", "", "");

            Chuteira chuteira = Chuteira.findById(id);
            if (chuteira != null) {
                chuteira.set("modelo", modelo,
                             "tamanho", tamanho,
                             "cliente", cliente,
                             "valor_diaria", valor_diaria,
                             "status", status);
                chuteira.saveIt();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }

        return "redirect:/chuteiras/index";
    }

    @GetMapping("/chuteiras/deletar/{id}")
    public String deletar(@PathVariable("id") Long id) {
        try {
            Base.open("org.sqlite.JDBC", "jdbc:sqlite:aluguel_chuteiras.db", "", "");

            Chuteira chuteira = Chuteira.findById(id);
            if (chuteira != null) {
                chuteira.delete();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } finally {
            Base.close();
        }

        return "redirect:/chuteiras/index";
    }
}
