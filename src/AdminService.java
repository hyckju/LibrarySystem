import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminService {
    private final LibraryManager LibraryManager;
    private final Scanner sc;

    public AdminService(LibraryManager LibraryManager, Scanner sc) {
        this.sc = sc;
        this.LibraryManager = LibraryManager;
    }

    // 관리자 메뉴 실행
    public void startAdminMenu() {
        while (true) {
            System.out.println("\n=======================================");
            System.out.println("           [ 관리자 전용 메뉴]            ");
            System.out.println("=======================================");
            System.out.println("1. 도서 등록");
            System.out.println("2. 도서 수정 및 삭제");
            System.out.println("3. 전체 도서 목록");
            System.out.println("4. 도서 검색");
            System.out.println("0. 로그아웃 (메인으로)");
            System.out.println("----------------------------------------");
            System.out.print("명령 입력 : ");


            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 0 ) {//로그아웃
                System.out.println("관리자 세션을 종료합니다.");
                break;
            }
            executeMenu(choice);
        }
    }

    private void executeMenu(int choice) {
        switch (choice) {
            case 1 -> {//도서 등록
                System.out.println("[도서 등록]");
                System.out.print("- 제목 입력 : "); String t = sc.nextLine();
                System.out.print("- 저자 입력 : "); String a = sc.nextLine();
                System.out.println("---------------------------------------");
                int newId = LibraryManager.addBook(t, a);//등록 후 생성된 Id 가져오기
                System.out.print("[결과] 등록이 완료되었습니다. (도서 ID:"+newId+ ")");
            }
            case 2 ->{//도서 수정 및 삭제
                if (LibraryManager.getBooks().isEmpty()) return;
                System.out.println("[도서 수정 및 삭제]");
                System.out.print("- 관리할 도서 ID 입력 : ");
                String input = sc.nextLine();
                System.out.println("----------------------------------------");

                int id;
                try {
                    id = Integer.parseInt(input);//숫자형의 문자열을 정수값으로 변경
                }catch (InputMismatchException e) {//문자 입력 인식
                    System.out.print("숫자만 입력 하세요. ");
                    return;
                }

                int idx = id - 1;
                var list = LibraryManager.getBooks();
                Book target = list.get(idx);
                //Book 클래스의 기존 변수를 활용하여 상태값 확인
                String status = target.getLoan() ? "비치중" : "대출중";
                //target은 선택된 Book 객체로 Book클래스에 있는 loan의 status 값을 체크
                System.out.printf("  현재 정보: [%s | %s | %s]\n", target.getTitle(), target.getAuthor(), status);
                System.out.println("  1. 제목 수정  2. 저자 수정  3. 도서 삭제  0. 취소");
                System.out.println("----------------------------------------");
                System.out.print("  선택: ");
                String subChoice = sc.nextLine();

                switch (subChoice) {
                    case "1" -> {
                        System.out.print("  -> 새 제목 입력: ");
                        String newTitle = sc.nextLine();
                        LibraryManager.updateTitle(idx, newTitle);//LibraryManager에게 수정 요청
                        System.out.println("[결과] 도서 제목이 수정되었습니다.");
                    }
                    case "2" -> {
                        System.out.print("  -> 새 저자 입력: ");
                        String newAuthor = sc.nextLine();
                        LibraryManager.updateAuthor(idx, newAuthor);//LibraryManager에게 수정 요청
                        System.out.println("[결과] 도서 저자가 수정되었습니다.");
                    }
                    case "3" -> {
                        LibraryManager.deleteBook(idx);//LibraryManager에게 삭제 요청
                        System.out.println("[결과] 해당 도서 정보가 시스템에서 삭제되었습니다.");
                    }
                    case "0" -> System.out.println("작업을 취소합니다.");
                    default -> System.out.println("잘못된 번호입니다.");
                }
            }
            case 3 -> showAll();//전체 도서 출력
        }
    }
    private void showAll() {// 전체 도서 출력 클래스
        var list = LibraryManager.getBooks();
        System.out.println("\n[전체 도서 목록]");
        if (list.isEmpty()) {
            System.out.println("등록된 도서가 없습니다.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                // 인덱스 + 1을 ID로 사용하여 출력
                System.out.println("ID: " + (i + 1) + " | " + list.get(i));
            }
        }
    }
}