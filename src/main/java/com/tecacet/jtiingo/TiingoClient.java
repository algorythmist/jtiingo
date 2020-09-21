package com.tecacet.jtiingo;

import com.tecacet.jtiingo.impl.EnvironmentTokenSupplier;
import com.tecacet.jtiingo.impl.FixedTokenSupplier;
import com.tecacet.jtiingo.impl.OkHttpTiingoClient;

import java.time.LocalDate;
import java.util.List;

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

    Quote getQuote(String symbol);

    List<? extends Quote> getQuoteHistory(String symbol, LocalDate startDate, LocalDate endDate);

}
