/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyschool.view;

import easyschool.model.DAO.ProfessorDAO;
import easyschool.model.DAO.TurmaDAO;
import easyschool.model.Professor;
import easyschool.model.Session;
import easyschool.model.Turma;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author aluno
 */
public class HomePageProfessorView extends javax.swing.JFrame {

    Professor professor;
    DefaultListModel defaultListModelTurmasAprovadas;
    DefaultListModel defaultListModelTurmasPendentes;
    ArrayList<Turma> turmasAprovadas;
    ArrayList<Turma> turmasPendentes;
    Frame frame = this;

    /**
     * Creates new form HomePageViewProfessor
     */
    public HomePageProfessorView() {
        initComponents();
        professor = Session.getInstance().getProfessor();
        welcomeLabel.setText("Bem vindo " + professor.getName());

        inicializaListas();

        JFrame frame = this;

        if (professor.getMensagem() == null) {
            mensagemMenu.setForeground(Color.BLACK);
        } else {
            mensagemMenu.setForeground(Color.RED);
        }

        mensagemMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (professor.getMensagem() != null) {
                    JOptionPane.showMessageDialog(null, professor.getMensagem(),
                            "Mensagem do administrador.", JOptionPane.INFORMATION_MESSAGE);
                    professor.setMensagem(null);
                    ProfessorDAO.getInstance().editarMensagem(professor);
                    Session.getInstance().setProfessor(professor);
                    mensagemMenu.setForeground(Color.BLACK);

                } else {
                    JOptionPane.showMessageDialog(null, "Você não tem nenhuma mensagem.");
                }
            }
        });

        editarMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PerfilEditProfessorView editarPerfil = new PerfilEditProfessorView();
                editarPerfil.setVisible(true);
                frame.setVisible(false);
            }
        });

        sairMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginView loginView = new LoginView();
                loginView.setVisible(true);
                frame.setVisible(false);
                Session.getInstance().apagarDados();
            }
        });
    }

    private void inicializaListas() {
        defaultListModelTurmasAprovadas = new DefaultListModel();
        defaultListModelTurmasPendentes = new DefaultListModel();
        turmasAprovadasJList.setModel(defaultListModelTurmasAprovadas);
        turmasPendentesJList.setModel(defaultListModelTurmasPendentes);

        turmasAprovadas = new ArrayList();
        turmasPendentes = new ArrayList();
        ArrayList<Turma> turmasProfessor = TurmaDAO.getInstance().buscarTurmaPorProfessorId(professor.getMatricula());

        for (int i = 0; i < turmasProfessor.size(); i++) {
            if (turmasProfessor.get(i).getNome() == null) {
                turmasPendentes.add(turmasProfessor.get(i));
            } else {
                turmasAprovadas.add(turmasProfessor.get(i));
            }
        }

        for (int i = 0; i < turmasAprovadas.size(); i++) {
            defaultListModelTurmasAprovadas.add(i, turmasAprovadas.get(i).getNome());
        }

        for (int i = 0; i < turmasPendentes.size(); i++) {
            defaultListModelTurmasPendentes.add(i, turmasPendentes.get(i).getMateria());
        }

        turmasAprovadasJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String turmaSelecionada = (String) turmasAprovadasJList.getSelectedValue();
                    for (int i = 0; i < turmasAprovadas.size(); i++) {
                        if (turmaSelecionada.equalsIgnoreCase(turmasAprovadas.get(i).getNome())) {
                            Session.getInstance().setTurma(turmasAprovadas.get(i));
                            InfoTurmView infoTurmView = new InfoTurmView();
                            infoTurmView.setVisible(true);
                            frame.setVisible(false);
                            break;
                        }
                    }
                }

            }
        });

        turmasPendentesJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String turmaSelecionada = (String) turmasPendentesJList.getSelectedValue();
                    for (int i = 0; i < turmasPendentes.size(); i++) {
                        if (turmaSelecionada.equalsIgnoreCase(turmasPendentes.get(i).getMateria())) {
                            Session.getInstance().setTurma(turmasPendentes.get(i));
                            InfoTurmView infoTurmView = new InfoTurmView();
                            infoTurmView.setVisible(true);
                            frame.setVisible(false);
                            break;
                        }
                    }
                }

            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        welcomeLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        turmasAprovadasJList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        turmasPendentesJList = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mensagemMenu = new javax.swing.JMenu();
        editarMenu = new javax.swing.JMenu();
        sairMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        welcomeLabel.setText("Bem-Vindo, Matheus");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeLabel)
                .addContainerGap(359, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomeLabel)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(turmasAprovadasJList);

        jLabel2.setText("Turmas aprovadas:");

        jLabel3.setText("Turmas pendentes:");

        turmasPendentesJList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        turmasPendentesJList.setMaximumSize(new java.awt.Dimension(0, 0));
        turmasPendentesJList.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane3.setViewportView(turmasPendentesJList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setForeground(new java.awt.Color(51, 102, 255));
        jButton1.setText("Adicionar Turma");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mensagemMenu.setForeground(new java.awt.Color(255, 0, 51));
        mensagemMenu.setText("Mensagens");
        jMenuBar1.add(mensagemMenu);

        editarMenu.setText("Editar Perfil");
        jMenuBar1.add(editarMenu);

        sairMenu.setText("Sair");
        jMenuBar1.add(sairMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        AddTurmeView addTurmeView = new AddTurmeView();
        addTurmeView.setVisible(true);
        this.setVisible(false);
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
            java.util.logging.Logger.getLogger(HomePageProfessorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomePageProfessorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomePageProfessorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomePageProfessorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomePageProfessorView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editarMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JMenu mensagemMenu;
    private javax.swing.JMenu sairMenu;
    private javax.swing.JList<String> turmasAprovadasJList;
    private javax.swing.JList<String> turmasPendentesJList;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
