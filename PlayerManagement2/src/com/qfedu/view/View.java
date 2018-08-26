package com.qfedu.view;

import java.util.Scanner;

import com.qfedu.dao.TeamManager;
import com.qfedu.entity.Player;

public class View {
	public static void main(String[] args) {

		TeamManager tm = new TeamManager("����B��");

		Player p1 = new Player("ɭ����", 21, 10000, "PG");
		Player p2 = new Player("����������", 19, 9000, "SF");
		Player p3 = new Player("����", 20, 9500, "SG");
		Player p4 = new Player("����Ժ", 22, 9800, "SG");
		Player p5 = new Player("����", 22, 8800, "PF");
		Player p6 = new Player("����", 22, 9900, "C");

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
			
			
			System.out.println("��ӭ��������B��");
			System.out.println("1. չʾ���г�Ա");
			System.out.println("2. ����µĳ�Ա");
			System.out.println("3. �����ϳ�Ա");
			System.out.println("4. ��ѯ��Ա����");
			System.out.println("5. �޸ĳ�Ա����");
			System.out.println("6. ������������");
			System.out.println("7. ������������");
			System.out.println("8. �˳�");
		
			choose = sc.nextInt();
			sc.nextLine();
			
			switch (choose) {
				case 1:
					//չʾ���ж�Ա
					tm.showAllPlayers();
					break;
				case 2:
					//����³�Ա
					System.out.println("�������³�Ա������");
					String name = sc.nextLine();
					
					System.out.println("�������³�Ա����");
					int age = sc.nextInt();
					sc.nextLine();
					
					System.out.println("�������³�Ա����");
					double salary = sc.nextDouble();
					sc.nextLine();
					
					System.out.println("�������³�Ա��λ��");
					String location = sc.nextLine();
					
					Player playerToAdd = new Player(name, age, salary, location);
					tm.addPlayer(playerToAdd);
					break;
				case 3:
					//����
					System.out.println("������Ҫ���۵��ϳ�ԱID");
					playerID = sc.nextInt();
					sc.nextLine();
					
					tm.showPlayerInfoByPlayerID(playerID);
					
					System.out.println("�Ƿ�Ҫȷ��ɾ��? Y or N");
					char ch = sc.nextLine().charAt(0);
					if ('Y' == ch || 'y' == ch) {
						tm.layoffPlayerByPlayerID(playerID);
					}
					
					break;
				case 4:
					//��ѯ
					System.out.println("������Ҫ��ѯ���ϳ�ԱID");
					playerID = sc.nextInt();
					sc.nextLine();
					
					tm.showPlayerInfoByPlayerID(playerID);
					
					break;
				case 5:
					System.out.println("������Ҫ�޸ĳ�ԱID");
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
					System.out.println("ѡ�����");
					break;
			}
			
			if (1 == flag) {
				System.out.println("���������ټ�~~~");
				break;
			}
		}
	}
}
