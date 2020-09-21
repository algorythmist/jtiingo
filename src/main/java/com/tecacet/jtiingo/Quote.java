package com.tecacet.jtiingo;

public interface Quote {
    java.math.BigDecimal getAdjClose();

    java.math.BigDecimal getAdjHigh();

    java.math.BigDecimal getAdjLow();

    java.math.BigDecimal getAdjOpen();

    Long getAdjVolume();

    java.math.BigDecimal getClose();

    java.time.LocalDate getDate();

    java.math.BigDecimal getDivCash();

    java.math.BigDecimal getHigh();

    java.math.BigDecimal getLow();

    java.math.BigDecimal getOpen();

    java.math.BigDecimal getSplitFactor();

    Long getVolume();
}
