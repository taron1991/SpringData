package spring.app.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Cardb")
@AllArgsConstructor
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "color")
    private String color;

    @Column(name = "price")
    private int price;

    public Car() {
    }
}
