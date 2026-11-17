package com.matheus.betty.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("chuteiras")
public class Chuteira extends Model {

    public String getModelo() {
        return getString("modelo");
    }

    public void setModelo(String modelo) {
        set("modelo", modelo);
    }

    public String getTamanho() {
        return getString("tamanho");
    }

    public void setTamanho(String tamanho) {
        set("tamanho", tamanho);
    }

    public String getCliente() {
        return getString("cliente");
    }

    public void setCliente(String cliente) {
        set("cliente", cliente);
    }

    public String getValor_diaria() {
        return getString("valor_diaria");
    }

    public void setValor_diaria(String valor_diaria) {
        set("valor_diaria", valor_diaria);
    }

    public String getStatus() {
        return getString("status");
    }

    public void setStatus(String status) {
        set("status", status);
    }
}
