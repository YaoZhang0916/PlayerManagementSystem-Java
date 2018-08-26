package com.qfedu.dao;

import java.util.Scanner;

import com.qfedu.entity.Player;

public class TeamManager {
	//��ӵ�����
	private String teamName;
	//������Ա��Ϣ������
	private Player[] allPlayers = new Player[defaultCount];

	//ͳ�Ƶ�ǰ������ж�����Ա
	private static int itemCount = 0; //Ԫ�ظ���
	//��ӵ�Ĭ����Ա����
	private static final int defaultCount = 10; 

	public TeamManager() {}

	public TeamManager(String teamName) {
		this.setTeamName(teamName);
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}

	/*
	 * �����Ա
	 * �����Ա
	 * �޸���Ա��Ϣ
	 * ��ѯ��Ա��Ϣ
	 * 
	 * �����㷨
	 * 
	 * ��������
	 */
	/**
	 * �������Ա��������
	 * @param playerToAdd Ҫ��ӵ���Ա�����
	 */
	public void addPlayer(Player playerToAdd) {
		//�����Ϸ����ж�(�Ժ�С��쳣����)
		if (null == playerToAdd) {
			System.out.println("��Ա��ϢΪ�գ��������");
			return;
		}

		//������һ��itemCount�ľ�̬��Ա������������ͳ�Ʋ����Ԫ�ظ���������Ҳ���¸�Ԫ�ر�����±�λ��
		//��Ϊ�ǲ������������Ҫ����������������⣬�����������ݸ����Ѿ�����������ĳ��ȣ���Ҫ����
		if (itemCount >= allPlayers.length) {
			//����
			grow();
		}

		allPlayers[itemCount++] = playerToAdd;
	}

	/**
	 * ͨ����Ա��IDɾ����Ա
	 * @param playerID
	 */
	public void layoffPlayerByPlayerID(int playerID) {
		//�����ѯ��������������˽�л�ͨ����ԱID��ѯ��Ա��������λ�õķ�������ȡ�±�
		int index = findPlayerIndexByPlayerID(playerID);

		if (index >= 0) {
			//ɾ����λ�õ���Ա��������������
			/*   1 2 3 4 5
			 index 1   << 3
			 index 3   << 1
			 */
			for (int i = index; i < itemCount - 1; i++) {
				allPlayers[i] = allPlayers[i + 1];
			}
			//ԭ�����һ����ЧԪ�ظ�ֵΪnull
			allPlayers[itemCount - 1] = null;

			//��Ա����Ա���� - 1
			itemCount--;
		} else {
			System.out.println("���޴��ˣ��޷�ɾ��");
		}
	}

	/**
	 * ͨ����Ա��ID������ѯ��Ա����Ϣ
	 * @param playerID Ҫչʾ����ԱID��
	 */
	public void showPlayerInfoByPlayerID(int playerID) {
		int index = findPlayerIndexByPlayerID(playerID);

		if (index > -1) {
			System.out.println(allPlayers[index]);
		} else {
			System.out.println("���޴���");
		}
	}

	/**
	 * ͨ����ԱID �޸���Ա��Ϣ
	 * @param playerID ��Ҫ�޸���Ϣ����ԱID
	 */
	public void modifyPlayerInfoByPlayerID(int playerID) {
		int index = findPlayerIndexByPlayerID(playerID);
		Scanner sc = new Scanner(System.in);
		//��ʾ�ҵ���Ա�������޸Ĳ���
		if (index > -1) {

			//while(true) switch - case
			int flag = 0;
			int choose = -1;
			Player temp = allPlayers[index];

			while (true) {
				System.out.println("�޸�" + temp.getId() + ":" + temp.getName() + "����Ϣ");
				System.out.println("***Age:" + temp.getAge());
				System.out.println("***Salary:" + temp.getSalary());
				System.out.println("***Location:" + temp.getLocation());

				System.out.println("1. �޸���Ա����");
				System.out.println("2. �޸���Ա����");
				System.out.println("3. �޸���Ա����");
				System.out.println("4. �޸���Աλ��");
				System.out.println("5. �˳�");

				choose = sc.nextInt();
				sc.nextLine();

				switch (choose) {
				case 1:
					System.out.println("��������Ա������:");
					String name = sc.nextLine();
					temp.setName(name);
					break;
				case 2:
					System.out.println("��������Ա������:");
					int age = sc.nextInt();
					temp.setAge(age);
					break;
				case 3:
					System.out.println("��������Ա�Ĺ���:");
					double salary = sc.nextDouble();
					temp.setSalary(salary);
					break;
				case 4:
					System.out.println("��������Ա��λ��:");
					String location = sc.nextLine();
					temp.setLocation(location);
					break;
				case 5:
					flag = 1;
					break;
				default:
					System.out.println("ѡ�����");
					break;
				} //switch(choose) - case

				if (1 == flag) {
					allPlayers[index] = temp;
					System.out.println("�����˳�");
					break;
				}

			} //while (true)

		} else {
			System.out.println("���޴���");
		}
		//sc.close();
	} 

	/**
	 * ���ʵĽ�������
	 */
	public void descendingSelectSortBySalary() {
		//����Դ���ݣ�����
		//1. ׼��һ������ר���������������������Ĵ�С��Դ������ЧԪ�ظ���һ��
		Player[] sortArray = new Player[itemCount];

		//2. ���ݿ���
		for (int i = 0; i < itemCount; i++) {
			sortArray[i] = allPlayers[i];
		}

		//������ѡ������Ĵ���
		for (int i = 0; i < itemCount - 1; i++) {
			int index = i;

			for (int j = i + 1; j < itemCount; j++) {
				if (sortArray[index].getSalary() < sortArray[j].getSalary()) {
					index = j;
				}
			}

			if (index != i) {
				Player temp = sortArray[index];
				sortArray[index] = sortArray[i];
				sortArray[i] = temp;
			}
		}

		showSortResult(sortArray);
	}

	/**
	 * �������������
	 */
	public void ascendingSelectSortByAge() {
		//����Դ���ݣ�����
		//1. ׼��һ������ר���������������������Ĵ�С��Դ������ЧԪ�ظ���һ��
		Player[] sortArray = new Player[itemCount];

		//2. ���ݿ���
		for (int i = 0; i < itemCount; i++) {
			sortArray[i] = allPlayers[i];
		}

		//������ѡ������Ĵ���
		for (int i = 0; i < itemCount - 1; i++) {
			int index = i;

			for (int j = i + 1; j < itemCount; j++) {
				if (sortArray[index].getAge() > sortArray[j].getAge()) {
					index = j;
				}
			}

			if (index != i) {
				Player temp = sortArray[index];
				sortArray[index] = sortArray[i];
				sortArray[i] = temp;
			}
		}

		showSortResult(sortArray);
	}

	/**
	 * �Լ��о�һ��ð������
	 */
	public void descendingBubbleSortByAge() {
		//����Դ���ݣ�����
		//1. ׼��һ������ר���������������������Ĵ�С��Դ������ЧԪ�ظ���һ��
		Player[] sortArray = new Player[itemCount];

		//2. ���ݿ���
		for (int i = 0; i < itemCount; i++) {
			sortArray[i] = allPlayers[i];
		}

		//�����ƱȽϵ��ִ�
		for (int i = 0; i < itemCount - 1; i++) {
			//�ڲ���������Ƚϴ���
			for (int j = 0; j < itemCount - i - 1; j++) {

				if (sortArray[j].getAge() < sortArray[j + 1].getAge()) {
					Player temp = sortArray[j];
					sortArray[j] = sortArray[j + 1];
					sortArray[j + 1] = temp;
				}
			}
		} 

		showSortResult(sortArray);

	}



	public void showAllPlayers() {
		for (Player player : allPlayers) {
			if (null == player) {
				break;
			}
			System.out.println(player);
		}
	}

	/**
	 * ��Ҫһ�����ݷ�������������Ҫ��������Ҫ������ã�ֻ��������ʹ��
	 */
	private void grow() {
		//1. ��ȡԭ����Ԫ�ظ���
		int oldCapacity = this.allPlayers.length;

		//2. ͨ��ԭ����Ԫ�ؼ����µ�Ԫ�ظ��� , ��Լ�����ԭ��Ԫ�ظ�����1.5��
		int newCapacity = oldCapacity + (oldCapacity >> 1);

		//3. �����µ����飬Ԫ�ظ�ʽ��ԭ����1.5��
		Player[] newArray = new Player[newCapacity];

		//4. ����ѭ����������
		for (int i = 0; i < oldCapacity; i++) {
			newArray[i] = this.allPlayers[i];
		}

		//5. ��ַ����
		this.allPlayers = newArray;
	}

	/**
	 * ˽�л��ķ�����ֻ�ṩ������ʹ�ã�������ȡָ����ԱID���������±�λ��
	 * @param playerID Ҫ��ѯ����ԱID��
	 * @return int���ͣ����ز�ѯ�������������е��±�λ�ã����û���ҵ�������-1
	 */
	private int findPlayerIndexByPlayerID(int playerID) {
		//�����Ϸ����ж�
		if (playerID < 1 || playerID > 100) {
			System.out.println("������ԱID���Ϸ�");
			return -1;
		}

		//����������Ա���±�λ��
		int index = -1;

		for (int i = 0; i < itemCount; i++) {
			//����Ա��ID�ʹ����ID���бȽ�
			if (allPlayers[i].getId() == playerID) {
				index = i;
				break;
			}
		}

		return index;
	}

	private void showSortResult(Player[] sortArray) {
		//��ǿforѭ��
		for (Player player : sortArray) {
			System.out.println(player);
		}
		//��ͨforѭ��
		//		for (int i = 0; i < sortArray.length; i++) {
		//			System.out.println(sortArray[i]);
		//		}
	}
}





















