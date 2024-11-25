package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final UserClient userClient;
    private final PizzaClient pizzaClient;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Delivery>> getDelivery(@PathVariable("id") Long id) {
        Optional<Delivery> delivery = deliveryRepository.findById(id);
        if (delivery.isPresent()) {
            return ResponseEntity.ok(delivery);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Delivery> createDelivery(@RequestParam("pizzaId") Long pizzaId,
                                           @RequestParam("userId") Long userId) {


        Delivery newDelivery = Delivery.builder()
                .userId(userId)
                .pizzaId(pizzaId)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryRepository.save(newDelivery));
    }


}
