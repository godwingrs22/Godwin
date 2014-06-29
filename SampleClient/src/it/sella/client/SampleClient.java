package it.sella.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.commons.codec.EncoderException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.commons.codec.binary.Base64;

public class SampleClient {

    public static void main(String[] args) throws IOException, EncoderException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        String usrpwd="riskapi:riskapi";
        String encoding = Base64.encodeBase64String(usrpwd.getBytes());
        HttpGet httpget = new HttpGet("http://localhost:8123/RESTfulExample/rest/payment/mkyong");
        httpget.setHeader("Authorization", "Basic " + encoding);

        System.out.println("executing request " + httpget.getRequestLine());
        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();
        System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());

        BufferedReader r = new BufferedReader(new InputStreamReader(entity.getContent()));
        String line = r.readLine();
        while (line != null) {
            System.out.println(line);
            line = r.readLine();
        }
    }
}
