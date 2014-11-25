package dudente;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 
 */
public class CadastroUI extends javax.swing.JPanel {

    javax.swing.JPanel janela = new javax.swing.JPanel();
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabelaDentistas;
    private javax.swing.JTable tabelaEmpregados;
    private final ArrayList<Funcionario> empregados;
    private final ArrayList<Funcionario> dentistas;

    public CadastroUI(ArrayList<Funcionario> dentistas, ArrayList<Funcionario> empregados) {
        this.empregados = empregados;
        this.dentistas = dentistas;
        
        carregaUI();
    }

    private void carregaUI() {
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaDentistas = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaEmpregados = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jTabbedPane1.setMaximumSize(this.preferredSize());


        Object conteudoDentistas[][] = new Object[dentistas.size()][3];
        int indice = 0;

        for (Funcionario f : dentistas) {
            conteudoDentistas[indice][0] = f.getNome();
            conteudoDentistas[indice][1] = f.getCargo();
            conteudoDentistas[indice][2] = f.getSalario();
            indice++;
        }

        tabelaDentistas.setModel(new javax.swing.table.DefaultTableModel(
                conteudoDentistas,
                new String[]{
                    "Nome", "Cargo", "Salário"
                }) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tabelaDentistas.setRowHeight(32);


        tabelaDentistas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = tabelaDentistas.getSelectedRow();
                if (selectedRow >= 0) {
                    jLabel4.setText(tabelaDentistas.getValueAt(tabelaDentistas.getSelectedRow(), 0).toString());
                    jLabel5.setText(tabelaDentistas.getValueAt(tabelaDentistas.getSelectedRow(), 1).toString());
                    jLabel6.setText(tabelaDentistas.getValueAt(tabelaDentistas.getSelectedRow(), 2).toString());
                }

            }
        });


        tabelaDentistas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabelaDentistas);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tabelaDentistas.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);


        jTabbedPane1.addTab("Dentistas", jScrollPane1);


        Object conteudoEmpregados[][] = new Object[empregados.size()][3];
        indice = 0;

        for (Funcionario f : empregados) {
            conteudoEmpregados[indice][0] = f.getNome();
            conteudoEmpregados[indice][1] = f.getCargo();
            conteudoEmpregados[indice][2] = f.getSalario();
            indice++;
        }

        tabelaEmpregados.setModel(new javax.swing.table.DefaultTableModel(
                conteudoEmpregados,
                new String[]{
                    "Nome", "Cargo", "Salário"
                }) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tabelaEmpregados.setRowHeight(32);

        tabelaEmpregados.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int selectedRow = tabelaEmpregados.getSelectedRow();
                if (selectedRow >= 0) {
                    jLabel4.setText(tabelaEmpregados.getValueAt(tabelaEmpregados.getSelectedRow(), 0).toString());
                    jLabel5.setText(tabelaEmpregados.getValueAt(tabelaEmpregados.getSelectedRow(), 1).toString());
                    jLabel6.setText(tabelaEmpregados.getValueAt(tabelaEmpregados.getSelectedRow(), 2).toString());

                }
            }
        });

        tabelaEmpregados.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tabelaEmpregados);
        tabelaEmpregados.getColumnModel().getColumn(0).setResizable(false);
        tabelaEmpregados.getColumnModel().getColumn(1).setResizable(false);
        tabelaEmpregados.getColumnModel().getColumn(2).setResizable(false);

        tabelaEmpregados.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        jTabbedPane1.addTab("Empregados", jScrollPane2);

        jButton1.setText("Cadastrar");
        jButton1.setMaximumSize(new java.awt.Dimension(81, 25));
        jButton1.setMinimumSize(new java.awt.Dimension(81, 25));
        jButton1.setPreferredSize(new java.awt.Dimension(81, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jButton1ActionPerformed(evt); ACAO AQUI
                botaoCadastrar b = new botaoCadastrar(tabelaDentistas, tabelaEmpregados);
                b.setIconImage(Clinica.icone);
                b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                b.setTitle("Cadastro");
                b.pack();
                b.setVisible(true);
            }
        });

        jButton2.setLabel("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int abaAberta = jTabbedPane1.getSelectedIndex();
                int linhaSelecionada = 0;
                String nome = "";
                String salario = "";
                String ocupacao = "";
                if (abaAberta == 0) {
                    linhaSelecionada = tabelaDentistas.getSelectedRow();
                    nome = (String) tabelaDentistas.getValueAt(linhaSelecionada, 0);
                    salario = (String) tabelaDentistas.getValueAt(linhaSelecionada, 2);
                    ocupacao = (String) tabelaDentistas.getValueAt(linhaSelecionada, 1);
                }
                if (abaAberta == 1) {
                    linhaSelecionada = tabelaEmpregados.getSelectedRow();
                    nome = (String) tabelaEmpregados.getValueAt(linhaSelecionada, 0);
                    salario = (String) tabelaEmpregados.getValueAt(linhaSelecionada, 2);
                    ocupacao = (String) tabelaEmpregados.getValueAt(linhaSelecionada, 1);
                }

                botaoEditar editbutton = new botaoEditar(tabelaDentistas, tabelaEmpregados, abaAberta, linhaSelecionada, nome, salario, ocupacao);
                editbutton.setIconImage(Clinica.icone);
                editbutton.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editbutton.setTitle("Editar");
                editbutton.pack();
                editbutton.setVisible(true);
//                
//                TakeSnapshot t = new TakeSnapshot();
//                t.setIconImage(Clinica.icone);
//                t.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                t.setTitle("Cadastro");
//                t.pack();
//                t.setVisible(true);
            }
        });

        jButton3.setLabel("Excluir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {



                int linhaFinal = 0;
                int response = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o funcionário selecionado?", "Confirmação",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.NO_OPTION) {
                    System.out.println("Clicou nao");
                } else if (response == JOptionPane.YES_OPTION) {

                    jLabel4.setText(" ");
                    jLabel5.setText(" ");
                    jLabel6.setText(" ");

                    int abaAberta = jTabbedPane1.getSelectedIndex();
                    int linhaSelecionada;
                    if (abaAberta == 0) {
                        linhaFinal = tabelaDentistas.getRowCount();
                        linhaSelecionada = tabelaDentistas.getSelectedRow();
                        ((DefaultTableModel) tabelaDentistas.getModel()).removeRow(linhaSelecionada);
                    }
                    if (abaAberta == 1) {
                        linhaFinal = tabelaEmpregados.getRowCount();
                        linhaSelecionada = tabelaEmpregados.getSelectedRow();
                        ((DefaultTableModel) tabelaEmpregados.getModel()).removeRow(linhaSelecionada);
                        ((DefaultTableModel) tabelaEmpregados.getModel()).fireTableRowsDeleted(0, linhaFinal - 1);
                    }
                } else if (response == JOptionPane.CLOSED_OPTION) {
                }
            }
        });

        jButton4.setLabel("Gerar Relatório");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRelatorio b = new botaoRelatorio(tabelaDentistas, tabelaEmpregados);
                b.setIconImage(Clinica.icone);
                b.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                b.setTitle("Relatório");
                b.pack();
                b.setVisible(true);
            }
        });
        jButton4.setMaximumSize(new java.awt.Dimension(105, 30));
        jButton4.setMinimumSize(new java.awt.Dimension(105, 30));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Funcionário"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); 
        jLabel1.setText("Nome:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 27, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); 
        jLabel2.setText("Ocupação: ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel3.setText("Salário: ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 93, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); 
        jLabel4.setText(" ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 27, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel5.setText(" ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12));
        jLabel6.setText(" ");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 93, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 460, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(39, 39, 39)));
    }
}