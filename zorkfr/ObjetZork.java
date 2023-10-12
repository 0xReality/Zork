/**
 *  <p>Un objet dans un jeu d'aventure.</p> 
 *
 *  <p>Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> 
 *
 *  <p>Dans le logiciel Zork un objet peut se trouver dans une pièce ou être
 *  transporté par un joueur s'il est transportable. Les objets transportables
 *  possèdent un poids.</p>
 *
 * @invariant descriptionCourte() != null;
 * @invariant getPoids() >= 0;
 *
 * @author     Marc Champesme
 * @since      4 mars 2006
 * @version    6 février 2010
 */
public class ObjetZork {
	private String description;
	private int poids;
	private boolean transportable;


	/**
	 *  Initialise un objet non transportable identifié par la chaîne de caractères
	 *  spécifiée. La chaîne de caractères spécifiée doit être différente de <code>null</code>.
	 *
	 * @requires description != null;
	 * @ensures descriptionCourte().equals(description);
	 * @ensures getPoids() == 0;
	 * @ensures !estTransportable();
	 *
	 * @param  description  La chaîne de caractères qui identifie cet objet
	 */
	public ObjetZork(String description) {
		this.description = description;
	}


	/**
	 *  Initialise un objet transportable identifié par la chaîne de caractères
	 *  spécifiée et dont le poids est l'entier spécifié. La chaîne de caractères
	 *  spécifiée doit être différente de <code>null</code> et le poids doit être supérieur ou
	 *  égal à 0.
	 *
	 * @requires description != null;
	 * @requires poids >= 0;
	 * @ensures descriptionCourte().equals(description);
	 * @ensures getPoids() == poids;
	 * @ensures estTransportable();
	 *
	 * @param  description  La chaîne de caractères qui identifie cet objet
	 * @param  poids        Le poids de l'objet
	 */
	public ObjetZork(String description, int poids) {
		this(description);
		transportable = true;
		this.poids = poids;
	}


	/**
	 *  Renvoie la description de cet objet (i.e. la description spécifiée lors de
	 *  la création de cette instance).
	 *
	 * @return    Description de cet objet
	 *
	 * @pure
	 */
	public String getDescription() {
		return description;
	}


	/**
	 *  Renvoie une description de cet objet contenant l'ensemble de ses
	 *  caractéristiques. Cette description est destinée a fournir une description
	 *  complète de cet objet formatée pour un affichage présenté a l'utilisateur.
	 *
	 * @return    Renvoie une description de cet objet contenant l'ensemble de ses
	 *      caractéristiques
	 *
	 * @pure
	 */
	public String descriptionLongue() {
		String descriptionLongue = description + "(";
		if (transportable) {
			descriptionLongue += poids + "kgs";
		}
		else {
			descriptionLongue += "non transportable";
		}
		return descriptionLongue + ")";
	}


	/**
	 *  Renvoie le poids de cet objet. Si cet objet n'est pas transportable, la
	 *  valeur retournée n'est pas significative.
	 *
	 * @return    Le poids de cet objet.
	 *
	 * @pure
	 */
	public int getPoids() {
		return poids;
	}


	/**
	 *  Renvoie true si cet objet est transportable ; false sinon.
	 *
	 * @return    true si cet objet est transportable ; false sinon
	 *
	 * @pure
	 */
	public boolean estTransportable() {
		return transportable;
	}


	/**
	 *  Renvoie true si et seulement si l'objet spécifié est un ObjetZork, que les
	 *  deux objets sont tous les deux non transportables, ou bien s'ils sont tous
	 *  les transportables, s'ils sont de meme poids.
	 *
	 * @param  o  L'objet a comparer avec cet objet.
	 * @return    true si et seulement si l'objet spécifié est un ObjetZork
	 *      possédant les memes propriétés ; false sinon
	 *
	 * @pure
	 */
	public boolean equals(Object o) {
		if (!(o instanceof ObjetZork)) {
			return false;
		}

		ObjetZork oz = (ObjetZork) o;
		if (estTransportable()) {
			if (!oz.estTransportable()) {
				return false;
			}
			if (getPoids() != oz.getPoids()) {
				return false;
			}
		}
		else {
			//@ assume !this.estTransportable();
			if (oz.estTransportable()) {
				return false;
			}
		}
		//@ assume this.estTransportable() == oz.estTransportable();
		//@ assume this.estTransportable() ==> (getPoids() == oz.getPoids());
		return description.equals(oz.description);
	}


	/**
	 *  Renvoie un code de hashage pour cette instance.
	 *
	 * @return    un code de hashage pour cette instance.
	 *
	 * @pure
	 */
	public int hashCode() {
		if (!estTransportable()) {
			return description.hashCode();
		}
		return description.hashCode() + 31 * (poids + 1);
	}


	/**
	 *  Renvoie une représentation rudimentaire de cette instance
	 * sous forme d'une chaîne de caractères.
	 *
	 * @return    une représentation rudimentaire de cette instance
	 * sous forme d'une chaîne de caractères.
	 *
	 * @pure
	 */
	public String toString() {
		return description + ":poids=" + poids + "transportable=" + transportable;
	}
}

