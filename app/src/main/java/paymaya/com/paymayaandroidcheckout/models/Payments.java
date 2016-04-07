package paymaya.com.paymayaandroidcheckout.models;

/**
 * Created by jadeantolingaa on 2/23/16.
 */
public class Payments {

    private String id;
    private String environment;
    private String state;
    private String type;
    private String paymentTokenId;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaymentTokenId() {
        return paymentTokenId;
    }

    public void setPaymentTokenId(String paymentTokenId) {
        this.paymentTokenId = paymentTokenId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
