package paymaya.com.paymayaandroidcheckout.request;

/**
 * Created by jadeantolingaa on 2/23/16.
 */
public class Response {
    private int code;
    private String response;

    public Response(int code, String response) {
        this.code = code;
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
