import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

class Hive {
    static int GetFileData(){
        return 0;
    }
}

class Settings {
    static int width = 1920;
    static int height = 1080;
    static int port = 1004;

    static int GetWidth(){
        return width;
    }
    static int GetHeight(){
        return height;
    }
    static void SetWidth(int width){
        Settings.width = width;
    }
    static void SetHeight(int height){
        Settings.height = height;
    }
    static int GetPort(){
        return port;
    }
    static void SetPort(int port){
        Settings.port = port;
    }
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
                    boolean model = false;
                    do {
                        System.out.println("------ 환경 설정 ------");
                        System.out.println("1. 해상도 설정 (현재 해상도 : " + Settings.GetWidth() + "*" + Settings.GetHeight() + ")");
                        System.out.println("2. 시스템 포트 설정 ( 경고 ) ( 현재 " + Settings.GetPort() + " 사용중 )");
                        System.out.println("3. 프리셋 저장");
                        System.out.println("4. 프리셋 불러오기");
                        System.out.println("5. 환경설정 종료");
                        Scanner cian = new Scanner(System.in);
                        int value = cian.nextInt();
                        switch(value){
                            case 1 -> {
                                System.out.println("해상도 설정");
                                System.out.print("가로 : ");
                                int width = cian.nextInt();
                                System.out.print("세로 : ");
                                int height = cian.nextInt();
                                Settings.SetWidth(width);
                                Settings.SetHeight(height);
                                System.out.println("해상도가 " + width + "*" + height + "로 변경되었습니다.");
                            }
                            case 2 -> {
                                System.out.println("시스템 포트 설정");
                                System.out.print("포트 : ");
                                int port = cian.nextInt();
                                Settings.SetPort(port);
                                System.out.println("포트가 " + port + "로 변경되었습니다.");
                            }
                            case 3 -> {
                                System.out.println("프리셋 저장");
                            }
                            case 4 -> {
                                System.out.println("프리셋 불러오기");
                            }
                            case 5 -> {
                                System.out.println("환경설정 종료");
                                model = true;
                            }
                            default -> {
                                System.out.println("잘못된 입력입니다.");
                                model = true;
                            }
                        }
                    }while (!model);

                }
                case 4 -> {
                    System.out.println("------ 정보 확인 ------");
                    String osName = "os.name";
                    String osVersion = "os.version";
                    String osArch = "os.arch";
                    InetAddress local;
                    local = InetAddress.getLocalHost();
                    String ipd = local.getHostAddress();
                    System.out.println("접속한 디바이스의 IP : " + ipd);
                    System.out.println("디바이스 OS : " + System.getProperty(osName));
                    System.out.println("운영체제 버전 : " + System.getProperty(osVersion));
                    System.out.println("시스템 아키텍쳐 : " + System.getProperty(osArch));
                }
                case 5 -> {
                    System.out.println("종료");
                    isexit = true;
                }
                default -> {
                    System.out.println("잘못된 입력입니다.");
                }
            }
        }while (!isexit);
    }
}
