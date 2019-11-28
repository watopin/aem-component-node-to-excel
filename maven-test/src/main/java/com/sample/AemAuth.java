package com.sample;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

public class AemAuth implements Authenticator {
	private final String username;
	private final String passwd;
	
	public AemAuth(String name, String passwd) {
		this.username = name;
		this.passwd = passwd;
	}
	
	@Override
	public Request authenticate(Route arg0, Response arg1) throws IOException {
		// TODO Auto-generated method stub
		String credential = Credentials.basic(this.username, this.passwd);
		return arg1.request().newBuilder().header("Authorization", credential).build();
	}

}
