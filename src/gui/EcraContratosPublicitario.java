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
 * Implementa o ecrã que permite aos publicitários, visualizar os
 * contratos dos anúncios que criaram para as suas frases.
 * Instancia os componentes da classe.
 */
public class EcraContratosPublicitario extends JPanel {

    protected JFrame objetoJframe;
    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    private final JPanel panelContratosPublicitario;
    private final JButton voltar = new JButton();
    private final JTable jTableContratosPublicitario = new JTable();
    private final JLabel labelContratos = new JLabel();
    private final JLabel instrucoes = new JLabel();


    /**
     * Implementa os botões de EcraContratosProgramador e as suas respetivas ações.
     */
    class BotoesEcraContratosPublicitario implements ActionListener {


        /**
         * Construtor.
         * Implementa as características dos botões conforme o idioma escolhido.
         */
        public BotoesEcraContratosPublicitario() {

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
         * @param eventosEcraContratosPublicitario referência do botão escolhido pelo utilizador.
         */
        @Override
        public void actionPerformed(ActionEvent eventosEcraContratosPublicitario) {
            objetoJframe.remove(panelContratosPublicitario);
            EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
        }
    }


    /**
     * Implementa os Jlabels de EcraContratosPublicitario e as suas respetivas ações.
     */
    class LabelEcraContratosPublicitario {


        /**
         * Construtor.
         * Implementa as características dos Jlabels conforme o idioma escolhido.
         */
        public LabelEcraContratosPublicitario() {

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
     * Implementa a Jtable de EcraContratosPublicitario.
     */
    class JTabelaEcraContratosPublicitario {


        /**
         * Construtor.
         * Chama o método construirCarregarDadosDaJtable().
         * Define a localização da tabela em JPanel.
         */
        public JTabelaEcraContratosPublicitario() {
            construirCarregarDadosDaJtable();
            jTableContratosPublicitario.setBounds(50, 330, 700, 300);
        }


        /**
         * Instancia um componente JScrollPane, adicionando a tabela ao mesmo.
         *
         * @return retorna o componente jScrollPane criado.
         */
        public JScrollPane definirScroll() {
            JScrollPane jScrollPane = new JScrollPane(jTableContratosPublicitario);
            jScrollPane.setBounds(50, 330, 700, 300);
            return jScrollPane;
        }


        /**
         * Verifica as informações dos contratos realizados pelo publicitário com ‘login’ ativo no momento.
         * Cumpre o requisito do Projeto: "Exibir o custo das campanhas publicitárias".
         * Cria o modelo de JTable e insere os dados na tabela.
         * Instancia um objeto TableRowSorter, e o coloca na tabela de modo a permitir a
         * ordenação das informações de cada coluna da tabela.
         */
        public void construirCarregarDadosDaJtable() {

            ArrayList<Anuncio> listaAnunciosPublicitario = new ArrayList<>(10);
            for (Anuncio anuncio : empresaObjeto.getListaAnuncios()) {
                if (anuncio.getAnunciante().equals(empresaObjeto.getUtilizadorAtivo())) {
                    listaAnunciosPublicitario.add(anuncio);
                }
            }

            Object[] vetorColunasTabela;

            if (empresaObjeto.isIdiomaPortugues()) {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(41)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(36)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(101)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(89)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(86)[1], trocaDeIdiomaObjeto.getIdiomasLista().get(85)[1]};
            } else {
                vetorColunasTabela = new Object[]{trocaDeIdiomaObjeto.getIdiomasLista().get(41)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(36)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(101)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(89)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(86)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(85)[2]};
            }

            jTableContratosPublicitario.setModel(new DefaultTableModel(new Object[][]{}, vetorColunasTabela));
            DefaultTableModel modeloTabela = (DefaultTableModel) jTableContratosPublicitario.getModel();

            Object[] dadosLinha = new Object[vetorColunasTabela.length];

            for (Anuncio anuncio : listaAnunciosPublicitario) {

                dadosLinha[0] = anuncio.getFraseAnunciada().getFrase();
                dadosLinha[1] = anuncio.getAplicacao().getNome();
                dadosLinha[2] = anuncio.getDataContrato();
                dadosLinha[3] = BigDecimal.valueOf(anuncio.getValorUnitario()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                dadosLinha[4] = anuncio.getNumExibicoes();
                dadosLinha[5] = BigDecimal.valueOf(anuncio.getValorAnuncio()).setScale(2, RoundingMode.UP) + trocaDeIdiomaObjeto.getIdiomasLista().get(154)[1];
                modeloTabela.addRow(dadosLinha);
            }
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(modeloTabela);
            jTableContratosPublicitario.setRowSorter(sorter);
            jTableContratosPublicitario.setDefaultEditor(Object.class, null);
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
    public EcraContratosPublicitario(EmpresaAorWords empresaObjeto, JFrame objetoJframe, TrocaDeIdioma trocaDeIdiomaObjeto, ValidadorGeral validadorGeral, AvisosPopUp avisosPopUp) {

        this.empresaObjeto = empresaObjeto;
        this.objetoJframe = objetoJframe;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
        this.validadorGeral = validadorGeral;
        this.avisosPopUp = avisosPopUp;
        this.panelContratosPublicitario = new JPanel();

        BotoesEcraContratosPublicitario botoesEcraContratosPublicitario = new BotoesEcraContratosPublicitario();
        LabelEcraContratosPublicitario labelEcraContratosPublicitario = new LabelEcraContratosPublicitario();
        JTabelaEcraContratosPublicitario jTabelaEcraContratosPublicitario = new JTabelaEcraContratosPublicitario();

        panelContratosPublicitario.setBounds(0, 225, 800, 580);
        panelContratosPublicitario.setLayout(null);
        panelContratosPublicitario.add(voltar);
        panelContratosPublicitario.add(labelContratos);
        panelContratosPublicitario.add(instrucoes);
        panelContratosPublicitario.add(jTabelaEcraContratosPublicitario.definirScroll());

        panelContratosPublicitario.setVisible(true);
        this.objetoJframe.add(panelContratosPublicitario);

        this.empresaObjeto.setEcraAtivo(this);
        this.empresaObjeto.setPainelAtivo(panelContratosPublicitario);

        this.objetoJframe.setVisible(true);
    }
}
