# NetworkConnection-for-Chese


Client
---------------------------------------------------------------------------

---------------------------
初始化
    private Client client = new Client();


---------------------------
新建一个消息队列，并关联
    private LinkedBlockingQueue<String> messageQueue;
	messageQueue = client.Listen();

//*****重点******
messageQueue的格式是在接收的字符串前面加上对方的ip地址
所以格式是："/192.168.1.34/ConfirmRegister/YES"

---------------------------
通用发送到服务器的方法。
public void SendtoServer(String data)
---------------------------
其他高级方法，例如
    public void Register(String name, String password){
        String[] tmp = {"Register",name, password};
        SendtoServer(Combine(tmp));
    }
只是把消息拼接到字符串然后发送
在Client.java里面看其他的方法。有不清楚再问我。
这个发送的格式是"/Register/wgy/1234"

----------------------------------------------------------------------------


Server
----------------------------------------------------------------------------

------------------------------
初始化
Server server = new Server();


------------------------------	
新建一个消息队列，并关联
    private LinkedBlockingQueue<String> messageQueue;
	messageQueue = server.StartListen();

//*****重点******
messageQueue的格式是在接收的字符串前面加上对方的ip地址
所以格式是："/192.168.1.6/Register/wgy/1234"

------------------------------
通用公共send方法，参数1客户端地址,类似"192.168.1.6"，参数2,字符串
    //general send
    public void SendtoClient(String ClientAddr, String data){
        clientConnection.Send(ClientAddr, ClientPort, data);
    }

------------------------------
其他高级sendtoclient方法，例如
    public void ConfirmRegister(String ClientAddr, String confirmInformation){
        String[] tmp = {"ConfirmRegister", confirmInformation};
        SendtoClient(ClientAddr, Combine(tmp));
    }
一样只是把消息拼接到字符串然后发送
在Server.java里面看其他的方法。有不清楚再问我。
这个发送的格式是"/ConfirmRegister/YES"


----------------------------------------------------------------------------
