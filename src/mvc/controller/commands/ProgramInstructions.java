package mvc.controller.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import mvc.controller.Command;
import mvc.model.Engine;
import mvc.view.View;

public class ProgramInstructions extends Command {

	public ProgramInstructions(Engine engine, View view) {
		super(engine, view);
	}

	@Override
	public void execute() throws IOException {
        String fileName = "instrucoes.txt";
        BufferedReader br;
        br = new BufferedReader(new FileReader(fileName));
        JTextArea instrucoes = new JTextArea(40, 60);
        String line;
        while((line = br.readLine()) != null) {
            instrucoes.append(line + "\n");
        }
        br.close();
        instrucoes.setEditable(false);
        instrucoes.setFocusable(false);
        JScrollPane scrollBar = new JScrollPane(instrucoes);
        instrucoes.setCaretPosition(0);
        instrucoes.setWrapStyleWord(true);
        JOptionPane.showMessageDialog(null, scrollBar, "Instrucoes de Uso", JOptionPane.PLAIN_MESSAGE);
	}

}
