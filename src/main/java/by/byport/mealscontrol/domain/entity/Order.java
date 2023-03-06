package by.byport.mealscontrol.domain.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id",
            length = 15)
    private String orderNumber;

    @OneToMany()
    @Cascade({ CascadeType.SAVE_UPDATE })
    @JoinTable(name = "orders_relaxers")
    private List<Relaxer> relaxers = new ArrayList<>();

    @Column(name = "days_accounting_type")
    private int daysAccountingType;

    public Order() {
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<Relaxer> getRelaxers() {
        return relaxers;
    }

    public void setRelaxers(List<Relaxer> relaxers) {
        this.relaxers = relaxers;
    }

    public int getDaysAccountingType() {
        return daysAccountingType;
    }

    public void setDaysAccountingType(int daysAccountingType) {
        this.daysAccountingType = daysAccountingType;
    }
}
