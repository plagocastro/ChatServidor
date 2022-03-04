package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class metodos {
    Main m = new Main();
    String nomHilo = m.Nick;

    public synchronized void chat() throws InterruptedException {
        Scanner teclado = new Scanner(System.in);
        try {
            System.out.println("Esperando conexión...");
            m.so = m.sc.accept();
            m.entrada = new DataInputStream(m.so.getInputStream());
            m.salida = new DataOutputStream(m.so.getOutputStream());
            String nMsn = "";
            String pMsn = "";
            String msn = "";
            nMsn = m.entrada.readUTF();
            System.out.println("Se conecto " + nMsn + "...");
            m.Nick = nMsn;
            System.out.println("Introduce nuevo puerto");

            if (nomHilo != "") {
                pMsn = m.entrada.readUTF();
                System.out.println("Cambio su puerto a " + pMsn + "!!\n->");
                pMsn = String.valueOf(m.PUERTO2);
                m.sc = new ServerSocket(m.PUERTO2);
                m.so = new Socket();
                m.so = m.sc.accept();

                while (!msn.equals("Salida()")) {

                    m.mensajeRecibido = m.entrada.readUTF();//Leemos respuesta
                    System.out.println(m.mensajeRecibido);
                    msn = teclado.nextLine();
                    m.salida.writeUTF("[Servidor]: " + msn);//enviamos mensaje

                }
                m.sc.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
