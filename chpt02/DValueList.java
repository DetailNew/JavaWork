package chpt02;

import java.util.Scanner;

public class DValueList {

	public static void main(String[] args) {
		System.out.println("���������ĸ�������ֵ:");
        Scanner input = new Scanner(System.in);
        int Cnt = input.nextInt();
        int Dvalue = input.nextInt();
        int []Value = new int[Cnt];
        System.out.print("������" + Cnt + "������:");
        for(int i = 0;i < Cnt;++i){
            Value[i] = input.nextInt();
        }
        boolean flag = true;
        for(int i = 1;i < Cnt;++i){
            if(Math.abs(Value[i - 1] - Value[i]) > Dvalue){
                System.out.println("���ݴӵ�" + i + "������ʼ�ض�");
                flag = false;
                break;
            }
        }
        if(flag){
            System.out.print("����" + Cnt + "������ɵ���������,��ֵΪ" + Dvalue);
        }
        input.close();
	}

}
