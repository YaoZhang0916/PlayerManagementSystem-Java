package com.dao;

import java.util.Scanner;

import javax.xml.transform.Templates;

import com.entiy.Player;

public class TeamManager {
	
	private String teamName;
	//this array to record player's information 
	private Player[] allPlayers = new Player[defaultCount];
	
	//record how many player in this team 
	private static  int itemCount =0; // elements number 
	// default teamPlayer's number 
	private static final int defaultCount = 10;
	
	public TeamManager(){}
	
	public TeamManager(String teamName){
		this.setTeamName(teamName);
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	//add, delete update search 
	//sort, array increase 
	
	public void showAllPlayers(){
		for(Player player: allPlayers){
			if(player==null){
				break;
			}
			System.out.println(player);
		}
	}
	
	public void addPlayer(Player playerToAdd){
		if(null==playerToAdd){
			System.out.println("Player's information is null");
			return;
		}
		
		//in class there is a static variable named 'itemCount', 1, record element number, 2 record index 
		//make sure items don't out of range 
		if(itemCount>=allPlayers.length){
				//increase the array 
				grow();
		}
		allPlayers[itemCount++] = playerToAdd;
	}
	
	// need a increase array method, be used in class 
	private void grow(){
		int oldCapacity = this.allPlayers.length;
		
		int newCapacity = oldCapacity + oldCapacity/2;
		
		Player[] newArray = new Player[newCapacity];
		
		for(int i =0; i<oldCapacity; i++){
			newArray[i] = this.allPlayers[i];
		}
		
		this.allPlayers = newArray;
		
	}
	
	//delete one player by id
	public void layoffPlayerByPlayerId(int playerID){
		//need a search method 
		int index = findPlayerIndexByPlayerID(playerID);
		
		if(index>=0){
			//delete player
			for(int i = index;i<itemCount-1;i++){
				allPlayers[i]=allPlayers[i+1];
			}
			//last element is null
			allPlayers[itemCount-1]=null;
			//number of player -1
			itemCount--;
		}else{
			System.out.println("Can't find this players");
		}
	}
	
	//find the player's information by ID
	public void showPlayerInfoByPlayerID(int playerID){
		int index = findPlayerIndexByPlayerID(playerID);
		
		if(index>-1){
			System.out.println(allPlayers[index]);
		}else{
			System.out.println("No such Player");
		}
	}
	
	public void desSelectSortBySalary(){
		//protect original data
		//create a array to save the data 
		Player[] sortArray = new Player[itemCount];
		
		//copy data
		for(int i=0; i<itemCount;i++){
			sortArray[i] = allPlayers[i];
		}
		
		for(int i=0;i<itemCount-1; i++){
			int index = i;
			
			for(int j= i+1;j<itemCount;j++){
				if(sortArray[index].getSalary()<sortArray[j].getSalary()){
					index = j;
				}
			}
			
			if(index !=i){
				Player temp = sortArray[index];
				sortArray[index] = sortArray[i];
				sortArray[i] = temp;
			}
		}
		
		showSortResult(sortArray);
	}
	//update player's information by player's id
	public void modifyPlayerInfoByPlayerID(int playerID){
		int index = findPlayerIndexByPlayerID(playerID);
		
		if(index>-1){
			//while(true) switch - case
			int flag = 0;
			int choose = -1;
			Player tem =  allPlayers[index];
			Scanner scanner = new Scanner(System.in);
			
			while(true){
				System.out.println(tem);
				
				System.out.println("1. update player's name");
				System.out.println("2. update player's age");
				System.out.println("3. update player's salary");
				System.out.println("4. update player's location");
				System.out.println("5. exit");
				
				choose = scanner.nextInt();
				
				switch (choose) {
				case 1:
					System.out.println("Please enter player's name:");
					String name = scanner.nextLine();
					tem.setName(name);		
					break;
				case 2:
					System.out.println("Please enter player's age:");
					int age = scanner.nextInt();
					tem.setAge(age);
					break;
				case 3:
					System.out.println("Please enter player's salary:");
					double salary = scanner.nextDouble();
					tem.setSalary(salary);	
					break;
				case 4:
					System.out.println("Please enter player's location:");
					String location = scanner.nextLine();
					tem.setLocation(location);	
					break;
				case 5:
					flag = 1;
					break;

				default:
					System.out.println("Error");
					break;
				}
				
				if(1==flag){
					allPlayers[index] = tem;
					System.out.println("save and exit");
					break;
				}
				
			}
			
		}else{
			System.out.println("no such player");
		}
	}
	
    private int findPlayerIndexByPlayerID(int playerID){
		if(playerID<1 || playerID>100){
			System.out.println("Error");
			return -1;
		}
		
		int index = -1;
		for(int i=0; i<itemCount;i++){
			if(allPlayers[i].getID()== playerID){
				index = i;
				break;
			}
		}
		return index;
	}
	
	private void showSortResult(Player[] sortArray){
		for(Player player: sortArray){
			System.out.println(player);
		}
	}
}
