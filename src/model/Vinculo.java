/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Vinculo {

    private int id;
    private int id_servidor;
    private int id_comissao;
    private String papel;
    private LocalDate dtEntrada;
    private LocalDate dtSaida;
    private LocalDate dtCriacao;
    private LocalDate dtModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_servidor() {
        return id_servidor;
    }

    public void setId_servidor(int id_servidor) {
        this.id_servidor = id_servidor;
    }

    public int getId_comissao() {
        return id_comissao;
    }

    public void setId_comissao(int id_comissao) {
        this.id_comissao = id_comissao;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public LocalDate getDtEntrada() {
        return dtEntrada;
    }

    public void setDtEntrada(LocalDate dtEntrada) {
        this.dtEntrada = dtEntrada;
    }

    public LocalDate getDtSaida() {
        return dtSaida;
    }

    public void setDtSaida(LocalDate dtSaida) {
        this.dtSaida = dtSaida;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDate dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public LocalDate getDtModificacao() {
        return dtModificacao;
    }

    public void setDtModificacao(LocalDate dtModificacao) {
        this.dtModificacao = dtModificacao;
    }
//
//    @Override
//    public String toString() {
//        String aux = "";
//        aux += "ID: " + this.id + "\n";
//
//        if (comissao != null) {
//            aux += "Comissao: " + comissao.getNameComissao() + "\n";
//        }
//
//        if (servidor != null) {
//            aux += "Servidor: " + servidor.getNome() + "\n";
//        }
//
//        aux += "Papel: " + this.papel + "\n";
//        aux += "Data de entrada: " + this.dtEntrada + "\n";
//        aux += "Data de saida: " + this.dtSaida + "\n";
//        aux += "Data de criação: " + this.dtCriacao + "\n";
//        if (this.getDtModificacao() != null) {
//            aux += "ID: " + this.dtModificacao + "\n";
//        }
//        aux += "\n\n";
//        return aux;
//    }

}