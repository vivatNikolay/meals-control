package by.byport.mealscontrol.domain.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "relaxers")
public class Relaxer {

    private Long relaxerId;
    private Individual individual;
    private Product relaxProduct;
    private Date arrivalDate;
    private Date departureDate;
    private int state;
    private Set<MealCheck> mealCheckSet = new HashSet<>();

    public Relaxer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "relaxer_id")
    public Long getRelaxerId() {
        return relaxerId;
    }

    public void setRelaxerId(Long relaxerId) {
        this.relaxerId = relaxerId;
    }

    @ManyToOne
    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    @ManyToOne
    @JoinColumn(name = "relax_product")
    public Product getRelaxProduct() {
        return relaxProduct;
    }

    public void setRelaxProduct(Product relaxProduct) {
        this.relaxProduct = relaxProduct;
    }

    @Column(name = "arrival_date")
    @Temporal(TemporalType.DATE)
    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Column(name = "departure_date")
    @Temporal(TemporalType.DATE)
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @OneToMany(mappedBy = "relaxer")
    public Set<MealCheck> getMealCheckSet() {
        return mealCheckSet;
    }

    public void setMealCheckSet(Set<MealCheck> mealCheckSet) {
        this.mealCheckSet = mealCheckSet;
    }
}
