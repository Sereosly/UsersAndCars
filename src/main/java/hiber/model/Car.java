package hiber.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car")
public final class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "model")
    private final String model;

    @Column(name = "series")
    private final int series;

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public Car() {
        series = 0;
        model = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }

    @Override
    public final String toString() {
        return "Car{model='" + model + "', series=" + series + '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && series == car.series && Objects.equals(model, car.model);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(model, series);
    }
}
