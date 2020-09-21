package com.tecacet.jtiingo.impl;


import com.tecacet.jtiingo.TokenSupplier;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class EnvironmentTokenSupplier implements TokenSupplier {

    private final String tokenVariableName;

    public EnvironmentTokenSupplier() {
        this("TIINGO_TOKEN");
    }

    @Override
    public String getToken() {
        Map<String,String> env = System.getenv();
        return env.get(tokenVariableName);
    }
}
