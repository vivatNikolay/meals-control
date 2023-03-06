package by.byport.mealscontrol.domain.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.*;

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

    private List<Visit> visits = new ArrayList<>();
    private Order order;

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
    @OneToMany()
    @Cascade({ org.hibernate.annotations.CascadeType.ALL })
    @JoinColumn(name = "relaxer_id")
    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    @ManyToOne()
    @JoinTable(name = "orders_relaxers",
            joinColumns={@JoinColumn(name="relaxers_relaxer_id")},
            inverseJoinColumns={@JoinColumn(name="orders_order_id")})
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
