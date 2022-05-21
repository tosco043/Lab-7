package Data;

import java.io.Serializable;

public class Organization implements Serializable {
    private long orgId;
    private String orgName;
    private double annualTurnover;
    private OrganizationType type;
    private Address postalAddress;

    public Organization(long orgId, String orgName, double annualTurnover,
                        OrganizationType type, Address postalAddress) {
       this.orgId = orgId;
        this.orgName = orgName;
        this.annualTurnover = annualTurnover;
        this.type = type;
        this.postalAddress = postalAddress;
    }
    public Organization(){}

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long id) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public double getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(double annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public OrganizationType getType() {
        return type;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(Address postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String toString(){
        String orgDetail =(" ID: "+ orgId + '\n' );
        orgDetail +=("             name: "+ orgName + '\n' );
        orgDetail +=("             Ann. turnover: "+ annualTurnover + '\n' );
        orgDetail +=("             type: "+ type + '\n' );
        orgDetail +=("          Address: "+'\n' + postalAddress.toString() + '\n' );
        return orgDetail;
    }
}
