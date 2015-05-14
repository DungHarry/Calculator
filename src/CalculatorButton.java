import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

interface CalculatorLabel {
	public String getLabel();
}

interface ButtonClickTask {
	void execute();
}

public class CalculatorButton extends JButton implements CalculatorLabel {
	private String label;
	
	
	public CalculatorButton(String label, int width, int height, ButtonClickTask task) {
		this.label = new String(label);
		
		this.setText(label);
		this.setSize(width, height);
		
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				task.execute();
			}
		});
	}
	
	public String getLabel() {
		return this.label;
	}
}
