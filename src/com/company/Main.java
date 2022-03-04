package com.company;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Main {

    static metodos met = new metodos();
    static int PUERTO2;
    static int PUERTO = 6666;
    ServerSocket sc;
    Socket so;
    DataOutputStream salida;
    DataInputStream entrada;
    String mensajeRecibido;
    String Nick;

     public void initServidor() {

         Scanner teclado = new Scanner(System.in);
         try {
             sc = new ServerSocket(PUERTO);
             so = new Socket();

             System.out.println("Esperando conexión...");
             so = sc.accept();
             entrada = new DataInputStream(so.getInputStream());
             salida = new DataOutputStream(so.getOutputStream());
             String msn = "";
             String nMsn = "";
             String pMsn = "";
             nMsn = entrada.readUTF();
             System.out.println("Se conecto " + nMsn + "...");
             Nick = nMsn;

             while (true) {

                 mensajeRecibido = entrada.readUTF();//Leemos respuesta
                 System.out.println(mensajeRecibido);
                 msn = teclado.nextLine();
                 salida.writeUTF("[Servidor]: " + msn);//enviamos mensaje

             }
         } catch (Exception e) {

         }
     }

    /*public static class centra extends Thread {
        public centra(String str) {
            super(str);
        }
        public void run() {
            try {
                met.chat();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

     */


    public static void main(String[] args) {
        Main o = new Main();
         o.initServidor();
        /*int n = 0;
        //while (n != 10) {
        // n = n + 1;
        centra entra = new centra(o.Nick);
        centra saida = new centra(o.Nick);
        entra.start();
        saida.start();

         */
    }
}

