/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyschool.view;

import easyschool.model.Aluno;
import easyschool.model.DAO.AlunoDAO;
import easyschool.model.DAO.ProfessorDAO;
import easyschool.model.DAO.TurmaDAO;
import easyschool.model.Professor;
import easyschool.model.Session;
import easyschool.model.Turma;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author aluno
 */
public class HomePageAdmView extends javax.swing.JFrame {

    /**
     * Creates new form HomePageViewAdm
     */
    ArrayList<Turma> turmasParaAprovacao;
    ArrayList<Professor> professoresParaAprovacao;
    ArrayList<String> materias;

    DefaultListModel defaultListModelAberturaTurma;
    DefaultListModel defaultListModelCadastroProfessor;
    DefaultListModel defaultListModelMaterias;
    Frame frame = this;

    public HomePageAdmView() {
        initComponents();

        buscarTurmasParaAprovacao();
        buscarProfessoresParaAprovacao();
        buscarMaterias();

        sairLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
                frame.setVisible(false);
                Session.getInstance().apagarDados();
            }
        });
    }

    private void buscarTurmasParaAprovacao() {
        defaultListModelAberturaTurma = new DefaultListModel();
        aberturaTurmasJList.setModel(defaultListModelAberturaTurma);
        aberturaTurmasJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String materiaSelecionada = (String) aberturaTurmasJList.getSelectedValue();
                    for (int i = 0; i < turmasParaAprovacao.size(); i++) {
                        if (materiaSelecionada.equalsIgnoreCase(turmasParaAprovacao.get(i).getMateria())) {
                            System.out.println(turmasParaAprovacao.get(i).getMateria());
                            Session.getInstance().setTurma(turmasParaAprovacao.get(i));
                            AproveTurmeView aproveTurmeView = new AproveTurmeView();
                            aproveTurmeView.setVisible(true);
                            frame.setVisible(false);
                            break;
                        }
                    }
                }

            }
        });

        turmasParaAprovacao = new ArrayList();
        ArrayList<Turma> turmas = TurmaDAO.getInstance().buscarTodasTurmas();

        for (int i = 0; i < turmas.size(); i++) {
            if (turmas.get(i).getNome() == null) {
                turmasParaAprovacao.add(turmas.get(i));
            }
        }

        for (int i = 0; i < turmasParaAprovacao.size(); i++) {
            defaultListModelAberturaTurma.add(i, turmasParaAprovacao.get(i).getMateria());
        }

    }

    private void buscarProfessoresParaAprovacao() {
        defaultListModelCadastroProfessor = new DefaultListModel();
        cadastroProfessorJList.setModel(defaultListModelCadastroProfessor);
        cadastroProfessorJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String professorSelecionado = (String) cadastroProfessorJList.getSelectedValue();
                    for (int i = 0; i < professoresParaAprovacao.size(); i++) {
                        if (professorSelecionado.equalsIgnoreCase(professoresParaAprovacao.get(i).getName())) {
                            Session.getInstance().setProfessor(professoresParaAprovacao.get(i));
                            AproveProfessorView aproveProfessorView = new AproveProfessorView();
                            aproveProfessorView.setVisible(true);
                            frame.setVisible(false);
                            break;
                        }
                    }
                }

            }
        });

        professoresParaAprovacao = ProfessorDAO.getInstance().buscarProfessoresPendentes();

        for (int i = 0; i < professoresParaAprovacao.size(); i++) {
            defaultListModelCadastroProfessor.add(i, professoresParaAprovacao.get(i).getName());
        }
    }

    public void buscarMaterias() {
        defaultListModelMaterias = new DefaultListModel();
        materiasJList.setModel(defaultListModelMaterias);

        materias = TurmaDAO.getInstance().buscarTodasMaterias();

        for (int i = 0; i < materias.size(); i++) {
            defaultListModelMaterias.add(i, materias.get(i));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        matriculaTextField = new javax.swing.JTextField();
        alunoRadioButton = new javax.swing.JRadioButton();
        professorRadioButton = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        sairLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        materiasJList = new javax.swing.JList<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        aberturaTurmasJList = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        cadastroProfessorJList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("Pesquisar por Matrícula");

        matriculaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matriculaTextFieldActionPerformed(evt);
            }
        });

        buttonGroup1.add(alunoRadioButton);
        alunoRadioButton.setText("Aluno");

        buttonGroup1.add(professorRadioButton);
        professorRadioButton.setText("Professor");
        professorRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                professorRadioButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Procurar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        sairLabel.setText("Sair");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(matriculaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(alunoRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(professorRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(54, 54, 54)
                .addComponent(sairLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(matriculaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alunoRadioButton)
                    .addComponent(professorRadioButton)
                    .addComponent(jButton1)
                    .addComponent(sairLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        materiasJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(materiasJList);

        jLabel3.setText("Matérias:");

        jLabel4.setText("Solicitação de Abertura de Turmas:");

        aberturaTurmasJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(aberturaTurmasJList);

        jLabel5.setText("Solicitação de Cadastro de Professor:");

        cadastroProfessorJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(cadastroProfessorJList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addContainerGap(615, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
            .addComponent(jScrollPane2)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void matriculaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_matriculaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_matriculaTextFieldActionPerformed

    private void professorRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_professorRadioButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_professorRadioButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String matriculaDigitada = matriculaTextField.getText();
        if (matriculaDigitada.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Digite uma matrícula");
            return;
        }
        if (alunoRadioButton.isSelected() == true) {
            Aluno aluno = AlunoDAO.getInstance().buscarAluno(matriculaDigitada);
            if (aluno != null) {
                Session.getInstance().setAluno(aluno);
                SearchStudentView searchStudentView = new SearchStudentView();
                searchStudentView.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado");
            }
        } else if (professorRadioButton.isSelected() == true) {
            Professor professor = ProfessorDAO.getInstance().buscaProfessor(matriculaDigitada);
            if (professor != null) {
                Session.getInstance().setProfessor(professor);
                SearchProfessorView searchProfessorView = new SearchProfessorView();
                searchProfessorView.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Professor não encontrado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um tipo de conta para pesquisar");
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomePageAdmView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePageAdmView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePageAdmView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePageAdmView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePageAdmView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> aberturaTurmasJList;
    private javax.swing.JRadioButton alunoRadioButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JList<String> cadastroProfessorJList;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> materiasJList;
    private javax.swing.JTextField matriculaTextField;
    private javax.swing.JRadioButton professorRadioButton;
    private javax.swing.JLabel sairLabel;
    // End of variables declaration//GEN-END:variables
}
