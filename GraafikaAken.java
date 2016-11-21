import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GraafikaAken extends Application {

	public String valedVastused(int ıiged, int valed) throws FileNotFoundException{ //Meetod, millega esitatakse tulemused ekraanile. Vajadusel failist.
		File valedvastused= new File("Valed Vastused.txt"); //Programmi poolt loodud faili m‰‰ramine, millest tulemusi loetakse. 
		Scanner tekst = new Scanner(valedvastused);
		String vale = "Te ei vastanud ¸helegi tehtele!";
		if (ıiged > 0 && valed == 0){ //Kontroll, kas m‰ngija on ¸ldse m‰nginud. 
			vale = "Olite tubli! Vastasite kıigile tehetele ıigesti.";}
		while (tekst.hasNextLine()){
			String rida = tekst.nextLine();
			vale = rida.replace(";", "\n");
		}
		tekst.close();
		return vale;
	}

	public void okVajutatud(Stage ekraan, Arvutustehted arvutus, StringBuilder sisestus, Writer file) { //Meetod, mis rakendub, kui vajutatakse OK nuppu vıi enter.
		try{//Failiga tekkivad probleemid p¸¸takse kinni.
			Integer.parseInt(sisestus.toString()); //Sisestus ei ole number, siis tehe j‰‰b samaks.
			M‰ngi.vastamine(arvutus, arvutus.getTulemus(), sisestus.toString(), file); //Sisestatud vastuse kontroll, kasutatakse klassi M‰ngi meetodit vastamine.
			Arvutustehted uusArvutus = new Arvutustehted(arvutus.get’igeteArv(), arvutus.getValedeArv()); //Kannab ¸le ıigete ja valede vastuste arvu.
			M‰ngi.alustaM‰ngu(uusArvutus, Tase.getTase()); //Moodustatakse uus arvutustehe, millele kasutaja saab vastata.
			ekraan(ekraan, uusArvutus, file); //Luuakse uus ekraan uue tehtega, millele kasutaja saab vastata. 
		}
		catch(NumberFormatException e){
			ekraan(ekraan,arvutus, file); //Sama tehe nii kaua, kuni sisestus on arv.
		}
		catch(Exception e){ //Kui faili ei leita, siis sellest teavitatakse kasutajat.
			System.out.println("Faili ei leitud!");
		};

	}

	public void kirjuta(TextField v‰li, int kursoriAsukoht, StringBuilder sisestus, String tekst){ //lisab etteantud asukohale sisestatud karakteri.
		sisestus.insert(v‰li.getCaretPosition(), tekst);
		v‰li.setText(sisestus.toString());
		v‰li.positionCaret(kursoriAsukoht+1); //m‰‰ran, kuhu kursor peab minema p‰rast sisestust, vastasel juhul saab see v‰‰rtuseks 0.
	}

	public void kustuta(TextField v‰li, int kursoriAsukoht, StringBuilder sisestus){ //kustutab antud kohalt karakteri.
		sisestus.deleteCharAt(v‰li.getCaretPosition()-1);
		v‰li.setText(sisestus.toString());
		v‰li.positionCaret(kursoriAsukoht-1);
	}

	public void ekraan(Stage ekraan, Arvutustehted arvutus, Writer file){ //Meetod, milles toimub pıhitegevus.

		StringBuilder sisestus = new StringBuilder(); //Kasutaja sisestuse hoidmiseks. 
		BorderPane Paan = new BorderPane(); //Ekraani paigutuse m‰‰ramiseks. 
		TextField tekstiv‰li = new TextField(); //Kasutaja sisendi kuvamine.

		tekstiv‰li.setPrefSize(100, 60); //Tekstiv‰lja suuruse m‰‰ramine. 
		tekstiv‰li.setFont(new Font("Arial", 20)); //Teksti suuruse ja stiili m‰‰ramine. 

		StackPane stack = new StackPane(); //Kuhja loomiseks. 
		Rectangle rec = new Rectangle(190, 100, Color.WHITE); //Nelinurk, millel peale pannakse arvutustehe, mis on tekst.
		Text tehe = new Text(arvutus.getTulemus().get(0));

		tehe.setFont(new Font("Arial", 30));
		stack.getChildren().addAll(rec, tehe); //Lisatakse kuhjale elemendid.

		HBox aluminePool = new HBox(); 
		aluminePool.setPadding(new Insets(10, 10, 10, 10));
		Paan.setCenter(aluminePool); //Horisontaalkasti lisame ekraani keskele.

		GridPane gridPane = new GridPane();
		gridPane.setVgap(10); 

		Label ıiged = new Label("’iged: "); 
		Label ıigedArv = new Label(Integer.toString(arvutus.get’igeteArv())); //’igete vastuste arvu kuvamine
		Label valed = new Label("Valed: ");
		Label valedArv = new Label(Integer.toString(arvutus.getValedeArv())); //Valede vastuste arvu kuvamine

		VBox ¸leminePool = new VBox(5);
		HBox ıigedJaValedArv = new HBox(30);
		HBox label’iged = new HBox(5);
		HBox labelValed = new HBox(5);
		HBox teheJaTekstiv‰li = new HBox(20);

		label’iged.getChildren().addAll(ıiged, ıigedArv);
		labelValed.getChildren().addAll(valed, valedArv);
		ıigedJaValedArv.getChildren().addAll(label’iged, labelValed);
		teheJaTekstiv‰li.getChildren().addAll(stack, tekstiv‰li);
		¸leminePool.getChildren().addAll(ıigedJaValedArv, teheJaTekstiv‰li);

		teheJaTekstiv‰li.setAlignment(Pos.CENTER_RIGHT);
		ıigedJaValedArv.setAlignment(Pos.CENTER);
		label’iged.setAlignment(Pos.CENTER_LEFT);
		labelValed.setAlignment(Pos.TOP_RIGHT);

		Paan.setTop(¸leminePool); //Elementide lisamine ekraanile sobivas paigutuses. 

		HBox x1 = new HBox();
		x1.setSpacing(10);
		HBox x2 = new HBox();
		x2.setSpacing(10);
		HBox x3 = new HBox();
		x3.setSpacing(10);
		HBox x4 = new HBox();
		x4.setSpacing(10);
		HBox x6 = new HBox();
		x6.setSpacing(10);
		x6.setPadding(new Insets(20,20,20,20));

		gridPane.add(x1,0,1);
		gridPane.add(x2,0,2);
		gridPane.add(x3,0,3);
		gridPane.add(x4,0,4);
		aluminePool.getChildren().add(gridPane);
		aluminePool.setAlignment(Pos.CENTER);

		for (int i = 1; i < 10; i++){
			int number = i;

			Button n1 = new Button(new Integer(i).toString());
			n1.setFocusTraversable(false);
			n1.setTextFill(Color.BLUE);
			n1.setPrefSize(60,30);
			if (i < 4) {
				x3.getChildren().add(n1);
			}
			else if (i < 7) {
				x2.getChildren().add(n1);
			}
			else {
				x1.getChildren().add(n1);
			}
			n1.setOnAction(event -> kirjuta(tekstiv‰li, tekstiv‰li.getCaretPosition(), sisestus, String.valueOf(number)));
		} //Numbri nuppude loomine ja ekraanile paigutamine.

		Button miinus = new Button("-");
		miinus.setTextFill(Color.RED);
		miinus.setPrefSize(60,30);
		miinus.setFocusTraversable(false);

		x2.getChildren().add(miinus);
		Button kustuta = new Button ("C");
		kustuta.setTextFill(Color.RED);
		kustuta.setPrefSize(60,30);
		kustuta.setFocusTraversable(false);

		Button nNull = new Button ("0");
		nNull.setTextFill(Color.BLUE);
		nNull.setPrefSize(60,30);
		nNull.setFocusTraversable(false);

		Button kustutaKıik = new Button ("CE");
		kustutaKıik.setTextFill(Color.RED);
		kustutaKıik.setPrefSize(60,30);
		kustutaKıik.setFocusTraversable(false);

		Button ok = new Button ("OK");
		ok.setTextFill(Color.GREEN);
		ok.setPrefSize(60,30);

		x1.getChildren().add(ok);
		x4.getChildren().addAll(kustuta, nNull, kustutaKıik);

		miinus.setOnAction(event ->{ //M‰‰ramine, mis juhtub, kui vajutatakse miinus nuppu.
			if (sisestus.length() < 1){
				kirjuta(tekstiv‰li, tekstiv‰li.getCaretPosition(), sisestus, "-");
			}
		});
		kustuta.setOnAction(event -> { //M‰‰ramine, mis juhtub, kui vajutatakse nuppu C.
			if (sisestus.length() > 0){
				kustuta(tekstiv‰li, tekstiv‰li.getCaretPosition(), sisestus);
			}
		});

		nNull.setOnAction(event -> kirjuta(tekstiv‰li, tekstiv‰li.getCaretPosition(), sisestus, "0")); //M‰‰ramine, mis juhtub, kui vajutatakse nuppu 0.

		kustutaKıik.setOnAction(event -> { //M‰‰ramine, mis juhtub, kui vajutatakse nuppu CE.
			sisestus.setLength(0); 
			tekstiv‰li.setText(sisestus.toString());
		});

		ok.setOnAction(event -> okVajutatud(ekraan, arvutus, sisestus, file)); //OK vajutamisel rakendatakse eelnevalt defineeritud meetodit. 

		tekstiv‰li.setOnKeyPressed(event ->{ //Klaviatuuri klahvide vajutamise tuvastamine.
			if (event.getCode().equals(KeyCode.ENTER)){ //Kui vajutatakse enter klahvi.
				okVajutatud(ekraan, arvutus, sisestus, file); //Rakendatakse OK nupu meetodit.
			}
			else if (event.getCode().equals(KeyCode.BACK_SPACE)){ //Kui vajutatakse kustustamise klahvi.
				if (sisestus.length() > 0){
					sisestus.deleteCharAt(tekstiv‰li.getCaretPosition()-1);
					//Sisestusest kustutatakse element vastavalt kursori asukohale.
				}
			}
			else if (event.getCode().equals(KeyCode.DELETE)){ //Kui vajutatakse klahvi delete.
				if (sisestus.length() > 0 && tekstiv‰li.getCaretPosition() != sisestus.length()){ 
					sisestus.deleteCharAt(tekstiv‰li.getCaretPosition());
					//Sisestusest kustutatakse element vastavalt kursori asukohale.
				}
			}
			else{
				sisestus.insert(tekstiv‰li.getCaretPosition(),event.getText()); //Vastasel juhul lisatakse sisestus StringBuilderisse.
				//Sisestus m‰‰ratakse vastavalt kursori asukohale. 
			}
		});

		Button lıpeta = new Button("Lıpeta");
		lıpeta.setPrefSize(60,30);
		x6.getChildren().add(lıpeta);
		x6.setAlignment(Pos.TOP_CENTER);
		Paan.setBottom(x6);

		lıpeta.setOnAction(event -> { //M‰‰ratakse, mis juhtub, kui vajutatakse nuppu lıpeta.
			Stage vastus = new Stage(); //Luuakse uus lava.
			Label kuvaValed = new Label();
			Button lıpetaP‰riselt = new Button("Lıpeta");
			Button uuesti = new Button("Alusta uuesti");
			try{
				file.close(); //t¸hjendame puhvri faili ja sulgeme selle.
				kuvaValed.setText(valedVastused(arvutus.get’igeteArv(), arvutus.getValedeArv()));//Rakendatakse eelnevalt defineeritud meetodit.
				//Valede tehete ja vastuste kuvamiseks. 
			}
			catch(Exception e){
				System.out.println("Faili ei leitud!");
			}
			lıpetaP‰riselt.setOnAction(event1 -> Platform.exit());

			uuesti.setOnAction(event12-> { //Nupp uuesti vıimaldab m‰ngu taasalustada. 
				vastus.hide();
				ekraan.setResizable(true);
				try{
					start(ekraan);
				}catch(Exception e){
					System.out.println("Failiga tekkis mingisugune probleem");
				}
			});
			FlowPane pane = new FlowPane(10,10);
			pane.setAlignment(Pos.CENTER);
			pane.getChildren().addAll(lıpetaP‰riselt, uuesti);

			VBox vBox = new VBox(10);
			vBox.setAlignment(Pos.CENTER);
			vBox.getChildren().addAll(kuvaValed, pane);
			ScrollPane scroll = new ScrollPane(vBox);

			scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED); //Vastavalt ekraani suuruse muutmisele tekivad vajadusel kerimisribad.
			scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
			Scene stseen2 = new Scene(scroll);
			vastus.setScene(stseen2);
			vastus.show();
			ekraan.hide();

		});


		Scene stseen1 = new Scene(Paan,  300, 380, Color.SNOW);  // luuakse stseen  
		ekraan.setTitle("Peast arvutamine");  // lava tiitelribale pannakse tekst
		ekraan.setScene(stseen1);  // lavale lisatakse stseen
		ekraan.setResizable(false); //M‰ngu ekraani suurust ei saa muuta.
		tekstiv‰li.requestFocus(); //Kursor l‰heb automaatselt sisestuse v‰ljale. 
		ekraan.show();  // lava tehakse n‰htavaks

	}

	@Override
	public void start(Stage peaLava) throws Exception{ //Alustamise ekraan.
		Writer kirjutaja = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Valed vastused.txt"), "utf-8"));
		//tekitame uue faili, millele hakkame puhvrisse teksti lisama.
		
		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED); //Vastavalt ekraani suuruse muutmisele tekivad vajadusel kerimisribad.
		scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		VBox kıikNupud = new VBox(10);
		HBox tasemeNupud = new HBox(10);
		Label m‰nguıpetus = new Label();
		m‰nguıpetus.setText("K‰esolev programm on mıeldud peast arvutamise treenimiseks.\n"
				+ "Programm esitab tehte, millele peate vastama. \n"
				+ "Vastamiseks saate kasutada nii numbrinuppe kui ka klaviatuuri. \n"
				+ "M‰ngul on kolm raskusastet. M‰ngu alustades saate valida endale sobiva\n"
				+ "raskusastme. M‰ngus muutuvad raskusastmed vastavalt ıigete vastuste arvule. \n"
				+ "M‰ngu lıppedes n‰itab programm Teile tehteid ja vastusied, millele vastasite valesti. \n"
				+ "M‰ngu lıpetamiseks vajutage nuppu \"Lıpeta\".\n"
				+ "M‰ngu alustamiseks valige tase."); //M‰ngu ıpetus. 
		m‰nguıpetus.setTextAlignment(TextAlignment.CENTER);
		Button esimeneTase = new Button("Tase 1");
		Button teineTase = new Button ("Tase 2");
		Button kolmasTase = new Button ("Tase 3");
		Button lıpp = new Button("Lıpeta");
		tasemeNupud.getChildren().addAll(esimeneTase, teineTase, kolmasTase);
		tasemeNupud.setAlignment(Pos.CENTER);

		kıikNupud.getChildren().addAll(m‰nguıpetus, tasemeNupud, lıpp);
		kıikNupud.setAlignment(Pos.CENTER);
		scroll.setContent(kıikNupud);

		esimeneTase.setOnAction(event -> { //Tegevus, mis juhtub, kui valitakse tase 1.
			Arvutustehted arvutus = new Arvutustehted(0,0); 
			Tase.setTase(1); //M‰‰ratakse tase 1.
			peaLava.hide();
			M‰ngi.alustaM‰ngu(arvutus, Tase.getTase()); 
			ekraan(peaLava, arvutus, kirjutaja); //Alustatakse m‰ngu, faili kantakse kaasas. 

		});
		teineTase.setOnAction(event -> {
			Arvutustehted arvutus = new Arvutustehted(0,0);
			Tase.setTase(2); //M‰‰ratakse tase 2.
			peaLava.hide();
			M‰ngi.alustaM‰ngu(arvutus, Tase.getTase());
			ekraan(peaLava, arvutus, kirjutaja);
		});
		kolmasTase.setOnAction(event -> {
			Arvutustehted arvutus = new Arvutustehted(0,0);
			Tase.setTase(3); //M‰‰ratakse tase 3.
			peaLava.hide();
			M‰ngi.alustaM‰ngu(arvutus, Tase.getTase());
			ekraan(peaLava, arvutus, kirjutaja);
		});
		lıpp.setOnAction(event -> Platform.exit());

		Scene stseenA = new Scene(scroll);
		peaLava.setTitle("Peast arvutamine");  // lava tiitelribale pannakse tekst
		peaLava.setScene(stseenA);
		peaLava.show();  // lava tehakse n‰htavaks

	}
	public static void main(String[] args) {
		launch(args);
	}
}