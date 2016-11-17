import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
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
	public String valedVastused(int õiged, int valed) throws FileNotFoundException{ //Meetod, millega esitatakse tulemused ekraanile. Vajadusel failist.
		File valedvastused= new File("testFile1.txt"); //Programmi poolt loodud faili määramine, millest tulemusi loetakse. 
		Scanner tekst = new Scanner(valedvastused);
		String vale="Te ei vastanud ühelegi tehtele!";
		if (õiged>0 && valed==0){ //Kontroll, kas mängija on üldse mänginud. 
			vale="Olite tubli! Vastasite kõigile tehetele õigesti.";}
		while (tekst.hasNextLine()){
			String rida = tekst.nextLine();
			vale=rida.replace(";", "\n");
		}
		tekst.close();
		return vale;
	}
	public void okVajutatud(Stage ekraan, Arvutustehted arvutus, StringBuilder sisestus, File file) { //Meetod, mis rakendub, kui vajutatakse OK nuppu või enter.
		try{//Failiga tekkivad probleemid püütakse kinni.
			Integer.parseInt(sisestus.toString()); //Sisestus ei ole number, siis tehe jääb samaks.
			Mängi.vastamine(arvutus, arvutus.getTulemus(), sisestus.toString(), file); //Sisestatud vastuse kontroll, kasutatakse klassi Mängi meetodit vastamine.
			Arvutustehted uusArvutus = new Arvutustehted(arvutus.getÕigeteArv(), arvutus.getValedeArv()); //Kannab üle õigete ja valede vastuste arvu.
			Mängi.alustaMängu(uusArvutus, Tase.getTase(), sisestus.toString()); //Moodustatakse uus arvutustehe, millele kasutaja saab vastata.
			ekraan(ekraan, uusArvutus, file); //Luuakse uus ekraan uue tehtega, millele kasutaja saab vastata. 
		}
		catch(NumberFormatException ev){
			ekraan(ekraan,arvutus, file); //Sama tehe nii kaua, kuni sisestus on arv.
		}
		catch(Exception event){ //Kui faili ei leita, siis sellest teavitatakse kasutajat.
			System.out.println("Faili ei leitud!");
		};

	}
	public void ekraan (Stage ekraan, Arvutustehted arvutus, File file){ //Meetod, milles toimub põhitegevus.

		StringBuilder sisestus = new StringBuilder(); //Kasutaja sisestuse hoidmiseks. 
		BorderPane Paan = new BorderPane(); //Ekraani paigutuse määramiseks. 
		TextField tekstiväli = new TextField(); //Kasutaja sisendi kuvamine.

		tekstiväli.setPrefSize(100, 60); //Tekstivälja suuruse määramine. 
		tekstiväli.setFont(new Font("Arial", 20)); //Teksti suuruse ja stiili määramine. 

		StackPane stack = new StackPane(); //Kuhja loomiseks. 
		Rectangle rec = new Rectangle(190, 100, Color.WHITE); //Nelinurk, millel peale pannakse arvutustehe, mis on tekst.
		Text tehe = new Text(arvutus.getTulemus().get(0));

		tehe.setFont(new Font("Arial", 30));
		stack.getChildren().addAll(rec, tehe); //Lisatakse kuhjale elemendid.

		HBox all= new HBox(); 
		all.setPadding(new Insets(10, 10, 10, 10));
		Paan.setCenter(all); //Horisontaalkasti lisame ekraani keskele.

		GridPane gridPane = new GridPane();
		gridPane.setVgap(10); 

		Label õiged = new Label("Õiged: "); 
		Label õigedArv = new Label("0"); //Õigete vastuste arvu kuvamine
		Label valed = new Label("Valed: ");
		Label valedArv = new Label("0"); //Valede vastuste arvu kuvamine
		valedArv.setText(Integer.toString(arvutus.getValedeArv())); //Õigete ja valede vastuste arvu muutmine vastavalt.
		õigedArv.setText(Integer.toString(arvutus.getÕigeteArv()));

		VBox ülemine = new VBox(5);
		HBox kaks = new HBox(30);
		HBox kaks_alumine = new HBox(30);
		HBox ülemine_ülemine_vasak = new HBox(5);
		HBox ülemine_ülemine_parem = new HBox(5);
		HBox ülemine_alumine_vasak = new HBox(20);
		HBox ülemine_alumine_parem = new HBox(20);

		kaks.getChildren().addAll(ülemine_ülemine_vasak, ülemine_ülemine_parem);
		kaks_alumine.getChildren().addAll(ülemine_alumine_vasak, ülemine_alumine_parem);
		ülemine.getChildren().addAll(kaks, kaks_alumine);
		ülemine_ülemine_vasak.getChildren().addAll(õiged, õigedArv);
		ülemine_ülemine_parem.getChildren().addAll(valed, valedArv);
		ülemine_alumine_parem.getChildren().add(tekstiväli);
		ülemine_alumine_vasak.getChildren().add(stack);
		ülemine_alumine_vasak.setAlignment(Pos.CENTER_LEFT);
		ülemine_alumine_parem.setAlignment(Pos.CENTER_RIGHT);
		kaks.setAlignment(Pos.CENTER);
		kaks_alumine.setAlignment(Pos.CENTER);
		ülemine_ülemine_vasak.setAlignment(Pos.CENTER_LEFT);
		ülemine_ülemine_parem.setAlignment(Pos.TOP_RIGHT);
		Paan.setTop(ülemine); //Elementide lisamine ekraanile sobivas paigutuses. 

		HBox x1=new HBox();
		x1.setSpacing(10);
		HBox x2=new HBox();
		x2.setSpacing(10);
		HBox x3=new HBox();
		x3.setSpacing(10);
		HBox x4=new HBox();
		x4.setSpacing(10);
		HBox x6 = new HBox();
		x6.setSpacing(10);
		x6.setPadding(new Insets(20,20,20,20));

		gridPane.add(x1,0,1);
		gridPane.add(x2,0,2);
		gridPane.add(x3,0,3);
		gridPane.add(x4,0,4);
		all.getChildren().add(gridPane);
		all.setAlignment(Pos.CENTER);

		for (int i=1; i<10; i++){
			Button n1=new Button (new Integer(i).toString());
			n1.setTextFill(Color.BLUE);
			n1.setPrefSize(60,30);
			if (i<4){
				x3.getChildren().add(n1);
				int number = i;
				n1.setOnAction(event -> {
					sisestus.append(number);
					tekstiväli.setText(sisestus.toString());
					tekstiväli.requestFocus();
					tekstiväli.selectEnd();
				});

			}
			else if (i<7){
				x2.getChildren().add(n1);
				int number = i;
				n1.setOnAction(event -> {
					sisestus.append(number);
					tekstiväli.setText(sisestus.toString());
					tekstiväli.requestFocus();
					tekstiväli.selectEnd();
				});
			}
			else{
				x1.getChildren().add(n1);
				int number = i;
				n1.setOnAction(event -> {
					sisestus.append(number);
					tekstiväli.setText(sisestus.toString());
					tekstiväli.requestFocus();
					tekstiväli.selectEnd();
				});
			}
		} //Numbri nuppude loomine ja ekraanile paigutamine.

		Button miinus= new Button("-");
		miinus.setTextFill(Color.RED);
		miinus.setPrefSize(60,30);
		x2.getChildren().add(miinus);
		Button kustuta=new Button ("C");
		kustuta.setTextFill(Color.RED);
		kustuta.setPrefSize(60,30);
		Button nNull=new Button ("0");
		nNull.setTextFill(Color.BLUE);
		nNull.setPrefSize(60,30);
		Button kustutaKõik=new Button ("CE");
		kustutaKõik.setTextFill(Color.RED);
		kustutaKõik.setPrefSize(60,30);
		x4.getChildren().addAll(kustuta,nNull,kustutaKõik);

		miinus.setOnAction(event ->{ //Määramine, mis juhtub, kui vajutatakse miinus nuppu.
			if (sisestus.length() < 1){
				sisestus.append("-");
				tekstiväli.setText(sisestus.toString());
				tekstiväli.requestFocus();
				tekstiväli.selectEnd();
			}
		});
		kustuta.setOnAction(event -> { //Määramine, mis juhtub, kui vajutatakse nuppu C.
			if (sisestus.length() > 0){
				sisestus.deleteCharAt(sisestus.length()-1);
				tekstiväli.setText(sisestus.toString());
				tekstiväli.requestFocus();
				tekstiväli.selectEnd();
			}
			else{
				tekstiväli.requestFocus(); //Fookuse tagasi seadmine.
			}

		});

		nNull.setOnAction(event -> { //Määramine, mis juhtub, kui vajutatakse nuppu 0.
			sisestus.append("0");
			tekstiväli.setText(sisestus.toString());
			tekstiväli.requestFocus();
			tekstiväli.selectEnd();
		});

		kustutaKõik.setOnAction(event -> { //Määramine, mis juhtub, kui vajutatakse nuppu CE.
			sisestus.setLength(0); 
			tekstiväli.setText(sisestus.toString());
			tekstiväli.requestFocus();
			tekstiväli.selectEnd();
		});

		Button ok = new Button ("OK");
		ok.setTextFill(Color.GREEN);
		ok.setPrefSize(60,30);
		x1.getChildren().add(ok);

		ok.setOnAction(event-> { //Määratakse, mis juhtub, kui vajutatakse OK nuppu.
			okVajutatud(ekraan, arvutus, sisestus, file); //Rakendatakse eelnevalt defineeritud meetodit. 

		});
		tekstiväli.setOnKeyPressed(event ->{ //Klaviatuuri klahvide vajutamise tuvastamine.

			if (event.getCode().equals(KeyCode.ENTER)){ //Kui vajutatakse enter klahvi.
				okVajutatud(ekraan, arvutus, sisestus, file); //Rakendatakse OK nupu meetodit.

			}
			else if (event.getCode().equals(KeyCode.BACK_SPACE)){ //Kui vajutatakse kustustamise klahvi.
				if (sisestus.length() > 0){ 
					kustuta.arm(); //Rakendatakse nupu C omadusi.
					sisestus.deleteCharAt(tekstiväli.getCaretPosition()-1);
					//Sisestusest kustutatakse element vastavalt kursori asukohale.
				}
			}
			else if (event.getCode().equals(KeyCode.DELETE)){ //Kui vajutatakse klahvi delete.
				if (sisestus.length() > 0){ 
					kustuta.arm(); //Rakendatakse nupu C omadusi.
					sisestus.deleteCharAt(tekstiväli.getCaretPosition());
					//Sisestusest kustutatakse element vastavalt kursori asukohale.
				}
			}
			else{
				sisestus.insert(tekstiväli.getCaretPosition(),event.getText()); //Vastasel juhul lisatakse sisestus StringBuilderisse.
				//Sisestus määratakse vastavalt kursori asukohale. 
			}
		});

		Button lõpeta = new Button("Lõpeta");
		lõpeta.setPrefSize(60,30);
		x6.getChildren().add(lõpeta);
		x6.setAlignment(Pos.TOP_CENTER);
		Paan.setBottom(x6);

		lõpeta.setOnAction(event -> { //Määratakse, mis juhtub, kui vajutatakse nuppu lõpeta.
			Stage vastus = new Stage(); //Luuakse uus lava.
			Label label = new Label();
			Button lõpetaPäriselt = new Button("Lõpeta");
			Button uuesti = new Button("Alusta uuesti");
			try{
				label.setText(valedVastused(arvutus.õigeteArv, arvutus.valedeArv)); //Rakendatakse eelnevalt defineeritud meetodit.
				//Valede tehete ja vastuste kuvamiseks. 
			}
			catch(Exception ee){
				System.out.println("Faili ei leitud!");
			}
			lõpetaPäriselt.setOnAction( event1->Platform.exit());

			uuesti.setOnAction(event12-> { //Nupp uuesti võimaldab mängu taasalustada. 
				vastus.hide();
				ekraan.setResizable(true);
				start(ekraan);
			});
			FlowPane pane = new FlowPane(10,10);
			pane.setAlignment(Pos.CENTER);
			pane.getChildren().addAll(lõpetaPäriselt, uuesti);

			VBox vBox = new VBox(10);
			vBox.setAlignment(Pos.CENTER);
			vBox.getChildren().addAll(label, pane);
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
		ekraan.setResizable(false); //Mängu ekraani suurust ei saa muuta.
		tekstiväli.requestFocus(); //Kursor läheb automaatsel sisestuse väljale. 
		ekraan.show();  // lava tehakse nähtavaks

	}

	@Override
	public void start(Stage peaLava) { //Alustamise ekraan.
		File file = new File("testFile1.txt"); 
		//Kontrollib, kas fail oli eelnevalt olemas, kui oli, siis see tühjendatakse, vastasel juhul luuakse uus.
		try {
			if (!file.createNewFile() && file.length() != 0){
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.setLength(0);
				raf.close();
			}
		} catch (Exception e) {
			System.out.println("Faili ei leitud või ei olnud võimalik faili kirjutada!");
		} 

		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollBarPolicy.AS_NEEDED); //Vastavalt ekraani suuruse muutmisele tekivad vajadusel kerimisribad.
		scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		StringBuilder nimi =new  StringBuilder();
		VBox kõikNupud=new VBox(10);
		HBox tasemeNupud=new HBox(10);
		Label label = new Label();
		label.setText("Käesolev programm on mõeldud peast arvutamise treenimiseks.\n"
				+ "Programm esitab tehte, millele peate vastama. \n"
				+ "Vastamiseks saate kasutada nii numbrinuppe kui ka klaviatuuri. \n"
				+ "Mängul on kolm raskusastet. Mängu alustades saate valida endale sobiva\n"
				+ "raskusastme. Mängus muutuvad raskusastmed vastavalt õigete vastuste arvule. \n"
				+ "Mängu lõppedes näitab programm Teile tehteid ja vastusied, millele vastasite valesti. \n"
				+ "Mängu lõpetamiseks vajutage nuppu \"Lõpeta\".\n"
				+ "Mängu alustamiseks valige tase."); //Mängu õpetus. 
		label.setTextAlignment(TextAlignment.CENTER);
		Button üks = new Button("Tase 1");
		Button kaks1 = new Button ("Tase 2");
		Button kolm = new Button ("Tase 3");
		Button lõpp = new Button("Lõpeta");
		tasemeNupud.getChildren().addAll(üks, kaks1, kolm);
		tasemeNupud.setAlignment(Pos.CENTER);

		kõikNupud.getChildren().addAll(label, tasemeNupud, lõpp);
		kõikNupud.setAlignment(Pos.CENTER);
		scroll.setContent(kõikNupud);

		üks.setOnAction(event -> { //Tegevus, mis juhtub, kui valitakse tase 1.
			Arvutustehted arvutus = new Arvutustehted(0,0); 
			Tase.setTase(1); //Määratakse tase 1.
			peaLava.hide();
			Mängi.alustaMängu(arvutus, Tase.getTase(), nimi.toString()); 
			ekraan(peaLava, arvutus, file); //Alustatakse mängu, faili kantakse kaasas. 

		});
		kaks1.setOnAction(event -> {
			Arvutustehted arvutus = new Arvutustehted(0,0);
			Tase.setTase(2); //Määratakse tase 2.
			peaLava.hide();
			Mängi.alustaMängu(arvutus, Tase.getTase(), nimi.toString());
			ekraan(peaLava, arvutus, file);
		});
		kolm.setOnAction(event -> {
			Arvutustehted arvutus = new Arvutustehted(0,0);
			Tase.setTase(3); //Määratakse tase 3.
			peaLava.hide();
			Mängi.alustaMängu(arvutus, Tase.getTase(), nimi.toString());
			ekraan(peaLava, arvutus, file);
		});
		lõpp.setOnAction(event -> {Platform.exit();

		});

		Scene stseenA=new Scene(scroll);
		peaLava.setTitle("Peast arvutamine");  // lava tiitelribale pannakse tekst
		peaLava.setScene(stseenA);
		peaLava.show();  // lava tehakse nähtavaks

	}
	public static void main(String[] args) {
		launch(args);
	}
}