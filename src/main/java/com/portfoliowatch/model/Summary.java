package com.portfoliowatch.model;

import com.portfoliowatch.model.financialmodelingprep.FMPProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Summary {
    private String symbol;
    private Double shares;
    private Double costBasis;
    private Double currentPrice;
    private Long averageVolume;
    private Double dollarChange;
    private Double percentChange;
    private String companyName;
    private String industry;
    private String sector;
    private Boolean isEtf;

    public Summary(Position position, FMPProfile fmpProfile) {
        if (position != null) {
            this.symbol = position.getSymbol();
            this.shares = position.getShares();
            this.costBasis = position.getCostBasis();
        }
        if (fmpProfile != null) {
            this.currentPrice = fmpProfile.getPrice();
            this.averageVolume = fmpProfile.getVolAvg();
            this.dollarChange = fmpProfile.getChanges();
            this.companyName = fmpProfile.getCompanyName();
            this.industry = fmpProfile.getIndustry();
            this.sector = fmpProfile.getSector();
            this.isEtf = fmpProfile.getIsEtf();
            this.percentChange = (currentPrice / (currentPrice - dollarChange)) - 1;
        }
    }
}
