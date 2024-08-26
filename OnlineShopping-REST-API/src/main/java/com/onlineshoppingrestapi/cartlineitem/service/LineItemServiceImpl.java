package com.onlineshoppingrestapi.cartlineitem.service;

import com.onlineshoppingrestapi.cartlineitem.dto.LineItem;
import com.onlineshoppingrestapi.cartlineitem.repository.ILineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineItemServiceImpl implements ILineItemService{
    @Autowired
    private ILineItemRepository lineItemRepository;

    @Override
    public String addLineItem(LineItem lineItem) {
        lineItemRepository.save(lineItem);
        return "Line Item Added Sucessfully ...";
    }

    @Override
    public String updateLineItem(LineItem lineItem) {
        lineItemRepository.save(lineItem);
        return "Line Item Updated Sucessfully ...";
    }

    @Override
    public String deleteLineItem(int lineItemId) {
        lineItemRepository.deleteById(lineItemId);
        System.out.println("lineItemService.deleteLineItem = " + lineItemId);
        return "Line Item Deleted Sucessfully ...";
    }

    @Override
    public LineItem getLineItem(int lineItemId) {
        return lineItemRepository.findById(lineItemId).get();
    }

    @Override
    public List<LineItem> getAllLineItems() {
        return lineItemRepository.findAll();
    }
}
