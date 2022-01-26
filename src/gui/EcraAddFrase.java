package gui;

import programa.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã que permite a adição e criação de frases pelos publicitários
 * ou pelos intermediários, em nome dos publicitários.
 * Instancia os componentes da classe.
 */
public class EcraAddFrase extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelAddFrase;
    private final JButton adicionar = new JButton();
    private final JButton voltar = new JButton();
    private final JLabel labelNovaFrase = new JLabel();
    private final JLabel labelIntroduzaFrase = new JLabel();
    private final JTextField fraseTxt = new JTextField(35);
    private final JTextField emailPublicitario = new JTextField(35);


    /**
     * Implementa os botões de EcraAddFrase e as suas respetivas ações.
     */
    class BotoesNovaFrasePublicitario implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesNovaFrasePublicitario() {

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
         * Implementa as respetivas ações dos botões da classe.
         * Instancia uma nova frase e adiciona a frase ao publicitário.
         * Chama o método mensagemDialogo() e o método limparTextFields().
         * Chama o método adicionarAnuncioNaArrayList() contido em EmpresaAorWords.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosEcraAddFrase referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraAddFrase) {

            String frase = fraseTxt.getText(), email = emailPublicitario.getText();

            if (eventosEcraAddFrase.getSource() == adicionar) {

                Frase objetoFrase;
                Publicitario publicitario;

                if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    if (frase.equals("") || email.equals("")) {
                        avisosPopUp.mensagemInformacao(142);

                    } else {
                        for (Utilizador utilizador : empresaObjeto.getListaUtilizadores()) {

                            if (utilizador.getEmail().equals(email)) {
                                if (utilizador instanceof Publicitario) {

                                    Utilizador utilizadorAtivoAuxiliar = empresaObjeto.getUtilizadorAtivo();

                                    empresaObjeto.setUtilizadorAtivo(utilizador);
                                    publicitario = (Publicitario) empresaObjeto.getUtilizadorAtivo();

                                    objetoFrase = new Frase(frase);
                                    publicitario.adicionarFrases(objetoFrase);

                                    empresaObjeto.setUtilizadorAtivo(utilizadorAtivoAuxiliar);
                                    avisosPopUp.mensagemInformacao(76);
                                    objetoJframe.remove(panelAddFrase);
                                    EcraAddFrase ecraAddFrase = new EcraAddFrase(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                                } else {
                                    avisosPopUp.mensagemDialogo(65, 50);
                                    emailPublicitario.setText("");
                                }
                            }
                        }

                        int contadorAuxiliar = 0;

                        for (Utilizador user : empresaObjeto.getListaUtilizadores()) {
                            if (user.getEmail().equals(email)) {
                                contadorAuxiliar++;
                            }
                        }

                        if (contadorAuxiliar == 0) {
                            avisosPopUp.mensagemDialogo(121, 50);
                            emailPublicitario.setText("");
                        }
                    }

                } else {

                    if (frase.equals("")) {
                        avisosPopUp.mensagemInformacao(142);
                    } else {
                        publicitario = (Publicitario) empresaObjeto.getUtilizadorAtivo();

                        objetoFrase = new Frase(frase);
                        publicitario.adicionarFrases(objetoFrase);
                        avisosPopUp.mensagemInformacao(76);
                        objetoJframe.remove(panelAddFrase);
                        EcraAddFrase ecraAddFrase = new EcraAddFrase(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                    }
                }


            } else if (eventosEcraAddFrase.getSource() == voltar) {
                objetoJframe.remove(panelAddFrase);

                if (empresaObjeto.getUtilizadorAtivo() instanceof Publicitario) {
                    EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }
            }
        }
    }


    /**
     * Implementa os Jlabels de EcraAddFrase e as suas respetivas ações.
     */
    class LabelNovaFrasePublicitario {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelNovaFrasePublicitario() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelNovaFrase.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(40)[1]);

                if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    labelIntroduzaFrase.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(138)[1]);
                } else {
                    labelIntroduzaFrase.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(80)[1]);
                }

            } else {

                labelNovaFrase.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(40)[2]);

                if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
                    labelIntroduzaFrase.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(138)[2]);
                } else {
                    labelIntroduzaFrase.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(80)[2]);
                }
            }
            labelIntroduzaFrase.setBounds(320, 300, 650, 25);
            labelIntroduzaFrase.setFont(new Font("Garamond", Font.BOLD, 20));

            labelNovaFrase.setOpaque(true);
            labelNovaFrase.setBackground(Color.gray);
            labelNovaFrase.setForeground(Color.WHITE);
            labelNovaFrase.setBounds(0, 235, 800, 40);
            labelNovaFrase.setFont(new Font("Garamond", Font.BOLD, 25));
        }
    }


    /**
     * Implementa os JTextFields de EcraAddFrase.
     */
    class TextFieldsNovaFrasePublicitario {


        /**
         * Construtor.
         * Implementa os JTextFields de EcraAddFrase e suas características conforme com o idioma escolhido.
         */
        public TextFieldsNovaFrasePublicitario() {

            if (empresaObjeto.isIdiomaPortugues()) {
                fraseTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(41)[1]));
                emailPublicitario.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(43)[1]));
            } else {
                fraseTxt.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(41)[2]));
                emailPublicitario.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(43)[2]));
            }
            fraseTxt.setBounds(250, 350, 300, 50);
            emailPublicitario.setBounds(250, 420, 300, 50);
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
    public EcraAddFrase(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        panelAddFrase = new JPanel();

        BotoesNovaFrasePublicitario botoesNovaFrasePublicitario = new BotoesNovaFrasePublicitario();
        LabelNovaFrasePublicitario labelNovaFrasePublicitario = new LabelNovaFrasePublicitario();
        TextFieldsNovaFrasePublicitario textFieldsNovaFrasePublicitario = new TextFieldsNovaFrasePublicitario();

        panelAddFrase.setBounds(0, 225, 800, 580);
        panelAddFrase.setLayout(null);
        panelAddFrase.add(adicionar);
        panelAddFrase.add(voltar);
        panelAddFrase.add(labelNovaFrase);
        panelAddFrase.add(labelIntroduzaFrase);
        panelAddFrase.add(fraseTxt);

        if (empresaObjeto.getUtilizadorAtivo() instanceof Intermediario) {
            panelAddFrase.add(emailPublicitario);
        }

        panelAddFrase.setVisible(true);
        this.objetoJframe.add(panelAddFrase);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelAddFrase);

        this.objetoJframe.setVisible(true);
    }
}