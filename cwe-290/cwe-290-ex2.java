while(true) {
    DatagramPacket rp=new DatagramPacket(rData,rData.length);
    outSock.receive(rp);
    String in = new String(p.getData(),0, rp.getLength());
    InetAddress clientIPAddress = rp.getAddress();
    int port = rp.getPort();

    // check if a request is from a trusted address before responding to the request
    Synthesizing 7/10 solutions

=======
Suggestion 1

        if (checkIfTrusted(clientIPAddress)) {
        // do something
    }
}
```

=======
Suggestion 2

        if (clientIPAddress.equals(InetAddress.getByName("

=======
Suggestion 3

        if (trustedAddresses.contains(clientIPAddress)) {
        System.out.println("Received from: " + clientIPAddress + ":" + port + " " + in);
        DatagramPacket sp = new DatagramPacket(in.getBytes(),in.length(),clientIPAddress,port);
        outSock.send(sp);
    } else {
        System.out.println("Received from: " + clientIPAddress + ":" + port + " " + in + " - not responding");
    }
}
```

=======
Suggestion 4

        if (clientIPAddress.equals(trustedClient1) || clientIPAddress.equals(trustedClient2)) {
        // if the request is from a trusted client, send a response
        String out = "hello";
        byte[] sData = out.getBytes();
        DatagramPacket sp = new DatagramPacket(sData, sData.length, clientIPAddress, port);
        outSock.send(sp);
    }
    else {
        // if the request is not from a trusted client, do nothing
    }
}
```

=======
Suggestion 5

        if (clientIPAddress.isSiteLocalAddress()) {
        // send response back to client
        byte[] sendData = "OK".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIPAddress, port);
        outSock.send(sendPacket);
    }
}
```

=======
Suggestion 6

        if (clientIPAddress.equals(trustedAddress)) {
        if (in.equals("request")) {
            String out = "response";
            DatagramPacket sp=new DatagramPacket(out.getBytes(),out.getBytes().length,clientIPAddress,port);
            outSock.send(sp);
        }
    }
}
```

=======
Suggestion 7

        if (trustedAddresses.contains(clientIPAddress)) {
        outSock.send(rp);
    }
}
```
