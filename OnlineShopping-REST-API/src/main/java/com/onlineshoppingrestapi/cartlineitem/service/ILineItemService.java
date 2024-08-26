package com.onlineshoppingrestapi.cartlineitem.service;

import com.onlineshoppingrestapi.cartlineitem.dto.LineItem;

import java.util.List;

public interface ILineItemService {
    public String addLineItem(LineItem lineItem);
    public String updateLineItem(LineItem lineItem);
    public String deleteLineItem(int lineItemId);
    public LineItem getLineItem(int lineItemId);
    public List<LineItem> getAllLineItems();
}
