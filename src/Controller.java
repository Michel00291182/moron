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
 * Description:  The Controller class controls the robot
 * Copyright:    Copyright (c) 2002
 * Company:      Universit di Bergamo
 * @author       Davide Brugali
 * @version 1.0
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
public class Controller implements Runnable
{
	private String waarde1;
	private String waarde2;
	
	private String waarde3;
	
	
	private String waarde4;
	
	
	
	
	
	
	Robot robot = null;
	OccupancyMap map = new OccupancyMap();

	public Controller(Robot robot) {
		this.robot = robot;
		if(robot == null)
			System.exit(1);
	}

	public void start() {
		robot.start();
		// This will cause the controller run() to be started in a separate thread.
		new Thread(this).start();
	}

	public void quit() {
	}

	/**
	 * In this method the controller sends commands to the robot and its devices.
	 * At the moment all the commands are hardcoded. 
	 * The exercise is to let the controller make intelligent decisions based on 
	 * what has been discovered so far. This information is contained in the OccupancyMap.
	 * 
	 */
	public void run() {
		String result = "";
		double position[] = new double[3];
		double measures[]  = new double[360];

		try {
			// *create the pipe and install buffered reader/writer
			// *so we can use readLine() and println

			PipedInputStream pipeIn = new PipedInputStream();
			BufferedReader input = new BufferedReader(new InputStreamReader(pipeIn));
			PrintWriter output = new PrintWriter(new PipedOutputStream(pipeIn), true);

			// *inform robot/device in other thread where to write
			robot.setOutput(output);

			System.out.println("Controller started");

			robot.sendCommand("R1.GETPOS");
			// *this thread now waits for result
			result = input.readLine();
			parsePosition(result, position);

			robot.sendCommand("L1.SCAN");
			result = input.readLine();
			parseMeasures(result, measures);
			map.drawLaserScan(position, measures);

			robot.sendCommand("P1.MOVEBW 60");
			result = input.readLine();

			robot.sendCommand("R1.GETPOS");
			result = input.readLine();
			parsePosition(result, position);

			robot.sendCommand("L1.SCAN");
			result = input.readLine();
			parseMeasures(result, measures);
			map.drawLaserScan(position, measures);

			robot.sendCommand("P1.ROTATERIGHT 90");
			result = input.readLine();

			robot.sendCommand("P1.MOVEFW 100");
			result = input.readLine();

			robot.sendCommand("R1.GETPOS");
			result = input.readLine();
			parsePosition(result, position);

			robot.sendCommand("L1.SCAN");
			result = input.readLine();
			parseMeasures(result, measures);
			map.drawLaserScan(position, measures);

			robot.sendCommand("P1.ROTATELEFT 45");
			result = input.readLine();

			robot.sendCommand("P1.MOVEFW 70");
			result = input.readLine();

			robot.sendCommand("R1.GETPOS");
			result = input.readLine();
			parsePosition(result, position);

			robot.sendCommand("L1.SCAN");
			result = input.readLine();
			parseMeasures(result, measures);
			map.drawLaserScan(position, measures);

			robot.sendCommand("P1.MOVEFW 70");
			result = input.readLine();

			robot.sendCommand("P1.ROTATERIGHT 45");
			result = input.readLine();

			robot.sendCommand("R1.GETPOS");
			result = input.readLine();
			parsePosition(result, position);

			robot.sendCommand("L1.SCAN");
			result = input.readLine();
			parseMeasures(result, measures);
			map.drawLaserScan(position, measures);

			robot.sendCommand("P1.MOVEFW 90");
			result = input.readLine();

			robot.sendCommand("R1.GETPOS");
			result = input.readLine();
			parsePosition(result, position);

			robot.sendCommand("L1.SCAN");
			result = input.readLine();
			parseMeasures(result, measures);
			map.drawLaserScan(position, measures);

			robot.sendCommand("P1.ROTATERIGHT 45");
			result = input.readLine();

			robot.sendCommand("P1.MOVEFW 90");
			result = input.readLine();

			robot.sendCommand("R1.GETPOS");
			result = input.readLine();
			parsePosition(result, position);

			robot.sendCommand("L1.SCAN");
			result = input.readLine();
			parseMeasures(result, measures);
			map.drawLaserScan(position, measures);

			robot.sendCommand("P1.ROTATERIGHT 45");
			result = input.readLine();

			robot.sendCommand("P1.MOVEFW 100");
			result = input.readLine();

			robot.sendCommand("R1.GETPOS");
			result = input.readLine();
			parsePosition(result, position);

			robot.sendCommand("L1.SCAN");
			result = input.readLine();
			parseMeasures(result, measures);
			map.drawLaserScan(position, measures);

			robot.sendCommand("P1.ROTATERIGHT 90");
			result = input.readLine();

			robot.sendCommand("P1.MOVEFW 80");
			result = input.readLine();

			robot.sendCommand("R1.GETPOS");
			result = input.readLine();
			parsePosition(result, position);

			robot.sendCommand("L1.SCAN");
			result = input.readLine();
			parseMeasures(result, measures);
			map.drawLaserScan(position, measures);

			robot.sendCommand("P1.MOVEFW 100");
			result = input.readLine();

			robot.sendCommand("R1.GETPOS");
			result = input.readLine();
			parsePosition(result, position);

			robot.sendCommand("L1.SCAN");
			result = input.readLine();
			parseMeasures(result, measures);
			map.drawLaserScan(position, measures);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void parsePosition(String value, double position[]) {
		int indexInit, indexEnd;
		String parameter;
		indexInit = value.indexOf("X=");
		parameter = value.substring(indexInit+2);
		indexEnd = parameter.indexOf(' ');
		position[0] = Double.parseDouble(parameter.substring(0, indexEnd));

		indexInit = value.indexOf("Y=");
		parameter = value.substring(indexInit+2);
		indexEnd = parameter.indexOf(' ');
		position[1] = Double.parseDouble(parameter.substring(0, indexEnd));

		indexInit = value.indexOf("DIR=");
		parameter = value.substring(indexInit+4);
		position[2] = Double.parseDouble(parameter);
	}
//asdad
	private void parseMeasures(String value, double measures[]) {
		for(int i=0; i < 360; i++)
			measures[i] = 100.0;
		if(value.length() < 5)
			return;
		value = value.substring(5);  // removes the "SCAN " keyword
		StringTokenizer tokenizer = new StringTokenizer(value, " ");
		double dist;
		int dir;
		while(tokenizer.hasMoreTokens()) {
			dist = Double.parseDouble(tokenizer.nextToken().substring(2));
			dir  = (int) Math.round( Math.toDegrees(Double.parseDouble(tokenizer.nextToken().substring(2))) );
			if(dir == 360)
				dir = 0;
			measures[dir] = dist;
//			System.out.println("dir = " + ((int) dir) + " dist = " + dist);
		}
	}

}
