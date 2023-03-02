package by.byport.mealscontrol.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/** Тип питания */
@Entity
@Table(name = "meal_seances_type")
public class MealSeanceType {

    private Long id;
    private String name;
    private String shortName;

    @Temporal(TemporalType.TIME)
    @Column(name = "begin_time",
            nullable = false)
    private Date beginTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "end_time",
            nullable = false)
    private Date endTime;

    public MealSeanceType() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String newShortName) {
        this.shortName = newShortName;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return shortName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MealSeanceType other = (MealSeanceType) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

