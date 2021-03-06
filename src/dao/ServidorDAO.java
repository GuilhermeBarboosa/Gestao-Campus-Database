/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    private CampusDAO campusDAO = new CampusDAO();

    public void create(Servidor servidor) throws Exception {
        String sql = "INSERT INTO servidores (nome, campus, email, cargo, login, senha, perfil, horas_totais, cadastrado) VALUES (?,?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, servidor.getNome());
            pstm.setInt(2, servidor.getCampus().getId());
            pstm.setString(3, servidor.getEmail());
            pstm.setString(4, servidor.getCargo());
            pstm.setString(5, servidor.getLogin());
            pstm.setString(6, servidor.getSenha());
            pstm.setInt(7, servidor.getPerfil());
            pstm.setDouble(8, servidor.getHorasTotais());
            Date date = Date.valueOf(servidor.getDtCriacao());
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

    public List<Servidor> read() throws Exception {

        String sql = "SELECT * FROM servidores";

        List<Servidor> vetResult = new ArrayList();

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
                servidor.setNome(rset.getString("nome"));

                Campus campus = campusDAO.find(rset.getInt("campus"));
                servidor.setCampus(campus);

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

                vetResult.add(servidor);
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
        String sql = "UPDATE servidores SET nome=?, campus=?, email=?, "
                + "cargo=?, login=?, senha=?, perfil=?, horas_totais=?, modificado=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setString(1, altServidor.getNome());
            pstm.setInt(2, altServidor.getCampus().getId());
            pstm.setString(3, altServidor.getEmail());
            pstm.setString(4, altServidor.getCargo());
            pstm.setString(5, altServidor.getLogin());
            pstm.setString(6, altServidor.getSenha());
            pstm.setInt(7, altServidor.getPerfil());
            pstm.setDouble(8, altServidor.getHorasTotais());

            Date date = Date.valueOf(altServidor.getDtModificacao());
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
        String sql = "DELETE FROM servidores WHERE id = ?";
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
        String sql = "SELECT * FROM servidores WHERE id = '" + idServidor + "'";

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
                servidor.setNome(rset.getString("nome"));

                Campus campus = campusDAO.find(rset.getInt("campus"));
                servidor.setCampus(campus);

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

    public void updateHours(Servidor servAux, double horasSemanais, int idServidor) throws SQLException {
        String sql = "UPDATE servidores SET horas_totais=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setDouble(1, (servAux.getHorasTotais() + horasSemanais));
            pstm.setInt(2, idServidor);

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

    public void removeHours(Servidor servAux, double horasSemanais, int idServidor) throws SQLException {
        String sql = "UPDATE servidores SET horas_totais=?"
                + "where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySql();

            pstm = (PreparedStatement) conn.prepareStatement(sql);

            pstm.setDouble(1, (servAux.getHorasTotais() - horasSemanais));
            pstm.setInt(2, idServidor);

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

}
