/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.ru.tsystems.restserviceclient;

import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.log4j.Logger;

/**
 *
 * @author alexggg99
 */
@Stateless(name = RestClientImpl.JNDI_NAME)
public class RestClientImpl implements RestClient {
    
    public static final String JNDI_NAME = "restClientBean";
    
    private static Logger logger = Logger.getLogger(RestClientImpl.class);
    
    public String askService(String dateFrom, String dateTo) {
        String output = null;
        try {
            String URLstring = "http://localhost:8080/SpringMVC/webService/"+dateFrom+'/'+dateTo;
            URL url = new URL(URLstring);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                return "Failed : HTTP error code : "
                        + conn.getResponseCode();
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
//            while ((output = br.readLine()) != null) {
//                System.out.println(output);
//            }
            output = br.readLine();
            conn.disconnect();

        } catch (MalformedURLException e) {

            logger.error("Error!", e);

        } catch (IOException e) {

            logger.error("Error!", e);

        }
        return output;
    }
    
}
