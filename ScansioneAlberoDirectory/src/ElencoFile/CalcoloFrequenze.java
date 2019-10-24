package ElencoFile;

import java.util.Arrays;
import java.util.HashMap;

public class CalcoloFrequenze {
	private String [] copiaelenco;
	private HashMap <String, Integer> frequenze =new HashMap<String, Integer>();

	/**
	 * Il costruttore prende in ingresso l'array di stringhe e lo ordina. Poi lo assegna alla variabile locale
	 * copiaelenco
	 * @param elenco Array di stringhe che contiene l'elenco di cui calcolare le frequenze delle occorrenze
	 */
	public CalcoloFrequenze(String[] elenco) {
		// TODO Auto-generated constructor stub
		//Prima cosa si ordina l'elenco in ordine alfabetico
		Arrays.sort(elenco);
		//si assegna elenco alla variabile copiaelenco
		setCopiaelenco(elenco);
	}
	public void trovaoccorrenze() {
		int count=1;
		int lunghezza=copiaelenco.length;
		for (int i=0;i<lunghezza;i++) {
			if (i<lunghezza-1) {
				if (copiaelenco[i].equals(copiaelenco[i+1])) {
					count++;
				}else {
					frequenze.put(copiaelenco[i], count);
					count=1;
				}
			}else {
				frequenze.put(copiaelenco[i], count);
			}
		}
		for (String i: frequenze.keySet()) {
			System.out.println("Chiave: "+i + " Valore: " + frequenze.get(i));
		}
	}
	private String[] getCopiaelenco() {
		return copiaelenco;
	}
	private void setCopiaelenco(String[] vettore) {
		this.copiaelenco = vettore;
	}

}
