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
    public String somar(int v1, int v2) throws RemoteException;
}
