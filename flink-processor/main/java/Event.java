import java.util.Objects;

public class Event {

    /** A Flink POJO must have public fields, or getters and setters */
    public String id = "id";
    public int temperature;
    public String date;


    /** A Flink POJO must have a no-args default constructor */
    public Event() {}

    public Event(final String id, final int temperature, final String date) {
        this.id = id;
        this.temperature = temperature;
        this.date = date;
    }

    /** Used for printing during development */
    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", temperature='" + temperature + '\'' + ", date=" + date + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return id.equals(event.id) && temperature == event.temperature && date.equals(event.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, temperature, date);
    }
}
