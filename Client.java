package com.wgy.networktest;

import android.content.ServiceConnection;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by wgy on 2017/10/15.
 */

public class Client {

    private ClientConnection clientConnection;
    private ServerConnections serverConnections;
    private String ServerIP;
    private int ServerPort;
    private int ClientPort;

    Client(){
        clientConnection = new ClientConnection();
        serverConnections = new ServerConnections();
        this.ServerIP = "192.168.1.34";
        this.ServerPort = 33000;
        this.ClientPort = 33001;
    }
    //
    Client(String serverIP){
        clientConnection = new ClientConnection();
        serverConnections = new ServerConnections();
        this.ServerIP = serverIP ;
        this.ServerPort = 33000;
        this.ClientPort = 33001;
    }

    public LinkedBlockingQueue<String> Listen(){
        return serverConnections.startListen(ClientPort);
    }

    public boolean SendSucceed(){
        return clientConnection.sendSucceed();
    }

    public void Register(String name, String password){
        String[] tmp = {"Register",name, password};
        SendtoServer(Combine(tmp));
    }

    public void Login(String name, String password){
        String[] tmp = {"Login",name, password};
        SendtoServer(Combine(tmp));
    }

    public void RequireVS(String UserA, String password, String UserB){
        String[] tmp = {"RequireVS",UserA, password, UserB};
        SendtoServer(Combine(tmp));
    }

//    accept or not : info =  YES or NO
    public void AcceptVS(String UserB, String password, String UserA, String info){
        String[] tmp = {"AcceptVS",UserA, password, UserB, info};
        SendtoServer(Combine(tmp));
    }

//    int n ：用第一象限，左下角n=1，往右n=2。。。再第二排，16，17.。。。
    public void ChessMove(String UserA, String password, String UserB, int n){
        String[] tmp = {"ChessMove",UserA, password, UserB, String.valueOf(n)};
        SendtoServer(Combine(tmp));
    }
//
    public void StopMatch(String UserA, String password, String UserB){
        String[] tmp = {"StopMatch",UserA, password, UserB};
        SendtoServer(Combine(tmp));
    }
//
    public void Win(String UserA, String password, String UserB) {
        String[] tmp = {"Win", UserA, password, UserB};
        SendtoServer(Combine(tmp));
    }

//

    //general send to server method
    public void SendtoServer(String data){
        clientConnection.Send(ServerIP, ServerPort, data);
    }
    //string combine method
    private String Combine(String[] args){
        String data = "";
        for (String a : args) {
           data = data.concat("/" + a);
        }
        return data;
    }
}
