import java.util.Objects;

public class People {


    /*
    {
        "id":"427dc479-c434-4839-a385-73284f2f1558"
        "timestamp":"2022-11-07 20:58:57.610088"
        "name":"Johnny Curtis"
        "country":"Brunei Darussalam"
        "job":"Glass blower/designer"
        "image":"https://picsum.photos/954/374"
    }
     */

    public String id;
    public String name;
    public String timestamp;

    public String country;

    public String job;
    public String image;

    public People() {
    }

    public People(String id, String name, String timestamp, String country, String job, String image) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.country = country;
        this.job = job;
        this.image = image;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("People{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", timestamp='").append(timestamp).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", job='").append(job).append('\'');
        sb.append(", image='").append(image).append('\'');
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
        return id.equals(people.id) && name == people.name && timestamp == people.timestamp && country == people.country && job == people.job && image == people.image;

    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, timestamp, country, job, image);
    }
}