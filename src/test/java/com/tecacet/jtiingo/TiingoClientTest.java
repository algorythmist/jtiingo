package com.tecacet.jtiingo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

class TiingoClientTest {

    @Test
    void getMetadata() {
        TiingoClient tiingoClient = TiingoClient.getInstance();
        StockMetadata result = tiingoClient.getMetadata("TSLA");
        assertTrue(result.getDescription().startsWith("Tesla Motors, Inc. "));
        assertEquals("Tesla Inc", result.getName());
        assertEquals("NASDAQ", result.getExchangeCode());
        assertEquals("TSLA", result.getTicker());
        assertEquals(LocalDate.of(2010,6,29), result.getStartDate());
    }

    @Test
    void getLastQuote() {
        TiingoClient tiingoClient = TiingoClient.getInstance();
        Quote quote = tiingoClient.getLastQuote("TSLA");
        assertEquals(1.0, quote.getSplitFactor().doubleValue(), 0.001);
        System.out.println(quote);
    }

    @Test
    void getQuoteHistory() {
        TiingoClient tiingoClient = TiingoClient.getInstance();
        List<? extends Quote> quotes = tiingoClient.getQuoteHistory("TSLA",
                LocalDate.of(2019, 1, 1),
                LocalDate.of(2020, 9, 15));
        assertEquals(430, quotes.size());
        Map<LocalDate, Quote> quoteMap = TiingoClient.toMap(quotes);
        Quote splitQuote = quoteMap.get(LocalDate.of(2020, 8, 31));
        assertEquals(5.0, splitQuote.getSplitFactor().doubleValue(), 0.001);
    }
}