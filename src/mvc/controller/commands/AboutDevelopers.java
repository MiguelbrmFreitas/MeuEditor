package mvc.controller.commands;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mvc.controller.Command;
import mvc.model.Engine;
import mvc.view.View;

public class AboutDevelopers extends Command {

	public AboutDevelopers(Engine engine, View view) {
		super(engine, view);
	}

	@Override
	public void execute() {
		JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

        JPanel daniel = buildDeveloperInfoPanel("daniel");
        container.add(daniel);

        JPanel miguel = buildDeveloperInfoPanel("miguel");
        container.add(miguel);

        JOptionPane.showMessageDialog(null, container, "Autores do Programa", JOptionPane.PLAIN_MESSAGE);
	}

	private JPanel buildDeveloperInfoPanel(String developerName) {
		JPanel infoPanel = new JPanel();
	    infoPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
	    
	    infoPanel.add(new JLabel(new ImageIcon(developerName + ".jpg")));
	    JPanel developerInfo = new JPanel(new GridLayout(0, 1));
	    try {
	        BufferedReader br = new BufferedReader(new FileReader("daniel.txt"));
	        String line;
	        while ( (line = br.readLine()) != null )
	            developerInfo.add(new JLabel(line));
	        br.close();
	    } catch (IOException e) {
	        developerInfo.add(new JLabel("Informacao sobre o autor nao encontrada."));
	    }
	    infoPanel.add(developerInfo);
	    return infoPanel;
	}
}