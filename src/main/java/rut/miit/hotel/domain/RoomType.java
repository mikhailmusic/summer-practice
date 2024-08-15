package rut.miit.hotel.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

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

    @Column(name = "description", length = 1500)
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

}
