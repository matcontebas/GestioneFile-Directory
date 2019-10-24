package ElencoFile;

import java.util.Arrays;
import java.util.HashMap;

public class CalcoloFrequenze {
	private String [] copiaelenco;

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
	/**
	 * il metodo trovaoccorrenze parte dall'Array di Stringhe copiaelenco
	 * ordinate in ordine alfabetico e determina le occorrenze di ogni stringa
	 * all'interno del vettore
	 * @return restituisce un oggetto HashMap che contiene le stringhe trovate nel vettore senza ripetizioni
	 * e il numero di occorrenze che ha trovato
	 *  */
	public HashMap <String, Integer> trovaoccorrenze() {
		HashMap <String, Integer> frequenze =new HashMap<String, Integer>();
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
		return frequenze;
	}
	protected String[] getCopiaelenco() {
		return copiaelenco;
	}
	/**
	 * Setter che inizializza la variabile copiaelenco al parametro vettore
	 * @param vettore Arrey di stringhe in ingresso
	 */
	protected void setCopiaelenco(String[] vettore) {
		this.copiaelenco = vettore;
	}

}
