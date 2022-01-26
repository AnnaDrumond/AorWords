package gui;

import programa.EmpresaAorWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã de ‘SignUp’.
 * Instancia os componentes da classe.
 */
public class EcraSignUp extends JPanel {

    protected EmpresaAorWords empresaObjeto;
    protected JFrame objetoJframe;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelSignUp;
    private final JButton voltar = new JButton();
    private final JLabel labelFaixa = new JLabel();
    private final JLabel labelSignUp = new JLabel();


    /**
     * Implementa os botões de EcraSignUp e as suas respetivas ações.
     */
    class BotoesSignUp implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesSignUp() {

            if (empresaObjeto.isIdiomaPortugues()) {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);

            } else {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
            }

            voltar.setBounds(340, 600, 120, 50);
            voltar.addActionListener(this);
        }


        /**
         * Implementa as ações dos botões de EcraSignUp.
         * Remove o JPanel atual.
         * Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventoBotaoVoltar referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventoBotaoVoltar) {
            objetoJframe.remove(panelSignUp);
            EcraInicial ecraInicial = new EcraInicial(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
        }
    }


    /**
     * Implementa a JLabel de EcraSignUp.
     */
    class LabelSignUp {


        /**
         * Construtor.
         * Implementa a JLabel de EcraSignUp e suas características conforme com o idioma escolhido.
         */
        public LabelSignUp() {
            if (empresaObjeto.isIdiomaPortugues()) {
                labelFaixa.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(1)[1]);
                labelSignUp.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(4)[1]);
            } else {
                labelFaixa.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(1)[2]);
                labelSignUp.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(4)[2]);
            }

            labelSignUp.setBounds(180, 400, 600, 50);
            labelSignUp.setFont(new Font("Garamond", Font.BOLD, 15));

            labelFaixa.setOpaque(true);
            labelFaixa.setBackground(Color.gray);
            labelFaixa.setForeground(Color.WHITE);
            labelFaixa.setBounds(0, 235, 800, 40);
            labelFaixa.setFont(new Font("Garamond", Font.BOLD, 20));
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
    public EcraSignUp(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) { // Construtor
        this.objetoJframe = objetoJframe;
        this.empresaObjeto = empresaObjeto;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        panelSignUp = new JPanel();

        BotoesSignUp objetoBotoes = new BotoesSignUp();
        LabelSignUp objetoLabel = new LabelSignUp();

        panelSignUp.setBounds(800, 225, 800, 580);
        panelSignUp.setLayout(null);
        panelSignUp.add(voltar);
        panelSignUp.add(labelFaixa);
        panelSignUp.add(labelSignUp);
        objetoJframe.add(panelSignUp);
        objetoJframe.setVisible(true);

        empresaObjeto.setEcraAtivo(this);
        empresaObjeto.setPainelAtivo(panelSignUp);

        panelSignUp.setVisible(true);
    }
}