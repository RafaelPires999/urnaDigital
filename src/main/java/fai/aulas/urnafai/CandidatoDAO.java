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
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author rafa-pires
 */
public class CandidatoDAO {
    private static final String ARQUIVO = "candidatos.txt";
    private List<Candidato> candidatos = new ArrayList<>();
    
    public CandidatoDAO(){
        carregarCandidato();
    }
    
    public List<Candidato> listarTodos(){
        return new ArrayList<>(candidatos);
    }
    
    public void adicionar(Candidato c){
        candidatos.add(c);
    }
    
    public void cadastrarCandidato(String numero, String nome, String partido, String vice, String cargo){
        Candidato candidato = new Candidato(numero, nome, partido, vice, cargo);
        candidatos.add(candidato);
        salvarCandidato(candidato);
    }
    
    public Candidato buscaPorNumero(String numero){
        for (Candidato c : candidatos){
            if(c.getNumero().equals(numero)){
                return c;
            }
        }
        return null;
    }
    
    public boolean numeroExiste(String numero){
        for(Candidato c : candidatos){
            if(c.getNumero().equals(numero)){
                return true;
            }
        }
        return false;
    }
    
    private void salvarCandidato(Candidato c){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))){
            bw.write(c.getNumero() + ";" + c.getNome() + ";" + c.getPartido() + ";" + (c.getVice() == null ? "" : c.getVice()) + ";" + c.getCargo());
            bw.newLine();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void carregarCandidato(){
        File arquivo = new File(ARQUIVO);
        if(!arquivo.exists()){
            return;
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader(arquivo))){
            String linha;
            while((linha = br.readLine()) != null){
                String[] partes = linha.split(";", -1);
                if(partes.length == 5){
                    String vice = partes[3].isEmpty() ? null : partes[3];
                    candidatos.add(new Candidato(partes[0], partes[1], partes[2], vice, partes[4]));
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
