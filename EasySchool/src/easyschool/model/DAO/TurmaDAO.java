/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyschool.model.DAO;

import com.mysql.jdbc.Statement;
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
public class TurmaDAO {

    private static TurmaDAO instance;
    Connection connection;

    public static TurmaDAO getInstance() {
        if (instance == null) {
            instance = new TurmaDAO();
        }
        return instance;
    }

    public TurmaDAO() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean criarTurma(Turma turma) {
        boolean cadastroSucesso;
        cadastroSucesso = false;
        String query = "insert into turma (horario,materia, matricula_professor)"
                + " values (?, ?, ?)";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = (PreparedStatement) connection.prepareStatement(query);

            preparedStmt.setString(1, turma.getHorario());
            preparedStmt.setString(2, turma.getMateria());
            preparedStmt.setInt(3, turma.getProfessorId());

            // execute the preparedstatement
            int sucesso = preparedStmt.executeUpdate();
            if (sucesso == 1) {
                cadastroSucesso = true;
            }
            System.out.println("Resultado da inserção no banco de turma " + cadastroSucesso);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cadastroSucesso;

    }

    public boolean aprovarTurma(Turma turma) {
        boolean updateSucesso;
        updateSucesso = false;
        String sql = "update turma set nome = ?, local = ?, numero_alunos = ?"
                + " where id = ?";

        PreparedStatement preparedStmt;
        try {
            preparedStmt = (PreparedStatement) connection.prepareStatement(sql);

            preparedStmt.setString(1, turma.getNome());
            preparedStmt.setString(2, turma.getLocal());
            preparedStmt.setInt(3, turma.getNumeroAlunos());
            preparedStmt.setInt(4, turma.getId());

            // execute the preparedstatement
            int sucesso = preparedStmt.executeUpdate();
            if (sucesso == 1) {
                updateSucesso = true;
            }
            System.out.println("Resultado da aprovação de turma no banco de " + updateSucesso);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return updateSucesso;
    }

    public boolean deletarTurma(int id) {
        try {
            String query = "DELETE FROM turma WHERE id = ? ";
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public ArrayList<Turma> buscarTodasTurmas() {
        ArrayList<Turma> turmas = new ArrayList();
        String query = "SELECT * FROM turma";
        // create the java statement
        try {
            Statement st = (Statement) connection.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                Turma turma = new Turma();
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
                turmas.add(turma);

            }
            st.close();
        } catch (Exception e) {

        }
        return turmas;
    }

    public ArrayList<Turma> buscarTurmaPorProfessorId(int matricula_professor) {
        ArrayList<Turma> turmas = new ArrayList();
        String query = "SELECT * FROM turma where matricula_professor = " + matricula_professor;
        // create the java statement
        try {
            Statement st = (Statement) connection.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                Turma turma = new Turma();
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
                turmas.add(turma);

            }
            st.close();
        } catch (Exception e) {

        }
        return turmas;
    }

    public ArrayList<Turma> buscarTurmasPorMateria(String materiaDigitada) {
        ArrayList<Turma> turmas = new ArrayList();
        String query = "select * from turma WHERE materia ='" + materiaDigitada + "'";

        // create the java statement
        try {
            Statement st = (Statement) connection.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                Turma turma = new Turma();
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
                turmas.add(turma);

            }
            st.close();
        } catch (Exception e) {

        }
        return turmas;
    }

    public int buscarQuantidadeAlunosPorTurma(int turmaId) {
        int count = 0;
        String query = "select * from aluno_has_turma where turma_id = " + turmaId;

        // create the java statement
        try {
            Statement st = (Statement) connection.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                count++;

            }
            st.close();
        } catch (Exception e) {

        }
        return count;
    }

    public ArrayList<Turma> buscarTurmaPorAlunoId(int matriculaAluno) {
        ArrayList<Turma> turmas = new ArrayList();
        String query = "select turma.id, turma.horario, turma.nome, turma.local, "
                + "turma.numero_alunos, turma.materia, turma.matricula_professor from aluno_has_turma, turma "
                + "where aluno_has_turma.aluno_matricula = " + matriculaAluno
                + " and aluno_has_turma.turma_id = turma.id";

        // create the java statement
        try {
            Statement st = (Statement) connection.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                Turma turma = new Turma();
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
                turmas.add(turma);

            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return turmas;
    }

    public ArrayList<String> buscarTodasMaterias() {
        ArrayList<String> materias = new ArrayList();
        String query = "select distinct materia from turma";

        // create the java statement
        try {
            Statement st = (Statement) connection.createStatement();
            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            while (rs.next()) {
                materias.add(rs.getString("materia"));

            }
            st.close();
        } catch (Exception e) {

        }
        return materias;
    }

}
