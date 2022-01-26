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
 * Implementa o ecrã que permite aos programadores, visualizar os contratos dos anúncios
 * constantes nas suas aplicações.
 * Instancia os componentes da classe.
 */
public class EcraContratosProgramador extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelContratosProgramador;
    private final JButton voltar = new JButton();
    private final JTable jTableContratosProgramador = new JTable();
    private final JLabel labelContratos = new JLabel();
    private final JLabel instrucoes = new JLabel();


    /**
     * Implementa os botões de EcraContratosProgramador e as suas respetivas ações.
     */
    class BotoesEcraContratosProgramador implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesEcraContratosProgramador() {

            if (empresaObjeto.isIdiomaPortugues()) {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[1]);
            } else {
                voltar.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(5)[2]);

            }
            voltar.setBounds(340, 650, 120, 45);
            voltar.addActionListener(this);
        }


        /**
         * Implementa as respetivas ações dos botões da classe.
         * Remove o JPanel atual. Instancia um objeto, correspodente à construção do próximo JPanel.
         *
         * @param eventosEcraContratosProgramador referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraContratosProgramador) {
            objetoJframe.remove(panelContratosProgramador);
            EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
        }
    }


    /**
     * Implementa os Jlabels de EcraContratosProgramador e as suas respetivas ações.
     */
    class LabelEcraContratosProgramador {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelEcraContratosProgramador() {

            if (empresaObjeto.isIdiomaPortugues()) {
                labelContratos.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(100)[1]);
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[1]);

            } else {
                labelContratos.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(100)[2]);
                instrucoes.setText(trocaDeIdiomaObjeto.getIdiomasLista().get(136)[2]);
            }

            labelContratos.setOpaque(true);
            labelContratos.setBackground(Color.gray);
            labelContratos.setForeground(Color.WHITE);
            labelContratos.setBounds(0, 235, 800, 40);
            labelContratos.setFont(new Font("Garamond", Font.BOLD, 20));

            instrucoes.setBounds(175, 295, 600, 20);
            instrucoes.setFont(new Font("Garamond", Font.BOLD, 20));
        }
    }


    /**
     * Implementa a Jtable de EcraContratosProgramador.
     */
    class JTabelaEcraContratosProgramador {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public JTabelaEcraContratosProgramador() {
            construirCarregarDadosDaJtable();
            jTableContratosProgramador.setBounds(50, 330, 700, 300);
        }


        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTableContratosProgramador);
            jScrollPane.setBounds(50, 330, 700, 300);
            return jScrollPane;
        }


        /**
         * Verifica as informações dos contratos referentes aos anúncios efetuados nas aplicações do
         * programador com ‘login’ ativo no momento.
         * Cumpre o requisito do Projeto: "Exibir a receita das campanhas publicitárias".
         * Cria o modelo de JTable e insere os dados na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna da tabela.
         */
        public void construirCarregarDadosDaJtable() {

            ArrayList<Anuncio> listaAnunciosProgramador = new ArrayList<>(10);
            for (Anuncio anuncio : empresaObjeto.getListaAnuncios()) {
                if (anuncio.getAnunciador().equals(empresaObjeto.getUtilizadorAtivo())) {
                    listaAnunciosProgramador.add(anuncio);
                }
            }

            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(36)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(41)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(101)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(89)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(86)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(103)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(36)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(41)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(101)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(89)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(86)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(103)[2]};
            }

            jTableContratosProgramador.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));

            DefaultTableModel modeloTabela = (DefaultTableModel) jTableContratosProgramador.getModel();

            Object dadosLinha[] = new Object[vetorColunasTabela.length];

            for (Anuncio anuncio : listaAnunciosProgramador) {

                dadosLinha[0] = anuncio.getAplicacao().getNome();
                dadosLinha[1] = anuncio.getFraseAnunciada().getFrase();
                dadosLinha[2] = anuncio.getDataContrato();
                dadosLinha[3] = BigDecimal.valueOf(anuncio.getValorUnitario()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                dadosLinha[4] = anuncio.getNumExibicoes();
                dadosLinha[5] = BigDecimal.valueOf(anuncio.getReceitaProgramador()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                modeloTabela.addRow(dadosLinha);
            }
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTabela);
            jTableContratosProgramador.setRowSorter(sorter);
            jTableContratosProgramador.setDefaultEditor(Object.class, null);
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
    public EcraContratosProgramador(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {
        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        this.panelContratosProgramador = new JPanel();

        BotoesEcraContratosProgramador botoesEcraContratosProgramador = new BotoesEcraContratosProgramador();
        LabelEcraContratosProgramador labelEcraContratosProgramador = new LabelEcraContratosProgramador();
        JTabelaEcraContratosProgramador jTabelaEcraContratosProgramador = new JTabelaEcraContratosProgramador();

        panelContratosProgramador.setBounds(0, 225, 800, 580);
        panelContratosProgramador.setLayout(null);
        panelContratosProgramador.add(voltar);
        panelContratosProgramador.add(labelContratos);
        panelContratosProgramador.add(instrucoes);
        panelContratosProgramador.add(jTabelaEcraContratosProgramador.definirScroll());

        panelContratosProgramador.setVisible(true);
        this.objetoJframe.add(panelContratosProgramador);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelContratosProgramador);

        this.objetoJframe.setVisible(true);
    }
}
