package com.codesquad.team14.dto.requestDTO;

import java.util.Arrays;

public class RequestItemDTO {

    private String detail_hash;
    private String image;
    private String alt;
    private String[] delivery_type;
    private String title;
    private String description;
    private String n_price;
    private String s_price;
    private String[] badge;
    private String[] thumb_images;
    private String product_description;
    private String point;
    private String delivery_info;
    private String delivery_fee;
    private String[] prices;
    private String[] detail_section;

    public String getDetail_hash() {
        return detail_hash;
    }

    public String getImage() {
        return image;
    }

    public String getAlt() {
        return alt;
    }

    public String[] getDelivery_type() {
        return delivery_type;
    }

    public String getDeliveryTypeInString() {
        return Arrays.toString(delivery_type);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getN_price() {
        return n_price;
    }

    public String getS_price() {
        return s_price;
    }

    public String[] getBadge() {
        return badge;
    }

    public String getBadgeInString() {
        return Arrays.toString(badge);
    }

    public String[] getThumb_images() {
        return thumb_images;
    }

    public String getImages() {
        return Arrays.toString(thumb_images);
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getPoint() {
        return point;
    }

    public String getDelivery_info() {
        return delivery_info;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public String[] getPrices() {
        return prices;
    }

    public String[] getDetail_section() {
        return detail_section;
    }

    public int getNormalPrice() {
        return convertPrice(n_price);
    }

    public int getSalePrice() {
        return convertPrice(s_price);
    }

    private int convertPrice(String price) {
        String temp = price.replace("원", "");
        return Integer.parseInt(temp.replace(",", ""));
    }

    public void setN_price(String price) {
        this.n_price = price;
    }
}
