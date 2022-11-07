import java.util.Objects;

public class People {

    public String id;
    public String name;
    public String brand;

    public String date;

    public People() {
    }

    public People(String id, String name, String brand, String date) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.date = date;
    }

    @java.lang.Override
    public java.lang.String toString() {
        final java.lang.StringBuilder sb = new java.lang.StringBuilder("People{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", brand='").append(brand).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        People people = (People) o;
        return id.equals(people.id) && name == people.name && brand == people.name && date == people.date;

    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, brand, date);
    }
}