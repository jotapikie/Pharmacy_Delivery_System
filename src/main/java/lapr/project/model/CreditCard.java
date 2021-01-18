package lapr.project.model;

import java.util.Date;

public class CreditCard {
    private long visaNumber;
    private String expDate;
    private int ccv;


    public CreditCard(long visaNumber, String expDate, int ccv) {
        this.setVisaNumber(visaNumber);
        this.setExpDate(expDate);
        this.setCcv(ccv);
    }
    public long getVisaNumber() {
        return visaNumber;
    }

    public void setVisaNumber(long visaNumber) {
            if (String.valueOf(visaNumber).length()!=16) {
                throw new IllegalArgumentException("Credit Cards visa is invalid.");
            }

        this.visaNumber = visaNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate)  {
        if (!expDate.matches("(?:0[1-9]|1[0-2])/[0-9]{2}")){
            throw new IllegalArgumentException("Credit Cards expire date is invalid");
        }
        this.expDate = expDate;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        if (String.valueOf(ccv).length()!=3) {
            throw new IllegalArgumentException("Credit Cards ccv is invalid.");
        }
        this.ccv = ccv;
    }


}
