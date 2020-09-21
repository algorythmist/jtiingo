package com.tecacet.jtiingo.impl;


import com.tecacet.jtiingo.TokenSupplier;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FixedTokenSupplier implements TokenSupplier {

    private final String token;

    @Override
    public String getToken() {
        return token;
    }
}
