package ElencoFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ListaFile extends Finestra{

	public ListaFile() {
		// TODO Auto-generated constructor stub
	}
public void elencafile() {
	File dir=sceglicartella();
	//nomifile � un tipo StringBuffer e mi serve per memorizzare temporaneamente i nomi dei file
	//trovati per poi alla fine visualizzarli sulla txtArea della finestra.
	StringBuffer nomifile=new StringBuffer();
	if (dir.getPath()!= null) {
		//JOptionPane.showMessageDialog(finestrastruttura, "La cartella scelta �: " + dir.getPath(), "Esito ricerca",JOptionPane.INFORMATION_MESSAGE);
		String [] filetrovati = getFileList(dir.getPath());
		//La seguente istruzione ordina gli elementi dell'Array di stringhe in ordine alfabetico
		Arrays.sort(filetrovati);
		int conteggiofile=0;
		for (String i: filetrovati) {
			nomifile.append(i + "\n");
			//txtArea.setText(txtArea.getText()+i+"\n");
			conteggiofile++;
		}
		//Al termine aggiorno la txtArea per visualizzare i file trovati ed il conteggio finale
		txtArea.setText(nomifile.toString()+"\nNumero File trovati: "+ conteggiofile);
	}
}
/**
 * il metodo sceglicartella apre una finestra che permette di scegliere una cartella
 * @return il metodo restituisce una oggetto file che punta alla cartella scelta
 */
public File sceglicartella() {
	JFileChooser cercacartella=new JFileChooser();
	//l'istruzione seguente imposta il filtro sulle Directory e non visualizza i file.
	cercacartella.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	cercacartella.setDialogTitle("Cerca il percorso");
	int scelta= cercacartella.showOpenDialog(finestrastruttura);
	if (scelta==JFileChooser.APPROVE_OPTION) {
		//??????L'istruzione seguente non si riesce a visualizzare. perch�?
		txtArea.setText("La cartella scelta �: " + cercacartella.getSelectedFile().getPath()+"\n");
		System.out.print("La cartella scelta �: " + cercacartella.getSelectedFile().getPath()+"\n");
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
	//Definisco un ArrayList di Stringhe in cui memorizzer� i nomi dei file che si trovano
	List<String> files = new ArrayList<String>();
	//Definisco uno stack di tipo File in cui far� il push delle sottodirectory
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