package de.codecentric.ddd.hexagonal.domain.order.api;

import de.codecentric.ddd.hexagonal.domain.order.impl.OrdersApiImpl;
import de.codecentric.ddd.hexagonal.order.persistence.OrderRepositoryInMemory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.joda.money.CurrencyUnit.EUR;
import org.joda.money.Money;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.UUID;

public class OrdersApiTest {

  public static final UUID UUID = java.util.UUID.fromString( "572e35e4-7be4-4c4a-bc49-32acd9aa09bc" );

  @Nested
  @DisplayName( "Given no orders have been taken" )
  public class GivenNoOrders {
    private OrdersApi api;

    @BeforeEach
    void setUp() {
      api = new OrdersApiImpl( new OrderRepositoryInMemory() );
    }

    @Nested
    @DisplayName( "when an order is created" )
    class WhenAnOrderIsCreated {
      private Order order;

      @BeforeEach
      void setUp() {
        order = new Order( UUID, Money.zero( EUR ), Collections.emptyList(), null );
        api.createOrder( order );
      }

      @Test
      @DisplayName( "should return a list with the new order" )
      void shouldReturnListWithNewOrder() {
        assertThat( api.getOrders() ).isEqualTo( Collections.singletonList( order ) );
      }
    }

    @AfterEach
    void tearDown() {
      api = null;
    }
  }
}