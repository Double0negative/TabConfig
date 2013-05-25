package org.mcsg.double0negative.tabconfig;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Pinger {
    public static int []  ping(String ip, int port) {
	try {
	    Socket sock = new Socket(ip, port);
	    DataOutputStream out = new DataOutputStream(sock.getOutputStream());
	    DataInputStream in = new DataInputStream(sock.getInputStream());
	    InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-16"); // UCS-2 reader.

	    out.write(0xFE);
            out.write(0x01);

            in.readByte(); // 0xFF

	    // get the length
            short length = in.readShort();
	    char[] chars = new char[length];
	    inputStreamReader.read(chars,0,length);
	    String message = new String(chars);

	    String[] arguments = message.split("\0");
	    arguments = stripEmtpyOff(arguments);
	    String maxPlayersRaw = arguments[arguments.length - 1];
	    String onlinePlayersRaw = arguments[arguments.length - 2];
	    int onlinePlayers = Integer.parseInt(onlinePlayersRaw);
	    int maxPlayers = Integer.parseInt(maxPlayersRaw);
	    out.close();
	    in.close();
	    sock.close();
	    return new int[] {onlinePlayers, maxPlayers};
	} catch (Exception e) {
	    return new int[] {-1, -1};
	}
    }

    private static String[] stripEmtpyOff(String[] arguments) {
	int i = 0;
	for (String s : arguments) {
	    if (s == null || s.isEmpty()) {
		i++;
	    }
	}
	String[] temp = new String[arguments.length - i];
	for (int x = 0; x < temp.length; x++) {
	    temp[x] = arguments[x];
	}
	return temp;
    }
}
