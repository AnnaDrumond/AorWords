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
 * Implementa o ecrã de visualização dos movimentos financeiros do utilizador ativo,
 * consoante seja um publicitário ou um programador.
 * Em caso de o utilizador ativo ser um intermediário, permite a visualização dos
 * movimentos financeiros da empresa, quanto as receitas geradas.
 * Implementa a hipótese do utilizar filtrar os seus movimentos por data, cumprindo o requisito do projeto:
 * "Registar/listar todas as transações monetárias realizadas filtradas por data."
 * Instancia os componentes respetivos da classe.
 */
public class EcraMovimentosFinanceiros extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    protected Programador programador;
    private final JPanel panelHistoricoTransacoes;
    private final JButton filtrar = new JButton();
    private final JButton voltar = new JButton();
    protected JTable jTableTransacoes = new JTable();
    protected JScrollPane jScrollPane = new JScrollPane(jTableTransacoes);
    protected TableRowSorter<TableModel> sorter;
    protected JLabel labelHistorico = new JLabel();
    protected ArrayList<MovimentoFinanceiro> listaDeMovimentosFinanceiros;
    private final JComboBox<String> comboBoxDatas = new JComboBox<>();
    private String dataSelecionada = "";


    /**
     * Implementa os botões e comboBox e respetivas ações.
     */
    class BotoesComboBoxHistTransacoes implements ActionListener {

        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         * Chama o método carregarDadosComboBox().
         * Chama o método capturarAcoesComboBoxEBotaoFiltrar().
         */
        public BotoesComboBoxHistTransacoes() {

            if (empresaObjeto.isIdiomaPortugues()) {
                filtrar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(117)[1]);
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
                comboBoxDatas.addItem(trocaDeIdiomaObjeto.getIdiomasLista().get(130)[1]);

            } else {
                filtrar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(117)[2]);
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
                comboBoxDatas.addItem(trocaDeIdiomaObjeto.getIdiomasLista().get(130)[2]);
            }
            filtrar.setBounds(480, 290, 100, 25);

            voltar.setBounds(340, 650, 120, 45);
            voltar.addActionListener(this);

            comboBoxDatas.setBounds(220, 290, 250, 25);
            comboBoxDatas.setBackground(Color.LIGHT_GRAY);

            carregarDadosComboBox();
            capturarAcoesComboBoxEBotaoFiltrar();
            inserirAcaoBotaoFiltrar();
        }

        /**
         * Captura e armazena a data selecionada na comboBox pelo utilizador.
         */
        public void capturarAcoesComboBoxEBotaoFiltrar() {
            comboBoxDatas.addItemListener(eventoComboBox -> {
                if (eventoComboBox.getStateChange() == ItemEvent.SELECTED) {
                    dataSelecionada = (String) eventoComboBox.getItem();
                }
            });
        }


        /**
         * Implementa a lógica necessária para que, quando o utilizador clicar no botão filtrar do ecrã,
         * seja acionado o ActionListener do botão que irá se encarregar de implementar o filtro na Jtable desta classe.
         */
        public void inserirAcaoBotaoFiltrar() {
            filtrar.addActionListener(eventoBotaoFiltrar -> {

                if (dataSelecionada.equals("")) {
                    sorter.setRowFilter(null);
                    avisosPopUp.mensagemInformacao(148);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(dataSelecionada));
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

            if (eventoBotaoVoltar.getSource() == voltar) {
                objetoJframe.remove(panelHistoricoTransacoes);

                EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
            }
        }
    }


    /**
     * Verifica o utilizador ativo no momento, e com isso, busca e carrega as datas a serem inseridos na comboBox,
     * permitindo ao utilizador escolher uma data a ser filtrada.
     */
    public void carregarDadosComboBox() {

        HashSet<String> datasLista = new HashSet<>();

        if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario || empresaObjeto.getUtilizadorAtivo() instanceof Programador) {

            for (Conta elementoConta : empresaObjeto.getListaContas()) {
                if (elementoConta.getUser().equals(empresaObjeto.getUtilizadorAtivo())) {
                    for (MovimentoFinanceiro elementoMov : elementoConta.getListaMovimentosFinanceiros()) {
                        datasLista.add(String.valueOf(elementoMov.getData()));
                    }
                }
            }
        }

        if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
            for (Conta elementoConta : empresaObjeto.getListaContas()) {
                if (elementoConta.getUser().getEmail().equals(empresaObjeto.getEmailFundador())) {
                    for (MovimentoFinanceiro elementoMov : elementoConta.getListaMovimentosFinanceiros()) {
                        datasLista.add(String.valueOf(elementoMov.getData()));
                    }
                }
            }
        }

        ArrayList<String> datas = new ArrayList<>(datasLista);
        Collections.sort(datas);

        for (String elemento : datas) {
            comboBoxDatas.addItem(elemento);
        }
    }


    /**
     * Implementa a Jtable de EcraMovimentosFinanceiros.
     */
    class JtableHistoricoTransacoes {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public JtableHistoricoTransacoes() {
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
         * Verifica e lista na tabela todos os movimentos financeiros do utilizador ativo, consoante seja
         * programador ou publicitário. Caso o utilizador ativo seja um intermediário, verifica e lista todos os movimentos
         * financeiros da empresa.
         * Cria o modelo de JTable e insere os dados na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna.
         */
        public void construirCarregarDadosDaJtable() {

            listaDeMovimentosFinanceiros = new ArrayList<>(10);

            if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {

                for (Conta elementoConta : empresaObjeto.getListaContas()) {
                    if (elementoConta.getUser().getEmail().equals(empresaObjeto.getEmailFundador())) {
                        listaDeMovimentosFinanceiros.addAll(elementoConta.getListaMovimentosFinanceiros());
                    }
                }

            } else {
                for (Conta elementoConta : empresaObjeto.getListaContas()) {
                    if (elementoConta.getUser().equals(empresaObjeto.getUtilizadorAtivo())) {
                        listaDeMovimentosFinanceiros.addAll(elementoConta.getListaMovimentosFinanceiros());
                    }
                }
            }

            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(101)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(104)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(105)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(101)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(104)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(105)[2]};
            }

            jTableTransacoes.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));

            DefaultTableModel model = (DefaultTableModel) jTableTransacoes.getModel();

            Object dadosLinha[] = new Object[vetorColunasTabela.length];

            for (MovimentoFinanceiro listaDeMovimentosFinanceiro : listaDeMovimentosFinanceiros) {
                dadosLinha[0] = listaDeMovimentosFinanceiro.getData();

                if (empresaObjeto.isIdiomaPortugues()) {
                    dadosLinha[1] = listaDeMovimentosFinanceiro.getTipoMovimento();
                } else {
                    if (listaDeMovimentosFinanceiro.getTipoMovimento().equals(TipoDeMovimento.DEPOSITO)) {
                        dadosLinha[1] = trocaDeIdiomaObjeto.getIdiomasLista().get(151)[2];
                    } else if (listaDeMovimentosFinanceiro.getTipoMovimento().equals(TipoDeMovimento.LEVANTAMENTO)) {
                        dadosLinha[1] = trocaDeIdiomaObjeto.getIdiomasLista().get(152)[2];
                    } else {
                        dadosLinha[1] = trocaDeIdiomaObjeto.getIdiomasLista().get(153)[2];
                    }
                }
                dadosLinha[2] = BigDecimal.valueOf(listaDeMovimentosFinanceiro.getValor()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                model.addRow(dadosLinha);
            }

            sorter = new TableRowSorter<>(model);
            jTableTransacoes.setRowSorter(sorter);
            jTableTransacoes.setDefaultEditor(Object.class, null);
        }
    }


    /**
     * Implementa as JLabels de EcraMovimentosFinanceiros.
     */
    class LabelHistoricoTransacoes {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelHistoricoTransacoes() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelHistorico.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(129)[1]);
            } else {
                labelHistorico.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(129)[2]);
            }

            labelHistorico.setOpaque(true);
            labelHistorico.setBackground(Color.gray);
            labelHistorico.setForeground(Color.WHITE);
            labelHistorico.setBounds(0, 235, 800, 40);
            labelHistorico.setFont(new Font("Garamond", Font.BOLD, 20));
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
    public EcraMovimentosFinanceiros(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {
        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        this.panelHistoricoTransacoes = new JPanel();

        BotoesComboBoxHistTransacoes botoesComboBoxHistTransacoes = new BotoesComboBoxHistTransacoes();
        LabelHistoricoTransacoes labelHistoricoTransacoes = new LabelHistoricoTransacoes();
        JtableHistoricoTransacoes jtableHistoricoTransacoes = new JtableHistoricoTransacoes();

        panelHistoricoTransacoes.setBounds(0, 225, 800, 580);
        panelHistoricoTransacoes.setLayout(null);
        panelHistoricoTransacoes.add(comboBoxDatas);

        panelHistoricoTransacoes.add(voltar);
        panelHistoricoTransacoes.add(filtrar);
        panelHistoricoTransacoes.add(labelHistorico);
        panelHistoricoTransacoes.add(jtableHistoricoTransacoes.definirScroll());

        panelHistoricoTransacoes.setVisible(true);
        this.objetoJframe.add(panelHistoricoTransacoes);
        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelHistoricoTransacoes);
        this.objetoJframe.setVisible(true);
    }
}
