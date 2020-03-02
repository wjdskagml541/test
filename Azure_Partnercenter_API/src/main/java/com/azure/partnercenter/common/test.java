package com.azure.partnercenter.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class test {
	
	public static void main(String args[]) throws UnknownHostException{

		InetAddress address = InetAddress.getLocalHost();

		System.out.println(address.getHostName());

		System.out.println(address.getHostAddress());

		

		address = InetAddress.getByName("www.naver.com");

		System.out.println("www.naver.com 의 이름과 IP주소:"+address);



		InetAddress sw[] = InetAddress.getAllByName("www.naver.com");



		for(int i=0;i<sw.length; i++){

			System.out.println(sw[i]);

		}



	}

}
