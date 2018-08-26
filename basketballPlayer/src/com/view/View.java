package com.view;

import com.dao.TeamManager;
import com.entiy.Player;

public class View {
	public static void main(String[] args){
		TeamManager tm = new TeamManager("rocket");
		
		Player p1 = new Player(" wade",  34, 100.0, "PG");
		Player p2 = new Player(" Yao",  30, 150.0, "MG");
		Player p3 = new Player(" Jobs",  24, 300.0, "SF");
		Player p4 = new Player(" White",  25, 350.0, "C");
		
		tm.addPlayer(p1);
		tm.addPlayer(p2);
		tm.addPlayer(p3);
		tm.addPlayer(p4);
		
		tm.showAllPlayers();
		System.out.println("--------------------");
		tm.addPlayer(new Player(" Jame",  27, 330.0, "C"));
		
		tm.showAllPlayers();
		
		System.out.println("---------------------");
		tm.layoffPlayerByPlayerId(5);
		tm.showAllPlayers();
		
		System.out.println("----------------------");
		tm.desSelectSortBySalary();
	}

}
