public class Objet{
	private int poids; 
	private String nom; 
	private boolean deplacer; 
	
	public Objet(int poids, String nom, boolean deplacer){
		this.poids = poids; 
		this.deplacer = deplacer; 
		this.nom = nom; 
	}
	
	
	
	public String Getnom(){
	return nom; 
	}
	
	public int Getpoids(){
	return poids; 
	}
	
	public boolean Getdeplacer(){
	return deplacer; 
	}
	
	public boolean equals(Objet o){
		if(!(o instanceof Object)){
		return false; 
		}
		
		Objet oz = (Objet)o; 
		if(!(oz.nom == this.nom) || !(oz.deplacer == this.deplacer) || !(oz.poids == this.poids)) 
		{	
			return false; 
		} 
		
		return true; 
	}
	
	public int hashCode(){
			
	}
	
	
	
	
}
