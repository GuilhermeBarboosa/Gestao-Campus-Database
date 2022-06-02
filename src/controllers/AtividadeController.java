/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import model.Atividade;
import model.Servidor;
import view.AtividadeView;
import dao.Default;

/**
 *
 * @author Aluno
 */
public class AtividadeController extends DefaultController implements Default{

    private final AtividadeView atividadeView = new AtividadeView();

    public void menu() throws Exception {
        System.out.println("ATIVIDADE");
        opcCrud = GUI.menu();

        List<Servidor> servidorVet = servidorDAO.read();

        List<String> vetResultId = atividadeDAO.readId();
        List<String> vetResult = atividadeDAO.read();

        try {
            switch (opcCrud) {
                case 1:
                    Atividade at = atividadeView.criarAtividade(servidorVet);

                    if (at != null) {
                        Servidor servAux = servidorDAO.find(at.getServidor().getId());
                        servidorDAO.updateHours(servAux, at.getHorasSemanais(), at.getServidor().getId());

                        atividadeDAO.create(at);
                    } else {
                        GUI.error();
                    }
                    break;
                case 2:
                    atividadeView.mostrarAtividades(vetResultId);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());
                    Atividade atAlt = atividadeDAO.find(auxLoc);

                    if (atAlt != null) {
                        Servidor servAux = servidorDAO.find(atAlt.getServidor().getId());
                        servidorDAO.removeHours(servAux, atAlt.getHorasSemanais(), atAlt.getServidor().getId());

                        atAlt = atividadeView.modifAtividade(atAlt, servidorVet);

                        servAux = servidorDAO.find(atAlt.getServidor().getId());
                        servidorDAO.updateHours(servAux, atAlt.getHorasSemanais(), atAlt.getServidor().getId());

                        atividadeDAO.update(atAlt);
                        GUI.sucess();
                    } else {
                        GUI.error();
                    }
                    break;
                case 3:
                    atividadeView.mostrarAtividades(vetResult);
                    break;
                case 4:
                    atividadeView.mostrarAtividades(vetResultId);
                    GUI.printID();
                    auxLoc = Integer.parseInt(ler.nextLine());

                    Atividade removeAtividade = atividadeDAO.find(auxLoc);
                    if (removeAtividade != null) {
                        Servidor servAux = servidorDAO.find(removeAtividade.getServidor().getId());
                        servidorDAO.removeHours(servAux, removeAtividade.getHorasSemanais(), removeAtividade.getServidor().getId());

                        atividadeDAO.delete(auxLoc);
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
