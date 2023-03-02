package by.byport.mealscontrol.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    private Long id;
    private int mealType;

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "meal_type")
    public int getMealType() {
        return mealType;
    }

    public void setMealType(int mealType) {
        this.mealType = mealType;
    }
}
