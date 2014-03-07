package Exceptions;

public class SpotsNotFoundException extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	public SpotsNotFoundException(){
		super("Nenhum Spot foi salvo no sistema!");
	}
	
	

}
