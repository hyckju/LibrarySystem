import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            LoginService loginService = new LoginService();
            AdminService adminService = new AdminService(sc);

            while (true) {
                System.out.println("=======================================");
                System.out.println("       [ 도서 관리 시스템 - LOGIN ]       ");
                System.out.println("---------------------------------------");
                System.out.print("아이디(ID)를 입력하세요: ");
                String id = sc.nextLine();
                System.out.print("비밀번호(PW)를 입력하세요: ");
                String pw = sc.nextLine();
                System.out.println("---------------------------------------");
                System.out.println("[확인 중...]");

                User user = loginService.attemptLogin(id, pw);

                if (user != null) {
                    if (user.isAdmin()) {// 관리자 로그인 성공 시
                        System.out.println(" => 관리자 권한으로 접속되었습니다. (관리자 메뉴로 이동)");
                        adminService.startAdminMenu();
                        // 일반 사용자 로그인시 실행 코드 작성
                    } else {
                        System.out.println("=> 일반 사용자 \" + user.getId() + \"님, 환영합니다.");
                    }
                }else {
                    System.out.println("[로그인 실패] 아이디 또는 비밀번호가 일치하지 않습니다.");
                }
            }
        }
    }
}