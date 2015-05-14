import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Calculator extends JPanel {
	CalculatorButton[] buttons;
	CalculatorScreen screen;
	Calculation calculation;
	
	public Calculator() {
		int iButtonWidth = 50;
		int iButtonHeight = 20;
		int iBorder = 5;
		
		JPanel screenPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		JPanel buttonPanelFirst = new JPanel();
		JPanel buttonPanelSecond = new JPanel();
		JPanel buttonPanelThird = new JPanel();
		
		this.buttons = new CalculatorButton[18];
		this.screen = new CalculatorScreen((iButtonWidth + iBorder) * 8, iButtonHeight + iBorder);
		this.calculation = new Calculation();
		
		this.buttons[0] = new CalculatorButton("0", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('0');
			}
			
		});
		
		this.buttons[1] = new CalculatorButton("1", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('1');
			}
			
		});
		
		this.buttons[2] = new CalculatorButton("2", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('2');
			}
			
		});
		
		this.buttons[3] = new CalculatorButton("3", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('3');
			}
			
		});
		
		this.buttons[4] = new CalculatorButton("4", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('4');
			}
			
		});
		
		this.buttons[5] = new CalculatorButton("5", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('5');
			}
			
		});
		
		this.buttons[6] = new CalculatorButton("6", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('6');
			}
			
		});
		
		this.buttons[7] = new CalculatorButton("7", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('7');
			}
			
		});
		
		this.buttons[8] = new CalculatorButton("8", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('8');
			}
			
		});
		
		this.buttons[9] = new CalculatorButton("9", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('9');
			}
			
		});
		
		this.buttons[10] = new CalculatorButton(".", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('.');
			}
		});
		
		this.buttons[11] = new CalculatorButton("+", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('+');
			}
		});
		
		this.buttons[12] = new CalculatorButton("-", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('-');
			}
		});
		
		this.buttons[13] = new CalculatorButton("*", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('*');
			}
		});
		
		this.buttons[14] = new CalculatorButton("/", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.appendLastCharacter('/');
			}
		});
		
		this.buttons[15] = new CalculatorButton("=", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				pair<Boolean, Float> result = calculation.calculateInfixExpress(screen.getContent());
				
				if(!result.first) {
					JOptionPane.showMessageDialog(null, "The expression is not valid!", "Notice", JOptionPane.ERROR_MESSAGE);
				} else {
					screen.setString(result.second.toString());
				}
			}
		});
		
		this.buttons[16] = new CalculatorButton("C", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.clearAll();
			}
		});
		
		this.buttons[17] = new CalculatorButton("S", iButtonWidth, iButtonHeight, new ButtonClickTask() {
			public void execute() {
				screen.clearLastCharacter();
			}
		});
		
		this.setLayout(new BorderLayout());
		this.setSize((iButtonWidth + iBorder) * 8, (iButtonHeight + iBorder) * 4);
		
		screenPanel.add(this.screen);
		
		buttonPanelFirst.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanelFirst.setSize((iButtonWidth + iBorder) * 8, iButtonHeight + iBorder);
		
		for(int i = 0; i < 8; i ++)
			buttonPanelFirst.add(this.buttons[i]);
		
		buttonPanelSecond.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanelSecond.setSize((iButtonWidth + iBorder) * 8, iButtonHeight + iBorder);
		
		for(int i = 8; i < 10; i ++)
			buttonPanelSecond.add(this.buttons[i]);
		
		buttonPanelThird.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanelThird.setSize((iButtonWidth + iBorder) * 8, iButtonHeight + iBorder);
		
		for(int i = 10; i < 18; i ++)
			buttonPanelThird.add(this.buttons[i]);
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.setSize((iButtonWidth + iBorder) * 8, (iButtonHeight + iBorder) * 3);
		
		buttonPanel.add(buttonPanelFirst);
		buttonPanel.add(buttonPanelSecond);
		buttonPanel.add(buttonPanelThird);
		
		this.add(screenPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		JFrame frame = new JFrame("BECalculator - Copyright © 2015 of BE Studio");
		
		frame.add(calculator);
		frame.setSize((50 + 5) * 8, (20 + 5) * 6);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
}
