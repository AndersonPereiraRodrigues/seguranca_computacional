package cryptography;

import java.io.*;
import java.security.*;

public class Cryptography {

    /**
     * @param args the command line arguments
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        System.out.println("\tACADEMICOS: ANDERSON PEREIRA RODRIGUES & TATIANY KEIKO MORI \n\t\t\t\tTRABALHO V\n");
        
        //tamanho do arquivo
        int Size = 26301439;// 4317513;//26301439; 
        
        //tamanho do bloco
        int bloco = 1024;
        
        //tamanho do shar
        int sharB = 32;

        //local do arquivo
        //FileInputStream fis = new FileInputStream("D:\\GIT\\Seguranca Computacional\\Cryptography\\src\\cryptography\\video03.mp4");
        FileInputStream fis = new FileInputStream("C:\\Users\\Anderson\\Downloads\\video03.mp4");
        
        //arquivo lido
        byte[] data = new byte[Size];
        

        byte[] text2 = new byte[bloco + sharB];
        
        int aux2 = 0;
        byte[] blocoShar = new byte[bloco + sharB];
        int nre = 0;
        
        //leitura do arquivo
        while (nre != -1) {
            nre = fis.read(data);
        }
        
        MessageDigest mensage = MessageDigest.getInstance("SHA-256");
        // inicio é o ponto onde ao dividir os blocos o fecha o valor inteiro é ondo inicia a
        //leitura do ultimo bloco que ira ser só gerado o shar
        int inicio = Size - (Size % bloco);
        // armazina o ultimo bloco de antes de gerar o shar
        byte[] text = new byte[Size - inicio];
        int cont = 0;
        
        //realiza a leitura do ultimo bloco e salca em text 
        while (inicio < Size) {
            text[cont] = data[inicio];
            inicio++;
            cont++;
        }
        //gera shar do ultimo bloco armazenado em text 
        byte[] hash = mensage.digest(text);

        //operação para pegar onde é inicio da leitura retirando o resto do bloco e o ultimo bloco que já foi operado
        inicio = Size - ((Size % bloco) + bloco);
        cont = 0;
        while (inicio >= 0) {
            //concatenação de dois blocos (arquivo total, inicio da leitura, pra onde vai, inicio de onde vai, quantos ler)
            System.arraycopy(data, inicio, blocoShar, 0, bloco);
            //pega hash iniciando em 0 (arquivo hash, inicio do hash, pra onde vai, inicio de onde salvar, quantos ler)
            System.arraycopy(hash, 0, blocoShar, bloco, sharB);
            //leituro de um bloco
            while (aux2 < blocoShar.length) {
                text2[cont] = blocoShar[aux2];
                aux2++;
                cont++;
            }
            
            cont = 0;
            aux2 = 0;

            hash = mensage.digest(text2);
            inicio = inicio - bloco;
        }
        //conversão o hash final em string HEX
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", 0xFF & b));
        }

        String senhahex = hexString.toString();
        System.out.println("\t"+senhahex+"\n");
    }
}
