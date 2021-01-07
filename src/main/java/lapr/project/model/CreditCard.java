package lapr.project.model;

import java.util.Date;

public class CreditCard {
    private long visaNumber;
    private Date expDate;
    private int ccv;


    public CreditCard(long visaNumber, Date expDate, int ccv) {
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

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate)  {
        if (expDate.before(new Date())){
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
