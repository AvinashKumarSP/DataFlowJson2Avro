
package com.example.types.json;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "date_time",
    "store_id",
    "pos_id",
    "customer_type",
    "delivery_type",
    "items_cost_list",
    "number_of_items"
})
public class PosInvoiceRaw {

    @JsonProperty("date_time")
    private String dateTime;
    @JsonProperty("store_id")
    private String storeId;
    @JsonProperty("pos_id")
    private String posId;
    @JsonProperty("customer_type")
    private String customerType;
    @JsonProperty("delivery_type")
    private String deliveryType;
    @JsonProperty("items_cost_list")
    private List<Integer> itemsCostList = new ArrayList<Integer>();
    @JsonProperty("number_of_items")
    private Integer numberOfItems;

    @JsonProperty("date_time")
    public String getDateTime() {
        return dateTime;
    }

    @JsonProperty("date_time")
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public PosInvoiceRaw withDateTime(String dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    @JsonProperty("store_id")
    public String getStoreId() {
        return storeId;
    }

    @JsonProperty("store_id")
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public PosInvoiceRaw withStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    @JsonProperty("pos_id")
    public String getPosId() {
        return posId;
    }

    @JsonProperty("pos_id")
    public void setPosId(String posId) {
        this.posId = posId;
    }

    public PosInvoiceRaw withPosId(String posId) {
        this.posId = posId;
        return this;
    }

    @JsonProperty("customer_type")
    public String getCustomerType() {
        return customerType;
    }

    @JsonProperty("customer_type")
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public PosInvoiceRaw withCustomerType(String customerType) {
        this.customerType = customerType;
        return this;
    }

    @JsonProperty("delivery_type")
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("delivery_type")
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public PosInvoiceRaw withDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    @JsonProperty("items_cost_list")
    public List<Integer> getItemsCostList() {
        return itemsCostList;
    }

    @JsonProperty("items_cost_list")
    public void setItemsCostList(List<Integer> itemsCostList) {
        this.itemsCostList = itemsCostList;
    }

    public PosInvoiceRaw withItemsCostList(List<Integer> itemsCostList) {
        this.itemsCostList = itemsCostList;
        return this;
    }

    @JsonProperty("number_of_items")
    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    @JsonProperty("number_of_items")
    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public PosInvoiceRaw withNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("dateTime", dateTime).append("storeId", storeId).append("posId", posId).append("customerType", customerType).append("deliveryType", deliveryType).append("itemsCostList", itemsCostList).append("numberOfItems", numberOfItems).toString();
    }

}
