package dt;

import java.math.BigDecimal;

/**
 * Tuny
 */
public class Tuny {
    String name;
    BigDecimal age;
    String bio;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAge() {
        return this.age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Tuny() {}

    public Tuny(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public Tuny(String name, String bio, int age) {
        this.name = name;
        this.bio = bio;
        this.age = new BigDecimal(age);
    }
}
