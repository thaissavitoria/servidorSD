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
    public String somar(double v1, double v2) throws RemoteException {
        String sRet="Não foi possível somar";
        
        try{
            sRet="O resultado da soma é: "+(v1+v2);
        }catch(Exception e){
            throw new RemoteException("Erro:" + e.getMessage());
        }
        
        return sRet;
    }
    
    @Override
    public String subtrair(double v1, double v2) throws RemoteException {
        String sRet="Não foi possível subtrair";
        
        try{
            sRet="O resultado da subtração é: "+(v1+v2);
        }catch(Exception e){
            throw new RemoteException("Erro:" + e.getMessage());
        }
        
        return sRet;
    }
    
    @Override
    public String multiplicar(double v1, double v2) throws RemoteException {
        String sRet="Não foi possível multiplicar";
        
        try{
            sRet="O resultado da multiplicação é: "+(v1+v2);
        }catch(Exception e){
            throw new RemoteException("Erro:" + e.getMessage());
        }
        
        return sRet;
    }
    
    
    @Override
    public String dividir(double v1, double v2) throws RemoteException {
        String sRet="Não foi possível dividir";
        
        try{
            if(v2!=0.0){
                sRet="O resultado da divisão é: "+(v1/v2);
            }
        }catch(Exception e){
            throw new RemoteException("Erro:" + e.getMessage());
        }
        
        return sRet;
    }
    
}
