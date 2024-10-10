package riccardogulin.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
// @DiscriminatorValue("Gatto")
@Table(name = "cats")
public class Cat extends Animal {
	private double maxJumpHeight;

	public Cat() {
	}

	public Cat(String name, int age, double maxJumpHeight) {
		super(name, age);
		this.maxJumpHeight = maxJumpHeight;
	}

	public double getMaxJumpHeight() {
		return maxJumpHeight;
	}

	public void setMaxJumpHeight(double maxJumpHeight) {
		this.maxJumpHeight = maxJumpHeight;
	}

	@Override
	public String toString() {
		return "Cat{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", maxJumpHeight=" + maxJumpHeight +
				"} ";
	}
}
