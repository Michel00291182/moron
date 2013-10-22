/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */

// package moro;

/**
 * Title:        The MObile RObot Simulation Environment
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:      Universit di Bergamo
 * @author Davide Brugali
 * @version 1.0
 */

import javax.swing.UIManager;

public class TestApplication {
	Environment environment = null;
	Controller controller = null;
	// This window shows the entire environment and the robot moving in it.
	SimulWindow simulWindow = null;
	// This window shows what the robot discovers
	ControlWindow controlWindow = null;

	public TestApplication() {
		// JB: This application does NOT use the Model-View-Controller standard :-(.
		// This means you will have to figure out how the different classes interact for yourself.
		// Invest some time. Take a look at the book and draw a diagram.
		environment = new Environment();
		simulWindow = new SimulWindow(environment);
		simulWindow.validate();
		simulWindow.setVisible(true);

		controller = new Controller(environment.robot);
		controlWindow = new ControlWindow(controller);
		controlWindow.validate();
		controlWindow.setVisible(true);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		@SuppressWarnings("unused")
		TestApplication testApplication = new TestApplication();
	}
}
