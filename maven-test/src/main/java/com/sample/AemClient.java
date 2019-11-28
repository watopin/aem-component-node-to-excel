package com.sample;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AemClient {
	
	private final AemComponentUrl url;
	private final AemAuth auth;
	
	public AemClient(AemComponentUrl url, AemAuth auth) {
		this.url = url;
		this.auth = auth;
	}
	
	
	public String getXml(AemComponentUrl url) {
		OkHttpClient client = provideHttpClient();
		Request req = new Request.Builder().url(this.url.getFullUrl()).build();
		try(Response res = client.newCall(req).execute()){
			return res.body().string();
		} catch (IOException e) {
			throw new RuntimeException("AEM server is not started or no such component or wrong URL", e);
		}
	}
	
	private OkHttpClient provideHttpClient() {
		return new OkHttpClient.Builder().authenticator(auth).build();
	}
	
	
}
