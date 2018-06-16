/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package easyschool.model.DAO;

import com.mysql.jdbc.Statement;
import easyschool.model.Aluno;
import easyschool.model.Professor;
import easyschool.model.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public class ProfessorDAO {

    private static ProfessorDAO instance;
    Connection connection;

    public static ProfessorDAO getInstance() {
        if (instance == null) {
            instance = new ProfessorDAO();
        }
        return instance;
    }

    public ProfessorDAO() {
        try {
            this.connection = DBConnection.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean cadastrarProfessor(Professor professor) {
        boolean cadastroSucesso;
        cadastroSucesso = false;
        String query = "insert into professor (matricula, nome, email, telefone, senha, aprovado)"
                + " values (?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = (PreparedStatement) connection.prepareStatement(query);

            preparedStmt.setInt(1, professor.getMatricula());
            preparedStmt.setString(2, professor.getName());
            preparedStmt.setString(3, professor.getEmail());
            preparedStmt.setString(4, professor.getTelefone());
            preparedStmt.setString(5, professor.getSenha());
            preparedStmt.setBoolean(6, false);

            // execute the preparedstatement
            int sucesso = preparedStmt.executeUpdate();
            if (sucesso == 1) {
                cadastroSucesso = true;
            }
            System.out.println("Resultado da inserção no banco de professor " + cadastroSucesso);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cadastroSucesso;
    }

    public Professor buscaProfessor(String matriculaDigitada) {
        Professor professor = null;
        String query = "select * from professor WHERE matricula ='" + matriculaDigitada + "'";
        try {
            Statement st = (Statement) connection.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                boolean aprovado = rs.getBoolean("aprovado");
                if (!aprovado) {
                    return professor;
                }
                int matricula = rs.getInt("matricula");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                String senha = rs.getString("senha");
                String mensagem = rs.getString("mensagem");

                professor = new Professor();
                professor.setName(nome);
                professor.setEmail(email);
                professor.setTelefone(telefone);
                professor.setMatricula(matricula);
                professor.setSenha(senha);
                professor.setMensagem(mensagem);

                st.close();
            }
        } catch (Exception e) {
        }
        return professor;

    }

    public Professor loginProfessor(String matriculaDigitada, String senhaDigitada) {
        Professor professor = buscaProfessor(matriculaDigitada);
        if (professor == null) {
            return professor;
        }
        if (professor.getSenha().equals(senhaDigitada)) {
            return professor;
        }

        return null;

    }

    public boolean editarPerfilProfessor(Professor professor) {
        boolean updateSucesso = false;
        String query = "update professor set nome = ?, email = ?, telefone = ?, senha = ? where matricula = '" + professor.getMatricula() + "'";

        try {
            com.mysql.jdbc.PreparedStatement preparedStmt = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            preparedStmt.setString(1, professor.getName());
            preparedStmt.setString(2, professor.getEmail());
            preparedStmt.setString(3, professor.getTelefone());
            preparedStmt.setString(4, professor.getSenha());

            int sucesso = preparedStmt.executeUpdate();
            if (sucesso == 1) {
                updateSucesso = true;
            }

        } catch (Exception e) {
            return false;
        }

        return updateSucesso;
    }

    public boolean editarMensagem(Professor professor) {
        boolean updateSucesso = false;
        String query = "update professor set mensagem = ? where matricula = '" + professor.getMatricula() + "'";

        try {
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
            if (professor.getMensagem() == null) {
                preparedStmt.setNull(1, 0);
            } else {
                preparedStmt.setString(1, professor.getMensagem());
            }
            int sucesso = preparedStmt.executeUpdate();
            if (sucesso == 1) {
                updateSucesso = true;
            }

        } catch (Exception e) {
            return false;
        }

        return updateSucesso;
    }

    public ArrayList<Professor> buscarProfessoresPendentes() {
        ArrayList professores = new ArrayList<Professor>();
        String query = "select * from professor WHERE aprovado = false";
        try {
            Statement st = (Statement) connection.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                int matricula = rs.getInt("matricula");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                String senha = rs.getString("senha");
                String mensagem = rs.getString("mensagem");

                Professor professor = new Professor();
                professor.setName(nome);
                professor.setEmail(email);
                professor.setTelefone(telefone);
                professor.setMatricula(matricula);
                professor.setSenha(senha);
                professor.setMensagem(mensagem);

                professores.add(professor);

            }
            st.close();
        } catch (Exception e) {
        }
        return professores;

    }

    public boolean aprovaProfessor(Professor professor) {
        boolean updateSucesso;
        updateSucesso = false;
        String sql = "update professor set aprovado = ?"
                + " where matricula = ?";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = (PreparedStatement) connection.prepareStatement(sql);

            preparedStmt.setBoolean(1, true);
            preparedStmt.setInt(2, professor.getMatricula());

            // execute the preparedstatement
            int sucesso = preparedStmt.executeUpdate();
            if (sucesso == 1) {
                updateSucesso = true;
            }
            System.out.println("Resultado da aprovação de professor no banco de " + updateSucesso);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return updateSucesso;
    }

    public boolean deletarProfessor(int matricula) {
        try {
            String query = "DELETE FROM professor WHERE matricula = ? ";
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, matricula);
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
