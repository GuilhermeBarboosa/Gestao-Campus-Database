/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ComissaoDAO;
import dao.ServidorDAO;
import dao.VinculoDAO;
import java.util.List;
import java.util.Scanner;
import model.Comissao;
import model.Servidor;
import model.Vinculo;
import view.Gui;
import view.VinculoView;

/**
 *
 * @author Aluno
 */
public class VinculoServidorComissaoController {

    private final VinculoView vinculoView = new VinculoView();

    private final VinculoDAO vinculoDAO = new VinculoDAO();
    private final ServidorDAO servidorDAO = new ServidorDAO();
    private final ComissaoDAO comissaoDAO = new ComissaoDAO();
    
    private Gui GUI = new Gui();
    private int opcCrud;
    private int auxLoc;
    private Scanner ler = new Scanner(System.in);

    public void menu() throws Exception {
        System.out.println("VINCULO DE SERVIDOR A COMISSÃO");

        List<Vinculo> vetResult = vinculoDAO.read();

        opcCrud = GUI.menu();

        try {
            switch (opcCrud) {
                case 1:
                    Vinculo vinc = vinculoView.criarVinculo(servidorDAO, comissaoDAO);
                    if (vinc != null) {
                        Servidor servAux = servidorDAO.find(vinc.getServidor().getId());
                        Comissao comAux = comissaoDAO.find(vinc.getComissao().getId());
                        servidorDAO.updateHours(servAux, comAux.getHorasSemanais(), vinc.getServidor().getId());

                        vinculoDAO.create(vinc);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    vinculoView.mostrarIdTodosVinculos(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Vinculo vinAlt = vinculoDAO.find(auxLoc);
                    if (vinAlt != null) {
                        Servidor servAux = servidorDAO.find(vinAlt.getServidor().getId());
                        Comissao comAux = comissaoDAO.find(vinAlt.getComissao().getId());
                        servidorDAO.removeHours(servAux, comAux.getHorasSemanais(), vinAlt.getServidor().getId());

                        vinculoDAO.update(vinculoView.modifVinculo(vinAlt, servidorDAO, comissaoDAO));

                        servAux = servidorDAO.find(vinAlt.getServidor().getId());
                        comAux = comissaoDAO.find(vinAlt.getComissao().getId());
                        servidorDAO.updateHours(servAux, comAux.getHorasSemanais(), vinAlt.getServidor().getId());

                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    vinculoView.mostrarTodosVinculos(vetResult);
                    break;
                case 4:
                    vinculoView.mostrarIdTodosVinculos(vetResult);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());

                    vinAlt = vinculoDAO.find(auxLoc);
                    if (vinAlt != null) {
                        Servidor servAux = servidorDAO.find(vinAlt.getServidor().getId());
                        Comissao comAux = comissaoDAO.find(vinAlt.getComissao().getId());
                        servidorDAO.removeHours(servAux, comAux.getHorasSemanais(), vinAlt.getServidor().getId());

                        vinculoDAO.delete(auxLoc);
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
