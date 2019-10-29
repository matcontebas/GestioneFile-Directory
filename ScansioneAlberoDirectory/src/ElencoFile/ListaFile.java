package ElencoFile;

import java.awt.Cursor;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ListaFile extends Finestra{

	public ListaFile() {
		// TODO Auto-generated constructor stub
	}
/**
 * elencafile() (implementazione del metodo astratto di Finestra) provvede a lanciare la ricerca del path all'utente ed elenca nella text area i file 
 * trovati in ordine alfabetico. In particolare si occupa di lanciare sceglicartella() per definire il path sul quale cercare i file
 * dopodichè lancia il metodo getFileList il quale trova tutti i file nel path e li memorizza in un
 * Array di stringhe (filetrovati). In seguito si ordina la collezione in ordine alfabetico con il metodo
 * statico Arrays.sort. A questo punto l'elenco dei file viene inserito in uni stringbuffer per essere visualizzato
 * nella txtArea (insieme al conteggio dei file trovati
 */
	public void elencafile() {
	File dir=sceglicartella();
	//nomifile è un tipo StringBuffer e mi serve per memorizzare temporaneamente i nomi dei file
	//trovati per poi alla fine visualizzarli sulla txtArea della finestra.
	StringBuffer nomifile=new StringBuffer();
	StringBuffer frequenzefile=new StringBuffer();
	if (dir.getPath()!= null) {
		//Le prossime due istruzioni trasformano il cursore in una clessidra
		this.finestrastruttura.setCursor(new Cursor (Cursor.WAIT_CURSOR));
		this.txtArea.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		String [] filetrovati = getFileList(dir.getPath());
		//Prova oggetto Calcolofrequenze
		CalcoloFrequenze freq=new CalcoloFrequenze(filetrovati);
		HashMap<String, Integer> frequenze =freq.trovaoccorrenze();
		//Fine prova oggetto CalcoloFrequenze
		
		//La seguente istruzione ordina gli elementi dell'Array di stringhe in ordine alfabetico
		Arrays.sort(filetrovati);
		//il codice seguente serve per scrivere nella textArea della finestra i nomi di file ed il conteggio finale
		int conteggiofile=0;
		for (String i: filetrovati) {
			nomifile.append(i + "\n");
			//txtArea.setText(txtArea.getText()+i+"\n");
			conteggiofile++;
		}
		for (String i: frequenze.keySet()) {
			frequenzefile.append("nome file: "+ i +" ripetizioni: "+ frequenze.get(i)+"\n");
		}

		//Al termine aggiorno la txtArea per visualizzare i file trovati ed il conteggio finale
		txtArea.setText(nomifile.toString()+"\nNumero File trovati: "+ conteggiofile + "\n"+ "\n"+frequenzefile.toString());
		//rimetto i cursori zi cursori di default
		this.finestrastruttura.setCursor(new Cursor (Cursor.DEFAULT_CURSOR));
		this.txtArea.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}
/**
 * il metodo sceglicartella apre una finestra che permette di scegliere una cartella
 * @return restituisce una oggetto file che punta alla cartella scelta
 */
public File sceglicartella() {
	JFileChooser cercacartella=new JFileChooser();
	//l'istruzione seguente imposta il filtro sulle Directory e non visualizza i file.
	cercacartella.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	cercacartella.setDialogTitle("Cerca il percorso");
	int scelta= cercacartella.showOpenDialog(finestrastruttura);
	if (scelta==JFileChooser.APPROVE_OPTION) {
		//??????L'istruzione seguente non si riesce a visualizzare. perchè?
		txtArea.setText("La cartella scelta è: " + cercacartella.getSelectedFile().getPath()+"\n");
		System.out.print("La cartella scelta è: " + cercacartella.getSelectedFile().getPath()+"\n");
		return cercacartella.getSelectedFile();
	}else {
		JOptionPane.showMessageDialog(finestrastruttura, "Operazione annullata","Warning",JOptionPane.WARNING_MESSAGE);
		return null;
	}
}
/**
 * Il seguente metodo scorre l'albero delle cartelle a partire dal percorso di partenza
 * che viene fornito con la variabile parent e memorizza in files tutti i nomi dei file
 * che incontra nella cartella indicata da parent e nelle sottocartelle
 * @param parent: stringa che contiene il percorso su cui avviare la ricerca
 * @return restituisce un Array di stringhe con i nomi dei file.
 */
public String[] getFileList(String parent) {
	//Definisco un ArrayList di Stringhe in cui memorizzerò i nomi dei file che si trovano
	List<String> files = new ArrayList<String>();
	//Definisco uno stack di tipo File in cui farò il push delle sottodirectory
	Stack<File> stack = new Stack<File>();
	stack.push(new File(parent));
	while(!stack.isEmpty()) {
		File[] children = stack.pop().listFiles();
		if(children == null || children.length == 0)
			continue;
		for(File f: children) {
			if(f.isDirectory()) {
				stack.push(f);
			} else {
				files.add(f.getName());
			}
		}
	}
	String[] ret = new String[files.size()];
	files.toArray(ret);
	return ret;		
}
}
