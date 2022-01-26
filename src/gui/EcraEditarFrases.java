package gui;

import programa.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã que permite aos publicitários a edição dos dados das suas frases.
 * Instancia os componentes da classe.
 */
public class EcraEditarFrases extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private ArrayList<Frase> arrayDeFrasesAuxiliar;
    private final Publicitario publicitario;
    private String nomeFraseSelecionada = "";
    private Frase fraseAuxiliar;
    private final JPanel panelEditarFrases;
    private final JButton voltar = new JButton();
    private final JButton atualizar = new JButton();
    private final JLabel labelFeedPub = new JLabel();
    private final JLabel labelFrases = new JLabel();
    private final JTextField fraseTxt = new JTextField(35);
    private final JTable jTableEditarFrases = new JTable();


    /**
     * Implementa os botões de EcraEditarFrases e as suas respetivas ações.
     */
    class BotoesEcraEditarFrases implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesEcraEditarFrases() {

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
         * Realiza a mudança dos dados das frases realizadas pelos publicitários.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosEcraEditarFrases referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraEditarFrases) {
            objetoJframe.remove(panelEditarFrases);

            if (eventosEcraEditarFrases.getSource() == voltar) {
                objetoJframe.remove(panelEditarFrases);

                if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {
                    EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }

            } else if (eventosEcraEditarFrases.getSource() == atualizar) {

                String frase = fraseTxt.getText();

                if (nomeFraseSelecionada.equals("")) {
                    avisosPopUp.mensagemDialogo(74, 88);
                } else {
                    if (frase.equals("")) {
                        avisosPopUp.mensagemInformacao(142);
                        EcraEditarFrases ecraEditarFrases = new EcraEditarFrases(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                    } else {
                        fraseAuxiliar.setFrase(frase);

                        avisosPopUp.mensagemInformacao(73);
                    }
                }

                EcraEditarFrases ecraEditarFrases = new EcraEditarFrases(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
            }
        }
    }


    /**
     * Implementa os JTextFields de EcraEditarFrases.
     */
    class TextFieldsEditarFrases {


        /**
         * Construtor.
         * Implementa os JTextFields de EcraEditarFrases e suas características conforme com o idioma escolhido.
         */
        public TextFieldsEditarFrases() {

            if (empresaObjeto.isIdiomaPortugues()) {
                fraseTxt.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(41)[1]));

            } else {
                fraseTxt.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(41)[2]));

            }
            fraseTxt.setBounds(275, 510, 250, 50);
        }

    }


    /**
     * Implementa os Jlabels de EcraEditarFrases e as suas respetivas ações.
     */
    class LabelEditarFrase {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelEditarFrase() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelFrases.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(74)[1]);
                labelFeedPub.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(66)[1]);

            } else {
                labelFrases.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(74)[2]);
                labelFeedPub.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(66)[2]);
            }

            labelFrases.setBounds(105, 285, 650, 25);
            labelFrases.setFont(new Font("Garamond", Font.BOLD, 20));
            labelFeedPub.setOpaque(true);
            labelFeedPub.setBackground(Color.gray);
            labelFeedPub.setForeground(Color.WHITE);
            labelFeedPub.setBounds(0, 235, 800, 40);
            labelFeedPub.setFont(new Font("Garamond", Font.BOLD, 25));
        }
    }


    /**
     * Implementa a Jtable de EcraEditarFrases.
     */
    class TabelaEditarFrases {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public TabelaEditarFrases() {
            construirCarregarDadosDaJtable();
            jTableEditarFrases.setBounds(100, 320, 600, 100);
        }


        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTableEditarFrases);
            jScrollPane.setBounds(100, 320, 600, 100);
            return jScrollPane;
        }


        /**
         * Verifica as frases do publicitário com ‘login’ ativo no momento, para poder as exibir na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna da tabela.
         */
        public void construirCarregarDadosDaJtable() {

            arrayDeFrasesAuxiliar = new ArrayList<>(10);
            arrayDeFrasesAuxiliar.addAll(publicitario.getListaFrases());

            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(41)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(41)[2]};
            }

            jTableEditarFrases.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));
            DefaultTableModel modeloTableFrases = (DefaultTableModel) jTableEditarFrases.getModel();

            Object[] dadosLinha = new Object[vetorColunasTabela.length];

            for (Frase frase : arrayDeFrasesAuxiliar) {
                dadosLinha[0] = frase.getFrase();
                modeloTableFrases.addRow(dadosLinha);
            }

            TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTableFrases);
            jTableEditarFrases.setRowSorter(sorter);
            jTableEditarFrases.setDefaultEditor(Object.class, null);
        }
    }


    /**
     * Implementa a lógica necessária para capturar a linha da tabela clicada com o rato pelo utilizador.
     * Armazena o nome da frase, que consta na linha selecionada, para que as textFields possam carregar
     * os dados da frase escolhida, permitindo a edição dos seus dados.
     */
    public void capturarAppSelecionadaNaJtable() {
        jTableEditarFrases.getSelectionModel().addListSelectionListener(event -> {

            nomeFraseSelecionada = jTableEditarFrases.getValueAt(jTableEditarFrases.getSelectedRow(), 0).toString();
            adicionarFraseSelecionadaNaTextField();
        });
    }


    /**
     * Adiciona o nome da frase selecionada na JTextField, permitindo que utilizador
     * edite o que deseja, de forma mais intuitiva e dinâmica.
     */
    public void adicionarFraseSelecionadaNaTextField() {
        for (Frase frase : arrayDeFrasesAuxiliar) {
            if (nomeFraseSelecionada.equals(frase.getFrase())) {
                fraseAuxiliar = frase;
                fraseTxt.setText(fraseAuxiliar.getFrase());
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
    public EcraEditarFrases(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.objetoJframe = objetoJframe;
        this.empresaObjeto = empresaObjeto;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;

        publicitario = (Publicitario) empresaObjeto.getUtilizadorAtivo();
        panelEditarFrases = new JPanel();

        BotoesEcraEditarFrases botoesEcraEditarFrases = new BotoesEcraEditarFrases();
        LabelEditarFrase labelEditarFrase = new LabelEditarFrase();
        TabelaEditarFrases tabelaEditarFrases = new TabelaEditarFrases();
        TextFieldsEditarFrases textFieldsEditarFrases = new TextFieldsEditarFrases();

        panelEditarFrases.setBounds(0, 225, 800, 580);
        panelEditarFrases.setLayout(null);

        panelEditarFrases.add(voltar);
        panelEditarFrases.add(atualizar);
        panelEditarFrases.add(labelFeedPub);
        panelEditarFrases.add(labelFrases);
        panelEditarFrases.add(fraseTxt);
        panelEditarFrases.add(tabelaEditarFrases.definirScroll());

        panelEditarFrases.setVisible(true);
        this.objetoJframe.add(panelEditarFrases);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelEditarFrases);

        capturarAppSelecionadaNaJtable();

        this.objetoJframe.setVisible(true);
    }

}
