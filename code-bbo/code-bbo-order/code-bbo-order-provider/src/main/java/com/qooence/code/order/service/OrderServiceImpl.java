package com.qooence.code.order.service;

import com.qooence.code.order.api.IOrderService;
import com.qooence.code.order.dto.DoOrderRequest;
import com.qooence.code.order.dto.DoOrderResponse;
import org.springframework.stereotype.Service;

@Service(value = "orderService")
public class OrderServiceImpl implements IOrderService {


    public DoOrderResponse doOrder(DoOrderRequest request) {
        System.out.println("曾经来过：" + request);
        DoOrderResponse response = new DoOrderResponse();
        response.setCode("111111");
        response.setMemo("处理成功");
        return response;
    }
}
