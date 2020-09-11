package com.biancheng;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.LoadBalancerBuilder;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.reactive.LoadBalancerCommand;
import com.netflix.loadbalancer.reactive.ServerOperation;

import rx.Observable;

public class MainClient {
	public static void main(String[] args) {
		// �����б�
		List<Server> serverList = new ArrayList<Server>();
		serverList.add(new Server("localhost", 8081));
		serverList.add(new Server("localhost", 8083));
		// ��������ʵ��
		ILoadBalancer loadBalancer = LoadBalancerBuilder.newBuilder().buildFixedServerListLoadBalancer(serverList);
		// ���� 5 ��������Ч��
		for (int i = 0; i < 5; i++) {
		    String result = LoadBalancerCommand.<String>builder().withLoadBalancer(loadBalancer).build()
		            .submit(new ServerOperation<String>() {
		                public Observable<String> call(Server server) {
		                    try {
		                        String addr = "http://" + server.getHost() + ":" + server.getPort() + "/provider/hello";
		                        System.out.println(" ���õ�ַ��" + addr);
		                        URL url = new URL(addr);
		                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		                        conn.setRequestMethod("GET");
		                        conn.connect();
		                        InputStream in = conn.getInputStream();
		                        byte[] data = new byte[in.available()];
		                        in.read(data);
		                        return Observable.just(new String(data));
		                    } catch (Exception e) {
		                        return Observable.error(e);
		                    }
		                }
		            }).toBlocking().first();
		    System.out.println(" ���ý����" + result);
		}
	}
}
