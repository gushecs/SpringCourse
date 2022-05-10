package com.sc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.sc.domain.enums.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private BCryptPasswordEncoder pe;

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

		Product p12 = new Product(null, "Product 12", 10.00);
		Product p13 = new Product(null, "Product 13", 10.00);
		Product p14 = new Product(null, "Product 14", 10.00);
		Product p15 = new Product(null, "Product 15", 10.00);
		Product p16 = new Product(null, "Product 16", 10.00);
		Product p17 = new Product(null, "Product 17", 10.00);
		Product p18 = new Product(null, "Product 18", 10.00);
		Product p19 = new Product(null, "Product 19", 10.00);
		Product p20 = new Product(null, "Product 20", 10.00);
		Product p21 = new Product(null, "Product 21", 10.00);
		Product p22 = new Product(null, "Product 22", 10.00);
		Product p23 = new Product(null, "Product 23", 10.00);
		Product p24 = new Product(null, "Product 24", 10.00);
		Product p25 = new Product(null, "Product 25", 10.00);
		Product p26 = new Product(null, "Product 26", 10.00);
		Product p27 = new Product(null, "Product 27", 10.00);
		Product p28 = new Product(null, "Product 28", 10.00);
		Product p29 = new Product(null, "Product 29", 10.00);
		Product p30 = new Product(null, "Product 30", 10.00);
		Product p31 = new Product(null, "Product 31", 10.00);
		Product p32 = new Product(null, "Product 32", 10.00);
		Product p33 = new Product(null, "Product 33", 10.00);
		Product p34 = new Product(null, "Product 34", 10.00);
		Product p35 = new Product(null, "Product 35", 10.00);
		Product p36 = new Product(null, "Product 36", 10.00);
		Product p37 = new Product(null, "Product 37", 10.00);
		Product p38 = new Product(null, "Product 38", 10.00);
		Product p39 = new Product(null, "Product 39", 10.00);
		Product p40 = new Product(null, "Product 40", 10.00);
		Product p41 = new Product(null, "Product 41", 10.00);
		Product p42 = new Product(null, "Product 42", 10.00);
		Product p43 = new Product(null, "Product 43", 10.00);
		Product p44 = new Product(null, "Product 44", 10.00);
		Product p45 = new Product(null, "Product 45", 10.00);
		Product p46 = new Product(null, "Product 46", 10.00);
		Product p47 = new Product(null, "Product 47", 10.00);
		Product p48 = new Product(null, "Product 48", 10.00);
		Product p49 = new Product(null, "Product 49", 10.00);
		Product p50 = new Product(null, "Product 50", 10.00);
		
		cat1.getProducts().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

		p12.getCategories().add(cat1);
		p13.getCategories().add(cat1);
		p14.getCategories().add(cat1);
		p15.getCategories().add(cat1);
		p16.getCategories().add(cat1);
		p17.getCategories().add(cat1);
		p18.getCategories().add(cat1);
		p19.getCategories().add(cat1);
		p20.getCategories().add(cat1);
		p21.getCategories().add(cat1);
		p22.getCategories().add(cat1);
		p23.getCategories().add(cat1);
		p24.getCategories().add(cat1);
		p25.getCategories().add(cat1);
		p26.getCategories().add(cat1);
		p27.getCategories().add(cat1);
		p28.getCategories().add(cat1);
		p29.getCategories().add(cat1);
		p30.getCategories().add(cat1);
		p31.getCategories().add(cat1);
		p32.getCategories().add(cat1);
		p33.getCategories().add(cat1);
		p34.getCategories().add(cat1);
		p35.getCategories().add(cat1);
		p36.getCategories().add(cat1);
		p37.getCategories().add(cat1);
		p38.getCategories().add(cat1);
		p39.getCategories().add(cat1);
		p40.getCategories().add(cat1);
		p41.getCategories().add(cat1);
		p42.getCategories().add(cat1);
		p43.getCategories().add(cat1);
		p44.getCategories().add(cat1);
		p45.getCategories().add(cat1);
		p46.getCategories().add(cat1);
		p47.getCategories().add(cat1);
		p48.getCategories().add(cat1);
		p49.getCategories().add(cat1);
		p50.getCategories().add(cat1);
		
		categoryRep.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		productRep.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null,"Uberlândia",st1);
		City c2 = new City(null,"São Paulo",st2);
		City c3 = new City(null,"Campinas",st2);
		
		st1.getCities().add(c1);
		st2.getCities().addAll(Arrays.asList(c2,c3));
		
		stateRep.saveAll(Arrays.asList(st1,st2));
		cityRep.saveAll(Arrays.asList(c1,c2,c3));
		
		Client cl1 = new Client(null, "Maria Silva", "gustavo.heitor@outlook.com", "36378912377", ClientType.PHYSICAL, pe.encode("123"));
		cl1.getPhones().addAll(Arrays.asList("27363323","93838393"));

		Client cl2 = new Client(null, "Ana Costa", "gu.ga.gh@hotmail.com", "31628382740", ClientType.PHYSICAL, pe.encode("123"));
		cl2.getPhones().addAll(Arrays.asList("27363324","93238393"));
		cl2.addProfile(Profile.ADMIN);

		Address a1 = new Address(null, "Rua Flores","300","apt 203","Jardins","38220834",cl1,c1);
		Address a2 = new Address(null, "Avenida Matos","105","sala 800","Centro","38777012",cl1,c2);
		Address a3 = new Address(null, "Avenida Floriano","2014",null,"Centro","28777012",cl2,c2);
		cl1.getAddresses().addAll(Arrays.asList(a1,a2));
		cl1.getAddresses().addAll(Arrays.asList(a3));
		
		clientRep.saveAll(Arrays.asList(cl1,cl2));
		addressRep.saveAll(Arrays.asList(a1,a2,a3));
		
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
