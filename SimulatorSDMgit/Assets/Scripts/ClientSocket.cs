using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Sockets;
using System.Text;
using UnityEngine;

class ClientSocket
{

    public void startClient()
    {
        List<Trigger> Triggerpoints = new List<Trigger>();
        Triggerpoints.Add(new Trigger("XWO", "A" , 1));
        Triggerpoints.Add(new Trigger("YNW", "B", 1));
        Triggerpoints.Add(new Trigger("XZW", "A", 1));

        TriggerList list = new TriggerList();
        list.triggerpoints = Triggerpoints;
        String json = JsonUtility.ToJson(list);

        try
        {
            // Create a TcpClient.
            // Note, for this client to work you need to have a TcpServer 
            // connected to the same address as specified by the server, port
            // combination.
            TcpClient client = new TcpClient("141.252.207.138", 1337);

            // Get a client stream for reading and writing.
            NetworkStream stream = client.GetStream();

            // Send the message to the connected TcpServer. 
            // Translate the passed message into ASCII and store it as a Byte array.
            Byte[] data = System.Text.Encoding.ASCII.GetBytes(json + "\r\n");
            stream.Write(data, 0, data.Length);
            Debug.Log("Sent to server:" + json);


            // Receive the TcpServer.response.
            // Buffer to store the response bytes.
            //data = new Byte[1024];

            // Read the first batch of the TcpServer response bytes.
            //Int32 bytes = stream.Read(data, 0, data.Length);
            StreamReader reader = new StreamReader(stream, Encoding.UTF8);
            try
            {
                while (!reader.EndOfStream)
                {
                    String st = reader.ReadLine();
                    var stoplichten = JsonUtility.FromJson<StoplichtList>(st);
                    List<Stoplicht> stop = stoplichten.stoplichten;
                    foreach (Stoplicht s in stop) {
                        Debug.Log("StoplichtID: "+ s.id + "  Status: " + s.status);
                    }
                }
            }
            catch (Exception e)
            {
                Debug.Log(e);
            }

            // String to store the response ASCII representation.
            //String responseData = System.Text.Encoding.ASCII.GetString(data, 0, bytes);
            //Debug.Log("Received: {0}", responseData);

            // Close everything.
            stream.Close();
            client.Close();
        }
        catch (Exception e) {
            Debug.Log("Can't connect to server " + e);
            //Debug.Log(e);
        }
    }
}
