package com.epam.mentoring;

import java.math.BigDecimal;
import java.time.Period;
import java.time.temporal.Temporal;
import java.util.List;

public class Contract {

    private final BigDecimal value;
    private final Validity validity;
    private final Obligation obligation;

    public Contract(BigDecimal value, Validity validity, Obligation obligation) {
        this.value = value;
        this.validity = validity;
        this.obligation = obligation;
    }

    public List<Fee> calculateFees() {
        return obligation.calculateFees(validity, value);
    }

    public class Validity {
        private final Temporal signingDate;
        private final Period period;

        public Validity(Temporal signingDate, Period period) {
            this.signingDate = signingDate;
            this.period = period;
        }

        public Temporal getSigningDate() {
            return signingDate;
        }

        public Period getPeriod() {
            return period;
        }
    }
}
