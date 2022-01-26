package gui;

import programa.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã que permite aos intermediários visualizar os movimentos financeiros de todos os utilizadores,
 * Implementa a hipótese do intermediário filtrar os movimentos por data e/ou utilizador, cumprindo o requisito do projeto:
 * "Registar/listar todas as transações monetárias realizadas filtradas por utilizador e/ou data."
 * Instancia os componentes respetivos da classe.
 */
public class EcraMovimentosUsers extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    protected Programador programador;
    protected Aplicacao aplicacao;
    private final JPanel panelMovimentosUsers;
    private final JButton filtrar = new JButton();
    private final JButton voltar = new JButton();
    protected JTable jTableTransacoes = new JTable();
    protected JScrollPane jScrollPane = new JScrollPane(jTableTransacoes);
    protected TableRowSorter<TableModel> sorter;
    protected JLabel labelFaixaCinza = new JLabel();
    protected ArrayList<Object[]> listaAuxDeMovimentos = new ArrayList<>(10);
    private final JComboBox<String> comboBoxDatas = new JComboBox<>();
    private final JComboBox<String> comboBoxID = new JComboBox<>();
    private String dataSelecionada = "";
    private String idSelecionado = "";


    /**
     * Implementa os botões e comboBox e respetivas ações.
     */
    class BotoesComboBoxMovUsers implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         * Chama o método capturarAcoesComboBox().
         * Chama o método carregarDadosComboBox().
         * Chama o método inserirAcaoBotaoFiltrar().
         */
        public BotoesComboBoxMovUsers() {

            if (empresaObjeto.isIdiomaPortugues()) {
                filtrar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(117)[1]);
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
                comboBoxDatas.addItem(trocaDeIdiomaObjeto.getIdiomasLista().get(130)[1]);
                comboBoxID.addItem(trocaDeIdiomaObjeto.getIdiomasLista().get(131)[1]);

            } else {
                filtrar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(117)[2]);
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
                comboBoxDatas.addItem(trocaDeIdiomaObjeto.getIdiomasLista().get(130)[2]);
                comboBoxID.addItem(trocaDeIdiomaObjeto.getIdiomasLista().get(131)[2]);

            }

            filtrar.setBounds(270, 650, 120, 45);
            voltar.setBounds(410, 650, 120, 45);
            voltar.addActionListener(this);

            comboBoxDatas.setBounds(120, 300, 250, 25);
            comboBoxDatas.setBackground(Color.LIGHT_GRAY);

            comboBoxID.setBounds(380, 300, 300, 25);
            comboBoxID.setBackground(Color.LIGHT_GRAY);

            carregarDadosComboBox();
            capturarAcoesComboBox();
            inserirAcaoBotaoFiltrar();

        }


        /**
         * Verifica e carrega as datas e utilizadores a serem inseridos na comboBox,
         * permitindo ao utilizador escolher uma data e/ou utilizador para o filtro.
         */
        public void carregarDadosComboBox() {

            HashSet<String> idLista = new HashSet<>();
            HashSet<String> datasLista = new HashSet<>();

            for (Conta elementoConta : empresaObjeto.getListaContas()) {

                if (elementoConta.getUser() instanceof Publicitario || elementoConta.getUser() instanceof Programador) {
                    idLista.add(String.valueOf(elementoConta.getUser().getIdUtilizador()));

                    for (MovimentoFinanceiro elementoMov : elementoConta.getListaMovimentosFinanceiros()) {
                        datasLista.add(String.valueOf(elementoMov.getData()));
                    }
                }
            }

            ArrayList<String> datas = new ArrayList<>(datasLista);
            Collections.sort(datas);

            for (String elementoData : datas) {
                comboBoxDatas.addItem(elementoData);
            }
            for (String elementoID : idLista) {
                comboBoxID.addItem(elementoID);
            }
        }


        /**
         * Captura e armazena a data e utilizador selecionados na comboBox pelo utilizador.
         */
        public void capturarAcoesComboBox() {
            comboBoxDatas.addItemListener(eventoComboBoxData -> {
                if (eventoComboBoxData.getStateChange() == ItemEvent.SELECTED) {
                    dataSelecionada = (String) eventoComboBoxData.getItem();
                }
            });

            comboBoxID.addItemListener(eventoComboBoxID -> {
                if (eventoComboBoxID.getStateChange() == ItemEvent.SELECTED) {
                    idSelecionado = (String) eventoComboBoxID.getItem();
                }
            });
        }


        /**
         * Implementa a lógica necessária para que, quando o utilizador clicar no botão filtrar do ecrã,
         * seja acionado o ActionListener do botão que irá se encarregar de implementar o filtro na Jtable desta classe.
         */
        public void inserirAcaoBotaoFiltrar() {
            filtrar.addActionListener(eventoBotaoFiltrar -> {

                ArrayList<RowFilter<Object, Object>> listaFiltros = new ArrayList<>();

                if (idSelecionado.equals("")) {
                    listaFiltros.add(RowFilter.regexFilter(dataSelecionada));
                    sorter.setRowFilter(RowFilter.andFilter(listaFiltros));
                }

                if (dataSelecionada.equals("")) {
                    listaFiltros.add(RowFilter.regexFilter(idSelecionado));
                    sorter.setRowFilter(RowFilter.andFilter(listaFiltros));
                }

                if (!dataSelecionada.equals("") && !idSelecionado.equals("")) {
                    listaFiltros.add(RowFilter.regexFilter(dataSelecionada));
                    listaFiltros.add(RowFilter.regexFilter(idSelecionado));
                    sorter.setRowFilter(RowFilter.andFilter(listaFiltros));
                }
                if (dataSelecionada.equals("") && idSelecionado.equals("")) {
                    sorter.setRowFilter(null);
                    avisosPopUp.mensagemInformacao(149);
                }
            });
        }


        /**
         * Implementa as respetivas ações dos botões voltar da classe.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventoBotaoVoltar referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventoBotaoVoltar) {
            objetoJframe.remove(panelMovimentosUsers);
            if (eventoBotaoVoltar.getSource() == voltar) {
                EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
            }
        }
    }


    /**
     * Implementa a Jtable de EcraMovimentosFinanceiros.
     */
    class JtableMovUsers {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public JtableMovUsers() {
            construirCarregarDadosDaJtable();
            jTableTransacoes.setBounds(100, 350, 600, 270);
        }


        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            jScrollPane.setBounds(100, 350, 600, 270);
            return jScrollPane;
        }


        /**
         * Verifica e lista na tabela todos os movimentos financeiros de todos os utilizadores,  programadores ou publicitários.
         * Cria o modelo de JTable e insere os dados na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna.
         */
        public void construirCarregarDadosDaJtable() {

            Object[] objects;

            if (empresaObjeto.isIdiomaPortugues()) {
                objects = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(101)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(104)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(105)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(30)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(140)[1]};
            } else {
                objects = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(101)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(104)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(105)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(30)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(140)[2]};

            }

            jTableTransacoes.setModel(new DefaultTableModel(new Object[][]{}, objects));
            DefaultTableModel model = (DefaultTableModel) jTableTransacoes.getModel();

            Object dadosLinha[] = new Object[objects.length];

            for (Conta conta : empresaObjeto.getListaContas()) {

                if (conta.getUser() instanceof Publicitario || conta.getUser() instanceof Programador) {

                    for (MovimentoFinanceiro movimentoFinanceiro : conta.getListaMovimentosFinanceiros()) {

                        dadosLinha[0] = movimentoFinanceiro.getData();

                        if (empresaObjeto.isIdiomaPortugues()) {
                            dadosLinha[1] = movimentoFinanceiro.getTipoMovimento();
                        } else {
                            if (movimentoFinanceiro.getTipoMovimento().equals(TipoDeMovimento.DEPOSITO)) {
                                dadosLinha[1] = trocaDeIdiomaObjeto.getIdiomasLista().get(151)[2];
                            } else if (movimentoFinanceiro.getTipoMovimento().equals(TipoDeMovimento.LEVANTAMENTO)) {
                                dadosLinha[1] = trocaDeIdiomaObjeto.getIdiomasLista().get(152)[2];
                            } else {
                                dadosLinha[1] = trocaDeIdiomaObjeto.getIdiomasLista().get(153)[2];
                            }
                        }

                        dadosLinha[2] = BigDecimal.valueOf(movimentoFinanceiro.getValor()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                        dadosLinha[3] = conta.getUser().getNome();
                        dadosLinha[4] = conta.getUser().getIdUtilizador();

                        listaAuxDeMovimentos.add(dadosLinha);
                        model.addRow(dadosLinha);
                    }
                }
            }
            sorter = new TableRowSorter<>(model);
            jTableTransacoes.setRowSorter(sorter);
        }
    }


    /**
     * Implementa as JLabels de EcraMovimentosUsers.
     */
    class LabelMovimentosUsers {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelMovimentosUsers() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelFaixaCinza.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(123)[1]);
            } else {
                labelFaixaCinza.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(123)[2]);
            }
            labelFaixaCinza.setOpaque(true);
            labelFaixaCinza.setBackground(Color.gray);
            labelFaixaCinza.setForeground(Color.WHITE);
            labelFaixaCinza.setBounds(0, 235, 800, 40);
            labelFaixaCinza.setFont(new Font("Garamond", Font.BOLD, 20));
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
    public EcraMovimentosUsers(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {
        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        this.panelMovimentosUsers = new JPanel();

        JtableMovUsers jtableMovUsers = new JtableMovUsers();
        BotoesComboBoxMovUsers botoesComboBoxMovUsers = new BotoesComboBoxMovUsers();
        LabelMovimentosUsers labelMovimentosUsers = new LabelMovimentosUsers();

        panelMovimentosUsers.setBounds(0, 225, 800, 580);
        panelMovimentosUsers.setLayout(null);
        panelMovimentosUsers.add(comboBoxDatas);
        panelMovimentosUsers.add(comboBoxID);

        panelMovimentosUsers.add(voltar);
        panelMovimentosUsers.add(filtrar);
        panelMovimentosUsers.add(labelFaixaCinza);
        panelMovimentosUsers.add(jtableMovUsers.definirScroll());

        panelMovimentosUsers.setVisible(true);
        this.objetoJframe.add(panelMovimentosUsers);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelMovimentosUsers);

        this.objetoJframe.setVisible(true);
    }
}