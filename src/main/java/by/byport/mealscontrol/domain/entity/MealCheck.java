package by.byport.mealscontrol.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "meal_check")
public class MealCheck {
    private Long id;
    private Relaxer relaxer;
    private MealSeanceType mealSeanceType;
    private Date checkDate;

    public MealCheck() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public Relaxer getRelaxer() {
        return relaxer;
    }

    public void setRelaxer(Relaxer relaxer) {
        this.relaxer = relaxer;
    }

    @ManyToOne
    @JoinColumn(name = "meal_seance_type")
    public MealSeanceType getMealSeanceType() {
        return mealSeanceType;
    }

    public void setMealSeanceType(MealSeanceType mealSeanceType) {
        this.mealSeanceType = mealSeanceType;
    }

    @Column(name = "check_date")
    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }
}
