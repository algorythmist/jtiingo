package com.tecacet.jtiingo.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tecacet.jtiingo.Quote;
import com.tecacet.jtiingo.StockMetadata;
import com.tecacet.jtiingo.TiingoClient;
import com.tecacet.jtiingo.TokenSupplier;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class OkHttpTiingoClient implements TiingoClient {

    private static final String URL_BASE = "https://api.tiingo.com/tiingo";
    private static final String DAILY_URL_BASE = URL_BASE + "/daily";

    private final OkHttpClient httpClient = new OkHttpClient();

    private final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules()
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private final TokenSupplier tokenSupplier;

    @Override
    @SneakyThrows
    public StockMetadata getMetadata(String symbol) {
        String url = DAILY_URL_BASE + "/" + symbol;
        log.info("Calling url {}", url);
        String content = execute(url);
        return objectMapper.readValue(content, StockMetadata.class);
    }

    @Override
    @SneakyThrows
    public Quote getQuote(String symbol) {
        String url = String.format("%s/%s/prices", DAILY_URL_BASE, symbol);
        log.info("Calling url {}", url);
        String content = execute(url);
        List<QuoteImpl>  quotes = objectMapper.readValue(content, new TypeReference<List<QuoteImpl>>() {
        });
        return quotes.isEmpty() ? null : quotes.get(0);
    }

    @Override
    @SneakyThrows
    public List<? extends Quote> getQuoteHistory(String symbol, LocalDate startDate, LocalDate endDate) {
        //TODO: url builder
        String url = String.format("%s/%s/prices?startDate=%s&endDate=%s", DAILY_URL_BASE, symbol,
                startDate, endDate);
        log.info("Calling url {}", url);
        String content = execute(url);
        return objectMapper.readValue(content, new TypeReference<List<QuoteImpl>>() {
        });
    }

    private String execute(String url) throws IOException {
        String token = tokenSupplier.getToken();
        Request request = new Request.Builder().url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization",
                        String.format("Token %s", token))
                .build();
        Response response = httpClient.newCall(request).execute();
        String content = response.body().string();
        if (!response.isSuccessful()) {
            throw new IOException(String.format("Call failed with code %d and message: %s", response.code(), content));
        }
        return content;
    }


}
