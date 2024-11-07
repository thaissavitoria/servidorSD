/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.JOptionPane;
import pacote.RmiInterface;
import pacote.RmiImplementation;

/**
 *
 * @author admlab
 */
public class Server {
    public Server(){
    try{
        Registry reg=LocateRegistry.createRegistry(6666);
        RmiInterface objRmi = new RmiImplementation();
        reg.bind("Servidor", objRmi);
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Erro"+ e.getMessage());
    }
    }
}
