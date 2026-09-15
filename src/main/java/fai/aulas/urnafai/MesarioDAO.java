/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fai.aulas.urnafai;

import java.util.ArrayList;
import java.util.List;
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
class MesarioDAO {
    private static final String ARQUIVO = "mesarios.txt";
    private List<Mesario> mesarios = new ArrayList<>();
    
    public void recarregar(){
    mesarios.clear();
    carregarMesario();
}
    
    public void cadastrarMesario(String nome, String matricula, String usuario, String senha){
        Mesario mesario = new Mesario(nome, matricula, usuario, senha);
        mesarios.add(mesario);
        salvarMesario(mesario);
    }
    
    public Mesario autenticarMesario(String usuario, String senha){
        for(Mesario m : mesarios){
            if(m.getUsuario().equals(usuario) && m.getSenha().equals(senha)){
                return m;
            }
        }
        return null;
    }
    
    public boolean existeUsuario(String usuario){
        for(Mesario m : mesarios){
            if(m.getUsuario().equals(usuario)){
                return true;
            }
        }
        return false;
    }
    
    public boolean existeMatricula(String matricula){
        for(Mesario m : mesarios){
            if(m.getMatricula().equals(matricula)){
                return true;
            }
        }
        return false;
    }
    
    private void salvarMesario(Mesario mesario){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO, true))){
            bw.write(mesario.getNome() + ";" + mesario.getMatricula() + ";" + mesario.getUsuario() + ";" + mesario.getSenha());
            bw.newLine();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    private void carregarMesario(){
        File arquivo = new File(ARQUIVO);
        if(!arquivo.exists()){
            return;
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while((linha = br.readLine()) != null){
                String[] partes = linha.split(";");
                if(partes.length == 4){
                    mesarios.add(new Mesario(partes[0], partes[1], partes[2], partes[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}