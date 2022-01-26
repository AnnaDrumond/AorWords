package gui;

import programa.Anuncio;
import programa.EmpresaAorWords;
import programa.Programador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã respetivo ao "menu" dos programadores.
 * Instancia os componentes da classe.
 */
public class EcraProgramador extends JPanel {

    protected EmpresaAorWords empresaObjeto;
    protected JFrame objetoJframe;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelMenuProgramador;
    private final Programador programador;
    private final JButton novaApp = new JButton();
    private final JButton conta = new JButton();
    private final JButton editarAplicacao = new JButton();
    private final JButton contratos = new JButton();
    private final JButton configuracoes = new JButton();
    private final JButton frasesAExibir = new JButton();
    private final JLabel labelProgramador = new JLabel();
    private final JLabel labelWelcome = new JLabel();


    /**
     * Implementa os botões de EcraProgramador e as suas respetivas ações.
     */
    class BotoesMenuProgramador implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesMenuProgramador() {

            if (empresaObjeto.isIdiomaPortugues()) {
                conta.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(16)[1]);
                editarAplicacao.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(71)[1]);
                novaApp.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(18)[1]);
                contratos.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(19)[1]);
                configuracoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(20)[1]);
                frasesAExibir.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(34)[1]);

            } else {
                conta.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(16)[2]);
                editarAplicacao.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(71)[2]);
                novaApp.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(18)[2]);
                contratos.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(19)[2]);
                configuracoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(20)[2]);
                frasesAExibir.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(34)[2]);

            }

            novaApp.setBounds(90, 370, 200, 100);
            novaApp.addActionListener(this);

            conta.setBounds(300, 370, 200, 100);
            conta.addActionListener(this);

            editarAplicacao.setBounds(510, 370, 200, 100);
            editarAplicacao.addActionListener(this);

            frasesAExibir.setBounds(90, 480, 200, 100);
            frasesAExibir.addActionListener(this);

            contratos.setBounds(300, 480, 200, 100);
            contratos.addActionListener(this);

            configuracoes.setBounds(510, 480, 200, 100);
            configuracoes.addActionListener(this);
        }


        /**
         * Implementa as respetivas ações dos botões da classe.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosEcraProgramador referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraProgramador) {

            if (eventosEcraProgramador.getSource() == novaApp) {
                objetoJframe.remove(panelMenuProgramador);
                EcraAddAplicacao ecraAddAplicacao = new EcraAddAplicacao(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraProgramador.getSource() == conta) {
                objetoJframe.remove(panelMenuProgramador);
                EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraProgramador.getSource() == editarAplicacao) {
                objetoJframe.remove(panelMenuProgramador);
                if (programador.getListaAplicacoes().size() == 0) {

                    avisosPopUp.mensagemInformacao(150);

                    EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else {
                    EcraEditarAplicacoes ecraPerfilProgramador = new EcraEditarAplicacoes(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }

            } else if (eventosEcraProgramador.getSource() == frasesAExibir) {

                if (programador.getListaAplicacoes().size() == 0) {

                    avisosPopUp.mensagemInformacao(150);
                    objetoJframe.remove(panelMenuProgramador);
                    EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else {
                    objetoJframe.remove(panelMenuProgramador);
                    EcraEscolherAppConsulta ecraEscolherAppConsulta = new EcraEscolherAppConsulta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }

            } else if (eventosEcraProgramador.getSource() == contratos) {

                int contador = 0;

                for (Anuncio anuncio : empresaObjeto.getListaAnuncios()) {
                    if (anuncio.getAnunciador().equals(empresaObjeto.getUtilizadorAtivo())) {
                        contador++;
                    }
                }

                if (contador > 0) {
                    objetoJframe.remove(panelMenuProgramador);
                    EcraContratosProgramador ecraContratosProgramador = new EcraContratosProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else {
                    avisosPopUp.mensagemInformacao(102);
                    objetoJframe.remove(panelMenuProgramador);
                    EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }

            } else if (eventosEcraProgramador.getSource() == configuracoes) {
                objetoJframe.remove(panelMenuProgramador);
                EcraConfiguracoes ecraConfiguracoes = new EcraConfiguracoes(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            }
        }
    }


    /**
     * Implementa as JLabels de EcraProgramador e as suas respetivas ações.
     */
    class LabelMenuProgramador {


        /**
         * Construtor.
         * Implementa a JLabel de EcraProgramador  e suas características conforme com o idioma escolhido.
         */
        public LabelMenuProgramador() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelWelcome.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(13)[1] + empresaObjeto.getUtilizadorAtivo().getNome());
            } else {
                labelWelcome.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(13)[2] + empresaObjeto.getUtilizadorAtivo().getNome());
            }
            labelProgramador.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(14)[1]);

            labelWelcome.setBounds(100, 310, 650, 25);
            labelWelcome.setFont(new Font("Garamond", Font.BOLD, 25));

            labelProgramador.setOpaque(true);
            labelProgramador.setBackground(Color.gray);
            labelProgramador.setForeground(Color.WHITE);
            labelProgramador.setBounds(0, 235, 800, 40);
            labelProgramador.setFont(new Font("Garamond", Font.BOLD, 25));
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
    public EcraProgramador(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        panelMenuProgramador = new JPanel();
        programador = (Programador) empresaObjeto.getUtilizadorAtivo();

        BotoesMenuProgramador botoesMenuProgramador = new BotoesMenuProgramador();
        LabelMenuProgramador labelMenuProgramador = new LabelMenuProgramador();

        panelMenuProgramador.setBounds(0, 225, 800, 580);
        panelMenuProgramador.setLayout(null);
        panelMenuProgramador.add(novaApp);
        panelMenuProgramador.add(conta);
        panelMenuProgramador.add(editarAplicacao);
        panelMenuProgramador.add(frasesAExibir);
        panelMenuProgramador.add(contratos);
        panelMenuProgramador.add(configuracoes);
        panelMenuProgramador.add(labelProgramador);
        panelMenuProgramador.add(labelWelcome);
        panelMenuProgramador.setVisible(true);
        this.objetoJframe.add(panelMenuProgramador);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelMenuProgramador);

        this.objetoJframe.setVisible(true);
    }
}