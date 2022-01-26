package gui;

import programa.EmpresaAorWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã inicial.
 * Instancia os componentes da Classe.
 */
public class EcraInicial extends JPanel {

    protected EmpresaAorWords empresaObjeto;
    protected JFrame objetoJframe;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelInicial;
    private final JButton login = new JButton();
    private final JButton signUp = new JButton();
    private final JLabel labelEcraInicial = new JLabel();


    /**
     * Classe interna de EcraInicial.
     * Implementa os botões de EcraInicial e as suas respetivas ações.
     */
    class BotoesEcraInicial implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesEcraInicial() {

            if (empresaObjeto.isIdiomaPortugues()) {
                login.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(2)[1]);
                signUp.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(3)[1]);
            } else {
                login.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(2)[2]);
                signUp.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(3)[2]);
            }
            login.setBounds(270, 600, 120, 50);
            login.addActionListener(this);
            signUp.setBounds(410, 600, 120, 50);
            signUp.addActionListener(this);
        }


        /**
         * Implementa as ações dos botões de EcraInicial.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosEcraInicial referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraInicial) {
            objetoJframe.remove(panelInicial);

            if (eventosEcraInicial.getSource() == login) {
                EcraLogin ecraLoginObjeto = new EcraLogin(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraInicial.getSource() == signUp) {
                EcraSignUp ecraSignUpObjeto = new EcraSignUp(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
            }
        }
    }


    /**
     * Classe interna de EcraInicial.
     * Implementa a JLabel de EcraInicial.
     */
    class LabelEcraInicial {


        /**
         * Construtor.
         * Implementa a JLabel de EcraInicial e suas características de acordo com o idioma escolhido.
         */
        public LabelEcraInicial() {
            if (empresaObjeto.isIdiomaPortugues()) {
                labelEcraInicial.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(1)[1]);
            } else {
                labelEcraInicial.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(1)[2]);
            }

            labelEcraInicial.setOpaque(true);
            labelEcraInicial.setBackground(Color.gray);
            labelEcraInicial.setForeground(Color.WHITE);
            labelEcraInicial.setBounds(0, 235, 800, 40);
            labelEcraInicial.setFont(new Font("Garamond", Font.BOLD, 20));
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
    public EcraInicial(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.objetoJframe = objetoJframe;
        this.empresaObjeto = empresaObjeto;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        panelInicial = new JPanel();

        BotoesEcraInicial objetoBotoes = new BotoesEcraInicial();
        LabelEcraInicial objetoLabel = new LabelEcraInicial();

        panelInicial.setBounds(800, 225, 800, 580);
        panelInicial.setLayout(null);
        panelInicial.add(login);
        panelInicial.add(signUp);
        panelInicial.add(labelEcraInicial);
        this.objetoJframe.add(panelInicial);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelInicial);

        this.objetoJframe.setVisible(true);
        panelInicial.setVisible(true);
    }
}