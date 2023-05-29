package com.bjpowernode.ajax.proxy;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@WebServlet("/proxy")
public class ProxyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpGet httpGet = new HttpGet("http://localhost:8081/b/target1");
        httpGet.setHeader("Content-Type","application/x-www-form-urlencoded");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse resp = httpClient.execute(httpGet);
        HttpEntity  entity = resp.getEntity();
        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
        String line=null;
        StringBuffer responseSB = new StringBuffer();
        while((line = reader.readLine()) != null){
            responseSB.append(line);
        }
        reader.close();
        httpClient.close();
        response.getWriter().print(responseSB);
    }
}
