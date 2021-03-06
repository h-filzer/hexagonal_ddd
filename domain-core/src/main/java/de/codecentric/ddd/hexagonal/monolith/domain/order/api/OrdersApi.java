package de.codecentric.ddd.hexagonal.monolith.domain.order.api;

import java.util.List;

public interface OrdersApi {
  List<Order> getOrders();

  void createOrder( Order order );
}
