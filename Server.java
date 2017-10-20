package com.wgy.networktest;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 王赣宇 on 2017/10/15.
 */

public class Server {

    private ClientConnection clientConnection;
    private ServerConnections serverConnections;

    private int ServerPort;
    private int ClientPort;

    Server(){
        //setting port
        ServerPort = 33000;
        ClientPort = 33001;
        clientConnection = new ClientConnection();
        serverConnections = new ServerConnections();
    }

    public LinkedBlockingQueue<String> StartListen(){
        return serverConnections.startListen(ServerPort);
    }

    public void ConfirmRegister(String ClientAddr, String confirmInformation){
        String[] tmp = {"ConfirmRegister", confirmInformation};
        SendtoClient(ClientAddr, Combine(tmp));
    }

    public void RequireVS(String ClientAddrUserB, String UserA){
        String[] tmp = {"RequireVS",UserA};
        SendtoClient(ClientAddrUserB, Combine(tmp));
    }

    //    accept or not : info =  YES or NO
    public void AcceptVS(String ClientAddrUserA, String UserB, String info){
        String[] tmp = {"AcceptVS", UserB, info};
        SendtoClient(ClientAddrUserA, Combine(tmp));
    }

    //    info: = RUNAWAY or PLAYEROFFLINE
    public void StopMatch(String ClientAddrUserB, String UserA, String info){
        String[] tmp = {"StopMatch", UserA, info};
        SendtoClient(ClientAddrUserB, Combine(tmp));
    }
    public void YouLose(String ClientAddrUserB, String UserA) {
        String[] tmp = {"StopMatch", UserA};
        SendtoClient(ClientAddrUserB, Combine(tmp));
    }


    //general send
    public void SendtoClient(String ClientAddr, String data){
        clientConnection.Send(ClientAddr, ClientPort, data);
    }

    //string combine
    private String Combine(String[] args){
        String data = "";
        for (String a : args) {
            data = data.concat("/" + a);
        }
        return data;
    }

}
