using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;

[Serializable]
public class Stoplicht
{
    public String id;
    public int status;

    public Stoplicht(String id, String type, int status){
        this.id = id;
        this.status = status;
    }
}

[Serializable]
public class StoplichtList
{
    public List<Stoplicht> stoplichten;
}
