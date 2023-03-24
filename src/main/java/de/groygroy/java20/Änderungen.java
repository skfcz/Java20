package de.groygroy.java20;

import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Locale;
import java.net.http.HttpClient;

public class Änderungen {

    public static void main(String... args) {
        // deprecated
        var l0 = new Locale("de", "DE");
        // ersetzen durch
        var l1 = Locale.of("de", "DE");
        var l2 = new Locale.Builder().setLanguage("de").setRegion("DE").build();

        System.out.println("l0 " + l0 + ", l1 " + l1 + ", l2 " + l2);


        // HttpClient hat statt 1200 Sekunden nun noch 30 Sekunden Timeout,
        // bei Bedarf muß der Timeout gesetzt werden

        final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(60)) // <-- timeout setzen
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://www.linux-magazin.de"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();

        try {
            System.out.printf("Anfrage bei %s ...%n" , request.uri() );
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status :" + response.statusCode());
            String body = response.body();

            System.out.println("Body :" + (body.length() > 100? body.substring(0,100) : body));

        } catch (Exception exp) {
            System.err.println("Fehler :" + exp.getMessage());
        }


    }
}
