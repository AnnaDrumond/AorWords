package gui;

import programa.Anuncio;
import programa.EmpresaAorWords;

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
 * Implementa o ecrã que permite aos intermediários, visualizar os contratos de anúncios
 * de todos os utilizadores.
 * Instancia os componentes da classe.
 */
public class EcraContratosEmpresa extends JPanel {

    protected EmpresaAorWords empresaObjeto;
    protected JFrame objetoJframe;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelContratosEmpresa;
    private final JButton voltar = new JButton();
    private final JTable jTableContratosEmpresa = new JTable();
    private final JLabel labelContratos = new JLabel();
    private final JLabel instrucoes = new JLabel();
    private String dataSelecionada = "";
    private final JButton filtrar = new JButton();
    protected TableRowSorter<TableModel> sorter;
    private final JComboBox<String> comboBoxDatas = new JComboBox<>();

    /**
     * Implementa os botões de EcraContratosEmpresa e as suas respetivas ações.
     */
    class BotoesComboBoxEcraContratosEmpresa implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         * Chama o método capturarAcoesComboBox().
         * Chama o método carregarDadosComboBox().
         * Chama o método inserirAcaoBotaoFiltrar().
         */
        public BotoesComboBoxEcraContratosEmpresa() {

            if (empresaObjeto.isIdiomaPortugues()) {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
                comboBoxDatas.addItem(trocaDeIdiomaObjeto.getIdiomasLista().get(130)[1]);
                filtrar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(117)[1]);

            } else {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
                comboBoxDatas.addItem(trocaDeIdiomaObjeto.getIdiomasLista().get(130)[2]);
                filtrar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(117)[2]);
            }

            filtrar.setBounds(270, 700, 120, 45);

            voltar.setBounds(410, 700, 120, 45);
            voltar.addActionListener(this);

            comboBoxDatas.setBounds(275, 300, 250, 25);
            comboBoxDatas.setBackground(Color.LIGHT_GRAY);

            carregarDadosComboBox();
            capturarAcoesComboBox();
            inserirAcaoBotaoFiltrar();
        }

        /**
         * Verifica e carrega as datas a serem inseridos na comboBox,
         * permitindo ao utilizador escolher uma data  de contrato para o filtro.
         */
        public void carregarDadosComboBox() {

            HashSet<String> datasLista = new HashSet<>();
            for (Anuncio anuncio : empresaObjeto.getListaAnuncios()) {
                datasLista.add(String.valueOf(anuncio.getDataContrato()));
            }

            ArrayList<String> datas = new ArrayList<>(datasLista);
            Collections.sort(datas);

            for (String elementoData : datas) {
                comboBoxDatas.addItem(elementoData);
            }
        }

        public void capturarAcoesComboBox() {
            comboBoxDatas.addItemListener(eventoComboBoxData -> {
                if (eventoComboBoxData.getStateChange() == ItemEvent.SELECTED) {
                    dataSelecionada = (String) eventoComboBoxData.getItem();
                }
            });
        }

        /**
         * Captura e armazena a data  selecionado na comboBox pelo utilizador.
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
         * Implementa as respetivas ações dos botões da classe.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventoEcraContratosEmpresa referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventoEcraContratosEmpresa) {
            objetoJframe.remove(panelContratosEmpresa);
            EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
        }
    }


    /**
     * Implementa os Jlabels de EcraContratosEmpresa e as suas respetivas ações.
     */
    class LabelEcraContratosEmpresa {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelEcraContratosEmpresa() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelContratos.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(100)[1]);
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[1]);

            } else {
                labelContratos.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(100)[2]);
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[2]);

            }
            labelContratos.setOpaque(true);
            labelContratos.setBackground(Color.gray);
            labelContratos.setForeground(Color.WHITE);
            labelContratos.setBounds(0, 235, 800, 40);
            labelContratos.setFont(new Font("Garamond", Font.BOLD, 20));

            instrucoes.setBounds(185, 340, 600, 20);
            instrucoes.setFont(new Font("Garamond", Font.BOLD, 20));
        }
    }


    /**
     * Implementa a Jtable de EcraContratosEmpresa.
     */
    class JTabelaEcraContratosEmpresa {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public JTabelaEcraContratosEmpresa() {
            construirCarregarDadosDaJtable();
            jTableContratosEmpresa.setBounds(50, 390, 700, 300);
        }


        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTableContratosEmpresa);
            jScrollPane.setBounds(50, 390, 700, 300);
            return jScrollPane;
        }


        /**
         * Verifica as informações de todos os anúncios de todos os utilizadores a serem exibidos na tabela
         * de controlo de contratos.
         * Cumpre o requisito do projeto: "Exibir o custo/receita das campanhas publicitárias".
         * Cria o modelo de JTable e insere os dados na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna da tabela.
         */
        public void construirCarregarDadosDaJtable() {

            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(36)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(25)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(41)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(24)[1],
                        trocaDeIdiomaObjeto.getIdiomasLista().get(101)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(89)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(86)[1],
                        trocaDeIdiomaObjeto.getIdiomasLista().get(103)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(36)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(25)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(41)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(24)[2],
                        trocaDeIdiomaObjeto.getIdiomasLista().get(101)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(89)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(86)[2],
                        trocaDeIdiomaObjeto.getIdiomasLista().get(103)[2]};
            }

            jTableContratosEmpresa.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));
            DefaultTableModel model = (DefaultTableModel) jTableContratosEmpresa.getModel();

            Object[] dadosLinha = new Object[vetorColunasTabela.length];

            for (Anuncio anuncio : empresaObjeto.getListaAnuncios()) {

                dadosLinha[0] = anuncio.getAplicacao().getNome();
                dadosLinha[1] = anuncio.getAnunciador().getNome();
                dadosLinha[2] = anuncio.getFraseAnunciada().getFrase();
                dadosLinha[3] = anuncio.getAnunciante().getNome();
                dadosLinha[4] = anuncio.getDataContrato();
                dadosLinha[5] = BigDecimal.valueOf(anuncio.getValorUnitario()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                dadosLinha[6] = anuncio.getNumExibicoes();
                dadosLinha[7] = BigDecimal.valueOf(anuncio.getValorAnuncio()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                model.addRow(dadosLinha);
            }
            sorter = new TableRowSorter<>(model);
            jTableContratosEmpresa.setRowSorter(sorter);
            jTableContratosEmpresa.setDefaultEditor(Object.class, null);
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
    public EcraContratosEmpresa(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {
        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        this.panelContratosEmpresa = new JPanel();

        BotoesComboBoxEcraContratosEmpresa botoesComboBoxEcraContratosEmpresa = new BotoesComboBoxEcraContratosEmpresa();
        LabelEcraContratosEmpresa labelEcraContratosEmpresa = new LabelEcraContratosEmpresa();
        JTabelaEcraContratosEmpresa jTabelaEcraContratosEmpresa = new JTabelaEcraContratosEmpresa();

        panelContratosEmpresa.setBounds(0, 225, 800, 580);
        panelContratosEmpresa.setLayout(null);
        panelContratosEmpresa.add(voltar);
        panelContratosEmpresa.add(labelContratos);
        panelContratosEmpresa.add(filtrar);
        panelContratosEmpresa.add(comboBoxDatas);
        panelContratosEmpresa.add(instrucoes);
        panelContratosEmpresa.add(jTabelaEcraContratosEmpresa.definirScroll());

        panelContratosEmpresa.setVisible(true);
        this.objetoJframe.add(panelContratosEmpresa);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelContratosEmpresa);

        this.objetoJframe.setVisible(true);
    }
}