//added BarnesAndNoble Tests

package org.example.Barnes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;



class BarnesAndNobleTest {
    private BarnesAndNoble barnesAndNoble;
    private BookDatabase bookDatabase;
    private BuyBookProcess buyBookProcess;




    @BeforeEach
    void setUp() {
        bookDatabase = mock(BookDatabase.class);
        buyBookProcess = mock(BuyBookProcess.class);
        barnesAndNoble = new BarnesAndNoble(bookDatabase, buyBookProcess);
    }




    @Test
    @DisplayName("Specification-based: Order with available books")
    void testOrderWithAvailableBooks() {
        Book book = new Book("123456", 20, 5);
        when(bookDatabase.findByISBN("123456")).thenReturn(book);

        Map<String, Integer> order = new HashMap<>();
        order.put("123456", 2);

        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);
        assertEquals(40, summary.getTotalPrice());
        verify(buyBookProcess).buyBook(book, 2);
    }




    @Test
    @DisplayName("Specification-based: Order with unavailable books")
    void testOrderWithUnavailableBooks() {
        Book book = new Book("654321", 15, 1);
        when(bookDatabase.findByISBN("654321")).thenReturn(book);

        Map<String, Integer> order = new HashMap<>();
        order.put("654321", 3);

        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);
        assertEquals(15, summary.getTotalPrice());
        assertEquals(2, summary.getUnavailable().get(book));
        verify(buyBookProcess).buyBook(book, 1);
    }




    @Test
    @DisplayName("Specification-based: Null order")
    void testNullOrder() {
        assertNull(barnesAndNoble.getPriceForCart(null));
    }




    @Test
    @DisplayName("Structural-based: Order with multiple books")
    void testOrderWithMultipleBooks() {
        Book book1 = new Book("111", 10, 3);
        Book book2 = new Book("222", 30, 2);

        when(bookDatabase.findByISBN("111")).thenReturn(book1);
        when(bookDatabase.findByISBN("222")).thenReturn(book2);

        Map<String, Integer> order = new HashMap<>();
        order.put("111", 2);
        order.put("222", 1);
        PurchaseSummary summary = barnesAndNoble.getPriceForCart(order);
        assertEquals(50, summary.getTotalPrice());
        verify(buyBookProcess).buyBook(book1, 2);
        verify(buyBookProcess).buyBook(book2, 1);
    }
}

