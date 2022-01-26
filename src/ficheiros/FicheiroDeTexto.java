package ficheiros;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Classe para ler e escrever no ficheiro de configuração e ler o ficheiro que contém os idiomas
 */
public class FicheiroDeTexto {

    private BufferedReader bufferedReaderAtributo;
    private BufferedWriter bufferedWriterAtributo;


    /**
     * Tenta abrir o ficheiro de texto para iniciar sua leitura.
     * @param nomeDoFicheiro corresponde ao nome do ficheiro que vai ser lido.
     * @return valor booleano true se conseguir encontrar e ler o ficheiro ou false, caso não consiga
     */
    public boolean abrirLeitura(String nomeDoFicheiro){
        try{
            bufferedReaderAtributo = new BufferedReader(new InputStreamReader(new FileInputStream(nomeDoFicheiro), StandardCharsets.UTF_8));
            return true;
        } catch (IOException erro){
            return false;
        }
    }



    /**
     * Inicia a reescrita do ficheiro de texto.
     *
     * @param nomeDoFicheiro corresponde ao nome do ficheiro que vai ser escrito.
     * @throws IOException Lança a exceção
     */
    public void abrirEscrita(String nomeDoFicheiro) throws IOException {
        bufferedWriterAtributo = new BufferedWriter(new FileWriter(nomeDoFicheiro, false));
    }


    /**
     * Realiza a leitura dos textos do ficheiro de texto, linha a linha.
     *
     * @return retorna a linha lida.
     * @throws IOException Lança a exceção
     */
    public String lerLinha() throws IOException {
        return bufferedReaderAtributo.readLine();
    }


    /**
     * Realiza a escrita dos textos do ficheiro de texto, linha a linha.
     *
     * @param linha corresponde a linha atual do ficheiro
     * @throws IOException Lança a exceção
     */
    public void escreverLinha(String linha) throws IOException {
        bufferedWriterAtributo.write(linha, 0, linha.length());
        bufferedWriterAtributo.newLine();
    }


    /**
     * Método necessário para fechar um ficheiro aberto em modo de leitura.
     *
     * @throws IOException Lança a exceção
     */
    public void fecharLeitura() throws IOException {
        bufferedReaderAtributo.close();
    }

    /**
     * Método necessário para fechar um ficheiro aberto em modo escrito.
     *
     * @throws IOException Lança a exceção
     */
    public void fecharEscrita() throws IOException {
        bufferedWriterAtributo.close();
    }

}