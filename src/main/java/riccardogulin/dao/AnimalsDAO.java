package riccardogulin.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import riccardogulin.entities.Animal;
import riccardogulin.entities.Cat;
import riccardogulin.entities.Dog;
import riccardogulin.entities.Owner;
import riccardogulin.exceptions.NotFoundException;

import java.util.List;

public class AnimalsDAO {
	private final EntityManager entityManager;

	public AnimalsDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Animal animal) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(animal);
		transaction.commit();

		System.out.println("L'animale " + animal.getName() + " è stato salvato correttamente");
	}

	public Animal findById(long animalId) {
		Animal found = entityManager.find(Animal.class, animalId);
		if (found == null) throw new NotFoundException(animalId);
		return found;
	}

	public Dog findDogById(long dogId) {
		Dog found = entityManager.find(Dog.class, dogId);
		if (found == null) throw new NotFoundException(dogId);
		return found;
	}

	public Cat findCatById(long catId) {
		Cat found = entityManager.find(Cat.class, catId);
		if (found == null) throw new NotFoundException(catId);
		return found;
	}

	public List<Animal> findAll() {
		// La seguente query è equivalente a fare SELECT * FROM animals solo nel caso della SINGLE TABLE
		// Nel caso di una JOINED sarà come sopra però con anche dei JOIN per prelevare i dati dalle altre tabelle
		// Nel caso della TABLE PER CLASS farà SELECT * FROM dogs, SELECT * FROM cats e unisce tutto in un unico risultato
		TypedQuery<Animal> query = entityManager.createQuery("SELECT a FROM Animal a", Animal.class); // Le typed query sono query alle quali specifico il tipo di
		// oggetto che mi verrà restituito
		return query.getResultList();
	}

	public List<Dog> findAllDogs() {
		TypedQuery<Dog> query = entityManager.createQuery("SELECT d FROM Dog d", Dog.class);
		return query.getResultList();
	}

	public List<Cat> findAllCats() {
		TypedQuery<Cat> query = entityManager.createQuery("SELECT c FROM Cat c", Cat.class);
		return query.getResultList();
	}

	public List<String> findAllNames() {
		TypedQuery<String> query = entityManager.createQuery("SELECT a.name FROM Animal a", String.class);
		return query.getResultList();
	}

	public List<Animal> filterAnimalsByNameStartingWith(String partialName) {
		TypedQuery<Animal> query = entityManager.createNamedQuery("findByNameStartsWith", Animal.class);
		query.setParameter("partialName", partialName + "%");
		return query.getResultList();
	}

	public void findAnimalsByNameAndUpdateName(String oldName, String newName) {

		EntityTransaction transaction = entityManager.getTransaction(); // Siccome non stiamo facendo una semplice lettura ma stiamo modificando le tabelle dobbiamo usare le transactions
		transaction.begin();
		Query query = entityManager.createQuery("UPDATE Animal a SET a.name = :newName WHERE a.name = :oldName"); // UPDATE animals SET name = 'Nuovonome' WHERE name = 'Vecchionome'
		query.setParameter("newName", newName);
		query.setParameter("oldName", oldName);

		int numModificati = query.executeUpdate(); // esegue le query di tipo UPDATE e DELETE

		transaction.commit();

		System.out.println(numModificati + " elementi sono stati modificati con successo");
	}

	public void findAnimalsByNameAndDelete(String name) {
		EntityTransaction transaction = entityManager.getTransaction(); // Siccome non stiamo facendo una semplice lettura ma stiamo modificando le tabelle dobbiamo usare le transactions
		transaction.begin();
		Query query = entityManager.createQuery("DELETE FROM Animal a WHERE a.name = :name"); // DELETE FROM animals WHERE name = 'Nomedacancellare'
		query.setParameter("name", name);

		int numCancellati = query.executeUpdate(); // esegue le query di tipo UPDATE e DELETE

		transaction.commit();

		System.out.println(numCancellati + " elementi sono stati cancellati con successo");
	}

	public List<Animal> findAnimalsByOwnersName(String ownerName) {
		TypedQuery<Animal> query = entityManager.createQuery("SELECT a FROM Animal a WHERE a.owner.name = :ownerName", Animal.class);
		query.setParameter("ownerName", ownerName);
		return query.getResultList();
	}

	public List<Animal> findAnimalsByOwner(Owner owner) {
		TypedQuery<Animal> query = entityManager.createQuery("SELECT a FROM Animal a WHERE a.owner = :owner", Animal.class);
		query.setParameter("owner", owner);
		return query.getResultList();
	}

	// Documentazione JPQL --> https://www.objectdb.com/java/jpa/query/jpql/structure

}
