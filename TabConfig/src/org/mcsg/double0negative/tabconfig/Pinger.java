package org.mcsg.double0negative.tabconfig;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import me.rmmccann.query.MCQuery;
import me.rmmccann.query.QueryResponse;

public class Pinger {

    @SuppressWarnings("deprecation")
    public static int[] ping(String ip, int port) {

	try {

	    Socket sk = new Socket(ip, port);

	    OutputStream out = sk.getOutputStream();

	    out.write(0xFE);
	    out.flush();

	    long time = new Date().getTime();

	    // System.out.print("w");
	    DataInputStream in = new DataInputStream(sk.getInputStream());
	    // System.out.print("-in-");

	    String s = in.readLine();
	    // System.out.println("r - "+(new Date().getTime() - time)+"ms");

	    String s1 = "";
	    for (int a = 0; a < s.length(); a += 2) {
		s1 = s1 + s.charAt(a);
	    }
	    String[] s2 = s1.split("§");

	    sk.close();
	    // System.out.println(s2.toString() + s2.length);
	    return new int[] { Integer.parseInt(s2[s2.length - 2]),
		    Integer.parseInt(s2[s2.length - 1]) };

	} catch (Exception e) {

	    // e.printStackTrace();
	    return new int[] { -1, -1 };
	} finally {

	}
    }

    public static int[] udpping(String ip, int port) {

	try {

	    MCQuery mcQuery = new MCQuery(ip, port);
	    QueryResponse response = mcQuery.basicStat();

	    return new int[] { response.getOnlinePlayers(),
		    response.getMaxPlayers() };

	} catch (Exception e) {

	    // e.printStackTrace();
	    return new int[] { -1, -1 };
	} finally {

	}

    }
}
