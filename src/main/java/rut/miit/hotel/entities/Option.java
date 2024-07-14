package rut.miit.hotel.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "options")
public class Option extends BaseEntity {
    private String name;
    private List<HotelOption> hotelOptions;

    public Option(String name, List<HotelOption> hotelOptions) {
        this.name = name;
        this.hotelOptions = hotelOptions;
    }

    protected Option() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
    public List<HotelOption> getHotelOptions() {
        return hotelOptions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHotelOptions(List<HotelOption> hotelOptions) {
        this.hotelOptions = hotelOptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return Objects.equals(name, option.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
