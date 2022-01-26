package gui;

import ficheiros.FicheiroDeTexto;
import programa.EmpresaAorWords;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa a única JFrame usada durante toda a GUI.
 * Implementa os botões de troca de idiomas e o botão de sair do programa.
 */
public class FrameUnica implements ActionListener {

    protected EmpresaAorWords empresaObjeto;
    protected ValidadorGeral validadorGeral;
    protected AvisosPopUp avisosPopUp;
    protected JFrame objetoJframe = new JFrame();
    private final JRadioButton botaoPortugues;
    private final JRadioButton botaoIngles;
    private final TrocaDeIdioma trocaDeIdiomaObjeto;


    /**
     * Cria e implementa os componentes da Jframe.
     * Implementa a ação do botão de sair do programa, que chama os métodos escreverNoFicheiroGeralASaida() e
     * atualizarFicheiroDeConfiguracao() declarados em EmpresaAorWords, garantindo a execução
     * destes métodos antes do encerramento do programa.
     *
     * @param empresaObjeto   instância de EmpresaAorWords criada no método main.
     * @param ficheiroDeTexto instância de FicheiroDeTexto criada no método main.
     */
    public FrameUnica(EmpresaAorWords empresaObjeto, FicheiroDeTexto ficheiroDeTexto) {

        this.empresaObjeto = empresaObjeto;
        this.validadorGeral = new ValidadorGeral();

        objetoJframe.setResizable(false);
        objetoJframe.setSize(800, 800);
        objetoJframe.setTitle(empresaObjeto.getDadosConfiguracao().get(11));
        objetoJframe.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JLabel logotipoAorWords = new JLabel(new ImageIcon(empresaObjeto.getDadosConfiguracao().get(12)));
        logotipoAorWords.setBounds(275, 45, 253, 172);
        objetoJframe.add(logotipoAorWords);

        JRadioButton botaoSair = new JRadioButton("");
        ImageIcon imagemSair = new ImageIcon(empresaObjeto.getDadosConfiguracao().get(13));
        botaoSair.setIcon(imagemSair);
        botaoSair.setBounds(650, 20, 100, 70);

        botaoSair.addActionListener(eventoSair -> {
            empresaObjeto.escreverNoFicheiroGeralASaida();
            empresaObjeto.atualizarFicheiroDeConfiguracao();
            objetoJframe.dispose();
            System.exit(0);
        });

        objetoJframe.add(botaoSair);

        ButtonGroup grupoBotoesIdioma = new ButtonGroup();
        botaoPortugues = new JRadioButton(empresaObjeto.getDadosConfiguracao().get(16));
        botaoIngles = new JRadioButton(empresaObjeto.getDadosConfiguracao().get(17));

        ImageIcon logoFrame = new ImageIcon(empresaObjeto.getDadosConfiguracao().get(19));
        objetoJframe.setIconImage(logoFrame.getImage());

        ImageIcon imagemPT = new ImageIcon(empresaObjeto.getDadosConfiguracao().get(14));
        botaoPortugues.setIcon(imagemPT);
        botaoPortugues.setBounds(50, 20, 100, 35);

        grupoBotoesIdioma.add(botaoPortugues);
        botaoPortugues.addActionListener(this);
        objetoJframe.add(botaoPortugues);

        ImageIcon imagemEN = new ImageIcon(empresaObjeto.getDadosConfiguracao().get(15));
        botaoIngles.setIcon(imagemEN);
        botaoIngles.setBounds(50, 50, 100, 35);

        grupoBotoesIdioma.add(botaoIngles);
        botaoIngles.addActionListener(this);
        objetoJframe.add(botaoIngles);

        objetoJframe.setLocationRelativeTo(null);

        trocaDeIdiomaObjeto = new TrocaDeIdioma(empresaObjeto, objetoJframe, ficheiroDeTexto);

        avisosPopUp = new AvisosPopUp(empresaObjeto, trocaDeIdiomaObjeto);

        EcraInicial ecraInicial = new EcraInicial(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        objetoJframe.setVisible(true);
    }


    /**
     * Implementa as ações dos botões de troca de idiomas, modificando o valor booleano do atributo "idiomaPortugues"
     * declarado em EmpresaAorWords, consoante o idioma escolhido pelo utilizador.
     * Chama o método recarregarEcraAtivoComNovoIdioma().
     *
     * @param eventoBotaoidioma referência do botão escolhido pelo utilizador.
     */
    @Override
    public void actionPerformed(ActionEvent eventoBotaoidioma) {

        if (eventoBotaoidioma.getSource() == botaoPortugues) {
            empresaObjeto.setIdiomaPortugues(true);
            botaoPortugues.setSelected(true);
            recarregarEcraAtivoComNovoIdioma();

        } else if (eventoBotaoidioma.getSource() == botaoIngles) {
            empresaObjeto.setIdiomaPortugues(false);
            botaoIngles.setSelected(true);
            recarregarEcraAtivoComNovoIdioma();
        }
    }


    /**
     * Implementa a lógica criada para a troca de idioma em tempo real.
     * Verifica o valor booleano atual do atributo "idiomaPortugues" declarado em EmpresaAorWords.
     * Verifica a instância da Classe atual guardada no atributo "ecraAtivo" e a instância do Jpanel atual guardada
     * no atributo "painelAtivo", ambos declarados em EmpresaAorWords.
     * Remove o JPanel atual.
     * Instancia novamente um objeto da classe respetiva a instância armazenada em EcraAtivo, garantindo que o ecrã seja novamente
     * carregado no idioma escolhido pelo utilizador.
     */
    public void recarregarEcraAtivoComNovoIdioma() {

        if (empresaObjeto.getEcraAtivo() instanceof EcraInicial) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraInicial ecraInicial = new EcraInicial(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraLogin) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraLogin ecraLogin = new EcraLogin(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraSignUp) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraSignUp ecraSignUp = new EcraSignUp(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraIntermediario) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraIntermediario ecraIntermediario = new EcraIntermediario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraAddUtilizador) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraAddUtilizador ecraAddUtilizador = new EcraAddUtilizador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraProgramador) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraProgramador ecraProgramador = new EcraProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraAddAplicacao) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraAddAplicacao ecraAddAplicacao = new EcraAddAplicacao(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraPublicitario) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraPublicitario ecraPublicitario = new EcraPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraAddFrase) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraAddFrase ecraAddFrase = new EcraAddFrase(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraConta) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraConta ecraConta = new EcraConta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraEditarAplicacoes) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraEditarAplicacoes ecraEditarAplicacoes = new EcraEditarAplicacoes(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraEditarFrases) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraEditarFrases ecraEditarFrases = new EcraEditarFrases(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraMovimentosFinanceiros) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraMovimentosFinanceiros ecraMovimentosFinanceiros = new EcraMovimentosFinanceiros(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraAddAnuncio) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraAddAnuncio ecraAddAnuncio = new EcraAddAnuncio(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraContratosPublicitario) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraContratosPublicitario ecraContratosPublicitario = new EcraContratosPublicitario(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraConfiguracoes) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraConfiguracoes ecraConfiguracoes = new EcraConfiguracoes(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraContratosEmpresa) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraContratosEmpresa ecraContratosEmpresa = new EcraContratosEmpresa(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraContratosProgramador) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraContratosProgramador ecraContratosProgramador = new EcraContratosProgramador(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraEscolherAppConsulta) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraEscolherAppConsulta ecraEscolherAppConsulta = new EcraEscolherAppConsulta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraVerFrasesPorExibir) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraVerFrasesPorExibir ecraVerFrasesPorExibir = new EcraVerFrasesPorExibir(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraEscolherFraseConsulta) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraEscolherFraseConsulta ecraEscolherFraseConsulta = new EcraEscolherFraseConsulta(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraVerAppsOndeExibidas) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraVerAppsOndeExibidas ecraVerAppsOndeExibidas = new EcraVerAppsOndeExibidas(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraMovimentosFinanceiros) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraMovimentosFinanceiros ecraMovimentosFinanceiros = new EcraMovimentosFinanceiros(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraMovimentosUsers) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraMovimentosUsers ecraMovimentosUsers = new EcraMovimentosUsers(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);

        } else if (empresaObjeto.getEcraAtivo() instanceof EcraConsultarUtilizadores) {
            objetoJframe.remove(empresaObjeto.getPainelAtivo());
            EcraConsultarUtilizadores ecraConsultarUtilizadores = new EcraConsultarUtilizadores(empresaObjeto, objetoJframe, trocaDeIdiomaObjeto, validadorGeral, avisosPopUp);
        }
    }
}

