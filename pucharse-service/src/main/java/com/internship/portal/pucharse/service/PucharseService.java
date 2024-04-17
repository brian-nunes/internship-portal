package com.internship.portal.pucharse.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class PucharseService {

    public void buyTicket(Long ticketId) throws Exception{
        try{
            String url = String.format("http://gateway-service:8080/ticket-service/ticket/%s", ticketId.toString());

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");

            int responseCode = con.getResponseCode();
            log.info("Codigo de resposta: " + responseCode);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                log.info("Resposta: " + response);
            }
        } catch (Exception e) {
            log.error("Ocorreu um erro: ", e);
            throw e;
        }
    }
}
