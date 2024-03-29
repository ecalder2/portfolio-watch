package com.portfoliowatch.model.nasdaq;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DividendProfile {

    private Date exDividendDate;
    private Date dividendPaymentDate;
    private String yield;
    private Double annualizedDividend;
    private Double payoutRatio;

}
