import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

class Server {
    final int width = 1920;
    final int height = 1080;
}

public class app {
    public static void main(String[] args) throws UnknownHostException {
        boolean isexit = false;
        do{
            System.out.println("-------- 메뉴 --------");
            System.out.println("1. 원격 데스크탑 서버 실행");
            System.out.println("2. 원격 데스크탑 클라이언트 실행");
            System.out.println("3. 환경 설정");
            System.out.println("4. 정보 확인");
            System.out.println("5. 종료");
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
                    System.out.println("------ 정보 확인 ------");
                    InetAddress local;
                    local = InetAddress.getLocalHost();
                    String ipd = local.getHostAddress();
                    System.out.println("접속한 디바이스의 IP : " + ipd);
                }
                case 5 -> {
                    System.out.println("종료");
                    isexit = true;
                }
            }
        }while (!isexit);
    }
}
