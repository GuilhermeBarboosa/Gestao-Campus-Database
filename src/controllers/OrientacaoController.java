/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.OrientacaoDAO;
import dao.ServidorDAO;
import java.util.List;
import java.util.Scanner;
import model.Orientacao;
import model.Servidor;
import view.Gui;
import view.OrientacaoView;

/**
 *
 * @author Aluno
 */
public class OrientacaoController {

    private final OrientacaoView orientacaoView = new OrientacaoView();
    private final OrientacaoDAO orientacaoDAO = new OrientacaoDAO();
    private final ServidorDAO servidorDAO = new ServidorDAO();

    private Gui GUI = new Gui();
    private int opcCrud;
    private int auxLoc;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("ORIENTAÇÃO");
        opcCrud = GUI.menu();

        List<Orientacao> vetResult = orientacaoDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Orientacao or = orientacaoView.criarOrientacao(servidorDAO);

                    if (or != null) {
                        orientacaoDAO.create(or);
                        Servidor servAux = servidorDAO.find(or.getServidor().getId());
                        servidorDAO.updateHours(servAux, or.getHorasSemanais(), or.getServidor().getId());
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    orientacaoView.mostrarIdTodasOrientacoes(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Orientacao orAlt = orientacaoDAO.find(auxLoc);

                    if (orAlt != null) {
                        Servidor servAux = servidorDAO.find(orAlt.getServidor().getId());
                        servidorDAO.removeHours(servAux, orAlt.getHorasSemanais(), orAlt.getServidor().getId());

                        orientacaoDAO.update(orientacaoView.modifOrientacao(orAlt, servidorDAO));

                        servAux = servidorDAO.find(orAlt.getServidor().getId());
                        servidorDAO.updateHours(servAux, orAlt.getHorasSemanais(), orAlt.getServidor().getId());
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    orientacaoView.mostrarTodasOrientacoes(vetResult);
                    break;
                case 4:
                    orientacaoView.mostrarIdTodasOrientacoes(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());

                    orAlt = orientacaoDAO.find(auxLoc);
                    if (orAlt != null) {
                        Servidor servAux = servidorDAO.find(orAlt.getServidor().getId());
                        servidorDAO.removeHours(servAux, orAlt.getHorasSemanais(), orAlt.getServidor().getId());

                        orientacaoDAO.delete(auxLoc);
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
