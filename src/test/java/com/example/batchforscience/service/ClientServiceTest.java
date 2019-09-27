package com.example.batchforscience.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.batchforscience.domain.Invoice;
import com.example.batchforscience.domain.Order;
import com.example.batchforscience.service.impl.ClientServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

	@Mock
	private HttpService httpService;
	
	private ClientService clientService;
	private Long clientId = 1L;
	
	@Before
	public void setUp() {
		clientService = new ClientServiceImpl(httpService);
	}
	
	@Test
	public void shouldReturnZeroWhenListOfInvoicesIsEmpty() {
		Mockito.when(httpService.getClientInvoices(clientId)).thenReturn(new ArrayList<Invoice>());
		
		BigDecimal debt = clientService.getDebt(clientId);
		
		assertThat(debt).isEqualTo(BigDecimal.ZERO);
	}
	
	@Test
	public void shouldReturnZeroWhenListContainsOnlyPaidInvoices() {
		Invoice inv1 = Invoice.of(BigDecimal.valueOf(1.0), true);
		Invoice inv2 = Invoice.of(BigDecimal.valueOf(1.0), true);
		List<Invoice> invoices = Arrays.asList(inv1, inv2);
		Mockito.when(httpService.getClientInvoices(clientId)).thenReturn(invoices);
		
		BigDecimal debt = clientService.getDebt(clientId);
		
		assertThat(debt).isEqualTo(BigDecimal.ZERO);
	}
	
	@Test
	public void shouldReturnGivenValueWhenListContainsOnlyOneInvoiceThatIsUnpaid() {
		Invoice inv1 = Invoice.of(BigDecimal.valueOf(15.5), false);
		List<Invoice> invoices = Arrays.asList(inv1);
		Mockito.when(httpService.getClientInvoices(clientId)).thenReturn(invoices);
		
		BigDecimal debt = clientService.getDebt(clientId);
		
		assertThat(debt).isEqualTo(BigDecimal.valueOf(15.5));
	}
	
	@Test
	public void shouldReturnGivenValueWhenListContainsOnlyOneUnpaidInvoice() {
		Invoice inv1 = Invoice.of(BigDecimal.valueOf(2.7), false);
		Invoice inv2 = Invoice.of(BigDecimal.valueOf(1.0), true);
		List<Invoice> invoices = Arrays.asList(inv1, inv2);
		Mockito.when(httpService.getClientInvoices(clientId)).thenReturn(invoices);
		
		BigDecimal debt = clientService.getDebt(clientId);
		
		assertThat(debt).isEqualTo(BigDecimal.valueOf(2.7));
	}
	
	@Test
	public void shouldReturnCorrectValueWhenListContainsMultipleVariousInvoices() {
		Invoice inv1 = Invoice.of(BigDecimal.valueOf(1.6), false);
		Invoice inv2 = Invoice.of(BigDecimal.valueOf(1.0), true);
		Invoice inv3 = Invoice.of(BigDecimal.valueOf(3.0), true);
		Invoice inv4 = Invoice.of(BigDecimal.valueOf(4.5), false);
		Invoice inv5 = Invoice.of(BigDecimal.valueOf(1.0), false);
		List<Invoice> invoices = Arrays.asList(inv1, inv2, inv3, inv4, inv5);
		Mockito.when(httpService.getClientInvoices(clientId)).thenReturn(invoices);
		
		BigDecimal debt = clientService.getDebt(clientId);
		
		assertThat(debt).isEqualTo(BigDecimal.valueOf(7.1));
	}
	
	@Test
	public void shouldReturnZeroWhenListOfOrdersIsEmpty() {
		Mockito.when(httpService.getClientOrders(clientId)).thenReturn(new ArrayList<Order>());
		
		Long ordersCount = clientService.getNumberOfCurrentOrders(clientId);
		
		assertThat(ordersCount).isEqualTo(0L);
	}
	
	@Test
	public void shouldReturnZeroWhenListContnainsOnlyPastOrders() {
		Order ord1 = Order.of(LocalDate.now().minusWeeks(50));
		Order ord2 = Order.of(LocalDate.now().minusDays(1));
		List<Order> orders = Arrays.asList(ord1, ord2);
		Mockito.when(httpService.getClientOrders(clientId)).thenReturn(orders);
		
		Long ordersCount = clientService.getNumberOfCurrentOrders(clientId);
		
		assertThat(ordersCount).isEqualTo(0L);
	}
	
	@Test
	public void shouldReturnCorrectNumberWhenListContainsVariousOrders() {
		Order ord1 = Order.of(LocalDate.now().minusDays(12)); //before
		Order ord2 = Order.of(LocalDate.now().minusMonths(4)); //before
		Order ord3 = Order.of(LocalDate.now().plusYears(1)); //after
		Order ord4 = Order.of(LocalDate.now().plusMonths(3)); //after
		Order ord5 = Order.of(LocalDate.now().minusYears(30)); //before
		List<Order> orders = Arrays.asList(ord1, ord2, ord3, ord4, ord5);
		Mockito.when(httpService.getClientOrders(clientId)).thenReturn(orders);
		
		Long ordersCount = clientService.getNumberOfCurrentOrders(clientId);
		
		assertThat(ordersCount).isEqualTo(2L);
	}
}
