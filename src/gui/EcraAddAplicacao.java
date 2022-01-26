package gui;

import programa.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã que permite a criação e adição de novas aplicações pelos programadores
 * ou pelos intermediários, em nome dos programadores.
 * Instancia os componentes da classe.
 */
public class EcraAddAplicacao extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    protected Programador programador;
    protected Aplicacao aplicacao;
    protected Utilizador utilizadorAtivoAuxiliar;
    private final JPanel panelAddApp;
    private final JButton adicionar = new JButton();
    private final JButton voltar = new JButton();
    private final JLabel labelNovaAplicacao = new JLabel();
    private final JLabel labelIntroduzaAplicacao = new JLabel();
    private final JTextField nomeTxt = new JTextField();
    private final JTextField descricaoTxt = new JTextField(35);
    private final JTextField valorUnitarioTxt = new JTextField(35);
    private final JTextField emailProgramador = new JTextField(35);


    /**
     * Implementa os botões de EcraAddAplicacao e as suas respetivas ações.
     */
    class BotoesNovaAplicacaoProgramador implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesNovaAplicacaoProgramador() {

            if (empresaObjeto.isIdiomaPortugues()) {

                adicionar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(33)[1]);
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);

            } else {

                adicionar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(33)[2]);
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
            }

            adicionar.setBounds(270, 600, 120, 45);
            adicionar.addActionListener(this);
            voltar.setBounds(410, 600, 120, 45);
            voltar.addActionListener(this);
        }


        /**
         * Implementa as respetivas ações dos botões de EcraAddAplicacao.
         * Instancia e adiciona novas aplicações ao programador.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         * Chama o método mensagemDialogo().
         *
         * @param eventosEcraAddAplicacao referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraAddAplicacao) {

            if (eventosEcraAddAplicacao.getSource() == adicionar) {

                String nome = nomeTxt.getText(), descricao = descricaoTxt.getText(), valor = valorUnitarioTxt.getText(), email = emailProgramador.getText();

                if (valor.equals("")) {
                    avisosPopUp.mensagemInformacao(142);

                } else {
                    boolean validador = validadorGeral.validarValorUnitario(valor);

                    if (validador) {
                        valor = valor.replaceAll(",", ".");

                        double num = Double.parseDouble(valor);

                        if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {

                            if (nome.equals("") || descricao.equals("") || email.equals("")) {
                                avisosPopUp.mensagemInformacao(142);

                            } else {

                                for (Utilizador utilizador : empresaObjeto.getListaUtilizadores()) {
                                    if (utilizador.getEmail().equals(email)) {
                                        if (utilizador instanceof Programador) {

                                            utilizadorAtivoAuxiliar = empresaObjeto.getUtilizadorAtivo();
                                            empresaObjeto.setUtilizadorAtivo(utilizador);
                                            programador = (Programador) empresaObjeto.getUtilizadorAtivo();
                                            aplicacao = new Aplicacao(nome, descricao, num);
                                            programador.adicionarApps(aplicacao);
                                            empresaObjeto.setUtilizadorAtivo(utilizadorAtivoAuxiliar);
                                            avisosPopUp.mensagemInformacao(75);
                                            objetoJframe.remove(panelAddApp);
                                            EcraAddAplicacao ecraAddAplicacao = new EcraAddAplicacao(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                                        } else {
                                            avisosPopUp.mensagemDialogo(64, 50);
                                            emailProgramador.setText("");
                                        }
                                    }
                                }


                                int i = 0;
                                for (Utilizador user : empresaObjeto.getListaUtilizadores()) {
                                    if (user.getEmail().equals(email)) {
                                        i++;
                                    }
                                }
                                if (i == 0) {

                                    avisosPopUp.mensagemDialogo(121, 50);
                                    emailProgramador.setText("");
                                }
                            }

                        } else {
                            if (nome.equals("") || descricao.equals("")) {
                                avisosPopUp.mensagemInformacao(142);
                            } else {
                                programador = (Programador) empresaObjeto.getUtilizadorAtivo();
                                aplicacao = new Aplicacao(nome, descricao, num);
                                programador.adicionarApps(aplicacao);
                                avisosPopUp.mensagemInformacao(75);
                                objetoJframe.remove(panelAddApp);
                                EcraAddAplicacao ecraAddAplicacao = new EcraAddAplicacao(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                            }
                        }

                    } else {
                        avisosPopUp.mensagemDialogo(53, 54);
                        valorUnitarioTxt.setText("");
                    }
                }


            } else if (eventosEcraAddAplicacao.getSource() == voltar) {
                objetoJframe.remove(panelAddApp);
                if (empresaObjeto.getUtilizadorAtivo() instanceof Programador) {
                    EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }
            }
        }
    }


    /**
     * Implementa os Jlabels da classe e as suas respetivas ações.
     */
    class LabelNovaAplicacaoProgramador {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelNovaAplicacaoProgramador() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelNovaAplicacao.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(35)[1]);
                labelIntroduzaAplicacao.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(81)[1]);
            } else {
                labelNovaAplicacao.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(35)[2]);
                labelIntroduzaAplicacao.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(81)[2]);
            }
            labelIntroduzaAplicacao.setBounds(290, 300, 650, 25);
            labelIntroduzaAplicacao.setFont(new Font("Garamond", Font.BOLD, 20));

            labelNovaAplicacao.setOpaque(true);
            labelNovaAplicacao.setBackground(Color.gray);
            labelNovaAplicacao.setForeground(Color.WHITE);
            labelNovaAplicacao.setBounds(0, 235, 800, 40);
            labelNovaAplicacao.setFont(new Font("Garamond", Font.BOLD, 25));
        }
    }


    /**
     * Implementa os JTextFields de EcraAddAplicacao.
     */
    class TextFieldsNovaAplicacaoProgramador {


        /**
         * Construtor.
         * Implementa os JTextFields de EcraAddAplicacao e suas características conforme com o idioma escolhido.
         */
        public TextFieldsNovaAplicacaoProgramador() {

            if (empresaObjeto.isIdiomaPortugues()) {
                nomeTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(36)[1]));
                descricaoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(37)[1]));
                valorUnitarioTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(38)[1]));
                emailProgramador.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(39)[1]));
            } else {
                nomeTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(36)[2]));
                descricaoTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(37)[2]));
                valorUnitarioTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(38)[2]));
                emailProgramador.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(39)[2]));

            }

            nomeTxt.setBounds(250, 350, 300, 50);

            descricaoTxt.setBounds(250, 410, 300, 50);

            valorUnitarioTxt.setBounds(250, 470, 300, 50);

            emailProgramador.setBounds(250, 540, 300, 50);
        }
    }


    /**
     * Construtor.
     * Instancia o componente JPanel da classe e as suas características.
     * Adiciona os demais componentes da classe ao JPanel. Adiciona o Jpanel ao JFrame.
     * Guarda a referência do ecraAtivo e a referência do painelAtivo através do método ‘setter’ de cada um destes atributos,
     * definidos em EmpresaAorWords.
     * Adiciona componentes ao Jpanel conforme a instância do utilizador ativo.
     *
     * @param empresaObjeto       instância de EmpresaAorWords criada no método main.
     * @param objetoJframe        instância de JFrame criada em FrameUnica.
     * @param trocaDeIdiomaObjeto instância de TrocaDeIdioma criada em FrameUnica.
     * @param validadorGeral      instância de ValidadorGeral criada em FrameUnica.
     * @param avisosPopUp         instância de AvisosPopUp criada em FrameUnica.
     */
    public EcraAddAplicacao(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {
        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        panelAddApp = new JPanel();
        BotoesNovaAplicacaoProgramador botoesNovaAplicacaoProgramador = new BotoesNovaAplicacaoProgramador();
        LabelNovaAplicacaoProgramador labelNovaAplicacaoProgramador = new LabelNovaAplicacaoProgramador();
        TextFieldsNovaAplicacaoProgramador textFieldsNovaAplicacaoProgramador = new TextFieldsNovaAplicacaoProgramador();
        panelAddApp.setBounds(0, 225, 800, 580);
        panelAddApp.setLayout(null);
        panelAddApp.add(adicionar);
        panelAddApp.add(voltar);
        panelAddApp.add(labelNovaAplicacao);
        panelAddApp.add(labelIntroduzaAplicacao);
        panelAddApp.add(nomeTxt);
        panelAddApp.add(descricaoTxt);
        panelAddApp.add(valorUnitarioTxt);

        if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
            panelAddApp.add(emailProgramador);
        }

        panelAddApp.setVisible(true);
        this.objetoJframe.add(panelAddApp);
        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelAddApp);
        this.objetoJframe.setVisible(true);

    }

}