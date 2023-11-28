import java.util.Scanner;
public class app {
    public static void main(String[] args){
        boolean isexit = false;
        do{
            System.out.println("-------- 메뉴 --------");
            System.out.println("1. 원격 데스크탑 서버 실행");
            System.out.println("2. 원격 데스크탑 클라이언트 실행");
            System.out.println("3. 환경 설정");
            System.out.println("4. 종료");
            Scanner sc = new Scanner(System.in);
            System.out.print("메뉴를 선택하세요 : ");
            int menu = sc.nextInt();
            switch(menu){
                case 1 -> {
                    System.out.println("원격 데스크탑 서버 실행");
                }
                case 2 -> {
                    System.out.println("원격 데스크탑 클라이언트 실행");
                }
                case 3 -> {
                    System.out.println("환경 설정");
                }
                case 4 -> {
                    System.out.println("종료");
                    isexit = true;
                }
            }
        }while (!isexit);
    }
}
