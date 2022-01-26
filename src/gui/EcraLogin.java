package gui;

import programa.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã de ‘login’.
 * Implementa o método que fará a validação dos dados de ‘login’.
 * Instancia os componentes da classe.
 */
public class EcraLogin extends JPanel {

    protected EmpresaAorWords empresaObjeto;
    protected JFrame objetoJframe;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelLogin;
    private final JLabel labelLogin = new JLabel();
    private final JButton entrar = new JButton();
    private final JButton esqueceuDados = new JButton();
    private final JTextField emailTxt = new JTextField(35);
    private final JPasswordField senhaTxt = new JPasswordField(35);


    /**
     * Implementa os botões de EcraLogin e as suas respetivas ações.
     */
    class BotoesEcraLogin implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesEcraLogin() {
            if (empresaObjeto.isIdiomaPortugues()) {
                entrar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(2)[1]);
                esqueceuDados.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(134)[1]);

            } else {
                entrar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(2)[2]);
                esqueceuDados.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(134)[2]);

            }
            esqueceuDados.setBounds(275, 455, 250, 35);
            esqueceuDados.addActionListener(this);

            entrar.setBounds(340, 600, 120, 50);
            entrar.addActionListener(this);
        }


        /**
         * Implementa as ações dos botões de EcraInicial.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         * Chama o método validarDadosDeLogin().
         *
         * @param eventosEcraLogin referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraLogin) {
            boolean podeEntrar;

            if (eventosEcraLogin.getSource() == esqueceuDados) {

                avisosPopUp.mensagemInformacao(135);

            } else if (eventosEcraLogin.getSource() == entrar) {

                podeEntrar = validarDadosDeLogin();

                if (podeEntrar) {
                    objetoJframe.remove(panelLogin);

                    if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                        EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                    } else if (empresaObjeto.getUtilizadorAtivo() instanceof Programador) {
                        EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                    } else if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {
                        EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                    }
                }
            }
        }
    }


    /**
     * Valida os dados de endereço eletrónico e palavra passe digitados pelo utilizador.
     *
     * @return valor booleano que corresponde ao sucesso on inssucesso da validação dos dados
     */
    public boolean validarDadosDeLogin() {

        boolean podeEntrar = false;

        for (Utilizador elemento : empresaObjeto.getListaUtilizadores()) {

            if (emailTxt.getText().equalsIgnoreCase(elemento.getEmail()) && senhaTxt.getText().equals(elemento.getPassword())) {
                empresaObjeto.setUtilizadorAtivo(elemento);
                podeEntrar = true;
                break;
            }
        }

        if (!podeEntrar) {
            avisosPopUp.mensagemDialogo(11, 12);
            limparTextFields();
        }
        return podeEntrar;
    }


    /**
     * Elimina os dados constantes nas textFields para que após o preenchimento
     * incorreto, o utilizador possa voltar a escrever a sua informação.
     */
    public void limparTextFields() {
        emailTxt.setText("");
        senhaTxt.setText("");
    }


    /**
     * Implementa os JTextFields de EcraLogin.
     */
    class TextFieldsEcraLogin {


        /**
         * Construtor.
         * Implementa os JTextFields de EcraLogin e as suas características conforme com o idioma escolhido.
         * Implementa a ação que permite ao utilizador, após digitar os seus dados de ‘login’ carregar no
         * botão ENTER do teclado e ser direcionado ao seu respetivo menu.
         */
        public TextFieldsEcraLogin() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelLogin.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(7)[1]);
                emailTxt.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(8)[1]));
                senhaTxt.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(9)[1]));

            } else {
                labelLogin.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(7)[2]);
                emailTxt.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(8)[2]));
                senhaTxt.setBorder(BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(9)[2]));
            }

            labelLogin.setOpaque(true);
            labelLogin.setBackground(Color.gray);
            labelLogin.setForeground(Color.WHITE);
            labelLogin.setBounds(0, 235, 800, 40);
            labelLogin.setFont(new Font("Garamond", Font.BOLD, 20));
            emailTxt.setBounds(250, 350, 300, 45);
            senhaTxt.setBounds(250, 400, 300, 45);

            senhaTxt.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent eventoLoginEnterTeclado) {
                    super.keyPressed(eventoLoginEnterTeclado);

                    if (eventoLoginEnterTeclado.getKeyCode() == KeyEvent.VK_ENTER) {
                        boolean podeEntrar = validarDadosDeLogin();
                        if (podeEntrar) {
                            if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                                objetoJframe.remove(panelLogin);
                                EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                            } else if (empresaObjeto.getUtilizadorAtivo() instanceof Programador) {
                                objetoJframe.remove(panelLogin);
                                EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                            } else if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {
                                objetoJframe.remove(panelLogin);
                                EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                            }
                        }
                    }
                }
            });
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
    public EcraLogin(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        panelLogin = new JPanel();

        BotoesEcraLogin botoesEcraLogin = new BotoesEcraLogin();
        TextFieldsEcraLogin textFieldsEcraLogin = new TextFieldsEcraLogin();

        panelLogin.setBounds(0, 225, 800, 580);
        panelLogin.setLayout(null);
        panelLogin.add(entrar);
        panelLogin.add(esqueceuDados);
        panelLogin.add(labelLogin);
        panelLogin.add(emailTxt);
        panelLogin.add(senhaTxt);
        panelLogin.setVisible(true);
        this.objetoJframe.add(panelLogin);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelLogin);

        this.objetoJframe.setVisible(true);
    }
}
