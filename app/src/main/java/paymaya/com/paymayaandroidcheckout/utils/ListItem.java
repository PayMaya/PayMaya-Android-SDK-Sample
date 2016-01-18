package paymaya.com.paymayaandroidcheckout.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import paymaya.com.paymayaandroidcheckout.R;
import paymaya.com.paymayaandroidcheckout.models.Product;

/**
 * Created by jadeantolingaa on 1/13/16.
 */
public class ListItem {

    private ListItem() {
    }
    /**
     *
     * @return List Product
     */
    public static List<Product> getItemModels() {
        /**
         * Create List of ItemModels for Checkout object
         */
        List<Product> mProducts = new ArrayList<>();

        /**
         * Create Product for List of Items
         *
         * @setmethods - Item ((Item) item)
         *
         */
        Product product = new Product();
        product.setItemName("Bag");
        product.setItemDescription("Black Leather Bag");
        product.setSkuCode("CVG-096731");
        product.setItemPrice(BigDecimal.valueOf(450.99));
        product.setThumbNails(R.mipmap.bag);
        mProducts.add(product);

        //item2
        product = new Product();
        product.setItemName("Shoe");
        product.setItemDescription("Brown Leather Shoe");
        product.setSkuCode("CVG-096732");
        product.setItemPrice(BigDecimal.valueOf(4500.25));
        product.setThumbNails(R.mipmap.shoe);
        mProducts.add(product);

        //item3
        product = new Product();
        product.setItemName("Necklace");
        product.setItemDescription("Pricey Necklace");
        product.setSkuCode("CVG-096733");
        product.setItemPrice(BigDecimal.valueOf(8300.25));
        product.setThumbNails(R.mipmap.necklace);
        mProducts.add(product);

        return mProducts;
    }
}
