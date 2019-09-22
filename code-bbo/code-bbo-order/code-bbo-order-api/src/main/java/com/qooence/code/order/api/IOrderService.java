package com.qooence.code.order.api;

import com.qooence.code.order.dto.DoOrderRequest;
import com.qooence.code.order.dto.DoOrderResponse;

public interface IOrderService {

    /*
     * 下单操作
     */
    DoOrderResponse doOrder(DoOrderRequest request);

}
