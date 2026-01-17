import java.util.ArrayList;
import java.util.List;

public class LoginService {
    private final List<User> users = new ArrayList<>();

    public LoginService() {
        // 임시 데이터 초기화, 추후 .csv 연도하여 비교
        users.add(new User("admin", "1234", true));
        users.add(new User("user01", "qwer", false));
    }

    public User attemptLogin(String id, String pw) {
        for (User user : users) {
            if (user.getId().equals(id) && user.getPassword().equals(pw)) {
                return user;// 인증 성공 시 유저 객체 반환 (권한 정보 포함)
            }
        }
        return null;// 인증 실패
    }
}