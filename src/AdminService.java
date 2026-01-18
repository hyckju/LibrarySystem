import java.util.*;

public class AdminService {
    private final Scanner sc;
    private final Map<Integer, ArrayList<Object>> bookMap = new HashMap<>();
    int count = 0;

    public AdminService(Scanner sc) {
        this.sc = sc;
        bookInitial();
    }
    private void bookInitial() {//초기 데이터 샘플
        bookMap.put(++count, new ArrayList<>(Arrays.asList("자바의 정석", "남궁성", true)));
        bookMap.put(++count, new ArrayList<>(Arrays.asList("어린왕자", "생텍쥐페리", true)));
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
            System.out.println("0. 종료");
            System.out.println("----------------------------------------");
            System.out.print("명령 입력 : ");

            String choice = sc.nextLine();
            if (choice.equals("0")) {//로그아웃
                if(confirmExit()){
                    System.out.println(" [데이터 저장 중...]");
                    System.out.println(" => 모든 데이터가 성공적으로 저장되었습니다.");//데이터 저장 기능 추가 예정
                    System.out.println("도서 관리 프로그램을 이용해 주셔서 감사합니다.");
                    System.out.print("----------------------------------------");
                    break;
                }else {
                    System.out.print("종료를 취소합니다.");
                    continue;
                }
            }
            Menu(choice);
        }
    }

    private void Menu(String choice){
        switch (choice){
            case "1" -> addBook();
            case "2" -> editOrDelete();
            case "3" -> showAll();
            //case "4" -> searchBook();
        }
    }
    private void addBook(){
        System.out.println("[도서 등록]");
        System.out.print("- 제목 입력 : "); String t = sc.nextLine();
        System.out.print("- 저자 입력 : "); String a = sc.nextLine();
        System.out.println("---------------------------------------");

        if (t.trim().isEmpty() || a.trim().isEmpty()) {//공백확인
            System.out.println("제목과 저자명은 공백일 수 없습니다.");
            return;
        }
        //count변수를 1씩 증가 시켜 newId 생성
        count++;
        int newId = count;
        //ArrayList에 제목,저자,대출여부 저장
        ArrayList<Object> books = new ArrayList<>();
        books.add(t); //index 0: 제목
        books.add(a); //index 1: 저자
        books.add(true); //index 2: 대출여부
        bookMap.put(newId, books);//Map.out (newId, list)
        System.out.print("[결과] 등록이 완료되었습니다. (도서 ID:"+newId+ ")");
    }
    private void editOrDelete(){
        System.out.println("[도서 수정 및 삭제]");
        System.out.print("- 관리할 도서 ID 입력 : ");
        String input = sc.nextLine();
        System.out.println("----------------------------------------");

        int id;
        try {
            id = Integer.parseInt(input);//숫자형의 문자열을 정수값으로 변경
        }catch (InputMismatchException e) {//문자 입력 인식
            System.out.print("잘못된 입력입니다. ");
            return;
        }
        if (!bookMap.containsKey(id)) {//Map에 Key값으로 존재여부 확인
            System.out.println("존재하지 않는 도서입니다. ");
            return;
        }
        ArrayList<Object> target = bookMap.get(id);
        //Book 클래스의 기존 변수를 활용하여 상태값 확인
        String status = (boolean)target.get(2) ? "비치중" : "대출중";

        System.out.printf("  현재 정보: [%s | %s | %s]\n", target.get(0), target.get(1), status);
        System.out.println("  1. 제목 수정  2. 저자 수정  3. 도서 삭제  0. 취소");
        System.out.println("----------------------------------------");
        System.out.print("  선택: ");
        String subChoice = sc.nextLine();

        switch (subChoice) {
            case "1" -> {
                System.out.print("  -> 새 제목 입력: ");
                target.set(0, sc.nextLine());
                System.out.println("[결과] 도서 제목이 수정되었습니다.");
            }
            case "2" -> {
                System.out.print("  -> 새 저자 입력: ");
                target.set(1, sc.nextLine());
                System.out.println("[결과] 도서 저자가 수정되었습니다.");
            }
            case "3" -> {
                bookMap.remove(id);
                System.out.println("[결과] 해당 도서 정보가 시스템에서 삭제되었습니다.");
            }
            case "0" -> System.out.println("작업을 취소합니다.");
            default -> System.out.println("잘못된 입력입니다.");
        }
    }
    private void showAll() {// 전체 도서 출력 클래스
        System.out.println("\n[전체 도서 목록]");
        if (bookMap.isEmpty()) {
            System.out.println("등록된 도서가 없습니다.");
        } else {
            for (Integer id : bookMap.keySet()) {
                System.out.println("ID: " + id + " | " + bookMap.get(id));
            }
        }
    }
    private boolean confirmExit(){
        System.out.println("=======================================");
        System.out.println("           [ 프로그램 종료 확인]            ");
        System.out.println("=======================================");
        System.out.print(" 정말로 프로그램을 종료하시겠습니까? [Y/n]: ");
        System.out.println("\n----------------------------------------");
        String answer = sc.nextLine().trim().toLowerCase();//대소문자 구분 없이 처리
        return answer.equals("y");
    }
}