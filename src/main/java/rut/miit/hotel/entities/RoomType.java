package rut.miit.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "room_types")
public class RoomType extends BaseEntity{
    private String name;
    private String description;
    private List<Room> rooms;

    public RoomType(String name, String description, List<Room> rooms) {
        this.name = name;
        this.description = description;
        this.rooms = rooms;
    }

    protected RoomType() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @OneToMany(mappedBy = "type")
    public List<Room> getRooms() {
        return rooms;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType roomType = (RoomType) o;
        return Objects.equals(name, roomType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
