package com.biancheng.utils;

import java.util.Random;

public class ServerPortUtils {
	public static int getAvailablePort() {
	    int max = 65535;
	    int min = 2000;
	    Random random = new Random();
	    int port = random.nextInt(max)%(max-min+1) + min;
	    boolean using = NetUtils.isLoclePortUsing(port);
	    if (using) {
	        return getAvailablePort();
	    } else {
	        return port;
	    }
	}
}
