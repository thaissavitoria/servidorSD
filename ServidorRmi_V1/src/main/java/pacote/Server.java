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
import java.awt.event.ActionEvent;
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
    MenuItem itemCalcular;
    MenuItem itemExit;
    PopupMenu popup;
    TrayIcon trayIcon;
    SystemTray systemTray;
    private RmiInterface objRmi;
    private Registry reg;
    private boolean servidorRodando;

    public Server() {
        try {
            if (!SystemTray.isSupported()) {
                System.out.println("Sem suporte a SystemTray");
                return;
            }

            servidorRodando = false;

            reg = LocateRegistry.createRegistry(6666);
            objRmi = new RmiImplementation();
            mnuActions = new Menu("Ações");
            itemStart = new CheckboxMenuItem("Iniciar");
            itemStop = new MenuItem("Parar");
            itemCalcular = new MenuItem("Calcular");
            itemExit = new MenuItem("Sair");

            mnuActions.add(itemStart);
            mnuActions.add(itemStop);
            mnuActions.add(itemCalcular);

            popup = new PopupMenu();
            popup.add(mnuActions);
            popup.addSeparator();
            popup.add(itemExit);

            ImageIcon imageIcon = new ImageIcon("images/servidor.jpg");
            trayIcon = new TrayIcon(imageIcon.getImage());
            trayIcon.setPopupMenu(popup);
            systemTray = SystemTray.getSystemTray();

            try {
                systemTray.add(trayIcon);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
                e.printStackTrace();
            }

            itemStart.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    try {
                        if (e.getStateChange() == 1) {
                            reg.rebind("Servidor", objRmi);
                            servidorRodando = true;
                            itemCalcular.setEnabled(true);
                            JOptionPane.showMessageDialog(null, "Servidor iniciado!");
                        } else {
                            reg.unbind("Servidor");
                            UnicastRemoteObject.unexportObject(objRmi, true);
                            JOptionPane.showMessageDialog(null, "Servidor parado!");
                        }
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro: " + erro.getMessage());
                    }

                }
            });

            itemCalcular.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    realizarCalculo();
                }
            });
            itemCalcular.setEnabled(false);

            itemStop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopRmiServices();
                }
            });

            itemExit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopRmiServices();

                    systemTray.remove(trayIcon);

                    System.exit(0);
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    private void stopRmiServices() {
        try {
            if (servidorRodando) {
                reg.unbind("Servidor");

                UnicastRemoteObject.unexportObject(objRmi, true);
                UnicastRemoteObject.unexportObject(reg, true);

                servidorRodando = false;
                itemCalcular.setEnabled(false);

                JOptionPane.showMessageDialog(null, "Servidor parado!");
            } else {
                JOptionPane.showMessageDialog(null, "Servidor já está parado.");
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Error: " + erro.getMessage());
        }
    }

    private void realizarCalculo() {
        try {
            String valor1Str = JOptionPane.showInputDialog("Digite o primeiro valor:");
            double valor1 = Double.parseDouble(valor1Str);

            String valor2Str = JOptionPane.showInputDialog("Digite o segundo valor:");
            double valor2 = Double.parseDouble(valor2Str);

            String[] operacoes = {"Soma", "Subtração", "Multiplicação", "Divisão"};
            String operacao = (String) JOptionPane.showInputDialog(
                    null,
                    "Escolha a operação:",
                    "Calculadora RMI",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    operacoes,
                    operacoes[0]
            );

            String resultado = "";

            switch (operacao) {
                case "Soma":
                    resultado = objRmi.somar(valor1, valor2);
                    break;
                case "Subtração":
                    resultado = objRmi.subtrair(valor1, valor2);
                    break;
                case "Multiplicação":
                    resultado = objRmi.multiplicar(valor1, valor2);
                    break;
                case "Divisão":
                    resultado = objRmi.dividir(valor1, valor2);
                    break;
            }

            JOptionPane.showMessageDialog(null, resultado);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Valor inválido digitado!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
        }
    }
}
