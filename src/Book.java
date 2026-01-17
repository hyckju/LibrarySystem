public class Book {
    private String title;
    private String author;
    private Boolean loan;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.loan = true;
    }
    //도서 정보 변경이 필요하기 때문에 setter를 사용해 가변성 추가
    public String getTitle() {return title;}
    public void setTitle(String title){this.title = title;}
    public String getAuthor() {return author;}
    public void setAuthor(String author){this.author = author;}
    public Boolean getLoan() {return loan;}
    public void setLoan(Boolean loan){this.loan = loan;}

    @Override
    public String toString() {
        String status = loan ? "[대출 가능}":"[대출 중]";//클래스 내의 boolean값을 사용자에게 한글로 출력하기 위함
        return "제목: "+title+"저자: "+author;

    }
}
