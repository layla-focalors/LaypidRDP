import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

class Client {
    int width = Settings.GetWidth();
    int height = Settings.GetHeight();
    int port = Settings.GetPort();

    public void ClientUI() throws IOException {
        System.out.println("서버 IP 주소와 포트를 입력해주세요");
        Scanner sc = new Scanner(System.in);
        System.out.print("서버 IP : ");
        String ip = sc.next();
        System.out.print("서버 포트 : ");
        int port = sc.nextInt();
        int x = Settings.GetWidth();
        int y = Settings.GetHeight();
        int w = Settings.GetWidth();
        int h = Settings.GetHeight();
        JFrame frame = new JFrame("RDP Client By Layla-focalors");
        frame.setBounds(x, y, w, h);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        ServerSocket socket_s = null;
        Socket socket = null;
        try {
            socket_s = new ServerSocket(port);
            socket = socket_s.accept();
            System.out.println("서버에 접속되었습니다.");
            System.out.println("서버 IP : " + ip + " 서버 포트 : " + port);
            System.out.println("연결 해상도 : " + width + "*" + height);
            BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());
            while(true){
                frame.getGraphics().drawImage(ImageIO.read(ImageIO.createImageInputStream(bin)), 0, 0, width, height, frame);
            }
        } catch (Exception e) {
            System.out.println("서버에 접속할 수 없습니다!");
            System.out.println("서버 IP 주소와 포트를 확인해주세요.");
            System.out.println("접속 서버 IP : " + ip + " 접속 서버 포트 : " + port);
        }
    }
}

class Server {
    int width = Settings.GetWidth();
    int height = Settings.GetHeight();
    int port = Settings.GetPort();

    public Server() throws UnknownHostException {
        InetAddress local;
        local = InetAddress.getLocalHost();
        String ipd = local.getHostAddress();
        System.out.println("서버를 시작합니다.");
        System.out.println("서버 IP : " + ipd);
        System.out.println("서버 포트 : " + port);
        System.out.println("연결 해상도 : " + width + "*" + height);
    }
}

class Hive {
    static void SetupFileData() throws IOException, ParseException, org.json.simple.parser.ParseException {
        Object Ob = new JSONParser().parse(new FileReader("./Config/Settings.json"));
        JSONObject jo = (JSONObject) Ob;
        Settings.SetWidth(Integer.parseInt(jo.get("width").toString()));
        Settings.SetHeight(Integer.parseInt(jo.get("height").toString()));
        Settings.SetPort(Integer.parseInt(jo.get("port").toString()));
    }
    static void WriteSettings() throws IOException {
        JSONObject jo = new JSONObject();
        jo.put("width", Settings.GetWidth());
        jo.put("height", Settings.GetHeight());
        jo.put("port", Settings.GetPort());
        String json = jo.toJSONString();
        File file = new File("./Config/Settings.json");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(json);
        bw.close();
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
    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
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
                    Client client = new Client();
                    client.ClientUI();
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
                                if(width < 0 || height < 0){
                                    System.out.println("경고! 가로 세로는 0보다 작을 수 없습니다~.");
                                    break;
                                }
                                if(width > 100000 || height > 100000){
                                    System.out.println("경고! 100,000 픽셀 이상의 경우 컴퓨터 사양에 따라 충돌, 혹은 오류가 발생할 수 있어요.");
                                    System.out.println("계속 진행하시겠습니까? (Y/N)");
                                    if(cian.next().equals("N")){
                                        break;
                                    }
                                }
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
                                Hive.WriteSettings();
                            }
                            case 4 -> {
                                System.out.println("프리셋 불러오기");
                                Hive.SetupFileData();
                            }
                            case 5 -> {
                                System.out.println("환경설정 종료");
                                Hive.WriteSettings();
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
