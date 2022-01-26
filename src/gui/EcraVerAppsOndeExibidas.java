package gui;

import programa.Anuncio;
import programa.EmpresaAorWords;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa o ecrã que permite aos publicitários visualizar as aplicações onde uma determinada frase
 * escolhida em EcraEscolherFraseConsulta será exibida, assim como o custo atual dos anúncios daquela frase.
 * Cumpre o requisito do projeto: "Verificar as aplicações nas quais as frases serão exibidas."
 * Instancia os componentes da classe.
 */
public class EcraVerAppsOndeExibidas extends JPanel {

    protected EmpresaAorWords empresaObjeto;
    protected JFrame objetoJframe;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelAppsOndeExibir;
    private final String nomeFraseSelecionada;
    private double custo = 0.0;
    private final JLabel labelFaixaCinza = new JLabel();
    private final JLabel instrucoes = new JLabel();
    private final JLabel labelCusto = new JLabel();
    private final JButton voltar = new JButton();
    private final JTable jTableAppsOndeExibirExibir = new JTable();
    private final ArrayList<Anuncio> anuncioListaAuxiliar = new ArrayList<>(10);


    /**
     * Implementa os botões de EcraEscolherAppConsulta e as suas respetivas ações.
     */
    class BotoesAppsOndeExibir implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesAppsOndeExibir() {

            if (empresaObjeto.isIdiomaPortugues()) {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
            } else {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);
            }

            voltar.setBounds(340, 650, 120, 45);
            voltar.addActionListener(this);
        }


        /**
         * Implementa as ações do botão voltar.
         * Remove o JPanel atual.
         * Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventoBotaoVoltar referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventoBotaoVoltar) {

            objetoJframe.remove(panelAppsOndeExibir);
            trocaDeIdiomaObjeto.setBufferAuxiliar(null);
            EcraEscolherFraseConsulta ecraEscolherFraseConsulta = new EcraEscolherFraseConsulta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
        }
    }


    /**
     * Implementa os Jlabels de EcraVerAppsOndeExibidas e as suas respetivas ações.
     */
    class LabelFaixaCinza {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelFaixaCinza() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelFaixaCinza.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(114)[1]);
                labelCusto.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(85)[1]));
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[1]);

            } else {
                labelFaixaCinza.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(114)[2]);
                labelCusto.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(85)[2]));
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[2]);

            }

            labelFaixaCinza.setOpaque(true);
            labelFaixaCinza.setBackground(Color.gray);
            labelFaixaCinza.setForeground(Color.WHITE);
            labelFaixaCinza.setBounds(0, 235, 800, 40);
            labelFaixaCinza.setFont(new Font("Garamond", Font.BOLD, 25));

            instrucoes.setBounds(155, 295, 600, 20);
            instrucoes.setFont(new Font("Garamond", Font.BOLD, 20));

            labelCusto.setBounds(340, 590, 120, 45);
        }
    }


    /**
     * Implementa a Jtable de  EcraVerAppsOndeExibidas.
     */
    class TabelaAppsOndeExibir {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public TabelaAppsOndeExibir() {
            construirCarregarDadosDaJtable();
            jTableAppsOndeExibirExibir.setBounds(100, 330, 600, 250);
        }


        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTableAppsOndeExibirExibir);
            jScrollPane.setBounds(100, 330, 600, 250);
            return jScrollPane;
        }


        /**
         * Implementa o ecrã que permite aos publicitários visualizar as aplicações onde uma determinada frase
         * escolhida em EcraEscolherFraseConsulta será exibida, assim como o custo atual dos anúncios daquela frase.
         * Verifica os anúncios de uma determinada frase escolhida pelo publicitário em EcraEscolherFraseConsulta,
         * buscando as aplicações onde esta frase será exibida, para poder exibir estes dados na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna da tabela.
         */
        public void construirCarregarDadosDaJtable() {

            for (Anuncio anuncio : empresaObjeto.getListaAnuncios()) {
                if (anuncio.getFraseAnunciada().getFrase().equals(nomeFraseSelecionada)) {
                    anuncioListaAuxiliar.add(anuncio);
                }
            }
            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(36)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(38)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(42)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(85)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(36)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(38)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(42)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(85)[2]};
            }

            jTableAppsOndeExibirExibir.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));
            DefaultTableModel modeloTableAppsOndeExibir = (DefaultTableModel) jTableAppsOndeExibirExibir.getModel();

            Object[] dadosLinha = new Object[vetorColunasTabela.length];

            for (Anuncio anuncio : anuncioListaAuxiliar) {
                dadosLinha[0] = anuncio.getAplicacao().getNome();
                dadosLinha[1] = BigDecimal.valueOf(anuncio.getValorUnitario()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                dadosLinha[2] = anuncio.getNumExibicoes();
                dadosLinha[3] = BigDecimal.valueOf(anuncio.getValorAnuncio()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];

                custo = custo + anuncio.getValorAnuncio();

                modeloTableAppsOndeExibir.addRow(dadosLinha);
            }

            labelCusto.setText(BigDecimal.valueOf(custo).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1]);

            TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTableAppsOndeExibir);
            jTableAppsOndeExibirExibir.setRowSorter(sorter);
            jTableAppsOndeExibirExibir.setDefaultEditor(Object.class, null);

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
    public EcraVerAppsOndeExibidas(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.objetoJframe = objetoJframe;
        this.empresaObjeto = empresaObjeto;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        this.nomeFraseSelecionada = trocaDeIdiomaObjeto.getBufferAuxiliar();

        panelAppsOndeExibir = new JPanel();
        panelAppsOndeExibir.setBounds(0, 225, 800, 580);
        panelAppsOndeExibir.setLayout(null);

        BotoesAppsOndeExibir botoesAppsOndeExibir = new BotoesAppsOndeExibir();
        LabelFaixaCinza labelFaixaCinza = new LabelFaixaCinza();
        TabelaAppsOndeExibir tabelaAppsOndeExibir = new TabelaAppsOndeExibir();

        panelAppsOndeExibir.add(tabelaAppsOndeExibir.definirScroll());
        panelAppsOndeExibir.add(this.labelFaixaCinza);
        panelAppsOndeExibir.add(voltar);
        panelAppsOndeExibir.add(instrucoes);
        panelAppsOndeExibir.add(labelCusto);

        panelAppsOndeExibir.setVisible(true);
        this.objetoJframe.add(panelAppsOndeExibir);
        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelAppsOndeExibir);
        this.objetoJframe.setVisible(true);
    }
}
