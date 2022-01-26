package gui;

import programa.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã respetivo ao "menu" dos publicitários.
 * Instancia os componentes da classe.
 */
public class EcraPublicitario extends JPanel {

    protected EmpresaAorWords empresaObjeto;
    protected JFrame objetoJframe;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelMenuPublicitario;
    private final Publicitario publicitario;
    private final JButton novaFrase = new JButton();
    private final JButton conta = new JButton();
    private final JButton editarFrases = new JButton();
    private final JButton novoAnuncio = new JButton();
    private final JButton contratos = new JButton();
    private final JButton configuracoes = new JButton();
    private final JButton appsOndeExibir = new JButton();
    private final JLabel labelPublicitario = new JLabel();
    private final JLabel labelWelcome = new JLabel();


    /**
     * Implementa os botões de EcraPublicitario e as suas respetivas ações.
     */
    class BotoesMenuPublicitario implements ActionListener {


        /**
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesMenuPublicitario() {

            if (empresaObjeto.isIdiomaPortugues()) {
                novaFrase.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(15)[1]);
                conta.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(16)[1]);
                editarFrases.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(17)[1]);
                contratos.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(19)[1]);
                configuracoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(20)[1]);
                novoAnuncio.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(22)[1]);
                appsOndeExibir.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(113)[1]);

            } else {
                novaFrase.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(15)[2]);
                conta.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(16)[2]);
                editarFrases.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(17)[2]);
                contratos.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(19)[2]);
                configuracoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(20)[2]);
                novoAnuncio.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(22)[2]);
                appsOndeExibir.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(113)[2]);
            }

            novaFrase.setBounds(90, 370, 200, 100);
            novaFrase.addActionListener(this);

            conta.setBounds(300, 370, 200, 100);
            conta.addActionListener(this);

            editarFrases.setBounds(510, 370, 200, 100);
            editarFrases.addActionListener(this);

            novoAnuncio.setBounds(90, 480, 200, 100);
            novoAnuncio.addActionListener(this);

            contratos.setBounds(300, 480, 200, 100);
            contratos.addActionListener(this);

            configuracoes.setBounds(510, 480, 200, 100);
            configuracoes.addActionListener(this);

            appsOndeExibir.setBounds(300, 590, 200, 100);
            appsOndeExibir.addActionListener(this);

        }


        /**
         * Implementa as respetivas ações dos botões da classe.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosEcraPublicitario referência do botão escolhido pelo utilizador.
         */

        @Override
        public void actionPerformed(ActionEvent eventosEcraPublicitario) {

            if (eventosEcraPublicitario.getSource() == novaFrase) {
                objetoJframe.remove(panelMenuPublicitario);
                EcraAddFrase ecraAddFrase = new EcraAddFrase(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraPublicitario.getSource() == conta) {
                objetoJframe.remove(panelMenuPublicitario);
                EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraPublicitario.getSource() == editarFrases) {

                if (publicitario.getListaFrases().size() == 0) {
                    avisosPopUp.mensagemInformacao(82);
                    objetoJframe.remove(panelMenuPublicitario);
                    EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else {
                    objetoJframe.remove(panelMenuPublicitario);
                    EcraEditarFrases ecraEditarFrases = new EcraEditarFrases(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }

            } else if (eventosEcraPublicitario.getSource() == contratos) {

                int contador = 0;

                for (Anuncio anuncio : empresaObjeto.getListaAnuncios()) {
                    if (anuncio.getAnunciante().equals(empresaObjeto.getUtilizadorAtivo())) {
                        contador++;
                    }
                }

                if (contador > 0) {
                    objetoJframe.remove(panelMenuPublicitario);
                    EcraContratosPublicitario ecraContratosPublicitario = new EcraContratosPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                } else {
                    avisosPopUp.mensagemInformacao(102);
                    objetoJframe.remove(panelMenuPublicitario);
                    EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }

            } else if (eventosEcraPublicitario.getSource() == configuracoes) {
                objetoJframe.remove(panelMenuPublicitario);
                EcraConfiguracoes ecraConfiguracoes = new EcraConfiguracoes(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

            } else if (eventosEcraPublicitario.getSource() == novoAnuncio) {

                for (Conta conta : empresaObjeto.getListaContas()) {
                    if (conta.getUser().equals(empresaObjeto.getUtilizadorAtivo())) {
                        if (conta.getSaldo() == 0.0) {
                            avisosPopUp.mensagemInformacao(108);
                            objetoJframe.remove(panelMenuPublicitario);
                            EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                        } else {
                            objetoJframe.remove(panelMenuPublicitario);
                            EcraAddAnuncio ecraAddAnuncio = new EcraAddAnuncio(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                        }
                    }
                }
            } else if (eventosEcraPublicitario.getSource() == appsOndeExibir) {

                if (publicitario.getListaFrases().size() == 0) {
                    avisosPopUp.mensagemInformacao(82);
                    objetoJframe.remove(panelMenuPublicitario);
                    EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

                } else {
                    objetoJframe.remove(panelMenuPublicitario);
                    EcraEscolherFraseConsulta ecraEscolherFraseConsulta = new EcraEscolherFraseConsulta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
                }
            }
        }
    }


    /**
     * Implementa as JLabels de EcraPublicitario e as suas respetivas ações.
     */
    class LabelMenuPublicitario {


        /**
         * Construtor.
         * Implementa a JLabel de EcraPublicitario e suas características conforme com o idioma escolhido.
         */
        public LabelMenuPublicitario() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelWelcome.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(13)[1] + empresaObjeto.getUtilizadorAtivo().getNome());
            } else {
                labelWelcome.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(13)[2] + empresaObjeto.getUtilizadorAtivo().getNome());
            }
            labelPublicitario.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(14)[1]);

            labelWelcome.setBounds(100, 310, 650, 25);
            labelWelcome.setFont(new Font("Garamond", Font.BOLD, 25));

            labelPublicitario.setOpaque(true);
            labelPublicitario.setBackground(Color.gray);
            labelPublicitario.setForeground(Color.WHITE);
            labelPublicitario.setBounds(0, 235, 800, 40);
            labelPublicitario.setFont(new Font("Garamond", Font.BOLD, 25));
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
    public EcraPublicitario(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        this.publicitario = (Publicitario) empresaObjeto.getUtilizadorAtivo();
        panelMenuPublicitario = new JPanel();

        BotoesMenuPublicitario botoesMenuPublicitario = new BotoesMenuPublicitario();
        LabelMenuPublicitario labelMenuPublicitario = new LabelMenuPublicitario();

        panelMenuPublicitario.setBounds(0, 225, 800, 580);
        panelMenuPublicitario.setLayout(null);
        panelMenuPublicitario.add(novaFrase);
        panelMenuPublicitario.add(conta);
        panelMenuPublicitario.add(editarFrases);
        panelMenuPublicitario.add(novoAnuncio);
        panelMenuPublicitario.add(contratos);
        panelMenuPublicitario.add(configuracoes);
        panelMenuPublicitario.add(appsOndeExibir);
        panelMenuPublicitario.add(labelPublicitario);
        panelMenuPublicitario.add(labelWelcome);
        panelMenuPublicitario.setVisible(true);
        this.objetoJframe.add(panelMenuPublicitario);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelMenuPublicitario);

        this.objetoJframe.setVisible(true);
    }
}