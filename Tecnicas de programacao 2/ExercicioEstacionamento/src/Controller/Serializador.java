/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

public class Serializador {
    
    public static void gravar(String caminho, Object objeto)throws FileNotFoundException,IOException{
        
        FileOutputStream outFile= new FileOutputStream(caminho);
        ObjectOutputStream outObj = new ObjectOutputStream(outFile);
        try {
            outObj.writeObject(objeto);
        } catch (Exception e) {
            new Exception("Erro ao gravar em arquivo!", e);
            
        }finally{
            outObj.close();
        }
        
        
    }
    public static Object ler(String caminho)throws FileNotFoundException,IOException, ClassNotFoundException{
        
        FileInputStream inFile = new FileInputStream(caminho);
        ObjectInputStream inObj=new ObjectInputStream(inFile);
        try{
            Object objeto = inObj.readObject();
            return objeto;
        }catch(Exception e){
            new Exception("Erro ao ler o arquivo", e);
        }finally{
            inObj.close();
        }
        
        return null;
        
    }
}