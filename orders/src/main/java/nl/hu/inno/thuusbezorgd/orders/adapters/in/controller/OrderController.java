package nl.hu.inno.thuusbezorgd.orders.adapters.in.controller;

import nl.hu.inno.thuusbezorgd.orders.adapters.in.controller.dto.CreateOrderRequest;
import nl.hu.inno.thuusbezorgd.orders.core.application.OrderCommandService;
import nl.hu.inno.thuusbezorgd.orders.core.application.OrderQueryService;
import nl.hu.inno.thuusbezorgd.orders.core.application.command.CreateOrderCommand;
import nl.hu.inno.thuusbezorgd.orders.core.application.query.OrderQuery;
import nl.hu.inno.thuusbezorgd.orders.core.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderQueryService queryService;

    private final OrderCommandService commandService;

    public OrderController(OrderQueryService queryService, OrderCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

//    private final OrderRepository orders;
//    private final DishRepository dishes;
//    private final UserRepository users;
//    private final DeliveryService deliveries;
//    private TimeProvider timeProvider;
//    private ReportService reports;

//    public record AddressDto(String street, String nr, String city, String zip) {
//        public AddressDto(Address a) {
//            this(a.getStreet(), a.getHousenr(), a.getCity(), a.getZipcode());
//        }
//
//        public Address toAddress() {
//            return new Address(city(), street(), nr(), zip());
//        }
//    }
//
//    public record DishDto(Long id, String name) {
//
//        public DishDto(long id) {
//            this(id, null);
//        }
//
//        public DishDto(Dish d) {
//            this(d.getId(), d.getName());
//        }
//    }
//
//    public record OrderDto(AddressDto address, List<DishDto> dishes) {
//    }
//
//    public record OrderResponseDto(AddressDto address, List<DishDto> dishes, OrderStatus status, String deliveryUrl) {
//        public static OrderResponseDto fromOrder(Order o) {
//            List<Dish> orderedDishes = o.getOrderedDishes();
//            List<DishDto> dtos = orderedDishes.stream().map(DishDto::new).collect(Collectors.toList());
//
//            return new OrderResponseDto(new AddressDto(o.getAddress()), dtos, o.getStatus(), String.format("/deliveries/%s", o.getDelivery().getId()));
//        }
//    }

//    public OrderController( //Dit begint al aardige constructor overinjection te worden!
//                            OrderRepository orders,
//                            DishRepository dishes,
//                            UserRepository users,
//                            DeliveryService deliveries,
//                            TimeProvider timeProvider,
//                            ReportService reports) {
//        this.orders = orders;
//        this.dishes = dishes;
//        this.users = users;
//        this.deliveries = deliveries;
//        this.timeProvider = timeProvider;
//        this.reports = reports;
//    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Order get(@PathVariable int orderId) {
        return this.queryService.get(new OrderQuery(Long.valueOf(orderId)));
//        return this.orders.findByUser(user).stream().map(OrderResponseDto::fromOrder).collect(Collectors.toList());
    }

    //    @GetMapping("{id}")
//    public ResponseEntity<OrderResponseDto> getOrder(User user, @PathVariable long id) {
//        Optional<Order> order = this.orders.findById(id);
//        if(order.isEmpty() || order.get().getUser() != user){
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(OrderResponseDto.fromOrder(order.get()));
//    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Order create(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        return this.commandService.create(new CreateOrderCommand(createOrderRequest.getUserName(),
                createOrderRequest.getOrderedDishes(),
                createOrderRequest.getAddress()));
    }

//    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
//    @Transactional
//    public ResponseEntity<OrderResponseDto> placeOrder(User user, @RequestBody MultiValueMap<String, String> paramMap) throws URISyntaxException {
//        List<DishDto> orderedDishes = new ArrayList<>();
//        for (String d : paramMap.get("dish")) {
//            long id = Long.parseLong(d);
//            orderedDishes.add(new DishDto(id, ""));
//        }
//
//        String city = paramMap.getFirst("city");
//        String street = paramMap.getFirst("street");
//        String nr = paramMap.getFirst("nr");
//        String zip = paramMap.getFirst("zip");
//
//        //Todo: validate
//
//        return placeOrder(user, new OrderDto(new AddressDto(street, nr, city, zip), orderedDishes));
//    }
//
//
//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
//    @Transactional
//    public ResponseEntity<OrderResponseDto> placeOrder(User user, @RequestBody OrderDto newOrder) throws URISyntaxException {
//        Order created = new Order(user, newOrder.address.toAddress());
//        for (DishDto orderedDish : newOrder.dishes()) {
//            Optional<Dish> d = this.dishes.findById(orderedDish.id());
//            if (d.isPresent()) {
//                created.addDish(d.get());
//            } else {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
//                        "Dish %s %s not found".formatted(orderedDish.id(), orderedDish.name()));
//            }
//        }
//
//        Order savedOrder = this.orders.save(created);
//        savedOrder.process(this.timeProvider.now());
//
//        Delivery newDelivery = deliveries.scheduleDelivery(savedOrder);
//        savedOrder.setDelivery(newDelivery);
//
//        return ResponseEntity
//                .created(new URI("/orders/%d".formatted(savedOrder.getId())))
//                .body(OrderResponseDto.fromOrder(savedOrder));
//
//    }
//
//    @GetMapping("report")
//    public ResponseEntity<List<OrdersPerDayDTO>> getReport(){
//        List<ReportService.OrdersPerDayDTO> orders = this.reports.generateOrderPerDayReport();
//
//        return ResponseEntity.ok(orders.stream().map(o -> new OrdersPerDayDTO(o.year(), o.month(), o.day(), o.count())).toList());
//    }
//
//    public record OrdersPerDayDTO(int year, int month, int day, int orders) {
//    }
}
