package com.internship.portal.nutrition.model;

import com.internship.portal.nutrition.dto.FoodDTO;
import com.internship.portal.nutrition.dto.NutritionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "food")
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "quantity", nullable = false)
    private String quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Meal meal;

    public Food(FoodDTO foodDTO){
        this.id = foodDTO.getId();
        this.name = foodDTO.getName();
        this.quantity = foodDTO.getQuantity();
    }
}
