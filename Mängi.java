import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class M�ngi extends Tase{ //Klass m�ngi on klassi tase alamklass.
	public M�ngi(int tase) {
		super(tase); //M�ngu loomisel kantakse kaasas taset.
	}

	public static void alustaM�ngu(Arvutustehted arvutus, int valitudTase, String sisestus){

		if (valitudTase == 1){ //M�ng algab esimeselt tasemelt.

			if (arvutus.get�igeteArv() < 3){ //Arvutustehted esimesel tasemel, kuni �igeid on v�hem kui kolm.
				esimeneTase(arvutus,sisestus);			
			}

			else if (arvutus.get�igeteArv() < 10){//Arvutustehted teisel tasemel, kuni �igeid on v�hem kui k�mme.
				teineTase(arvutus,sisestus);
			}

			else if (arvutus.get�igeteArv() >= 10){//Arvutustehted kolmandal tasemel alates k�mnendast �igest vastusest.
				kolmasTase(arvutus,sisestus);
			}
		}

		else if (valitudTase == 2){ // M�ng algab teiselt tasemelt.
			if (arvutus.get�igeteArv() < 7){
				teineTase(arvutus,sisestus);
			}

			else if (arvutus.get�igeteArv() >= 7){
				kolmasTase(arvutus,sisestus);
			}

		}

		else if (valitudTase == 3){ //M�ng algab kolmandalt tasemelt.
			kolmasTase(arvutus,sisestus);
		}

	}

	public static void kirjutaFaili(Arvutustehted tehe, ArrayList<String> teheJaVastus, String sisestus, File fail) throws Exception{
		FileWriter writer = new FileWriter(fail, true); //Avab faili, kuhu kirjutatakse, true lubab faili lisada meetodiga append. 
		writer.append("Tehe: " + teheJaVastus.get(0) +teheJaVastus.get(1)+ ", " +" aga Teie vastasite: " + sisestus +";");
		writer.close();
	}

	public static void vastamine(Arvutustehted tehe, ArrayList<String> teheJaVastus, String sisestus, File file) throws Exception{ 
//Vastuse kontroll.
		if (!tehe.vastuseKontroll(Integer.parseInt(sisestus))){ //Kui sisestus oli vale, siis kirjutab faili.
			kirjutaFaili(tehe, teheJaVastus, sisestus, file);
		}
		tehe.annaPunkt(tehe.vastuseKontroll(Integer.parseInt(sisestus))); //Punkti lisamine vastavalt sisestatud vastuse t�esusele.

		alustaM�ngu(new Arvutustehted(tehe.get�igeteArv(), tehe.getValedeArv()), getTase(), sisestus); //Esitatakse uus tehe.
	}
}