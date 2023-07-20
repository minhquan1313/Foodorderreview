package com.mtb.foodorderreview.global;

import com.mtb.foodorderreview.something.Order;

public class OrderGlobal {
    private Order order;
    private static final OrderGlobal instance = new OrderGlobal();

    //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
    public static OrderGlobal getInstance() {
        return instance;
    }


    private OrderGlobal() {
    }

    public void reset() {
        order = null;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
