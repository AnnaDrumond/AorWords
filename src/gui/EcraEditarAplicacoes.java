package gui;


import programa.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã que permite aos programadores a edição dos dados das suas aplicações.
 * Instancia os componentes da classe.
 */
public class EcraEditarAplicacoes extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelEditarApps;
    private final JButton voltar = new JButton();
    private final JButton atualizar = new JButton();
    private final JLabel labelFeedProg = new JLabel();
    private final JLabel labelApps = new JLabel();
    private final JTextField nomeTxt = new JTextField(35);
    private final JTextField descricaoTxt = new JTextField(35);
    private final JTextField valorUnitarioTxt = new JTextField(35);
    private final JTable jTablePerfilProgramador = new JTable();
    private ArrayList<Aplicacao> arrayDeAppsAuxiliar;
    private final Programador programador;
    private String nomeAppSelecionada = "";
    private Aplicacao appAuxiliar;


    /**
     * Implementa os botões de EcraEditarAplicacoes e as suas respetivas ações.
     */
    class BotoesEditarAplicacoes implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesEditarAplicacoes() {

            if (empresaObjeto.isIdiomaPortugues()) {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
                atualizar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(57)[1]);

            } else {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
                atualizar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(57)[2]);
            }
            voltar.setBounds(410, 650, 120, 45);
            voltar.addActionListener(this);
            atualizar.setBounds(270, 650, 120, 45);
            atualizar.addActionListener(this);
        }


        /**
         * Implementa as respetivas ações dos botões da classe.
         * Realiza a mudança dos dados das aplicações realizadas pelo programador.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosEcraEditarAplicacoes referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraEditarAplicacoes) {
            objetoJframe.remove(panelEditarApps);
            if (eventosEcraEditarAplicacoes.getSource() == voltar) {

                EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraEditarAplicacoes.getSource() == atualizar) {

                if (nomeAppSelecionada.equals("")) {
                    avisosPopUp.mensagemDialogo(69, 88);

                } else {
                    String nome = nomeTxt.getText(), descricao = descricaoTxt.getText(), valor = valorUnitarioTxt.getText();

                    if (nome.equals("") || descricao.equals("") || valor.equals("")) {

                        avisosPopUp.mensagemDialogo(69, 88);
                        EcraEditarAplicacoes ecraEditarAplicacoes = new EcraEditarAplicacoes(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                    } else {

                        boolean validador = validadorGeral.validarValorUnitario(valor);
                        if (validador) {
                            appAuxiliar.setNome(nome);
                            appAuxiliar.setDescricao(descricao);
                            double valorUnitarioDouble = Double.parseDouble(valor);
                            appAuxiliar.setValorUnitarioPorExibicao(valorUnitarioDouble);

                            avisosPopUp.mensagemInformacao(70);

                        } else {

                            avisosPopUp.mensagemDialogo(51, 52);
                            valorUnitarioTxt.setText("");
                        }
                    }
                }

                EcraEditarAplicacoes ecraPerfilProgramador = new EcraEditarAplicacoes(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
            }
        }
    }


    /**
     * Implementa as TextFields e suas características.
     */
    class TextFieldsEditarAplicacao {


        /**
         * Construtor.
         * Implementa os JTextFields de EcraEditarAplicacoes e suas características conforme com o idioma escolhido.
         */
        public TextFieldsEditarAplicacao() {

            if (empresaObjeto.isIdiomaPortugues()) {
                nomeTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(30)[1]));
                valorUnitarioTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(38)[1]));
                descricaoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(37)[1]));

            } else {
                nomeTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(30)[2]));
                valorUnitarioTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(38)[2]));
                descricaoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(37)[2]));
            }

            nomeTxt.setBounds(275, 450, 250, 50);
            descricaoTxt.setBounds(275, 510, 250, 50);
            valorUnitarioTxt.setBounds(275, 570, 250, 50);
        }
    }


    /**
     * Implementa os Jlabels de EcraEditarAplicacoes e as suas respetivas ações.
     */
    class LabelEditarAplicacao {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelEditarAplicacao() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelFeedProg.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(68)[1]);
                labelApps.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(69)[1]);

            } else {
                labelFeedProg.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(68)[2]);
                labelApps.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(69)[2]);
            }

            labelApps.setBounds(105, 285, 650, 25);
            labelApps.setFont(new Font("Garamond", Font.BOLD, 20));

            labelFeedProg.setOpaque(true);
            labelFeedProg.setBackground(Color.gray);
            labelFeedProg.setForeground(Color.WHITE);
            labelFeedProg.setBounds(0, 235, 800, 40);
            labelFeedProg.setFont(new Font("Garamond", Font.BOLD, 25));
        }
    }


    /**
     * Implementa a Jtable de EcraEditarAplicacoes.
     */
    class TabelaEditarAplicacao {

        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public TabelaEditarAplicacao() {
            construirCarregarDadosDaJtable();
            jTablePerfilProgramador.setBounds(100, 320, 600, 100);
        }

        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTablePerfilProgramador);
            jScrollPane.setBounds(100, 320, 600, 100);
            return jScrollPane;
        }

        /**
         * Verifica as aplicações do programador com ‘login’ ativo no momento, para poder as exibir na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna da tabela.
         */
        public void construirCarregarDadosDaJtable() {

            arrayDeAppsAuxiliar = new ArrayList<>(10);

            arrayDeAppsAuxiliar.addAll(programador.getListaAplicacoes());

            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(36)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(37)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(38)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(36)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(37)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(38)[2]};
            }

            jTablePerfilProgramador.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));
            DefaultTableModel modeloTableApps = (DefaultTableModel) jTablePerfilProgramador.getModel();

            Object dadosLinha[] = new Object[vetorColunasTabela.length];

            for (Aplicacao aplicacao : arrayDeAppsAuxiliar) {

                dadosLinha[0] = aplicacao.getNome();
                dadosLinha[1] = aplicacao.getDescricao();
                dadosLinha[2] = BigDecimal.valueOf(aplicacao.getValorUnitarioPorExibicao()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                modeloTableApps.addRow(dadosLinha);
            }

            TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTableApps);
            jTablePerfilProgramador.setRowSorter(sorter);
            jTablePerfilProgramador.setDefaultEditor(Object.class, null);
        }
    }


    /**
     * Implementa a lógica necessária para capturar a linha da tabela clicada com o rato pelo utilizador.
     * Armazena o nome da aplicação, que consta na linha selecionada, para que as textFields possam carregar
     * os dados da aplicação escolhida, permitindo a edição dos seus dados.
     */
    public void capturarAppSelecionadaNaJtable() {
        jTablePerfilProgramador.getSelectionModel().addListSelectionListener(event -> {

            nomeAppSelecionada = jTablePerfilProgramador.getValueAt(jTablePerfilProgramador.getSelectedRow(), 0).toString();

            adicionarDadosAplicacaoSelecionadaNasTextFields();
        });
    }

    /**
     * Adiciona os dados da aplicação selecionada nas JTextFields, permitindo que utilizador
     * edite o que deseja, de forma mais intuitiva e dinâmica.
     */
    public void adicionarDadosAplicacaoSelecionadaNasTextFields() {

        for (Aplicacao aplicacao : arrayDeAppsAuxiliar) {
            if (nomeAppSelecionada.equals(aplicacao.getNome())) {
                appAuxiliar = aplicacao;
                nomeTxt.setText(appAuxiliar.getNome());
                descricaoTxt.setText(appAuxiliar.getDescricao());
                String valorUnitario = String.valueOf(appAuxiliar.getValorUnitarioPorExibicao());
                valorUnitarioTxt.setText(valorUnitario);
            }
        }

    }

    /**
     * Construtor.
     * Instancia o componente JPanel da classe e as suas características.
     * Adiciona os demais componentes da classe ao JPanel. Adiciona o Jpanel ao JFrame.
     * Guarda a referência do ecraAtivo e a referência do painelAtivo através do método ‘setter’ de cada um destes atributos,
     * definidos em EmpresaAorWords.
     * Chama o método capturarAppSelecionadaNaJtable().
     *
     * @param empresaObjeto       instância de EmpresaAorWords criada no método main.
     * @param objetoJframe        instância de JFrame criada em FrameUnica.
     * @param trocaDeIdiomaObjeto instância de TrocaDeIdioma criada em FrameUnica.
     * @param validadorGeral      instância de ValidadorGeral criada em FrameUnica.
     * @param avisosPopUp         instância de AvisosPopUp criada em FrameUnica.
     */
    public EcraEditarAplicacoes(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.objetoJframe = objetoJframe;
        this.empresaObjeto = empresaObjeto;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;

        programador = (Programador) empresaObjeto.getUtilizadorAtivo();

        panelEditarApps = new JPanel();

        BotoesEditarAplicacoes botoesEditarAplicacoes = new BotoesEditarAplicacoes();
        LabelEditarAplicacao labelFeedProgramador = new LabelEditarAplicacao();
        TabelaEditarAplicacao tabelaEditarAplicacao = new TabelaEditarAplicacao();
        TextFieldsEditarAplicacao textFieldsEditarAplicacao = new TextFieldsEditarAplicacao();

        panelEditarApps.setBounds(0, 225, 800, 580);
        panelEditarApps.setLayout(null);

        panelEditarApps.add(voltar);
        panelEditarApps.add(atualizar);
        panelEditarApps.add(labelFeedProg);
        panelEditarApps.add(labelApps);
        panelEditarApps.add(nomeTxt);
        panelEditarApps.add(descricaoTxt);
        panelEditarApps.add(valorUnitarioTxt);

        panelEditarApps.add(tabelaEditarAplicacao.definirScroll());
        panelEditarApps.setVisible(true);
        this.objetoJframe.add(panelEditarApps);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelEditarApps);

        capturarAppSelecionadaNaJtable();

        this.objetoJframe.setVisible(true);
    }
}