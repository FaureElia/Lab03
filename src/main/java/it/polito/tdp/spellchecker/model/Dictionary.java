package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {
	List <String> listaParole=new ArrayList<String>();
	
	public void loadDictionary(String language) {
		try {
			FileReader fr=new FileReader("src/main/resources/"+language+".txt");
			BufferedReader br=new BufferedReader(fr);
			String word;
			while ((word=br.readLine()) != null) {
				listaParole.add(word);	
			}
		}catch(IOException e) {
			System.out.println("errore nella lettura da file");
		}	
	}

	
	public List spellCheckTextLinear(String testoDaControllare){
		
		// creo lista parole input
		
		List<RichWord> listaParoleInput=new ArrayList<RichWord>();
		testoDaControllare=testoDaControllare.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		String[] vettoreParole=testoDaControllare.split(" ");
		for (String s:vettoreParole) {
			//setto inizialmente i valori a falso
			RichWord parola=new RichWord(s,false);
			listaParoleInput.add(parola);
		}
		//per ogni parola nella lista di parole input  modifico il vole confronto dell'oggetto
		
		for (RichWord parola : listaParoleInput) {
			if(this.listaParole.contains(parola.getParolaInput())) {
				parola.setConfronto(true);
			}
		}
		return listaParoleInput;
	}
	
public List spellCheckTextDicotomic(String testoDaControllare){
		
		// creo lista parole input
		
		List<RichWord> listaParoleInput=new ArrayList<RichWord>();
		testoDaControllare=testoDaControllare.replaceAll("[.,\\/#!?$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "");
		String[] vettoreParole=testoDaControllare.split(" ");
		for (String s:vettoreParole) {
			//setto inizialmente i valori a falso
			RichWord parola=new RichWord(s,false);
			listaParoleInput.add(parola);
		}
		//effettuo una ricera dicotomica
		
		
		for (RichWord parola : listaParoleInput) {
			int inf=0;
			int sup=this.listaParole.size();
			int indiceRicerca;
			while(inf!=sup) {
				indiceRicerca=(sup+inf)/2;
				int risultato=this.listaParole.get(indiceRicerca).compareTo(parola.getParolaInput());
				if (risultato>=0) {
					sup=indiceRicerca;
				}
				if (risultato<=0) {
					inf=indiceRicerca;
				}
				if (risultato==0){
					parola.setConfronto(true);
				}
			}
		}
		
		
		
		
		return listaParoleInput;}
	
	

}
