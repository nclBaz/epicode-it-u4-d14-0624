package riccardogulin.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "animals")
/*@Inheritance(strategy = InheritanceType.SINGLE_TABLE)*/
// @DiscriminatorColumn(name = "tipo_animale") // OPZIONALE. Serve per cambiare il nome della discriminator column (default si chiama DTYPE)
/*
SINGLE TABLE: è la strategia più semplice. Mi genererà un'unica tabella contenente tutti gli animali
sia Cat che Dog. Ha la comodità di fornirci una sola tabella dove troverò tutti gli animali, il che è più gestibile e performante rispetto
ad avere multiple tabelle. Di contro però ci troveremo ad avere, soprattutto se i figli differiscono per un buon numero di attributi, ad avere
delle tabelle abbastanza disordinate e piene di valori null, ciò significa anche che non potrò mettere dei vincoli NON NULL sulle colonne ma dovrò
gestire il tutto tramite codice

 */

// @Inheritance(strategy = InheritanceType.JOINED)
/*
JOINED è la strategia che ci porta ad avere tabelle sia per il padre che per i figli. Nel nostro caso avremo 3 tabelle, una per Animal, una per Cat
e una per Dog. Nella tabella degli animali avremo gli attributi in comune, nelle figlie gli attributi specifici. Questa strategia ci porta ad avere
una struttura più "pulita" rispetto alla precedente (posso ad esempio inserire dei vincoli di non-nullness nelle colonne). Di contro però le operazioni
di lettura dei dati dovranno coinvolgere dei JOIN (seppur hanno un costo limitato, hanno sono pur sempre un'operazione). Questa strategia è da preferire
quando i figli hanno parecchi attributi diversi tra loro e pochi in comune
*/
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/*
TABLE PER CLASS anche detta Table per CONCRETE Class, è la strategia che creerà tabelle per ogni classe concreta. Se la classe padre è astratta, essa
non avrà una tabella di riferimento, viceversa se il padre fosse non astratto verrà creata una tabella anche per esso. In generale possiamo dire che
questa strategia ci porta ad avere uno schema pulito delle tabelle in quanto tutto è ben separato, però di contro ha degli svantaggi abbastanza
impattanti per quanto riguarda le query polimorfiche, ovvero query che nel nostro caso coinvolgono tutti gli animali (se invece ci sono query che
coinvolgono solo cani o solo gatti no problem)

*/

public abstract class Animal {
	@Id
	@GeneratedValue
	protected long id;
	protected String name;
	protected int age;

	public Animal() {
	}

	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Animal{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
