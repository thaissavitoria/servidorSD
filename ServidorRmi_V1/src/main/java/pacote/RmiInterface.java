/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author admlab
 */
public interface RmiInterface extends Remote{
    public String somar(double v1, double v2) throws RemoteException;
    public String subtrair(double v1, double v2) throws RemoteException;
    public String multiplicar(double v1, double v2) throws RemoteException;
    public String dividir(double v1, double v2) throws RemoteException;
}
