//added Part3 Amazon Tests

package org.example.Amazon;

import org.example.Amazon.Cost.PriceRule;
import org.example.Amazon.Cost.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;




public class AmazonUnitTest {
    private ShoppingCart shoppingCartMock;
    private PriceRule priceRuleMock;
    private Amazon amazon;



    @BeforeEach
    public void setup() {
        shoppingCartMock = mock(ShoppingCart.class);
        priceRuleMock = mock(PriceRule.class);
        amazon = new Amazon(shoppingCartMock, Collections.singletonList(priceRuleMock));
    }



    @Test
    @DisplayName("Specification-based: Unit test for calculate method with mocked PriceRule")
    public void testCalculateWithMock() {
        when(shoppingCartMock.getItems()).thenReturn(Collections.singletonList(new Item(ItemType.OTHER, "Dummy Item", 2, 20.0)));
        when(priceRuleMock.priceToAggregate(any())).thenReturn(40.0);
        assertEquals(40.0, amazon.calculate(), "Calculate should use the mocked PriceRule to determine total.");
        verify(priceRuleMock).priceToAggregate(any());
    }



    @Test
    @DisplayName("Specification-based: Unit test for adding items to the cart")
    public void testAddToCart() {
        Item dummyItem = new Item(ItemType.OTHER, "Test Item", 1, 10.0);
        amazon.addToCart(dummyItem);
        verify(shoppingCartMock).add(dummyItem);
    }



    @Test
    @DisplayName("Structural-based: Unit test for PriceRule application on multiple items")
    public void testMultipleItemsCalculation() {
        List<Item> items = List.of(
                new Item(ItemType.OTHER, "Item 1", 1, 10.0),
                new Item(ItemType.OTHER, "Item 2", 2, 15.0)
        );
        when(shoppingCartMock.getItems()).thenReturn(items);
        when(priceRuleMock.priceToAggregate(any())).thenAnswer(invocation -> {
            List<Item> argItems = invocation.getArgument(0);
            return argItems.stream().mapToDouble(item -> item.getQuantity() * item.getPricePerUnit()).sum();
        });
        assertEquals(40.0, amazon.calculate(), "Total should accurately sum the products of quantity and price per unit.");
    }
}

