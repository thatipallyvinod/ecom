package com.shopping.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mens_dress_id")
    private MensDress mensDress;

    @ManyToOne
    @JoinColumn(name = "womens_dress_id")
    private WomensDress womensDress;

    @ManyToOne
    @JoinColumn(name = "kids_dress_id")
    private KidsDress kidsDress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.CONFIRMED;

    @ManyToOne
    @JoinColumn(name = "register_details_id")
    private RegisterDetails registerDetails;

    // Override toString() method to avoid circular references
    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
