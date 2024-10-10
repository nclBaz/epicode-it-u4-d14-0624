package riccardogulin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import riccardogulin.dao.AnimalsDAO;
import riccardogulin.entities.Animal;
import riccardogulin.entities.Cat;
import riccardogulin.entities.Dog;

public class Application {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4d14");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		AnimalsDAO ad = new AnimalsDAO(em);

		Cat felix = new Cat("Felix", 10, 5);
		Dog ringhio = new Dog("Ringhio", 5, 3);
		Cat tom = new Cat("Tom", 1, 5);
		Dog rex = new Dog("Rex", 2, 3);

/*		ad.save(felix);
		ad.save(ringhio);
		ad.save(tom);
		ad.save(rex);*/

		Cat cat = ad.findCatById(1);
		Dog dog = ad.findDogById(2);
		Animal animal = ad.findById(4);

		System.out.println(cat);
		System.out.println(dog);
		System.out.println(animal);

		System.out.println("----------------------- QUERIES ---------------------");

		ad.findAnimalsByOwnersName("Giovanni").forEach(System.out::println);


		em.close();
		emf.close();


	}
}
