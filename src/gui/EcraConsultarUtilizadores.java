package gui;

import programa.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

public class EcraConsultarUtilizadores extends JPanel {

    protected EmpresaAorWords empresaObjeto;
    protected JFrame objetoJframe;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelConsultarUtilizadores;
    private final JButton voltar = new JButton();
    private final JTable jTableConsultarUtilizadores = new JTable();
    private final JLabel labelConsulta = new JLabel();
    private final JLabel instrucoes = new JLabel();

    /**
     * Implementa os botões de EcraConsultarUtilizadores e as suas respetivas ações.
     */
    class BotoesConsultarUtilizadores implements ActionListener {

        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesConsultarUtilizadores() {

            if (empresaObjeto.isIdiomaPortugues()) {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);

            } else {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
            }
            voltar.setBounds(340, 650, 120, 45);
            voltar.addActionListener(this);
        }

        /**
         * Implementa as respetivas ações dos botões da classe.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventoEcraConsultaUtilizadores referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventoEcraConsultaUtilizadores) {
            objetoJframe.remove(panelConsultarUtilizadores);
            EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
        }
    }

    /**
     * Implementa os Jlabels de EcraConsultarUtilizadores e as suas respetivas ações.
     */
    class LabelConsultarUtilizadores {
        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelConsultarUtilizadores() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelConsulta.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(141)[1]);
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[1]);

            } else {
                labelConsulta.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(141)[2]);
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[2]);
            }
            labelConsulta.setOpaque(true);
            labelConsulta.setBackground(Color.gray);
            labelConsulta.setForeground(Color.WHITE);
            labelConsulta.setBounds(0, 235, 800, 40);
            labelConsulta.setFont(new Font("Garamond", Font.BOLD, 20));

            instrucoes.setBounds(175, 295, 600, 20);
            instrucoes.setFont(new Font("Garamond", Font.BOLD, 20));
        }
    }

    /**
     * Implementa a Jtable de EcraConsultarUtilizadores.
     */

    class JTabelaConsultarUtilizadores {

        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */

        public JTabelaConsultarUtilizadores() {
            construirCarregarDadosDaJtable();
            jTableConsultarUtilizadores.setBounds(50, 330, 700, 300);
        }

        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */

        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTableConsultarUtilizadores);
            jScrollPane.setBounds(50, 330, 700, 300);
            return jScrollPane;
        }

        /**
         * Verifica as informações de todos os utilizadores a serem exibidos na tabela
         * de consulta dos utilizadores.
         * Cria o modelo de JTable e insere os dados na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna da tabela.
         */
        public void construirCarregarDadosDaJtable() {

            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(30)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(31)[1],
                        trocaDeIdiomaObjeto.getIdiomasLista().get(32)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(8)[1],
                        trocaDeIdiomaObjeto.getIdiomasLista().get(140)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(144)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(30)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(31)[2],
                        trocaDeIdiomaObjeto.getIdiomasLista().get(32)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(8)[2],
                        trocaDeIdiomaObjeto.getIdiomasLista().get(140)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(144)[2]};
            }

            jTableConsultarUtilizadores.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));
            DefaultTableModel model = (DefaultTableModel) jTableConsultarUtilizadores.getModel();

            Object[] dadosLinha = new Object[vetorColunasTabela.length];

            for (Utilizador utilizador : empresaObjeto.getListaUtilizadores()) {
                dadosLinha[0] = utilizador.getNome();
                dadosLinha[1] = utilizador.getMorada();
                dadosLinha[2] = utilizador.getTelefone();
                dadosLinha[3] = utilizador.getEmail();
                dadosLinha[4] = utilizador.getIdUtilizador();

                if (empresaObjeto.isIdiomaPortugues()) {
                    if (utilizador instanceof Publicitario) {
                        dadosLinha[5] = trocaDeIdiomaObjeto.getIdiomasLista().get(24)[1];
                    } else if (utilizador instanceof Programador) {
                        dadosLinha[5] = trocaDeIdiomaObjeto.getIdiomasLista().get(25)[1];
                    } else if (utilizador.getEmail().equals(empresaObjeto.getEmailFundador()) && utilizador instanceof Intermediario) {
                        dadosLinha[5] = trocaDeIdiomaObjeto.getIdiomasLista().get(145)[1];
                    } else if (utilizador instanceof Intermediario) {
                        dadosLinha[5] = trocaDeIdiomaObjeto.getIdiomasLista().get(26)[1];
                    }
                } else {
                    if (utilizador instanceof Publicitario) {
                        dadosLinha[5] = trocaDeIdiomaObjeto.getIdiomasLista().get(24)[2];
                    } else if (utilizador instanceof Programador) {
                        dadosLinha[5] = trocaDeIdiomaObjeto.getIdiomasLista().get(25)[2];
                    } else if (utilizador.getEmail().equals(empresaObjeto.getEmailFundador()) && utilizador instanceof Intermediario) {
                        dadosLinha[5] = trocaDeIdiomaObjeto.getIdiomasLista().get(145)[2];
                    } else if (utilizador instanceof Intermediario) {
                        dadosLinha[5] = trocaDeIdiomaObjeto.getIdiomasLista().get(26)[2];
                    }
                }

                model.addRow(dadosLinha);
            }

            TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
            jTableConsultarUtilizadores.setRowSorter(sorter);
            jTableConsultarUtilizadores.setDefaultEditor(Object.class, null);
        }
    }

    /**
     * Construtor.
     * Instancia o componente JPanel da classe e as suas características.
     * Adiciona os demais componentes da classe ao JPanel. Adiciona o Jpanel ao JFrame.
     * Guarda a referência do ecraAtivo e a referência do painelAtivo através do método ‘setter’ de cada um destes atributos,
     * definidos em EmpresaAorWords.
     *
     * @param empresaObjeto       instância de EmpresaAorWords criada no método main.
     * @param objetoJframe        instância de JFrame criada em FrameUnica.
     * @param trocaDeIdiomaObjeto instância de TrocaDeIdioma criada em FrameUnica.
     * @param validadorGeral      instância de ValidadorGeral criada em FrameUnica.
     * @param avisosPopUp         instância de AvisosPopUp criada em FrameUnica.
     */
    public EcraConsultarUtilizadores(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {
        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        this.panelConsultarUtilizadores = new JPanel();

        BotoesConsultarUtilizadores botoesConsultarUtilizadores = new BotoesConsultarUtilizadores();
        LabelConsultarUtilizadores labelConsultarUtilizadores = new LabelConsultarUtilizadores();
        JTabelaConsultarUtilizadores jTabelaConsultarUtilizadores = new JTabelaConsultarUtilizadores();

        panelConsultarUtilizadores.setBounds(0, 225, 800, 580);
        panelConsultarUtilizadores.setLayout(null);
        panelConsultarUtilizadores.add(voltar);
        panelConsultarUtilizadores.add(labelConsulta);
        panelConsultarUtilizadores.add(instrucoes);
        panelConsultarUtilizadores.add(jTabelaConsultarUtilizadores.definirScroll());

        panelConsultarUtilizadores.setVisible(true);
        this.objetoJframe.add(panelConsultarUtilizadores);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelConsultarUtilizadores);

        this.objetoJframe.setVisible(true);
    }

}
