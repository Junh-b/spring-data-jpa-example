package net.junhabaek.spring_data_jpa_example.template;

import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@DataJpaTest
@ActiveProfiles("test")
//@Import(OrderRepositoryImpl.class)
@Transactional
@Disabled
public class RepositoryTestTemplate {
//    @Autowired
//    OrderRepository orderRepository;
//
//    @Test
//    void testName() throws Exception {
//        //given
//        Order order = new Order(1L, 2L, 300L);
//
//        //when
//        Order insertedOrder = orderRepository.save(order);
//        Order foundOrder = orderRepository.findById(insertedOrder.getId());
//
//        //then
//        assertEquals(insertedOrder, foundOrder);
//    }
}