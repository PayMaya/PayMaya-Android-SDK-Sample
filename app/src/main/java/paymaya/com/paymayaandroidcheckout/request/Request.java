package paymaya.com.paymayaandroidcheckout.request;

import java.net.URL;
import java.util.Map;

/**
 * Created by jadeantolingaa on 2/23/16.
 */
public class Request {

    public enum Method {
        GET, POST
    }

    private Method method;
    private URL url;
    private byte[] body;
    private Map<String, String> headers;

    public Request(Method method, URL url) {
        this.method = method;
        this.url = url;
    }

    public Request(Method method, URL url, byte[] body, Map<String, String> headers) {
        this.method = method;
        this.url = url;
        this.body = body;
        this.headers = headers;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
