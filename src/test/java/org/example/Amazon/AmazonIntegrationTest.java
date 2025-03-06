//added Part3 Amazon Tests

package org.example.Amazon;

import org.example.Amazon.Cost.PriceRule;
import org.example.Amazon.Cost.ItemType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.List;


public class AmazonIntegrationTest {
    private Database database;
    private ShoppingCartAdaptor shoppingCartAdaptor;
    private Amazon amazon;



    private static class DummyPriceRule implements PriceRule {
        @Override
        public double priceToAggregate(List<Item> items) {
            return items.stream().mapToDouble(item -> item.getQuantity() * item.getPricePerUnit()).sum();
        }
    }


    @BeforeEach
    public void setup() {
        database = new Database();
        database.resetDatabase();
        shoppingCartAdaptor = new ShoppingCartAdaptor(database);
        amazon = new Amazon(shoppingCartAdaptor, Collections.singletonList(new DummyPriceRule()));
    }




    @Test
    @DisplayName("Specification-based: Integration test with an empty cart")
    public void testCalculateWithEmptyCart() {
        assertEquals(0, amazon.calculate(), "Total should be 0 for an empty cart.");
    }




    @Test
    @DisplayName("Specification-based: Integration test with a single item")
    public void testCalculateWithSingleItem() {
        Item item = new Item(ItemType.OTHER, "Test Item", 2, 15.0);
        amazon.addToCart(item);
        assertEquals(30.0, amazon.calculate(), "Total should reflect the price of added items.");
    }





    @Test
    @DisplayName("Specification-based: Integration test with multiple items")
    public void testCalculateWithMultipleItems() {
        amazon.addToCart(new Item(ItemType.OTHER, "Item 1", 1, 10.0));
        amazon.addToCart(new Item(ItemType.OTHER, "Item 2", 3, 5.0));
        assertEquals(25.0, amazon.calculate(), "Total should sum prices of all items.");
    }





    @Test
    @DisplayName("Structural-based: Testing reset of database before each test")
    public void testDatabaseReset() {
        amazon.addToCart(new Item(ItemType.OTHER, "Item", 1, 20.0));
        setup();
        assertEquals(0, amazon.calculate(), "Database should be reset, resulting in an empty cart.");
    }




}

