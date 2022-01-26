package gui;

import programa.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã que permite a edição dos dados pessoais do utilizador ou a exclusão da conta do utilizador.
 * Instancia os componentes da classe.
 */
public class EcraConfiguracoes extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelConfiguracoes;
    private final JButton atualizar = new JButton();
    private final JButton voltar = new JButton();
    private final JButton logOut = new JButton();
    private final JButton apagarConta = new JButton();
    private final JLabel labelConf = new JLabel();
    private final JTextField nomeTxt = new JTextField();
    private final JTextField moradaTxt = new JTextField(35);
    private final JTextField telefoneTxt = new JTextField(35);
    private final JPasswordField passwordTxt = new JPasswordField(35);


    /**
     * Implementa os botões de EcraConfiguracoes e as suas respetivas ações.
     */
    class BotoesConfiguracoes implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesConfiguracoes() {

            if (empresaObjeto.isIdiomaPortugues()) {

                logOut.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(6)[1]);
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
                atualizar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(57)[1]);
                apagarConta.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(58)[1]);

            } else {
                logOut.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(6)[2]);
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
                atualizar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(57)[2]);
                apagarConta.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(58)[2]);
            }
            apagarConta.setBounds(300, 550, 200, 30);
            apagarConta.addActionListener(this);

            atualizar.setBounds(210, 600, 120, 45);
            atualizar.addActionListener(this);

            voltar.setBounds(340, 600, 120, 45);
            voltar.addActionListener(this);

            logOut.setBounds(470, 600, 120, 45);
            logOut.addActionListener(this);
        }


        /**
         * Implementa as respetivas ações dos botões da classe.
         * Atualiza os dados pessoais do utilizador ativo conforme modificações feitas pelo mesmo.
         * Caso o utilizador ativo seja o fundador, chama o método atualizarDadosFundador() declarado em EmpresaAorWords.
         * Chama os métodos de validação dos dados inseridos pelo utilizador, contidos em ValidadorGeral.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosEcraConfiguracoes referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraConfiguracoes) {

            if (eventosEcraConfiguracoes.getSource() == voltar) {
                objetoJframe.remove(panelConfiguracoes);

                if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {
                    EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else if (empresaObjeto.getUtilizadorAtivo() instanceof Programador) {
                    EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }

            } else if (eventosEcraConfiguracoes.getSource() == atualizar) {
                String nome = nomeTxt.getText(), morada = moradaTxt.getText(), telefone = telefoneTxt.getText(), password = passwordTxt.getText();
                boolean validador = false;

                boolean validarNome = validadorGeral.validarNome(nome);
                if (!validarNome) {
                    avisosPopUp.mensagemDialogo(55, 56);
                    nomeTxt.setText(empresaObjeto.getUtilizadorAtivo().getNome());
                }
                boolean validarTelefone = validadorGeral.validarTelefone(telefone);

                if (!validarTelefone) {
                    avisosPopUp.mensagemDialogo(27, 28);
                    telefoneTxt.setText(empresaObjeto.getUtilizadorAtivo().getTelefone());
                }

                if (morada.equals("")){
                    avisosPopUp.mensagemInformacao(142);
                    moradaTxt.setText(empresaObjeto.getUtilizadorAtivo().getMorada());
                }

                if (password.equals("")){
                    avisosPopUp.mensagemInformacao(142);
                    passwordTxt.setText(empresaObjeto.getUtilizadorAtivo().getPassword());
                }

                if (validarNome && validarTelefone && !morada.equals("")&&!password.equals("")) {
                    validador = true;
                }

                if (validador ) {

                    empresaObjeto.getUtilizadorAtivo().setNome(nome);
                    empresaObjeto.getUtilizadorAtivo().setMorada(morada);
                    empresaObjeto.getUtilizadorAtivo().setTelefone(telefone);
                    empresaObjeto.getUtilizadorAtivo().setPassword(password);

                    if (empresaObjeto.getUtilizadorAtivo().getEmail().equals(empresaObjeto.getEmailFundador())) {
                        empresaObjeto.atualizarDadosFundador();
                    }

                    avisosPopUp.mensagemInformacao(60);

                }

            } else if (eventosEcraConfiguracoes.getSource() == apagarConta) {

                if (empresaObjeto.getUtilizadorAtivo() instanceof Programador || empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {
                    for (Conta conta : empresaObjeto.getListaContas()) {
                        if (conta.getUser().equals(empresaObjeto.getUtilizadorAtivo())) {
                            if (conta.getSaldo() > 0.0) {
                                avisosPopUp.mensagemInformacao(146);
                                break;
                            } else {
                                mostrarPopUpCancelarConta();
                            }
                            break;
                        }
                    }
                } else {
                    mostrarPopUpCancelarConta();
                }

            } else if (eventosEcraConfiguracoes.getSource() == logOut) {
                empresaObjeto.setUtilizadorAtivo(null);
                objetoJframe.remove(panelConfiguracoes);
                EcraInicial ecraInicial = new EcraInicial(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
            }
        }
    }


    /**
     * Implementa PopUp de confirmação quanto a exclusão da conta do utilizador.
     */
    public void mostrarPopUpCancelarConta() {
        int auxiliarPopUp = avisosPopUp.popUpSimOuNaoSimples(61, 62);

        if (auxiliarPopUp == JOptionPane.YES_OPTION) {

            empresaObjeto.getListaContas().removeIf(conta -> conta.getUser().equals(empresaObjeto.getUtilizadorAtivo()));

            empresaObjeto.getListaUtilizadores().remove(empresaObjeto.getUtilizadorAtivo());

            avisosPopUp.mensagemInformacao(63);

            empresaObjeto.setUtilizadorAtivo(null);

            objetoJframe.remove(panelConfiguracoes);
            EcraInicial ecraInicial = new EcraInicial(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
        }
    }


    /**
     * Implementa os Jlabels de EcraConfiguracoes e as suas respetivas ações.
     */
    class LabelConfiguracoes {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelConfiguracoes() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelConf.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(118)[1]);

            } else {
                labelConf.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(118)[2]);
            }

            labelConf.setOpaque(true);
            labelConf.setBackground(Color.gray);
            labelConf.setForeground(Color.WHITE);
            labelConf.setBounds(0, 235, 800, 40);
            labelConf.setFont(new Font("Garamond", Font.BOLD, 25));
        }
    }


    /**
     * Implementa os JTextFields de EcraConfiguracoes.
     */
    class TextFieldsConfiguracoes {


        /**
         * Construtor.
         * Implementa os JTextFields de EcraAddAplicacao e suas características conforme com o idioma escolhido.
         */
        public TextFieldsConfiguracoes() {

            if (empresaObjeto.isIdiomaPortugues()) {
                nomeTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(30)[1]));
                moradaTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(31)[1]));
                telefoneTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(32)[1]));
                passwordTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(9)[1]));

            } else {
                nomeTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(30)[2]));
                moradaTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(31)[2]));
                telefoneTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(32)[2]));
                passwordTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(9)[2]));


            }
            nomeTxt.setBounds(275, 300, 250, 40);
            nomeTxt.setText(empresaObjeto.getUtilizadorAtivo().getNome());

            moradaTxt.setBounds(275, 350, 250, 40);
            moradaTxt.setText(empresaObjeto.getUtilizadorAtivo().getMorada());

            telefoneTxt.setBounds(275, 400, 250, 40);
            telefoneTxt.setText(empresaObjeto.getUtilizadorAtivo().getTelefone());

            passwordTxt.setBounds(275, 450, 250, 40);
            passwordTxt.setText(empresaObjeto.getUtilizadorAtivo().getPassword());
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
    public EcraConfiguracoes(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        panelConfiguracoes = new JPanel();

        BotoesConfiguracoes botoesConfiguracoes = new BotoesConfiguracoes();
        LabelConfiguracoes labelConfiguracoes = new LabelConfiguracoes();
        TextFieldsConfiguracoes textFieldsConfiguracoes = new TextFieldsConfiguracoes();

        panelConfiguracoes.setBounds(0, 225, 800, 580);
        panelConfiguracoes.setLayout(null);
        panelConfiguracoes.add(voltar);
        panelConfiguracoes.add(atualizar);

        if (!(empresaObjeto.getUtilizadorAtivo() instanceof Intermediario)) {
            panelConfiguracoes.add(apagarConta);
        }

        panelConfiguracoes.add(passwordTxt);
        panelConfiguracoes.add(logOut);
        panelConfiguracoes.add(nomeTxt);
        panelConfiguracoes.add(moradaTxt);
        panelConfiguracoes.add(telefoneTxt);
        panelConfiguracoes.add(labelConf);
        panelConfiguracoes.setVisible(true);
        this.objetoJframe.add(panelConfiguracoes);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelConfiguracoes);

        this.objetoJframe.setVisible(true);
    }
}