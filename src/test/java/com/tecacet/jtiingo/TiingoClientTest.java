package com.tecacet.jtiingo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class TiingoClientTest {

    @Test
    void getMetadata() {
        TiingoClient tiingoClient = TiingoClient.getInstance();
        StockMetadata result = tiingoClient.getMetadata("TSLA");
        System.out.println(result);
    }

    @Test
    void getQuote() {
        TiingoClient tiingoClient = TiingoClient.getInstance();
        Quote result = tiingoClient.getQuote("TSLA");
        System.out.println(result);
    }

    @Test
    void getQuoteHistory() {
        TiingoClient tiingoClient = TiingoClient.getInstance();
        List<? extends Quote> result = tiingoClient.getQuoteHistory("TSLA",
                LocalDate.of(2018, 1, 1),
                LocalDate.of(2020, 1, 1));
        System.out.println(result);
    }
}