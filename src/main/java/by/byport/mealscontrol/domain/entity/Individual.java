package by.byport.mealscontrol.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "individuals")
public class Individual {
    private Long clientId;
    private String name;
    private String surname;
    private String patronymic;
    private Date dateOfBirth;

    public Individual() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return (surname != null ? surname : "") +
                (name != null ? " " + name : "") +
                (patronymic != null ? " " + patronymic : "") +
                (dateOfBirth != null ? " " + dateOfBirth : "");
    }
}
