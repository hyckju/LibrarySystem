public class Book {
    private String title;
    private String author;
    private Boolean loan;
    //책 정보 저장
    public Book(String title, String author, Boolean loan) {
        this.title = title;
        this.author = author;
        this.loan = true;
    }
    //도서 정보 변경이 필요하기 때문에 setter를 사용해 가변성 추가
    public String getTitle() {return title;}
    public void setTitle(String title){this.title = title;}
    public String getAuthor() {return author;}
    public void setAuthor(String author){this.author = author;}
    public Boolean isLoan() {return loan;}
    public void setLoan(Boolean loan){this.loan = loan;}

}
