package com.paymaya.checkoutsample.models;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by samfrancisco on 10/1/15.
 */
public class CheckoutPayload {

  private JSONObject payload;

  public CheckoutPayload() {

  }

  public CheckoutPayload(JSONObject payload) {
    this.payload = payload;
  }

  public JSONObject getPayload() {
    return this.payload;
  }

  public void generateSamplePayload(String successUrl, String failureUrl, String cancelUrl)
          throws JSONException {
    payload = new JSONObject();

    JSONObject totalAmount = new JSONObject();
      totalAmount.put("currency", "PHP");
      totalAmount.put("value", "6404.90");
      JSONObject details = new JSONObject();
        details.put("discount", "300.00");
        details.put("serviceCharge", "50.00");
        details.put("shippingFee", "200.00");
        details.put("tax", "691.60");
        details.put("subtotal", "5763.30");
      totalAmount.put("details", details);
    payload.put("totalAmount", totalAmount);


    JSONObject buyer = new JSONObject();
      buyer.put("firstName", "Juan");
      buyer.put("middleName", "dela");
      buyer.put("lastName", "Cruz");
      JSONObject contact = new JSONObject();
        contact.put("phone", "+63(2)1234567890");
        contact.put("email", "paymayabuyer1@gmail.com");
      buyer.put("contact", contact);
      JSONObject shippingAddress = new JSONObject();
        shippingAddress.put("line1", "9F Robinsons Cybergate 3");
        shippingAddress.put("line2", "Pioneer Street");
        shippingAddress.put("city", "Mandaluyong City");
        shippingAddress.put("state", "Metro Manila");
        shippingAddress.put("zipCode", "12345");
        shippingAddress.put("countryCode", "PH");
      buyer.put("shippingAddress", shippingAddress);
      JSONObject billingAddress = new JSONObject();
        billingAddress.put("line1", "9F Robinsons Cybergate 3");
        billingAddress.put("line2", "Pioneer Street");
        billingAddress.put("city", "Mandaluyong City");
        billingAddress.put("state", "Metro Manila");
        billingAddress.put("zipCode", "12345");
        billingAddress.put("countryCode", "PH");
      buyer.put("billingAddress", billingAddress);
      buyer.put("ipAddress", "125.60.148.241");
    payload.put("buyer", buyer);


    JSONArray items = new JSONArray();
      JSONObject item1 = new JSONObject();
        item1.put("name", "Canvas Slip Ons");
        item1.put("code", "CVG-096732");
        item1.put("description", "Shoes");
        item1.put("quantity", "3");
        JSONObject amount1 = new JSONObject();
          amount1.put("value", "1621.10");
          JSONObject details1 = new JSONObject();
            details1.put("discount", "100.00");
            details1.put("subtotal", "1721.10");
          amount1.put("details", details1);
        item1.put("amount", amount1);
        JSONObject totalAmount1 = new JSONObject();
          totalAmount1.put("value", "4863.30");
          JSONObject totalAmountDetails1 = new JSONObject();
            totalAmountDetails1.put("discount", "300.00");
            totalAmountDetails1.put("subtotal", "5163.30");
          totalAmount1.put("details", totalAmountDetails1);
        item1.put("totalAmount", totalAmount1);
      items.put(item1);
    payload.put("items", items);


    JSONObject redirectUrl = new JSONObject();
      redirectUrl.put("success", Uri.parse(successUrl).toString());
      redirectUrl.put("failure", Uri.parse(failureUrl).toString());
      redirectUrl.put("cancel", Uri.parse(cancelUrl).toString());
    payload.put("redirectUrl", redirectUrl);


    payload.put("requestReferenceNumber", "000141386713");
    payload.put("isAutoRedirect", false);
      JSONObject metadata = new JSONObject();
    payload.put("metadata", metadata);
  }

  @Override
  public String toString() {
    return payload.toString();
  }
}
