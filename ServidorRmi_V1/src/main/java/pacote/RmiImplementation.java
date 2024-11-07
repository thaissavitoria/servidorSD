/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RmiImplementation extends UnicastRemoteObject implements RmiInterface{
    
    public RmiImplementation() throws RemoteException{
        super();
    }
    
    @Override
    public String somar(int v1, int v2) throws RemoteException {
        String sRet="Não foi possível somar";
        
        try{
            sRet="A soma é: "+(v1+v2);
        }catch(Exception e){
            throw new RemoteException("Erro:" + e.getMessage());
        }
        
        return sRet;
    }
    
}
