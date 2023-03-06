package by.byport.mealscontrol.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "places")
public class Place {

    private Long id;
    private Room room;

    public Place() {
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "room_id",
            nullable = false)
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
