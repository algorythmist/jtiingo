package com.tecacet.jtiingo;

import com.tecacet.jtiingo.impl.EnvironmentTokenSupplier;
import com.tecacet.jtiingo.impl.FixedTokenSupplier;
import com.tecacet.jtiingo.impl.OkHttpTiingoClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface TiingoClient {


    static TiingoClient getInstance(String token) {
        return getInstance(new FixedTokenSupplier(token));
    }

    static TiingoClient getInstance(TokenSupplier tokenSupplier) {
        return new OkHttpTiingoClient(tokenSupplier);
    }

    static TiingoClient getInstance() {
        return new OkHttpTiingoClient(new EnvironmentTokenSupplier());
    }

    StockMetadata getMetadata(String symbol);

    Quote getLastQuote(String symbol);

    List<? extends Quote> getQuoteHistory(String symbol, LocalDate startDate, LocalDate endDate);

    static Map<LocalDate, Quote> toMap( List<? extends Quote> quotes) {
        return quotes.stream().collect(Collectors.toMap(Quote::getDate, Function.identity()));
    }
}
