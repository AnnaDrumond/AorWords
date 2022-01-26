package gui;

import programa.EmpresaAorWords;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã respetivo ao "menu" dos intermediários.
 * Instancia os componentes da classe.
 */
public class EcraIntermediario extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelMenuIntermediario;
    private final JButton novaFrase = new JButton();
    private final JButton conta = new JButton();
    private final JButton novaApp = new JButton();
    private final JButton contratos = new JButton();
    private final JButton configuracoes = new JButton();
    private final JButton novoUtilizador = new JButton();
    private final JButton novoAnuncio = new JButton();
    private final JButton verUtilizadores = new JButton();
    private final JLabel labelMenuIntermediario = new JLabel();
    private final JLabel labelWelcome = new JLabel();


    /**
     * Implementa os botões de EcraIntermediario e as suas respetivas ações.
     */
    class BotoesMenuIntermediario implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesMenuIntermediario() {

            if (empresaObjeto.isIdiomaPortugues()) {
                novaFrase.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(15)[1]);
                conta.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(16)[1]);
                novaApp.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(18)[1]);
                contratos.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(19)[1]);
                configuracoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(20)[1]);
                novoUtilizador.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(21)[1]);
                novoAnuncio.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(22)[1]);
                verUtilizadores.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(139)[1]);

            } else {
                novaFrase.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(15)[2]);
                conta.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(16)[2]);
                novaApp.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(18)[2]);
                contratos.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(19)[2]);
                configuracoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(20)[2]);
                novoUtilizador.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(21)[2]);
                novoAnuncio.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(22)[2]);
                verUtilizadores.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(139)[2]);
            }

            novaFrase.setBounds(90, 370, 200, 100);
            novaFrase.addActionListener(this);

            conta.setBounds(300, 370, 200, 100);
            conta.addActionListener(this);

            novoUtilizador.setBounds(510, 370, 200, 100);
            novoUtilizador.addActionListener(this);

            novaApp.setBounds(90, 480, 200, 100);
            novaApp.addActionListener(this);

            contratos.setBounds(300, 480, 200, 100);
            contratos.addActionListener(this);

            configuracoes.setBounds(510, 480, 200, 100);
            configuracoes.addActionListener(this);

            novoAnuncio.setBounds(195, 590, 200, 100);
            novoAnuncio.addActionListener(this);

            verUtilizadores.setBounds(405, 590, 200, 100);
            verUtilizadores.addActionListener(this);
        }


        /**
         * Implementa as respetivas ações dos botões da classe.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosEcraIntermediario referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraIntermediario) {

            if (eventosEcraIntermediario.getSource() == novaFrase) {
                objetoJframe.remove(panelMenuIntermediario);
                EcraAddFrase ecraAddFrase = new EcraAddFrase(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraIntermediario.getSource() == conta) {
                objetoJframe.remove(panelMenuIntermediario);
                EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraIntermediario.getSource() == novaApp) {
                objetoJframe.remove(panelMenuIntermediario);
                EcraAddAplicacao ecraAddAplicacao = new EcraAddAplicacao(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraIntermediario.getSource() == contratos) {

                if (empresaObjeto.getListaAnuncios().size() > 0) {
                    objetoJframe.remove(panelMenuIntermediario);
                    EcraContratosEmpresa ecraContratosEmpresas = new EcraContratosEmpresa(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else {
                    avisosPopUp.mensagemInformacao(102);
                }

            } else if (eventosEcraIntermediario.getSource() == configuracoes) {
                objetoJframe.remove(panelMenuIntermediario);
                EcraConfiguracoes ecraConfiguracoes = new EcraConfiguracoes(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraIntermediario.getSource() == novoUtilizador) {
                objetoJframe.remove(panelMenuIntermediario);
                EcraAddUtilizador ecraAddUtilizador = new EcraAddUtilizador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraIntermediario.getSource() == novoAnuncio) {
                objetoJframe.remove(panelMenuIntermediario);
                EcraAddAnuncio ecraAddAnuncio = new EcraAddAnuncio(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraIntermediario.getSource() == verUtilizadores) {
                objetoJframe.remove(panelMenuIntermediario);
                EcraConsultarUtilizadores ecraConsultarUtilizadores = new EcraConsultarUtilizadores(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
            }
        }
    }


    /**
     * Implementa a JLabel de EcraIntermediario.
     */
    class LabelMenuIntermediario {


        /**
         * Construtor.
         * Implementa a JLabel de EcraIntermediario e suas características conforme com o idioma escolhido.
         */
        public LabelMenuIntermediario() {

            if (empresaObjeto.isIdiomaPortugues()) {

                if (empresaObjeto.getUtilizadorAtivo().getEmail().equals(empresaObjeto.getEmailFundador())) {
                    labelWelcome.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(13)[1] + trocaDeIdiomaObjeto.getIdiomasLista().get(126)[1] + empresaObjeto.getUtilizadorAtivo().getNome());

                } else {
                    labelWelcome.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(13)[1] + empresaObjeto.getUtilizadorAtivo().getNome());
                }

            } else {

                if (empresaObjeto.getUtilizadorAtivo().getEmail().equals(empresaObjeto.getEmailFundador())) {
                    labelWelcome.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(13)[2] + trocaDeIdiomaObjeto.getIdiomasLista().get(126)[2] + empresaObjeto.getUtilizadorAtivo().getNome());

                } else {
                    labelWelcome.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(13)[2] + empresaObjeto.getUtilizadorAtivo().getNome());
                }
            }
            labelMenuIntermediario.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(14)[1]);

            labelWelcome.setBounds(100, 310, 650, 25); //bounds da faixa
            labelWelcome.setFont(new Font("Garamond", Font.BOLD, 25)); //características da letra

            labelMenuIntermediario.setOpaque(true); //para a faixa ficar visivel
            labelMenuIntermediario.setBackground(Color.gray); //cor da faixa
            labelMenuIntermediario.setForeground(Color.WHITE); //cor das letras
            labelMenuIntermediario.setBounds(0, 235, 800, 40); //bounds da faixa
            labelMenuIntermediario.setFont(new Font("Garamond", Font.BOLD, 25)); //características da letra
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
    public EcraIntermediario(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        panelMenuIntermediario = new JPanel();

        BotoesMenuIntermediario botoesObjeto = new BotoesMenuIntermediario();
        LabelMenuIntermediario labelObjeto = new LabelMenuIntermediario();

        panelMenuIntermediario.setBounds(0, 225, 800, 580);
        panelMenuIntermediario.setLayout(null);
        panelMenuIntermediario.add(novaFrase);
        panelMenuIntermediario.add(conta);
        panelMenuIntermediario.add(novaApp);
        panelMenuIntermediario.add(contratos);


        panelMenuIntermediario.add(configuracoes);
        panelMenuIntermediario.add(novoUtilizador);
        panelMenuIntermediario.add(novoAnuncio);
        panelMenuIntermediario.add(verUtilizadores);
        panelMenuIntermediario.add(labelMenuIntermediario);
        panelMenuIntermediario.add(labelWelcome);
        panelMenuIntermediario.setVisible(true);
        this.objetoJframe.add(panelMenuIntermediario);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelMenuIntermediario);

        this.objetoJframe.setVisible(true);
    }
}