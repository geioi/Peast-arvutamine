import java.io.Writer;
import java.util.ArrayList;

public class M�ngi extends Tase{ //Klass m�ngi on klassi tase alamklass.
	public M�ngi(int tase) {
		super(tase); //M�ngu loomisel kantakse kaasas taset.
	}

	public static void alustaM�ngu(Arvutustehted arvutus, int valitudTase){

		if (valitudTase == 1){ //M�ng algab esimeselt tasemelt.
			if (arvutus.get�igeteArv() < 3){ //Arvutustehted esimesel tasemel, kuni �igeid on v�hem kui kolm.
				esimeneTase(arvutus);
			}
			else if (arvutus.get�igeteArv() < 10){//Arvutustehted teisel tasemel, kuni �igeid on v�hem kui k�mme.
				teineTase(arvutus);
			}
			else {//Arvutustehted kolmandal tasemel alates k�mnendast �igest vastusest.
				kolmasTase(arvutus);
			}
		}
		else if (valitudTase == 2){ // M�ng algab teiselt tasemelt.
			if (arvutus.get�igeteArv() < 7){
				teineTase(arvutus);
			}
			else {
				kolmasTase(arvutus);
			}
		}
		else { //M�ng algab kolmandalt tasemelt.
			kolmasTase(arvutus);
		}

	}

	public static void vastamine(Arvutustehted tehe, ArrayList<String> teheJaVastus, String sisestus, Writer file) throws Exception{ 
//Vastuse kontroll.
		if (!tehe.vastuseKontroll(Integer.parseInt(sisestus))){ //Kui sisestus oli vale, siis kirjutab puhvrisse.
			file.append("Tehe: " + teheJaVastus.get(0) +teheJaVastus.get(1)+ ", " +" aga Teie vastasite: " + sisestus +";");
			//lisab puhvrisse teksti.
		}
		tehe.annaPunkt(tehe.vastuseKontroll(Integer.parseInt(sisestus))); //Punkti lisamine vastavalt sisestatud vastuse t�esusele.
	}
}