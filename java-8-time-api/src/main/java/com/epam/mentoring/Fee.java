package com.epam.mentoring;

import java.math.BigDecimal;
import java.time.temporal.Temporal;

public class Fee {
    private BigDecimal value;
    private Temporal duetime;

    public Fee(BigDecimal value, Temporal duetime) {
        this.value = value;
        this.duetime = duetime;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Temporal getDuetime() {
        return duetime;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "value=" + value +
                ", duetime=" + duetime +
                '}';
    }
}
