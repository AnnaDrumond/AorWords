package gui;

import programa.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã que permite a criação de anúncios pelos publicitários
 * ou pelos intermediários, em nome dos publicitários.
 * Instancia os componentes da classe.
 */
public class EcraAddAnuncio extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelAddAnuncio;
    private String nomeAppSelecionada = "";
    private Aplicacao appAuxiliar;
    private String fraseSelecionada = "";
    private Programador programadorAux;
    private Programador auxProg;
    private final JLabel labelAnuncio = new JLabel();
    private final JLabel custoLabel = new JLabel();
    private final JButton voltar = new JButton();
    private final JButton criar = new JButton();
    private final JTable jTableAddAnuncio = new JTable();
    private final JComboBox<String> comboBoxFrases = new JComboBox<>();
    private final ArrayList<Programador> programadorLista = new ArrayList<>(10);
    private final JTextField quantidadeExibicoesTxt = new JTextField(15);
    private double valorPorExibicao;
    private double custoDouble;


    /**
     * Implementa os botões e comboBox e respetivas ações.
     */
    class BotoesComBoxAddAnuncio implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         * Chama o método capturarAcaoComboBox().
         * Chama o método carregarDadosComboBox().
         */
        public BotoesComBoxAddAnuncio() {

            if (empresaObjeto.isIdiomaPortugues()) {
                criar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(23)[1]);
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
                comboBoxFrases.addItem(trocaDeIdiomaObjeto.getIdiomasLista().get(92)[1]);

            } else {
                criar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(23)[2]);
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
                comboBoxFrases.addItem(trocaDeIdiomaObjeto.getIdiomasLista().get(92)[2]);
            }
            criar.setBounds(270, 650, 120, 45);
            criar.addActionListener(this);

            voltar.setBounds(410, 650, 120, 45);
            voltar.addActionListener(this);

            comboBoxFrases.setBounds(50, 290, 400, 25);
            comboBoxFrases.setBackground(Color.LIGHT_GRAY);

            carregarDadosComboBox();
            capturarAcaoComboBox();
        }


        /**
         * Captura e armazena a frase selecionada na comboBox pelo utilizador.
         */
        public void capturarAcaoComboBox() {
            comboBoxFrases.addItemListener(eventoComboBox -> {
                if (eventoComboBox.getStateChange() == ItemEvent.SELECTED) {
                    fraseSelecionada = (String) eventoComboBox.getItem();
                }
            });
        }


        /**
         * Verifica a instância do criador do anúncio.
         * Com base no utilizador ativo no momento, busca e carrega os dados a serem inseridos na comboBox.
         * Verifica e cumpre o requisito do projeto "Os intermediários devem partilhar uma lista única com
         * referência para todas as frases a exibir."
         */
        public void carregarDadosComboBox() {

            if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {

                for (Utilizador utilizador : empresaObjeto.getListaUtilizadores()) {
                    if (utilizador instanceof Publicitario) {

                        for (Conta conta : empresaObjeto.getListaContas()) {
                            if (conta.getUser().equals(utilizador)) {

                                if (conta.getSaldo() > 0.0) {

                                    for (Frase frase : ((Publicitario) utilizador).getListaFrases()) {
                                        comboBoxFrases.addItem(frase.getFrase());
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {

                for (Frase frase : ((Publicitario) empresaObjeto.getUtilizadorAtivo()).getListaFrases()) {
                    comboBoxFrases.addItem(frase.getFrase());
                }
            }
        }


        /**
         * Implementa as respetivas ações dos botões da classe.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosEcraAddAnuncio referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraAddAnuncio) {

            objetoJframe.remove(panelAddAnuncio);

            if (eventosEcraAddAnuncio.getSource() == criar) {

                if (nomeAppSelecionada.equals("") || fraseSelecionada.equals("") || quantidadeExibicoesTxt.getText().equals("")) {
                    avisosPopUp.mensagemInformacao(143);
                    EcraAddAnuncio ecraAddAnuncio = new EcraAddAnuncio(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else {
                    if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {

                        for (Utilizador utilizador : empresaObjeto.getListaUtilizadores()) {
                            if (utilizador instanceof Publicitario) {

                                for (Frase frase : ((Publicitario) utilizador).getListaFrases()) {
                                    if (frase.getFrase().equals(fraseSelecionada)) {

                                        for (Conta conta : empresaObjeto.getListaContas()) {
                                            if (conta.getUser().equals(utilizador)) {

                                                verificarSaldoParaFazerAnuncio(conta, frase, (Publicitario) utilizador);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {

                        for (Frase frase : ((Publicitario) empresaObjeto.getUtilizadorAtivo()).getListaFrases()) {
                            if (frase.getFrase().equals(fraseSelecionada)) {

                                for (Conta conta : empresaObjeto.getListaContas()) {
                                    if (conta.getUser().equals(empresaObjeto.getUtilizadorAtivo())) {

                                        verificarSaldoParaFazerAnuncio(conta, frase, (Publicitario) empresaObjeto.getUtilizadorAtivo());
                                    }
                                }
                            }
                        }
                    }
                }

            } else if (eventosEcraAddAnuncio.getSource() == voltar) {

                if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {
                    EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                }
            }
        }

        /**
         * Verifica se o Utilizador que pretende fazer o Anúncio tem saldo suficiente, se não, remove o JPanel atual,
         * instancia um objeto, correspodente à construção do próximo JPanel,
         * se sim mostra o Pop-Up que se encontra na classe AvisosPopUp a perguntar se pretende ou não estabelecer contrato e chama o método adicionarAnuncio
         * Verifica e cumpre os requisitos do projeto: "os publicitários só poderão criar campanhas após terem o valor
         * necessário na sua conta."
         *
         * @param conta        instância obtida ao percorrer o ArrayList de contas de empresaObjeto
         * @param frase        instância obtida ao percorrer o ArrayList de frases de cada publicitário
         * @param publicitario instância obtida através do getUtilizadorAtivo no caso de o publicitário estar a adicionar um Anúncio, ou ao percorrer a lista de utilizadores, no caso de um Intermediário estar a adicionar um Anúncio
         */
        public void verificarSaldoParaFazerAnuncio(Conta conta, Frase frase, Publicitario publicitario) {

            if (custoDouble > conta.getSaldo()) {

                avisosPopUp.mensagemDialogo(108, 88);
                objetoJframe.remove(panelAddAnuncio);
                EcraAddAnuncio ecraAddAnuncio = new EcraAddAnuncio(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else {
                String custo = String.valueOf(new BigDecimal(custoDouble).setScale(2, RoundingMode.UP)), valorUnitarioPExib = String.valueOf(appAuxiliar.getValorUnitarioPorExibicao()),
                        quantExib = String.valueOf(quantidadeExibicoesTxt.getText());
                int resultado = avisosPopUp.popUpSimOuNao(fraseSelecionada, quantExib, nomeAppSelecionada, valorUnitarioPExib, custo);
                adicionarAnuncio(resultado, frase, publicitario);
            }
        }

        /**
         * Instancia um novo anúncio, após confirmação do utilizador.
         * Chama o método adicionarAnuncioNaArrayList() contido em EmpresaAorWords.
         * Verifica e cumpre os requisitos do projeto: "Verificar/receber novas campanhas publicitárias (os
         * intermediários conseguirão agir em nome quer dos publicitários, quer dos programadores
         * quando estes não possam interagir com a aplicação)".
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param resultado    resultado do Pop-Up
         * @param frase        instância obtida ao percorrer o ArrayList de frases de cada publicitário
         * @param publicitario instância obtida através do getUtilizadorAtivo no caso de o publicitário estar a adicionar um Anúncio, ou ao percorrer a lista de utilizadores, no caso de um Intermediário estar a adicionar um Anúncio
         */

        public void adicionarAnuncio(int resultado, Frase frase, Publicitario publicitario) {

            if (resultado == JOptionPane.YES_OPTION) {

                Anuncio anuncio = new Anuncio(Integer.parseInt(quantidadeExibicoesTxt.getText()),
                        custoDouble, appAuxiliar, frase, publicitario, auxProg);

                empresaObjeto.adicionarAnuncioNaArrayListEAdicionarMovimentos(anuncio, auxProg, publicitario, custoDouble);

                objetoJframe.remove(panelAddAnuncio);
                EcraAddAnuncio ecraAddAnuncio = new EcraAddAnuncio(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else {
                objetoJframe.remove(panelAddAnuncio);
                EcraAddAnuncio ecraAddAnuncio = new EcraAddAnuncio(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
            }
        }
    }


    /**
     * Implementa as JLabels de EcraAddAnuncio.
     */
    class LabelAddAnuncio {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelAddAnuncio() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelAnuncio.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(84)[1]);
                custoLabel.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(85)[1]));
            } else {
                labelAnuncio.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(84)[2]);
                custoLabel.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(85)[2]));
            }

            labelAnuncio.setOpaque(true);
            labelAnuncio.setBackground(Color.gray);
            labelAnuncio.setForeground(Color.WHITE);
            labelAnuncio.setBounds(0, 235, 800, 40);
            labelAnuncio.setFont(new Font("Garamond", Font.BOLD, 25));
            custoLabel.setBounds(300, 580, 200, 45);
        }
    }


    /**
     * Implementa a TextField e suas características.
     */
    class TextFieldsAddAnuncio {


        /**
         * Construtor.
         * Implementa as características das TextFields conforme o idioma escolhido.
         * Chama o método  verificarAcaoTextFieldQuantidadeExibicoes().
         */
        public TextFieldsAddAnuncio() {

            if (empresaObjeto.isIdiomaPortugues()) {
                quantidadeExibicoesTxt.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(86)[1]));

            } else {
                quantidadeExibicoesTxt.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(86)[2]));
            }

            quantidadeExibicoesTxt.setBounds(300, 520, 200, 45);
            verificarAcaoTextFieldQuantidadeExibicoes();
        }


        /**
         * Implementa o KeyListener referente a TextField que recebe
         * a quantidade de exibições do anúncio digitadas pelo utilizador.
         */
        public void verificarAcaoTextFieldQuantidadeExibicoes() {
            quantidadeExibicoesTxt.addKeyListener(new KeyAdapter() {

                @Override
                public void keyReleased(KeyEvent evt) {

                    if (appAuxiliar == null) {
                        avisosPopUp.mensagemDialogo(87, 88);

                    } else {
                        String numExib = quantidadeExibicoesTxt.getText();
                        boolean validarNumExibicoes = validadorGeral.validarNumExibicoes(numExib);
                        if (validarNumExibicoes) {
                            valorPorExibicao = appAuxiliar.getValorUnitarioPorExibicao();

                            if (!quantidadeExibicoesTxt.getText().isEmpty()) {
                                custoDouble = Integer.parseInt(quantidadeExibicoesTxt.getText()) * valorPorExibicao;

                                BigDecimal bigObjeto = new BigDecimal(custoDouble).setScale(2, RoundingMode.UP);
                                String custo = String.valueOf((bigObjeto));
                                custoLabel.setText(custo + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1]);
                                remove(panelAddAnuncio);

                            } else {
                                custoLabel.setText("");
                            }
                        } else {
                            avisosPopUp.mensagemDialogo(51, 52);
                            quantidadeExibicoesTxt.setText("");
                        }
                    }
                }
            });
        }
    }


    /**
     * Implementa a Jtable de EcraAddAnuncio.
     */
    class TabelaAddAnuncio {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public TabelaAddAnuncio() {
            construirCarregarDadosDaJtable();
            jTableAddAnuncio.setBounds(50, 390, 650, 130);
        }


        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTableAddAnuncio);
            jScrollPane.setBounds(50, 360, 650, 130);
            return jScrollPane;
        }


        /**
         * Verifica e lista na tabela as aplicações disponíveis para anúncios.
         * Verifica e cumpre o requisito do Projeto "Utilizadores sem aplicações não
         * serão visíveis aos publicitários".
         * Cria o modelo de JTable e insere os dados na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna, cumprindo o requisito do projeto:
         * "Ordenar as aplicações disponíveis por valor crescente/decrescente do seu custo
         * unitário para a exibição de frases publicitárias."
         */
        public void construirCarregarDadosDaJtable() {

            for (Utilizador utilizador : empresaObjeto.getListaUtilizadores()) {
                if (utilizador instanceof Programador) {
                    programadorAux = (Programador) utilizador;
                    programadorLista.add(programadorAux);
                }
            }
            programadorLista.removeIf(elemento -> elemento.getListaAplicacoes().size() == 0);

            Object[] vetorNomeColunas;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorNomeColunas = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(83)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(36)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(37)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(89)[1]};
            } else {
                vetorNomeColunas = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(83)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(36)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(37)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(89)[2]};
            }

            jTableAddAnuncio.setModel(new DefaultTableModel(new Object[][]{}, vetorNomeColunas));

            DefaultTableModel modeloTableAppsGeral = (DefaultTableModel) jTableAddAnuncio.getModel();

            Object[] dadosLinha = new Object[vetorNomeColunas.length];

            for (Programador programador : programadorLista) {
                for (Aplicacao aplicacao : programador.getListaAplicacoes()) {
                    dadosLinha[0] = programador.getNome();
                    dadosLinha[1] = aplicacao.getNome();
                    dadosLinha[2] = aplicacao.getDescricao();
                    dadosLinha[3] = BigDecimal.valueOf(aplicacao.getValorUnitarioPorExibicao()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                    modeloTableAppsGeral.addRow(dadosLinha);
                }
            }

            TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTableAppsGeral);
            jTableAddAnuncio.setRowSorter(sorter);
            jTableAddAnuncio.setDefaultEditor(Object.class, null);
        }
    }


    /**
     * Permite capturar qual a aplicação que o utilizador selecionou na tabela,
     * para criação do anúncio.
     */
    public void capturarAppSelecionada() {

        jTableAddAnuncio.getSelectionModel().addListSelectionListener(event -> {

            nomeAppSelecionada = jTableAddAnuncio.getValueAt(jTableAddAnuncio.getSelectedRow(), 1).toString();

            for (Programador programador : programadorLista) {
                for (Aplicacao aplicacao : programador.getListaAplicacoes()) {
                    if (nomeAppSelecionada.equals(aplicacao.getNome())) {
                        appAuxiliar = aplicacao;
                        auxProg = programador;
                    }
                }
            }
        });
    }


    /**
     * Construtor.
     * Instancia o componente JPanel da classe e as suas características.
     * Adiciona os demais componentes da classe ao JPanel. Adiciona o Jpanel ao JFrame.
     * Guarda a referência do ecraAtivo e a referência do painelAtivo através do método ‘setter’ de cada um destes atributos,
     * definidos em EmpresaAorWords.
     * Chama o método capturarAppSelecionada().
     *
     * @param empresaObjeto       instância de EmpresaAorWords criada no método main.
     * @param objetoJframe        instância de JFrame criada em FrameUnica.
     * @param trocaDeIdiomaObjeto instância de TrocaDeIdioma criada em FrameUnica.
     * @param validadorGeral      instância de ValidadorGeral criada em FrameUnica.
     * @param avisosPopUp         instância de AvisosPopUp criada em FrameUnica.
     */
    public EcraAddAnuncio(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {
        this.objetoJframe = objetoJframe;
        this.empresaObjeto = empresaObjeto;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;

        panelAddAnuncio = new JPanel();
        panelAddAnuncio.setBounds(0, 225, 800, 580);
        panelAddAnuncio.setLayout(null);

        LabelAddAnuncio labelAddAnuncio = new LabelAddAnuncio();
        TextFieldsAddAnuncio textFieldsAddAnuncio = new TextFieldsAddAnuncio();
        BotoesComBoxAddAnuncio botoesComBoxAddAnuncio = new BotoesComBoxAddAnuncio();
        TabelaAddAnuncio tabelaAddAnuncio = new TabelaAddAnuncio();

        panelAddAnuncio.add(labelAnuncio);
        panelAddAnuncio.add(tabelaAddAnuncio.definirScroll());
        panelAddAnuncio.add(criar);
        panelAddAnuncio.add(voltar);
        panelAddAnuncio.add(comboBoxFrases);
        panelAddAnuncio.add(quantidadeExibicoesTxt);
        panelAddAnuncio.add(custoLabel);
        panelAddAnuncio.setVisible(true);
        this.objetoJframe.add(panelAddAnuncio);
        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelAddAnuncio);

        capturarAppSelecionada();

        this.objetoJframe.setVisible(true);
    }
}