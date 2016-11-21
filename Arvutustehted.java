import java.util.ArrayList;

public class Arvutustehted {
	ArrayList<String> tulemus= new ArrayList<String>(); //Faili kirjutamise ja ekraanile kuvamise jaoks.
	private int esimenearv;
	private int teinearv;
	private int vastus; 
	private int õigeteArv;
	private int valedeArv;

	public ArrayList<String> getTulemus() {
		return tulemus;
	}
	public int getÕigeteArv() {
		return õigeteArv;
	}
	public int getValedeArv() {
		return valedeArv;
	}


	public Arvutustehted(int õigeteArv, int valedeArv) { //Algkonstruktor tehte loomiseks ning kaasas kantakse õigete ja valede arvu.
		this.õigeteArv = õigeteArv;
		this.valedeArv = valedeArv;
		esimenearv = 0;
		teinearv = 0;
		vastus = 0;
	}

	public ArrayList<String> moodustaLiitmine(int min, int max){ //Liitmistehte moodustamine, kus antakse ette randomi vahemik.
		esimenearv = (int)Math.round(Math.random()*(max-min)+min);
		teinearv = (int)Math.round(Math.random()*(max-min)+min);
		vastus = esimenearv + teinearv;
		String tehe = esimenearv + " + " + teinearv+ " = ";
		tulemus.add(tehe);
		tulemus.add(Integer.toString(vastus));
		return tulemus;

	}
	public ArrayList<String> moodustaLahutamine(int min, int max){ //Lahutamistehte moodustamine, kus antakse ette randomi vahemik.
		esimenearv = (int)Math.round(Math.random()*(max-min)+min);
		teinearv = (int)Math.round(Math.random()*(max-min)+min);
		vastus = esimenearv - teinearv;
		String tehe = (esimenearv + " - " + teinearv + " = ");
		tulemus.add(tehe);
		tulemus.add(Integer.toString(vastus));
		return tulemus;
	}

	public ArrayList<String> moodustaKorrutamine(int min, int max) {//Korrutamistehte moodustamine, kus antakse ette randomi vahemik.
		esimenearv = (int)Math.round(Math.random()*(max-min)+min);
		teinearv = (int)Math.round(Math.random()*(max-min)+min);
		vastus = esimenearv * teinearv;
		String tehe = (esimenearv + " * " + teinearv + " = ");
		tulemus.add(tehe);
		tulemus.add(Integer.toString(vastus));
		return tulemus;
	}

	public ArrayList<String> moodustaJagamine(int min, int max) { //Jagamistehte moodustamine, kus antakse ette randomi vahemik.
		esimenearv = (int)Math.round(Math.random()*(max-min)+min);
		teinearv = (int)Math.round(Math.random()*(max-min)+min);
		if (teinearv == 0){ //Teine tegur ei saa võrduda nulliga.
			return moodustaJagamine(min, max);
		}
		else{
			int korrutis = esimenearv*teinearv;
			String tehe = korrutis + "/" + teinearv +" = ";
			vastus = esimenearv;
			tulemus.add(tehe);
			tulemus.add(Integer.toString(vastus));
			return tulemus;
		}

	}

	public boolean vastuseKontroll(int sisestus){ //Kasutaja sisestatud vastuse kontroll.
		if (vastus == sisestus){
			return true;
		}
		return false;
	}

	public void annaPunkt(boolean vastuseTõesus){ //Punktide arvestamine. 
		if (vastuseTõesus){ //Õige vastuse korral liidetakse 1 juurde muutujale õiged.
			õigeteArv++;
		}
		else {//Vale vastuse korral liidetakse 1 juurde muutujale valed.
			valedeArv++;
		}
	}
}