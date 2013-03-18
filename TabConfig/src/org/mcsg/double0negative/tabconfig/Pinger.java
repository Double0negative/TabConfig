package org.mcsg.double0negative.tabconfig;

public class Pinger {

    public static int[] ping(String ip, int port) {

	try {

	    int playerCount = -1;
	    int maxCount = -1;

	    MC14Fetch serverPing = new MC14Fetch();

	    serverPing.setAddress(ip);
	    serverPing.setPort(port);
	    serverPing.setTimeout(4000);
	    if (serverPing.fetchData()) {
		playerCount = serverPing.getPlayersOnline();
		maxCount = serverPing.getMaxPlayers();
	    }

	    return new int[] { playerCount, maxCount };

	} catch (Exception e) {

//	    e.printStackTrace();
	    return new int[] { -1, -1 };
	} finally {

	}
    }

}
