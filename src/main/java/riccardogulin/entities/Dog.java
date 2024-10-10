package riccardogulin.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
// @DiscriminatorValue("Cane") // <-- OPZIONALE, serve per customizzare il valore inserito nella Discriminator Column (default nome della classe Dog)
@Table(name = "dogs")
public class Dog extends Animal {
	private double maxSpeed;

	public Dog() {
	}

	public Dog(String name, int age, double maxSpeed) {
		super(name, age);
		this.maxSpeed = maxSpeed;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	@Override
	public String toString() {
		return "Dog{" +
				"maxSpeed=" + maxSpeed +
				", id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				"} ";
	}
}
