package com.sc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.domain.Address;
import com.sc.domain.CardPayment;
import com.sc.domain.Category;
import com.sc.domain.City;
import com.sc.domain.Client;
import com.sc.domain.OrderClass;
import com.sc.domain.OrderItem;
import com.sc.domain.Payment;
import com.sc.domain.Product;
import com.sc.domain.State;
import com.sc.domain.TicketPayment;
import com.sc.domain.enums.ClientType;
import com.sc.domain.enums.PaymentStatus;
import com.sc.repositories.AddressRepository;
import com.sc.repositories.CategoryRepository;
import com.sc.repositories.CityRepository;
import com.sc.repositories.ClientRepository;
import com.sc.repositories.OrderClassRepository;
import com.sc.repositories.OrderItemRepository;
import com.sc.repositories.PaymentRepository;
import com.sc.repositories.ProductRepository;
import com.sc.repositories.StateRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoryRepository categoryRep;
	
	@Autowired
	private ProductRepository productRep;
	
	@Autowired
	private CityRepository cityRep;
	
	@Autowired
	private StateRepository stateRep;
	
	@Autowired
	private ClientRepository clientRep;
	
	@Autowired
	private AddressRepository addressRep;
	
	@Autowired
	private OrderClassRepository orderRep;
	
	@Autowired
	private PaymentRepository payRep;
	
	@Autowired
	private OrderItemRepository orderItemRep;
	
	public void instantiateTesteDatabase() throws ParseException{
		
		Category cat1 = new Category(null,"computers");
		Category cat2 = new Category(null,"office");
		Category cat3 = new Category(null,"houseware");
		Category cat4 = new Category(null,"eletronics");
		Category cat5 = new Category(null,"gardening");
		Category cat6 = new Category(null,"decoration");
		Category cat7 = new Category(null,"cosmetics");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null,"Office table", 30.00);
		Product p5 = new Product(null,"Towel", 50.00);
		Product p6 = new Product(null,"Quilt", 200.00);
		Product p7 = new Product(null,"True color TV", 1200.00);
		Product p8 = new Product(null,"Brushcutter", 800.00);
		Product p9 = new Product(null,"Bedside lamp", 100.00);
		Product p10 = new Product(null,"Pending lamp", 180.00);
		Product p11 = new Product(null,"Shampoo", 90.00);
		
		cat1.getProducts().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProducts().addAll(Arrays.asList(p2,p4));
		cat3.getProducts().addAll(Arrays.asList(p5,p6));
		cat4.getProducts().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9,p10));
		cat7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(cat1,cat4));
		p2.getCategories().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategories().addAll(Arrays.asList(cat1,cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));
		
		categoryRep.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		productRep.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null,"Uberlândia",st1);
		City c2 = new City(null,"São Paulo",st2);
		City c3 = new City(null,"Campinas",st2);
		
		st1.getCities().add(c1);
		st2.getCities().addAll(Arrays.asList(c2,c3));
		
		stateRep.saveAll(Arrays.asList(st1,st2));
		cityRep.saveAll(Arrays.asList(c1,c2,c3));
		
		Client cl1 = new Client(null, "Maria Silva", "maria@silva.com", "36378912377", ClientType.PHYSICAL);
		cl1.getPhones().addAll(Arrays.asList("27363323","93838393"));
		
		Address a1 = new Address(null, "Rua Flores","300","apt 203","Jardins","38220834",cl1,c1);
		Address a2 = new Address(null, "Avenida Matos","105","sala 800","Centro","38777012",cl1,c2);
		cl1.getAddresses().addAll(Arrays.asList(a1,a2));
		
		clientRep.save(cl1);
		addressRep.saveAll(Arrays.asList(a1,a2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		OrderClass o1 = new OrderClass(null, sdf.parse("30/09/2017 10:32"), cl1, a1);
		OrderClass o2 = new OrderClass(null, sdf.parse("10/10/2017 19:35"), cl1, a2);
		
		Payment pay1 = new CardPayment(null, PaymentStatus.PAID, o1, 6);
		o1.setPayment(pay1);
		Payment pay2 = new TicketPayment(null, PaymentStatus.WAITING, o2, sdf.parse("20/10/2017 00:00"), null);
		o2.setPayment(pay2);
		cl1.getOrders().addAll(Arrays.asList(o1,o2));
		
		orderRep.saveAll(Arrays.asList(o1,o2));
		payRep.saveAll(Arrays.asList(pay1,pay2));
		
		OrderItem oi1 = new OrderItem(o1, p1, 0.00, 1, 2000.00);
		OrderItem oi2 = new OrderItem(o1, p3, 0.00, 2, 80.00);
		OrderItem oi3 = new OrderItem(o2, p2, 100.00, 1, 800.00);
		o1.getItems().addAll(Arrays.asList(oi1,oi2));
		o2.getItems().add(oi3);
		p1.getItems().add(oi1);
		p2.getItems().add(oi3);
		p3.getItems().add(oi2);
		
		orderItemRep.saveAll(Arrays.asList(oi1,oi2,oi3));
		
	}

}
