public class User {
    private final String ID;
    private final String Password;
    private final boolean isAdmin; // true면 관리자, false면 일반 사용자
    //로그인 정보 저장
    public User(String ID, String Password, boolean isAdmin) {
        this.ID = ID;
        this.Password = Password;
        this.isAdmin = isAdmin;
    }
    //상태 변경이 불필요하기 때문에 setter 미사용
    public String getId() {return ID;}
    public String getPassword() {return Password;}
    public boolean isAdmin() {return isAdmin;}
}