/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyschool.model.DAO;

import com.mysql.jdbc.Statement;
import easyschool.model.Aluno;
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
public class AlunoDAO {

    private static AlunoDAO instance;
    Connection connection;

    public static AlunoDAO getInstance() {
        if (instance == null) {
            instance = new AlunoDAO();
        }
        return instance;
    }

    public AlunoDAO() {
        try {
            this.connection = DBConnection.getInstance().getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean cadastrarAluno(Aluno aluno) {
        boolean cadastroSucesso;
        cadastroSucesso = false;
        String query = "insert into aluno (matricula, nome, email, telefone, senha,curso,periodo)"
                + " values (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = (PreparedStatement) connection.prepareStatement(query);

            preparedStmt.setInt(1, aluno.getMatricula());
            preparedStmt.setString(2, aluno.getName());
            preparedStmt.setString(3, aluno.getEmail());
            preparedStmt.setString(4, aluno.getTelefone());
            preparedStmt.setString(5, aluno.getSenha());
            preparedStmt.setString(6, aluno.getCurso());
            preparedStmt.setString(7, aluno.getPeriodo());

            // execute the preparedstatement
            int sucesso = preparedStmt.executeUpdate();
            if (sucesso == 1) {
                cadastroSucesso = true;
            }
            System.out.println("Resultado da inserção no banco de aluno " + cadastroSucesso);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cadastroSucesso;
    }

    public Aluno buscarAluno(String matriculaDigitada) {

        Aluno aluno = null;
        String query = "select * from aluno WHERE matricula ='" + matriculaDigitada + "'";
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
                String curso = rs.getString("curso");
                String periodo = rs.getString("periodo");
                String senha = rs.getString("senha");

                aluno = new Aluno();
                aluno.setMatricula(matricula);
                aluno.setName(nome);
                aluno.setEmail(email);
                aluno.setTelefone(telefone);
                aluno.setCurso(curso);
                aluno.setPeriodo(periodo);
                aluno.setSenha(senha);

            }
            st.close();
        } catch (Exception e) {

        }
        return aluno;

    }

    public Aluno loginAluno(String matriculaDigitada, String senhaDigitada) {
        Aluno aluno = buscarAluno(matriculaDigitada);
        if (aluno == null) {
            return aluno;
        }
        if (aluno.getSenha().equals(senhaDigitada)) {
            return aluno;
        }
        return null;
    }

    public boolean editarPerfilAluno(Aluno aluno) {
        boolean cadastroSucesso = false;
        String query = "update aluno set nome = ?, email = ?, telefone = ?, senha = ? where matricula = '" + aluno.getMatricula() + "'";

        try {
            com.mysql.jdbc.PreparedStatement preparedStmt = (com.mysql.jdbc.PreparedStatement) connection.prepareStatement(query);
            preparedStmt.setString(1, aluno.getName());
            preparedStmt.setString(2, aluno.getEmail());
            preparedStmt.setString(3, aluno.getTelefone());
            preparedStmt.setString(4, aluno.getSenha());

            int sucesso = preparedStmt.executeUpdate();
            if (sucesso == 1) {
                cadastroSucesso = true;
            }

        } catch (Exception e) {
            return false;
        }

        return cadastroSucesso;
    }

    public boolean adicionarTurma(Aluno aluno, Turma turma) {
        boolean cadastroSucesso;
        if (alunoTemTurma(aluno, turma.getMateria()) != null) {
            return updateTurmaDeAluno(aluno, turma);
        }

        cadastroSucesso = false;
        String query = "insert into aluno_has_turma (aluno_matricula, turma_id)"
                + " values (?, ?)";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = (PreparedStatement) connection.prepareStatement(query);

            preparedStmt.setInt(1, aluno.getMatricula());
            preparedStmt.setInt(2, turma.getId());

            // execute the preparedstatement
            int sucesso = preparedStmt.executeUpdate();
            if (sucesso == 1) {
                cadastroSucesso = true;
            }
            System.out.println("Resultado da inserção de turma em aluno " + cadastroSucesso);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cadastroSucesso;
    }

    public boolean updateTurmaDeAluno(Aluno aluno, Turma turma) {
        boolean updateSucesso;

        updateSucesso = false;
        String query = "update aluno_has_turma set turma_id = ?"
                + " where aluno_matricula = ?";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = (PreparedStatement) connection.prepareStatement(query);

            preparedStmt.setInt(1, turma.getId());
            preparedStmt.setInt(2, aluno.getMatricula());

            // execute the preparedstatement
            int sucesso = preparedStmt.executeUpdate();
            if (sucesso == 1) {
                updateSucesso = true;
            }
            System.out.println("Resultado da inserção de turma em aluno " + updateSucesso);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return updateSucesso;
    }

    public Turma alunoTemTurma(Aluno aluno, String materiaDigitada) {
        Turma turma = null;
        String query = "select turma.id, turma.horario, turma.nome, turma.local,"
                + "turma.numero_alunos, turma.materia, turma.matricula_professor from aluno_has_turma, turma where aluno_has_turma.aluno_matricula = " + aluno.getMatricula()
                + " and turma.materia = '" + materiaDigitada
                + "' and turma.id = aluno_has_turma.turma_id";

        // create the java statement
        try {
            Statement st = (Statement) connection.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                turma = new Turma();
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String horario = rs.getString("horario");
                String local = rs.getString("local");
                String materia = rs.getString("materia");
                int numeroAlunos = rs.getInt("numero_alunos");
                int matriculaProfessor = rs.getInt("matricula_professor");

                turma.setId(id);
                turma.setNome(nome);
                turma.setHorario(horario);
                turma.setLocal(local);
                turma.setMateria(materia);
                turma.setProfessorId(matriculaProfessor);
                turma.setNumeroAlunos(numeroAlunos);

            }
            st.close();
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());

        }
        return turma;
    }
}
