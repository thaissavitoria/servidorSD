/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacote;

import java.awt.CheckboxMenuItem;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pacote.RmiInterface;
import pacote.RmiImplementation;

/**
 *
 * @author admlab
 */
public class Server {

    Menu mnuActions;
    CheckboxMenuItem itemStart;
    MenuItem itemStop;
    MenuItem itemAbout;

    PopupMenu popup;
    TrayIcon trayIcon;
    SystemTray systemTray;

    public Server() {
        try {
            if (!SystemTray.isSupported()) {
                System.out.println("Sem suporte a SystemTray");
                return;
            }

            Registry reg = LocateRegistry.createRegistry(6666);
            RmiInterface objRmi = new RmiImplementation();
            mnuActions = new Menu("Ações");
            itemStart = new CheckboxMenuItem("Iniciar");
            itemStop = new MenuItem("Parar");
            itemAbout = new MenuItem("Sobre");

            mnuActions.add(itemStart);
            mnuActions.add(itemStop);

            popup = new PopupMenu();
            popup.add(mnuActions);
            popup.addSeparator();
            popup.add(itemAbout);

            ImageIcon imageIcon = new ImageIcon("images/servidor.jpg");
            trayIcon = new TrayIcon(imageIcon.getImage());
            trayIcon.setPopupMenu(popup);
            systemTray = SystemTray.getSystemTray();

            try {
                systemTray.add(trayIcon);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro" + e.getMessage());
                e.printStackTrace();
            }

            itemStart.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    try {
                        if(e.getStateChange()==1){
                          reg.rebind("Servidor", objRmi);
                          JOptionPane.showMessageDialog(null, "Servidor iniciado!");
                        }
                        else{
                          reg.unbind("Servidor");
                          UnicastRemoteObject.unexportObject(objRmi, true);
                          JOptionPane.showMessageDialog(null, "Servidor parado!");
                        }
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro" + erro.getMessage());
                    }

                }
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro" + e.getMessage());
        }
    }
}
