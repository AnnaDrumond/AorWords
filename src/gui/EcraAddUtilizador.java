package gui;

import programa.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã que permite que aos intermediários a criação de novos utilizadores.
 * Instancia os componentes da classe.
 */
public class EcraAddUtilizador extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelEcraAddUtilizador;
    private final JLabel labelNovoUser = new JLabel();
    private final JTextField nomeTxt = new JTextField(35);
    private final JTextField moradaTxt = new JTextField(35);
    private final JTextField telefoneTxt = new JTextField(35);
    private final JTextField emailTxt = new JTextField(35);
    private final JPasswordField passwordTxt = new JPasswordField(35);
    private final JButton voltar = new JButton();
    private final JButton criar = new JButton();
    private JComboBox novoUtilizador;
    private String[] vetorTipoUtilizadores;
    private Utilizador utilizadorAtributo;
    private Conta contaAtributo;


    /**
     * Implementa os botões e JComboBox de EcraAddUtilizador e as suas respetivas ações.
     */
    class BotoesEComboBoxNovoUtilizador implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões e JComboBox conforme o idioma escolhido.
         */
        public BotoesEComboBoxNovoUtilizador() {

            if (empresaObjeto.isIdiomaPortugues()) {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
                criar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(23)[1]);
                vetorTipoUtilizadores = new String[]{(trocaDeIdiomaObjeto.getIdiomasLista().get(24)[1]), (trocaDeIdiomaObjeto.getIdiomasLista().get(25)[1]), (trocaDeIdiomaObjeto.getIdiomasLista().get(26)[1])};
            } else {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
                criar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(23)[2]);
                vetorTipoUtilizadores = new String[]{(trocaDeIdiomaObjeto.getIdiomasLista().get(24)[2]), (trocaDeIdiomaObjeto.getIdiomasLista().get(25)[2]), (trocaDeIdiomaObjeto.getIdiomasLista().get(26)[2])};
            }

            voltar.setBounds(410, 620, 140, 50);
            voltar.addActionListener(this);

            criar.setBounds(240, 620, 140, 50);
            criar.addActionListener(this);

            novoUtilizador = new JComboBox(vetorTipoUtilizadores);
            novoUtilizador.setBounds(325, 560, 150, 40);
        }


        /**
         * Implementa as respetivas ações dos botões da classe.
         * Instancia um novo utilizador, conforme o tipo escolhido.
         * Instancia uma nova Conta, e envia o utilizador criado como parâmetro.
         * Chama o método mensagemDialogo().
         * Chama os métodos de validação de dados contidos em ValidadorGeral.
         * Instancia um novo utilizador conforme o tipo determinado pelo utilizador intermediário.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosBotoesEcraAddutilizador referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosBotoesEcraAddutilizador) {


            if (eventosBotoesEcraAddutilizador.getSource() == voltar) {
                objetoJframe.remove(panelEcraAddUtilizador);
                EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);


            } else if (eventosBotoesEcraAddutilizador.getSource() == criar) {

                String nome = nomeTxt.getText(), morada = moradaTxt.getText(), telefone = telefoneTxt.getText(), email = emailTxt.getText(), password = passwordTxt.getText();

                boolean validador = false, validarNome, emailUnico, validarEmail = false, validarTelefone;

                if (nome.equals("") || morada.equals("") || telefone.equals("") || email.equals("") || password.equals("")) {
                    avisosPopUp.mensagemInformacao(142);

                } else {
                    validarNome = validadorGeral.validarNome(nome);

                    if (!validarNome) {
                        avisosPopUp.mensagemDialogo(55, 56);
                        nomeTxt.setText("");
                    }

                    emailUnico = true;

                    for (Utilizador user : empresaObjeto.getListaUtilizadores()) {
                        if (user.getEmail().equals(email)) {

                            emailUnico = false;
                            avisosPopUp.mensagemDialogo(72, 50);
                            emailTxt.setText("");
                            break;
                        }
                    }

                    if (emailUnico) {
                        validarEmail = validadorGeral.validarEmail(email);

                        if (!validarEmail) {
                            avisosPopUp.mensagemDialogo(49, 50);
                            emailTxt.setText("");
                        }
                    }

                    validarTelefone = validadorGeral.validarTelefone(telefone);

                    if (!validarTelefone) {
                        avisosPopUp.mensagemDialogo(27, 28);
                        telefoneTxt.setText("");
                    }

                    if (validarNome && validarEmail && validarTelefone) {
                        validador = true;
                    }

                    if (validador) {

                        empresaObjeto.atualizarIterador();

                        if (novoUtilizador.getSelectedItem() == vetorTipoUtilizadores[0]) { //publicitário
                            utilizadorAtributo = new Publicitario(nome, morada, telefone, email, password);
                            contaAtributo = new Conta(utilizadorAtributo);
                            avisosPopUp.mensagemInformacao(77);

                        } else if (novoUtilizador.getSelectedItem() == vetorTipoUtilizadores[1]) { //programador
                            utilizadorAtributo = new Programador(nome, morada, telefone, email, password);
                            contaAtributo = new Conta(utilizadorAtributo);
                            avisosPopUp.mensagemInformacao(78);

                        } else if (novoUtilizador.getSelectedItem() == vetorTipoUtilizadores[2]) { //intermediario
                            utilizadorAtributo = new Intermediario(nome, morada, telefone, email, password);
                            avisosPopUp.mensagemInformacao(79);

                        }
                        empresaObjeto.adicionarUtilizadorNaArrayList(utilizadorAtributo);
                        empresaObjeto.adicionarContaNaArrayList(contaAtributo);
                        objetoJframe.remove(panelEcraAddUtilizador);
                        EcraAddUtilizador ecraAddUtilizador = new EcraAddUtilizador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                    }
                }
            }
        }
    }


    /**
     * Implementa os Jlabels de EcraAddUtilizador e as suas respetivas ações.
     */
    class LabelNovoUtilizador {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelNovoUtilizador() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelNovoUser.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(29)[1]);
            } else {
                labelNovoUser.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(29)[2]);
            }
            labelNovoUser.setOpaque(true);
            labelNovoUser.setBackground(Color.gray);
            labelNovoUser.setForeground(Color.WHITE);
            labelNovoUser.setBounds(0, 235, 800, 40);
            labelNovoUser.setFont(new Font("Garamond", Font.BOLD, 25));
        }
    }


    /**
     * Implementa os JTextFields de EcraAddUtilizador.
     */
    class TextFieldsNovoUtilizador {


        /**
         * Construtor.
         * Implementa os JTextFields de EcraAddUtilizador e suas características conforme com o idioma escolhido.
         */
        public TextFieldsNovoUtilizador() {

            if (empresaObjeto.isIdiomaPortugues()) {
                nomeTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(30)[1]));
                moradaTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(31)[1]));
                telefoneTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(32)[1]));
                emailTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(8)[1]));
                passwordTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(9)[1]));

            } else {
                nomeTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(30)[2]));
                moradaTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(31)[2]));
                telefoneTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(32)[2]));
                emailTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(8)[2]));
                passwordTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(9)[2]));

            }
            nomeTxt.setBounds(275, 300, 250, 40);
            moradaTxt.setBounds(275, 350, 250, 40);
            telefoneTxt.setBounds(275, 400, 250, 40);
            emailTxt.setBounds(275, 450, 250, 40);
            passwordTxt.setBounds(275, 500, 250, 40);
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
    public EcraAddUtilizador(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        panelEcraAddUtilizador = new JPanel();

        BotoesEComboBoxNovoUtilizador botoesEComboBoxNovoUtilizador = new BotoesEComboBoxNovoUtilizador();
        LabelNovoUtilizador labelNovoUtilizador = new LabelNovoUtilizador();
        TextFieldsNovoUtilizador textFieldsNovoUtilizador = new TextFieldsNovoUtilizador();

        panelEcraAddUtilizador.setBounds(0, 225, 800, 580);
        panelEcraAddUtilizador.setLayout(null);
        panelEcraAddUtilizador.add(labelNovoUser);
        panelEcraAddUtilizador.add(nomeTxt);
        panelEcraAddUtilizador.add(moradaTxt);
        panelEcraAddUtilizador.add(telefoneTxt);
        panelEcraAddUtilizador.add(emailTxt);
        panelEcraAddUtilizador.add(passwordTxt);
        panelEcraAddUtilizador.add(voltar);
        panelEcraAddUtilizador.add(criar);
        panelEcraAddUtilizador.add(novoUtilizador);
        panelEcraAddUtilizador.setVisible(true);
        objetoJframe.add(panelEcraAddUtilizador);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelEcraAddUtilizador);

        objetoJframe.setVisible(true);
    }

}
