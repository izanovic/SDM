using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;

[Serializable]
public class Trigger
{
    public String id;
    public String type;
    public int status;

    public Trigger(String id, String type, int status){
        this.id = id;
        this.type = type;
        this.status = status;
    }
}

[Serializable]
public class TriggerList
{
    public List<Trigger> triggerpoints;
}
