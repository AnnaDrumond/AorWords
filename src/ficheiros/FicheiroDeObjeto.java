package ficheiros;

import java.io.*;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Classe para ler e escrever o ficheiro de objetos.
 */
public class FicheiroDeObjeto {

    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    /**
     * Inicia leitura do ficheiro de objetos.
     *
     * @param ficheiro corresponde ao ficheiro que vai ser lido, que foi criado na classe EmpresaAorWords.
     * @throws IOException lança a exceção
     */
    public void abreLeitura(File ficheiro) throws IOException {
        objectInputStream = new ObjectInputStream(new FileInputStream(ficheiro));
    }


    /**
     * Inicia a escrita no ficheiro de objetos.
     *
     * @param ficheiro corresponde ao ficheiro que vai ser escrito, que foi criado na classe EmpresaAorWords.
     * @throws IOException lança a exceção
     */
    public void abreEscrita(File ficheiro) throws IOException {
        objectOutputStream = new ObjectOutputStream(new FileOutputStream(ficheiro));
    }


    /**
     * Realiza a leitura dos objetos gravados do ficheiro de objetos.
     *
     * @return retorna o objeto lido no ficheiro de objetos.
     * @throws IOException            lança a exceção
     * @throws ClassNotFoundException lança a exceção
     */
    public Object leObjeto() throws IOException, ClassNotFoundException {
        return objectInputStream.readObject();
    }


    /**
     * Escreve os objetos no ficheiro de objetos.
     *
     * @param object escreve o objeto no ficheiro de objetos.
     * @throws IOException Lança a exceção
     */
    public void escreveObjeto(Object object) throws IOException {
        objectOutputStream.writeObject(object);
    }


    /**
     * Fecha um ficheiro aberto em modo leitura.
     *
     * @throws IOException Lança a exceção
     */
    public void fechaLeitura() throws IOException {
        objectInputStream.close();
    }


    /**
     * Fecha um ficheiro aberto em modo escrita.
     *
     * @throws IOException Lança a exceção
     */
    public void fechaEscrita() throws IOException {
        objectOutputStream.close();
    }
}