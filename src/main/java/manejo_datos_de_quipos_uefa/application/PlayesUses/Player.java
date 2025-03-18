package manejo_datos_de_quipos_uefa.application.PlayesUses;


import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("dorsal") private int dorsal;
    @JsonProperty("name") private String name;
    @JsonProperty("nationality") private String nationality;
    @JsonProperty("age") private int age;
    @JsonProperty("height") private int height;
    @JsonProperty("weight") private int weight;
    @JsonProperty("position") private String position;

    public Player() {}

    public Player(int dorsal, String name, String nationality, int age, int height, int weight, String position) {
        this.dorsal = dorsal;
        this.name = name;
        this.nationality = nationality;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.position = position;
    }

    public int getDorsal() { return dorsal; }
    public String getName() { return name; }
    public String getNationality() { return nationality; }
    public int getAge() { return age; }
    public int getHeight() { return height; }
    public int getWeight() { return weight; }
    public String getPosition() { return position; }

    public void setDorsal(int dorsal) { this.dorsal = dorsal; }
    public void setName(String name) { this.name = name; }
    public void setNationality(String nationality) { this.nationality = nationality; }
    public void setAge(int age) { this.age = age; }
    public void setHeight(int height) { this.height = height; }
    public void setWeight(int weight) { this.weight = weight; }
    public void setPosition(String position) { this.position = position; }

    @Override
    public String toString() {
        return "Player [dorsal=" + dorsal + ", name=" + name + ", nationality=" + nationality +
               ", age=" + age + ", height=" + height + ", weight=" + weight + ", position=" + position + "]";
    }
}