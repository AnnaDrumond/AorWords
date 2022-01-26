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
 * Implementa o ecrã que permite aos programadores visualizar as frases a serem exibidas numa determinada aplicação
 * escolhida em EcraEscolherAppConsulta, assim como a receita atual dos anúncios daquela aplicação.
 * Cumpre o requisito do projeto: "Verificar as frases a exibir"
 * Instancia os componentes da classe.
 */
public class EcraVerFrasesPorExibir extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private TableRowSorter<TableModel> sorter;
    private final JPanel panelFrasesExibir;
    private DefaultTableModel modeloTableFrasesExibir;
    private final String nomeAppSelecionada;
    private final JLabel labelFaixaCinza = new JLabel();
    private final JLabel labelReceitas = new JLabel();
    private final JLabel instrucoes = new JLabel();
    private double receitas = 0.0;
    private final JButton voltar = new JButton();
    private final JTable jTableFrasesExibir = new JTable();
    private final ArrayList<Anuncio> anuncioListaAuxiliar = new ArrayList<>(10);


    /**
     * Implementa os botões de EcraVerFrasesPorExibir e as suas respetivas ações.
     */
    class BotoesFrasesExibir implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesFrasesExibir() {
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
         * @param eventosFrasesExibir referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosFrasesExibir) {

            objetoJframe.remove(panelFrasesExibir);
            trocaDeIdiomaObjeto.setBufferAuxiliar(null);
            EcraEscolherAppConsulta ecraEscolherAppConsulta = new EcraEscolherAppConsulta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        }
    }


    /**
     * Implementa os Jlabels de EcraVerFrasesPorExibir e as suas respetivas ações.
     */
    class LabelFaixaCinza {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelFaixaCinza() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelFaixaCinza.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(110)[1]);
                labelReceitas.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(103)[1]));
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[1]);
            } else {
                labelFaixaCinza.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(110)[2]);
                labelReceitas.setBorder(javax.swing.BorderFactory.createTitledBorder(trocaDeIdiomaObjeto.getIdiomasLista().get(103)[2]));
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[2]);
            }

            labelFaixaCinza.setOpaque(true);
            labelFaixaCinza.setBackground(Color.gray);
            labelFaixaCinza.setForeground(Color.WHITE);
            labelFaixaCinza.setBounds(0, 235, 800, 40);
            labelFaixaCinza.setFont(new Font("Garamond", Font.BOLD, 25));

            instrucoes.setBounds(155, 295, 600, 20);
            instrucoes.setFont(new Font("Garamond", Font.BOLD, 20));

            labelReceitas.setBounds(340, 590, 120, 45);
        }
    }


    /**
     * Implementa a Jtable de EcraVerFrasesPorExibir.
     */
    class TabelaFrasesExibir {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public TabelaFrasesExibir() {
            construirCarregarDadosDaJtable();
            jTableFrasesExibir.setBounds(100, 330, 600, 250);
        }


        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTableFrasesExibir);
            jScrollPane.setBounds(100, 330, 600, 250);
            return jScrollPane;
        }


        /**
         * Implementa o ecrã que permite aos programadores visualizar as frases a serem exibidas numa determinada aplicação
         * escolhida em EcraEscolherAppConsulta, assim como a receita atual dos anúncios daquela aplicação.
         * Verifica os anúncios de uma determinada aplicação escolhida pelo programador em EcraEscolherAppConsulta,
         * buscando as frases a serem exibidas nesta aplicação, para poder exibir estes dados na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna da tabela.
         */
        public void construirCarregarDadosDaJtable() {

            for (Anuncio anuncio : empresaObjeto.getListaAnuncios()) {
                if (anuncio.getAplicacao().getNome().equals(nomeAppSelecionada)) {
                    anuncioListaAuxiliar.add(anuncio);
                }
            }

            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(41)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(42)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(103)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(41)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(42)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(103)[2]};
            }

            jTableFrasesExibir.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));
            modeloTableFrasesExibir = (DefaultTableModel) jTableFrasesExibir.getModel();

            Object dadosLinha[] = new Object[vetorColunasTabela.length];

            for (Anuncio anuncio : anuncioListaAuxiliar) {
                dadosLinha[0] = anuncio.getFraseAnunciada().getFrase();
                dadosLinha[1] = anuncio.getNumExibicoes();
                dadosLinha[2] = BigDecimal.valueOf(anuncio.getReceitaProgramador()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];

                receitas = receitas + anuncio.getReceitaProgramador();
                modeloTableFrasesExibir.addRow(dadosLinha);
            }

            labelReceitas.setText(BigDecimal.valueOf(receitas).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1]);

            sorter = new TableRowSorter<>(modeloTableFrasesExibir);
            jTableFrasesExibir.setRowSorter(sorter);
            jTableFrasesExibir.setDefaultEditor(Object.class, null);
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
    public EcraVerFrasesPorExibir(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.objetoJframe = objetoJframe;
        this.empresaObjeto = empresaObjeto;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        this.nomeAppSelecionada = trocaDeIdiomaObjeto.getBufferAuxiliar();

        panelFrasesExibir = new JPanel();
        panelFrasesExibir.setBounds(0, 225, 800, 580);
        panelFrasesExibir.setLayout(null);

        LabelFaixaCinza labelFaixaCinza = new LabelFaixaCinza();
        BotoesFrasesExibir botoesFrasesExibir = new BotoesFrasesExibir();
        TabelaFrasesExibir tabelaFrasesExibir = new TabelaFrasesExibir();

        panelFrasesExibir.add(tabelaFrasesExibir.definirScroll());
        panelFrasesExibir.add(this.labelFaixaCinza);
        panelFrasesExibir.add(voltar);
        panelFrasesExibir.add(labelReceitas);
        panelFrasesExibir.add(instrucoes);

        panelFrasesExibir.setVisible(true);
        this.objetoJframe.add(panelFrasesExibir);
        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelFrasesExibir);
        this.objetoJframe.setVisible(true);
    }
}