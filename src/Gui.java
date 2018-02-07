import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static java.lang.Integer.parseInt;

/**
 *
 */
public class Gui extends JFrame implements ActionListener {
    private JTextField tekstfieldPokeID, tekstfieldPokemonBestand, tekstfieldGekozenID, tekstfieldBijbehorendePokemon;
    private JLabel labelPokeID, labelPokemonBestand, labelGekozenID, labelBijbehorendePokemon, labelLengte, labelGewicht;
    private JButton buttonKies, buttonBrowse, buttonOpen;
    private JPanel panelLengteGewicht;
    JFileChooser fileChooser;
    String pokeLengte, pokeGewicht;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Gui frame = new Gui();
        frame.setSize(600, 500);
        frame.setTitle("Pokemeter");
        frame.createGUI();
        frame.setVisible(true);
    }

    /**
     * eigen qui gemaakt
     */
    public void createGUI() {
        this.setDefaultCloseOperation(3);
        Container window = this.getContentPane();
        window.setLayout(null);

        tekstfieldPokeID = new JTextField();
        tekstfieldPokeID.setBounds(350, 60, 100, 20);
        labelPokeID = new JLabel("Geef pokemon ID in hele getallen (1<=ID=>151):");
        labelPokeID.setBounds(25, 60, 300, 20);
        window.add(labelPokeID);
        window.add(tekstfieldPokeID);

        buttonKies = new JButton("Kies");
        buttonKies.setBounds(460, 60, 70, 20);
        buttonKies.addActionListener(this);
        window.add(buttonKies);

        tekstfieldPokemonBestand = new JTextField();
        tekstfieldPokemonBestand.setBounds(160, 30, 200, 20);
        labelPokemonBestand = new JLabel("Pokemon bestand:");
        labelPokemonBestand.setBounds(25, 30, 150, 20);
        window.add(labelPokemonBestand);
        window.add(tekstfieldPokemonBestand);

        buttonBrowse = new JButton("Browse");
        buttonBrowse.setBounds(370, 30, 90, 20);
        buttonBrowse.addActionListener(this);
        window.add(buttonBrowse);

        buttonOpen = new JButton("Open");
        buttonOpen.setBounds(470, 30, 90, 20);
        buttonOpen.addActionListener(this);
        window.add(buttonOpen);

        tekstfieldGekozenID = new JTextField();
        tekstfieldGekozenID.setBounds(120, 90, 100, 20);
        labelGekozenID = new JLabel("Gekozen ID:");
        labelGekozenID.setBounds(25, 90, 120, 20);
        window.add(labelGekozenID);
        window.add(tekstfieldGekozenID);

        tekstfieldBijbehorendePokemon = new JTextField();
        tekstfieldBijbehorendePokemon.setBounds(420, 90, 100, 20);
        labelBijbehorendePokemon = new JLabel("Bijbehorende pokemon:");
        labelBijbehorendePokemon.setBounds(250, 90, 150, 20);
        window.add(labelBijbehorendePokemon);
        window.add(tekstfieldBijbehorendePokemon);

        labelLengte = new JLabel("Lengte: ");
        labelLengte.setBounds(50, 180, 100, 20);
        window.add(labelLengte);

        labelGewicht = new JLabel("Gewicht: ");
        labelGewicht.setBounds(50, 300, 100, 20);
        window.add(labelGewicht);

        panelLengteGewicht = new JPanel();
        panelLengteGewicht.setBounds(25, 160, 520, 250);
        panelLengteGewicht.setBackground(Color.WHITE);
        window.add(panelLengteGewicht);
    }

    /**
     * voor een actie uit door op de button te klikken
     * @param event
     */
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == buttonKies) {
            tekstfieldPokeID.getText();
            tekstfieldGekozenID.setText(tekstfieldPokeID.getText());
            doorzoekBestand zoekId = new doorzoekBestand(tekstfieldPokemonBestand.getText(), tekstfieldPokeID.getText());
            tekstfieldBijbehorendePokemon.setText(zoekId.getPokeNaam());
            pokeLengte = zoekId.getPokeLengte();
            labelLengte.setText("Lengte: "+ pokeLengte);
            pokeGewicht = zoekId.getPokeGewicht();
            labelGewicht.setText("Gewicht: "+ pokeGewicht);
            Graphics paper = panelLengteGewicht.getGraphics();
            tekenGrafiek(paper);
        }

        if (event.getSource() == buttonOpen) {
            JOptionPane.showMessageDialog(null, "het bestand is geopend, voer een pokeon ID in");
            doorzoekBestand zoek = new doorzoekBestand(tekstfieldPokemonBestand.getText(), tekstfieldPokeID.getText());
        }

        int Path;
        File selectedFile;

        if (event.getSource() == buttonBrowse) {
            fileChooser = new JFileChooser();
            Path = fileChooser.showSaveDialog(this);
            if (Path == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                tekstfieldPokemonBestand.setText(selectedFile.getAbsolutePath());

            }
        }
    }

    /**
     * teken de rechthoeken met gewicht en lengte
     * @param paper
     */
    public void tekenGrafiek(Graphics paper){
        paper.setColor(Color.BLACK);
        paper.drawRect(30, 50, 450, 15);
        paper.setColor(Color.GREEN);
        paper.fillRect(31, 51, parseInt(pokeLengte), 14);

        paper.setColor(Color.BLACK);
        paper.drawRect(30, 170, 450, 15);
        paper.setColor(Color.BLUE);
        paper.fillRect(31, 171, parseInt(pokeGewicht), 14);
    }


}
