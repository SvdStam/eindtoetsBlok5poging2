import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class doorzoekBestand  {
    BufferedReader infile;
    String[] file;
    String pokeNaam = "";
    String pokeLengte = "";
    String pokeGewicht = "";
    int pokeId1;

    /**
     * open en lees het bestand door en zet de pokemon naam, lengte en gewicht in een String
     * exeptions afvangen
     * @param bestandnaam word meegegeven uit de class VirusGui, tekst uit het tekstfieldBestand
     * @param pokeId word meegegeven uit de class Gui, tekst uit het tekstfieldPokeId
     */
    public doorzoekBestand(String bestandnaam, String pokeId){
        try {
            infile = new BufferedReader(new FileReader(bestandnaam));
            String line;

            while((line = infile.readLine()) != null) {
                file = line.split(";");
                if(pokeId.equals(file[0])){
                    pokeNaam += file[1];
                    pokeLengte += file[3];
                    pokeGewicht += file[4];
                }
            }
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("Bestand niet gevonden");
        } catch (IOException ioe) {
            System.out.println("Kan niet lezen in bestand");
        } catch (Exception e) {
            System.out.println("Onbekende fout: raadpleeg uw systeembeheerder");
        }
    }

    /**
     *
     * @return
     */
    public String getPokeNaam() {
        return pokeNaam;
    }

    public String getPokeLengte() {
        return pokeLengte;
    }

    public String getPokeGewicht() {
        return pokeGewicht;
    }
}
