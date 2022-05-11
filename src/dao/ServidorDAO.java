/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.PreparedStatement;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Campus;
import model.Servidor;

/**
 *
 * @author Aluno
 */
public class ServidorDAO {

    public void create(Servidor servidor) throws Exception {
        String sql = "INSERT INTO servidor (campus, nome, email, cargo, login, senha, perfil, horas_totais, cadastrado) VALUES (?,?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, servidor.getId_campus());
            pstm.setString(2, servidor.getNome());
            pstm.setString(3, servidor.getEmail());
            pstm.setString(4, servidor.getCargo());
            pstm.setString(5, servidor.getLogin());
            pstm.setString(6, servidor.getSenha());
            pstm.setInt(7, servidor.getPerfil());
            pstm.setDouble(8, servidor.getHorasTotais());
            Date date = Date.valueOf(LocalDate.now());
            pstm.setDate(9, date);

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> read() throws Exception {

        String sql = "SELECT *, c.nome FROM servidor AS s INNER JOIN campus c ON c.id = s.campus";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add( "Id: \t" + rset.getString(1) + "\n"
                        + "Nome: \t" + rset.getString(3) + "\n"
                        + "Campus: \t" + rset.getString(13) + "\n"
                        + "Email: \t" + rset.getString(4) + "\n"
                        + "Cargo: \t" + rset.getString(5) + "\n"
                        + "Login: \t" + rset.getString(6) + "\n"
                        + "Senha: \t" + rset.getString(7) + "\n"
                        + "Perfil: \t" + rset.getString(8) + "\n"
                        + "Horas: \t" + rset.getString(9) + "\n"
                        + "Cadastrado: \t" + rset.getString(10) + "\n"
                        + "Modificado: \t" + rset.getString(11) + "\n"
                        + "--------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return vetResult;
    }
    
    public List<String> readId() throws Exception {

        String sql = "SELECT *, c.nome FROM servidor AS s INNER JOIN campus c ON c.id = s.campus";

        List<String> vetResult = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                vetResult.add("=========================\n"
                        + "Id: " + rset.getString(1) + "\n"
                        + "Nome: " + rset.getString(3) + "\n"
                        + "Campus: " + rset.getString(12) + "\n"
                        + "=========================" + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return vetResult;
    }

    public void update(Servidor altServidor) throws Exception {;
        String sql = "UPDATE servidor SET campus=?, nome=?, email=?, "
                + "cargo=?, login=?, senha=?, perfil=?, horas_totais=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, altServidor.getId_campus());
            pstm.setString(2, altServidor.getNome());
            pstm.setString(3, altServidor.getEmail());
            pstm.setString(4, altServidor.getCargo());
            pstm.setString(5, altServidor.getLogin());
            pstm.setString(6, altServidor.getSenha());
            pstm.setInt(7, altServidor.getPerfil());
            pstm.setDouble(8, altServidor.getHorasTotais());

            Date date = Date.valueOf(LocalDate.now());
            pstm.setDate(9, date);

            pstm.setInt(10, altServidor.getId());

            pstm.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void delete(int idServidor) throws Exception {
        String sql = "DELETE FROM servidor WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setInt(1, idServidor);

            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public Servidor find(int idServidor) throws Exception {
        String sql = "SELECT * FROM servidor WHERE id = '" + idServidor + "'";

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            rset = pstm.executeQuery();

            while (rset.next()) {

                Servidor servidor = new Servidor();

                servidor.setId(rset.getInt("id"));
                servidor.setId_campus(rset.getInt("campus"));
                servidor.setNome(rset.getString("nome"));
                servidor.setEmail(rset.getString("email"));
                servidor.setCargo(rset.getString("cargo"));
                servidor.setLogin(rset.getString("login"));
                servidor.setSenha(rset.getString("senha"));
                servidor.setPerfil(rset.getInt("perfil"));
                servidor.setHorasTotais(rset.getDouble("horas_totais"));

                Date date = rset.getDate("cadastrado");
                LocalDate dataAtualizada = date.toLocalDate();
                servidor.setDtCriacao(dataAtualizada);

                date = rset.getDate("modificado");
                if (date != null) {
                    dataAtualizada = date.toLocalDate();
                    servidor.setDtModificacao(dataAtualizada);
                }
                return servidor;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return null;
    }

}
