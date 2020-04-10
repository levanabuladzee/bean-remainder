package model;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalTime;

public class Remainder implements Serializable {
    @NotNull
    int id;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String name;
    @NotNull
    private LocalTime time;
    @NotNull
    @Valid
    private Location location;

    public Remainder() {
    }

    public Remainder(@NotNull int id, @NotNull @NotEmpty @Size(max = 50) String name, @NotNull LocalTime time, @NotNull @Valid Location location) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.location = location;
    }

    public Remainder(@NotNull @NotEmpty @Size(max = 50) String name, @NotNull LocalTime time, @NotNull @Valid Location location) {
        this.name = name;
        this.time = time;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Remainder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", location=" + location +
                '}';
    }
}
