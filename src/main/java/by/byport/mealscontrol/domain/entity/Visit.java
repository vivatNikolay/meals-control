package by.byport.mealscontrol.domain.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "visit_id")
    private Long visitId;

    @ManyToOne
    @JoinColumn(name = "relaxer_id",
            insertable = false,
            updatable = false)
    private Relaxer relaxer;

    @OneToOne()
    @JoinColumn(name = "place_id")
    private Place place;

    public Visit() {
    }

    public Long getVisitId() {
        return visitId;
    }

    public void setVisitId(Long visitId) {
        this.visitId = visitId;
    }

    public Relaxer getRelaxer() {
        return relaxer;
    }

    public void setRelaxer(Relaxer relaxer) {
        this.relaxer = relaxer;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
