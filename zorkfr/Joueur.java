import java.util.*;
/**
 *  <p>Un joueur dans un jeu d'aventure.</p>
 *
 *  <p>Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p>
 *
 * <p>Un joueur peut transporter des objets avec lui. Chaque objet a un poids.
 *	Le joueur ne peut transporter des objets que dans la mesure où le poids
 *	total des objets transportés ne dépasse pas une certaine limite ﬁxée à
 *	l’avance. Le joueur peut transporter plusieurs occurences d'un même
 *	objet (les objets sont distingués en utilisant la méthode equals).
*	Un joueur est caractérisé par:
 *	<ul>
 *		<li>son nom</li>
 *		<li>les objets qu'il transporte</li>
 *		<li>le poids total maximal des objets que le joueur peut
 *			transporter</li>
 *	</ul>
 * </p>
 *
 * @invariant getNom() != null;
 * @invariant getPoidsMax() >= 0;
 * @invariant getPoids() >= 0 && getPoids() <= getPoidsMax();
 * @invariant getNbObjets() >= 0;
 *
 * @author Sujeevan ASEERVATHAM, Marc Champesme
 * @since 31 janvier 2007
 * @version 2 novembre 2020
 */
public class Joueur {
	private String nom;

	// Les méthodes (ou attributs) modifiées ou ajoutées pour le TD/TP4
	// sont placées en tête du fichier à partir de cette ligne.

	private ArrayList<ObjetZork> LesObjets = new ArrayList<ObjetZork>(); 
	private int nbObjets;
	private int poids;
	private int poidsMax;

	/**
	 * Initalise un joueur avec le nom spécifié.
	 *
	 * @param nom Le nom du joueur
	 *
	 * @requires nom != null;
	 * @ensures getNom().equals(nom);
	 * @ensures getNbObjets() == 0;
	 * @ensures getPoids() == 0;
	 * @ensures getPoidsMax() == 10;
	 */
	public Joueur(String nom) {
		this.nom = nom;
		poidsMax = 10;
		LesObjets = new ArrayList<ObjetZork>(); 
	}



	/**
	 * Initialise un nouveau joueur.
	 *
	 * @param nom le nom du joueur.
	 * @param poidsMax Le poids max autorisé pour le joueur.
	 * @param objets les objets en possession du joueur.
	 * @param nbObjets Le nombre d'objet non null dans le tableau objets.
	 *
	 * @requires nom != null;
	 * @requires poidsMax > 0;
	 * @requires objets != null;
	 * @requires objets.length >= nbObjets;
	 * @requires nbObjets >= 0;
	 * @requires (\forall int i; i >= 0 && i < nbObjets;
	 *			objets[i] != null);
	 * @requires (\forall int i; i >= 0 && i < nbObjets;
	 *			objets[i].estTransportable());
	 * @requires (\sum int i; i >= 0 && i < nbObjets; objets[i].getPoids())
	 *							<= poidsMax;
	 * @ensures getNom().equals(nom);
	 * @ensures getNbObjets() == nbObjets;
	 * @ensures (\forall int i; i >= 0 && i < nbObjets;
	 *			contient(objets[i]));
	 * @ensures getPoids() == (\sum int i; i >= 0 && i < nbObjets; objets[i].getPoids())
	 * @ensures getPoidsMax() == poidsMax;
	 */
	public Joueur(String nom, int poidsMax, int nbObjets) {
		this.nom = nom;
		this.poidsMax = poidsMax;
		LesObjets = new ArrayList<ObjetZork>();
		poids = 0;
		this.nbObjets = nbObjets;
		
	}

	/**
	  *  Renvoie le nombre d'exemplaires de l'ObjetZork spécifié en possession
	  * de cette personne. La présence d'un objet est testée en utilisant la
	  * méthode equals de la classe ObjetZork. L'argument doit etre non
	  * <code>null</code>.
	  *
	  * @param  oz  Objet dont on cherche a savoir en combien d'exemplaires le joeur possede l'objet.
	  * @return     le nombre d'exemplaires de l'objet spécifié en possession de ce joueur.
	  *
	  * @requires oz != null;
	  * @ensures \result >= 0;
	  * @ensures \result == (\num_of int i; i >= 0 && i < getNbObjets();
	  *				\old(getTabObjets()[i]).equals(oz));
	  * @ensures contient(oz) <==> \result > 0;
	  * @ensures !contient(oz) <==> \result == 0;
	  *
	  * @pure
	  */
	public int contientCombienDe(ObjetZork oz) {
		int n = 0; 					//compteur
		for (int i = 0; i < LesObjets.size(); i++)
		{
			if (LesObjets.get(i).equals(oz))
				n++;
		}
		return n;
	}

	/**
	 * Indique si l'objet spécifié peut être ajouté aux objets possédés
	 * par ce Joueur. Pour pouvoir être ajouté, l'objet spécifié doit être
	 * non null, transportable et son poids ne doit pas conduire à ce que
	 * le poids total des objets transportés dépasse le poids maximal, si
	 * l'objet était ajouté.
	 *
	 *
	 * @param oz l'ObjetZork dont on souhaite tester s'il peut être ajouté
	 *
	 * @return true si l'objet spécifié peut être ajouté à ce Joueur ; false sinon
	 *
	 * @ensures \result <==> (oz != null
	 *		 && oz.estTransportable()
	 *		 && ((this.getPoids() + oz.getPoids()) <= this.getPoidsMax()));
	 *
	 * @pure
	 */
	public boolean ajoutEstPossible(ObjetZork oz) { 
		if(this.poids + oz.getPoids() <= this.poidsMax && oz.estTransportable())
		{
			return true; 
		}
		return false;
	}


	/**
	 *  Ajoute l'objet spécifié aux objets possedés par ce joueur. Si l'objet
	 *  est déja possédé par ce joueur, un exemplaire supplémentaire de cet objet
	 *  y est ajouté. L'objet spécifié doit pouvoir être ajouté
	 * au sens de la méthode ajoutEstPossible().
	 *
	 * @param  oz  L'objet a ajouter dans cette piece
	 *
	 * @requires ajoutEstPossible(oz);
	 * @ensures contient(oz);
	 * @ensures contientCombienDe(oz) == \old(contientCombienDe(oz)) + 1;
	 * @ensures getNbObjets() == \old(getNbObjets() + 1);
	 */
	public void ajouter(ObjetZork oz) {
		
		LesObjets.add(oz);
		
		this.poids += oz.getPoids();
		
	}


	/**
	* Affihe l'inventaire du joueur 
	*/
	public void Inventaire() 
	{
		if( this.poids == 0) 
		{
			System.out.println("Pas d'objet me soule po"); 
			return; 
		}
		for(int i = 0; i < LesObjets.size(); i++) 
		{
			System.out.println("" + LesObjets.get(i).getDescription()); 
		}
		
		System.out.println(" poids : " +this.poids); 
		
	}
	


	/**
	 *  Retire un exemplaire de l'objet spécifié du joueur si cet objet y est
	 *  présent. Renvoie true si cet objet était effectivement présent chez le
	 *  joueur et que l'objet a pu etre effectivement retiré ; renvoie false sinon.
	 *  La présence d'un objet est testé en utilisant la méthode equals de la
	 *  classe ObjetZork. L'argument doit etre non <code>null</code>.
	 *
	 * @param  oz  Objet dont un exemplaire doit etre retirer de cette piece
	 * @return     true si cet objet était effectivement présent ; false sinon.
	 *
	 * @requires oz != null;
	 * @ensures \old(contient(oz)) <==> \result;
	 * @ensures \old(contient(oz)) <==> (contientCombienDe(oz)
	 *					== \old(contientCombienDe(oz)) - 1);
	 * @ensures \old(contientCombienDe(oz) == 1) ==> !contient(oz);
	 * @ensures \old(contientCombienDe(oz) > 1) <==> contient(oz);
	 */
	public boolean retirer(ObjetZork oz) {
		return LesObjets.remove(oz); 
 
	}



	/**
	 *  Renvoie true si la personne possede au moins un exemplaire de l'objet
	 *  spécifié. La présence d'un objet est testé en utilisant la méthode equals
	 *  de la classe ObjetZork. L'argument doit etre non <code>null</code>.
	 *
	 * @param  oz  Objet dont on cherche a savoir s'il est présent dans cette piece
	 * @return     true si ce joueur possede au moins un exemplaire de l'objet
	 *      spécifié ; false sinon.
	 *
	 * @requires oz != null;
	 * @ensures \result <==> (\exists int i; i >= 0 && i < getNbObjets();
	 *				\old(getTabObjets()[i]).equals(oz));
	 * @pure
	 */
	public boolean contient(ObjetZork oz) {
		return LesObjets.contains(oz); 
	}


	/**
	 * Renvoie le poids total des objets portés par ce joueur.
	 *
	 * @return Le poids total des objets portés par ce joueur.
	 *
	 * @pure
	 */
	public int getPoids() {
		return poids;
	}

	/**
	 * Retourne le poids max des objets que le joueur peut porter.
	 *
	 * @return le poids max.
	 *
	 * @pure
	 */
	public int getPoidsMax() {
		return poidsMax;
	}

	/**
	 *  Renvoie le nombre d'ObjetZork du joueur.
	 *
	 * @return    Le nombre d'ObjetZork du joueur.
	 *
	 * @pure
	 */
	public int getNbObjets() {
		return nbObjets;
	}

	/**
	 * Retourne le nom du joueur.
	 *
	 * @return Le nom du joueur.
 	 *
	 * @pure
	 */
	public String getNom() {
		return nom;
	}
	

}
