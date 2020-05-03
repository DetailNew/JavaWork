package chpt03;

import java.util.Scanner;

class University{
	String Name;
	int Fee;
	University(String s, int fee){
		Name = s;
		Fee = fee;
	}
	
	University(){}
	
	public String toString() {
		return "Name:" + this.Name + " Fee:" + this.Fee;
	}

}

public class StringList {
	University Info[];

	StringList(){
		Info = new University[5];           // ����һ���������ָ�������
	}
	
	public String ShowMaxLenName() {
		int pos = 0;
		for(int i = 0;i < 5;++i) {
			if(Info[pos].Name.length() < Info[i].Name.length()) {
				pos = i;
			}
		}
		return Info[pos].Name.toUpperCase();
	}
	
	public String ShowMaxExpen() {
		int pos = 0;
		for(int i = 0;i < 5;++i) {
			if(Info[pos].Fee < Info[i].Fee) {
				pos = i;
			}
		}
		return Info[pos].Name;
	}
	
	@SuppressWarnings("resource")
	public University Found() {
		System.out.println("������Ҫ���ҵ�У��");
		Scanner input = new Scanner(System.in);
		String str = input.next();
		for(int i = 0;i < 5;++i) {
			if(str.toUpperCase().equals(Info[i].Name.toUpperCase())) {   // == ���� equals ֵ
				return Info[i];
			}
		}
		return null;
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringList sop = new StringList();
		for(int i = 0;i < 5;++i) {
			System.out.println("�������ѧ����:");
			String str = input.next();
			System.out.println("������ѧ��:");
			int fee = input.nextInt();
			sop.Info[i] = new University(str, fee);     // ������Ԫ�ش���
		}
		System.out.print("�����:");
		System.out.println(sop.ShowMaxExpen());
		System.out.println("���ѧ��:");
		System.out.println(sop.ShowMaxLenName());
		University u = sop.Found();
//		System.out.println();
		if(u == null) {
			System.out.println("δ�ҵ�");
		}else {
			System.out.println(u);
		}
	}

}
