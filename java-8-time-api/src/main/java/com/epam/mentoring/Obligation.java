package com.epam.mentoring;

import java.math.BigDecimal;
import java.util.List;

public interface Obligation {
    List<Fee> calculateFees(Contract.Validity contractValidity, BigDecimal value);
}
