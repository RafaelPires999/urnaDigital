/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rafa-pires
 */
public class EleitorDAO {
    private static final String ARQUIVO = "eleitores.txt";
    private List<Eleitor> eleitores = new ArrayList<>();
    
    public EleitorDAO(){
        carregarEleitor();
    }
    
    private static String limparNumeros(String texto){
        return texto.replaceAll("[^0-9]", "");
    }
    
    public void cadastrarEleitor(String cpf, String titulo, String nome){
        String cpfLimpo = limparNumeros(cpf);
        String tituloLimpo = limparNumeros(titulo);
        Eleitor eleitor = new Eleitor(md5(cpfLimpo), tituloLimpo, nome, false);
        eleitores.add(eleitor);
        salvarEleitor(eleitor);
    }
    
    public Eleitor buscaPorCpf(String cpf){
        String cpfHash = md5(limparNumeros(cpf));
        for (Eleitor e : eleitores){
            if(e.getCpf().equals(cpfHash)){
                return e;
            }
        }
        return null;
    }
    
    public boolean existeTitulo(String titulo){
        String tituloLimpo = limparNumeros(titulo);
        for(Eleitor e : eleitores){
            if(e.getTitulo().equals(tituloLimpo)){
                return true;
            }
        }
        return false;
    }
    
    public void resetarVotos(){
        for(Eleitor e : eleitores){
            e.setJaVotou(false);
        }
        salvarTodos();
    }
    
    private void salvarEleitor(Eleitor eleitor){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))){
            bw.write(eleitor.getCpf() + ";" + eleitor.getTitulo() + ";" + eleitor.getNome() + ";" + eleitor.isJaVotou());
            bw.newLine();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void registrarVoto(Eleitor eleitor){
        eleitor.setJaVotou(true);
        salvarTodos();
    }

    private void salvarTodos(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, false))){
            for(Eleitor e : eleitores){
                bw.write(e.getCpf() + ";" + e.getTitulo() + ";" + e.getNome() + ";" + e.isJaVotou());
                bw.newLine();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void carregarEleitor(){
        File arquivo = new File(ARQUIVO);
        if(!arquivo.exists()){
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;
            while((linha = br.readLine()) != null){
                String[] partes = linha.split(";");
                if(partes.length == 4){
                    eleitores.add(new Eleitor(partes[0], partes[1], partes[2], Boolean.parseBoolean(partes[3]))); 
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    private static String md5(String texto){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(texto.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}