package chpt03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookSystem {
	private Building []BuildList;
	private static final int MaxBuild = 10;
	private int NowBuild = 0;
	
	BookSystem(){
		BuildList = new Building[MaxBuild];
	}
	
	public void AddBuild(Building b) {
		if(NowBuild >= MaxBuild) {
			System.out.println("¥�����Ѵﵽ���� �޷����");
			return ;
		}
		BuildList[NowBuild++] = b;
	}
	
	public void ShowAllBuildInfo() {
		for(int i = 0;i < this.NowBuild;++i) {
			System.out.println(this.BuildList[i]);
		}
	}
	
	public void ModifyFee(Building b, int fee) {
		b.ModifyFee(fee);
	}
	
	public void AdminSytem() {
		System.out.println("������Ҫִ�еĲ���");
		System.out.println("1 ��ʾ���н�����Ϣ");
		System.out.println("2 �޸ķ���");
		System.out.println("3 �޸Ŀ���ʱ��");
		int choice;
		Scanner input = new Scanner(System.in);
		try {
			choice = input.nextInt();
		}catch(InputMismatchException e) {
			System.out.println("������� ����������");
			return ;
		}
		switch(choice) {
		case 1:
			this.ShowAllBuildInfo();
			break;
		case 2:
			System.out.println();
			int fee = 0;
			
			while(true) {
				try {
					System.out.println("�������޸ĺ��ÿСʱ����:");    // ����չΪ�޸ĵ���һ���������� �ʷ��÷�static(��Ϊʱ���ϵ δ���)
					fee = input.nextInt();
					for(int i = 0;i < this.NowBuild;++i) {
						this.BuildList[i].ModifyFee(fee);
					}
					break;
				}catch(InputMismatchException e) {
					System.out.println("������� ����������");
				}
			}
			
			break;
		case 3:
			int opentime = 0;
			int closetime = 0;
			
			while(true) {
				try {
					System.out.println("�����뿪��ʱ��:");
					opentime = input.nextInt();
					System.out.println("������ر�ʱ��:");
					closetime = input.nextInt();
					this.BuildList[0].ModifyTime(opentime, closetime);
					break;
				}catch(InputMismatchException e) {
					System.out.println("������� ����������");
				}
			}

			break;
			default:
				System.out.println("�޴�ѡ�� ����������");
		}
	}
	
	public void BookingSystem() {
		System.out.println("������ѡ��");
		System.out.println("1 Ԥ����λ");
		System.out.println("2 �˶���λ");
		System.out.println("3 ��ѯ����");
		int choice = 0;
		int buildpos = 0;
		int slotpos = 0;
		Scanner in = new Scanner(System.in);
		try {
			choice = in.nextInt();
			switch(choice) {
			case 1:
				for(int i = 0;i < this.NowBuild;++i) {
					System.out.println((i + 1) + ":" + this.BuildList[i]);
				}
				System.out.println("������Ҫѡ��Ľ�����:");
				try {
					buildpos = in.nextInt();
					if(buildpos > this.NowBuild) {
						System.out.println("�޴˽���");
						return ;
					}
					for(int j = 0;j < this.BuildList[buildpos - 1].nowslotNum;++j) {
						System.out.println((j + 1) + " : " + this.BuildList[buildpos - 1].slotList[j]);
					}
					System.out.println("������Ҫѡ�����λ��:");
					slotpos = in.nextInt();
					System.out.println("Ԥ���ɹ�");
					System.out.println("��������������  �Ա��˶�");
					String pName = in.next();
					System.out.println("������Ԥ��ʱ��");
						
					while(true) {
						try {
							int reserTime = in.nextInt();
							BookingPerson p = new BookingPerson(pName, reserTime);
							boolean isreser= this.BuildList[buildpos - 1].slotList[slotpos - 1].Reserved(p);
							break;
						}catch(InputMismatchException e) {
							System.out.println("������� ����������");
						}
					}
						
				}catch(InputMismatchException e) {
					System.out.println("������� ����������");
				}catch(Exception e) {
					System.out.println("�������� ������һ��");
				}
				break;
			case 2:
				System.out.println("��������������");
				String pName = in.next();
				for(int i = 0;i < this.NowBuild;++i) {
					for(int j = 0;j < this.BuildList[i].nowslotNum;++j) {
						if(this.BuildList[i].slotList[j].isReserved()) {
							if(this.BuildList[i].slotList[j].person.getName().equals(pName)) {
								System.out.println(this.BuildList[i].slotList[j] + ":");
								System.out.println("The Booking Name:" + pName);
								System.out.println("The Booking Time:" + this.BuildList[i].slotList[j].person.getTime());
								System.out.println("�����λ�������?(y/n)");
								String choice1 = in.next();
								if(choice1.charAt(0) == 'y') {
									this.BuildList[i].slotList[j].unReserved();
									System.out.println("�˶��ɹ�");
									return ;
								}
							}
						}
					}
				}
				System.out.println("δ�ҵ�������λ");
				break;
			case 3:
				System.out.println("����������Ԥ��ʱ��(����):");
				try {
					int BTime = in.nextInt();
					System.out.println("����Ϊ:" + BTime * Building.getFee());
				}catch(Exception e) {
					System.out.println("�������� ����������");
				}
				break;
			default:
				System.out.println("�޴�ѡ�� ����������");
			}
		}catch(InputMismatchException e) {
			e.printStackTrace();
			System.out.println("������� ����������");
		}
	}
	
	public static void main(String[] args) {
		BookSystem system = new BookSystem();
		Scanner in = new Scanner(System.in);
		int choice = 0;
		Building firstBuiling = new Building("North Library St. "); 
		Building secondBuilding = new Building ("South main St."); 
		system.AddBuild(firstBuiling);
		system.AddBuild(secondBuilding);
		firstBuiling.addSlot(new Slot("North Building 2-305-22"));
		firstBuiling.addSlot(new Slot("North Building 1-201-11"));
		firstBuiling.addSlot(new Slot("North Building 2-306-24"));
		firstBuiling. addSlot (new Slot("North Building 1-103-15"));
		while(true) {
			System.out.println("������ѡ��");
			System.out.println("1 ��λԤ��");
			System.out.println("2 ����Ա��¼");
			try {
				choice = in.nextInt();
				switch(choice) {
				case 1:
					system.BookingSystem();
					break;
				case 2:
					system.AdminSytem();
					break;
				default:
					System.out.println("�޴�ѡ�� ����������");
				}
			}catch(InputMismatchException e) {
				System.out.println("������� ����������");
			}
		}
	}

}
