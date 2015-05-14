import javax.swing.*;

interface ScreenFunctions {
	public void clearLastCharacter();
	public void clearAll();
	public void appendLastCharacter(char character);
	public void appendLastString(String string);
	public void setString(String string);
	public String getContent();
}

public class CalculatorScreen extends JTextPane implements ScreenFunctions {
	public CalculatorScreen(int width, int height) {
		this.setText("");
		this.setEditable(false);
		this.setSize(width, height);
	}
	
	public void clearLastCharacter() {
		StringBuffer buffer = new StringBuffer(this.getText());
		
		buffer.deleteCharAt(buffer.length() - 1);
		
		this.setText(buffer.toString());
	}
	
	public void clearAll() {
		this.setText("");
	}
	
	public void appendLastCharacter(char character) {
		StringBuffer buffer = new StringBuffer(this.getText());
		
		buffer.append(character);
		
		this.setText(buffer.toString());
	}
	
	public void appendLastString(String string) {
		if(string == null)
			return;
		
		StringBuffer buffer = new StringBuffer(this.getText());
		
		buffer.append(string);
		
		this.setText(buffer.toString());
	}
	
	public void setString(String string) {
		this.setText(string == null ? "" : string);
	}
	
	public String getContent() {
		return new String(this.getText());
	}
}
