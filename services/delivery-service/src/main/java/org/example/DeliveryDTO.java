package org.example;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryDTO {

    private Long id;

    private User user;

    private Pizza pizza;
}
