using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

public class Simulator : MonoBehaviour
{
    ClientSocket client = new ClientSocket();
    void Start()
    {
        Debug.Log("Client Started");
        Thread clientThread = new Thread(client.startClient);
        clientThread.Start();
    }
}
