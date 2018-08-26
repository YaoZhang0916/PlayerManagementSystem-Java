package com.qfedu.view;

import java.util.Scanner;

import com.qfedu.dao.TeamManager;
import com.qfedu.entity.Player;

public class View {
	public static void main(String[] args) {

		TeamManager tm = new TeamManager("孤狼B组");

		Player p1 = new Player("森林狼", 21, 10000, "PG");
		Player p2 = new Player("西伯利亚狼", 19, 9000, "SF");
		Player p3 = new Player("鸵鸟", 20, 9500, "SG");
		Player p4 = new Player("卫生院", 22, 9800, "SG");
		Player p5 = new Player("恶狼", 22, 8800, "PF");
		Player p6 = new Player("老炮", 22, 9900, "C");

		tm.addPlayer(p1);
		tm.addPlayer(p2);
		tm.addPlayer(p3);
		tm.addPlayer(p4);
		tm.addPlayer(p5);
		tm.addPlayer(p6);

		int flag = 0;
		int choose = 0;
		int playerID = 0;
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			
			
			System.out.println("欢迎来到孤狼B组");
			System.out.println("1. 展示所有成员");
			System.out.println("2. 添加新的成员");
			System.out.println("3. 退役老成员");
			System.out.println("4. 查询成员资料");
			System.out.println("5. 修改成员资料");
			System.out.println("6. 按照年龄排序");
			System.out.println("7. 按照收入排序");
			System.out.println("8. 退出");
		
			choose = sc.nextInt();
			sc.nextLine();
			
			switch (choose) {
				case 1:
					//展示所有队员
					tm.showAllPlayers();
					break;
				case 2:
					//添加新成员
					System.out.println("清输入新成员的名字");
					String name = sc.nextLine();
					
					System.out.println("请输入新成员年龄");
					int age = sc.nextInt();
					sc.nextLine();
					
					System.out.println("请输入新成员工资");
					double salary = sc.nextDouble();
					sc.nextLine();
					
					System.out.println("请输入新成员的位置");
					String location = sc.nextLine();
					
					Player playerToAdd = new Player(name, age, salary, location);
					tm.addPlayer(playerToAdd);
					break;
				case 3:
					//退役
					System.out.println("请输入要退役的老成员ID");
					playerID = sc.nextInt();
					sc.nextLine();
					
					tm.showPlayerInfoByPlayerID(playerID);
					
					System.out.println("是否要确定删除? Y or N");
					char ch = sc.nextLine().charAt(0);
					if ('Y' == ch || 'y' == ch) {
						tm.layoffPlayerByPlayerID(playerID);
					}
					
					break;
				case 4:
					//查询
					System.out.println("请输入要查询的老成员ID");
					playerID = sc.nextInt();
					sc.nextLine();
					
					tm.showPlayerInfoByPlayerID(playerID);
					
					break;
				case 5:
					System.out.println("请输入要修改成员ID");
					playerID = sc.nextInt();
					sc.nextLine();
					
					tm.modifyPlayerInfoByPlayerID(playerID);
					break;
				case 6:
					tm.ascendingSelectSortByAge();
					
					break;
				case 7:
					tm.descendingSelectSortBySalary();
					break;
				case 8:
					flag = 1;
					break;
	
				default:
					System.out.println("选择错误");
					break;
			}
			
			if (1 == flag) {
				System.out.println("啊，朋友再见~~~");
				break;
			}
		}
	}
}
