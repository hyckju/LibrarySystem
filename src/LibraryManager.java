import java.util.ArrayList;

public class LibraryManager {
    private static final ArrayList<Book> books = new ArrayList<>();
    public LibraryManager() {
        books.add(new Book("자바의 정석", "남궁성"));
    }//초기 데이터
    //도서 추가 후 부여된 ID(index+1)값을 반환
    public int addBook(String title, String author){
        books.add(new Book(title, author));
        return books.size();//라스트의 크기가 마지막 도서의 번호
    }
    public ArrayList<Book> getBooks(){
        return books;
    }
    public void updateTitle(int index, String newTitle){//제목 수정
        if(isValidIndex(index)){
            books.get(index).setTitle(newTitle);
        }
    }
    public void updateAuthor(int index, String newAuthor) {//저자 수정
        if (isValidIndex(index)) {
            books.get(index).setAuthor(newAuthor);
        }
    }
    public void deleteBook(int index) {//도서 삭제
       if (isValidIndex(index)) {
           books.remove(index);
       }
    }
    // 입력받은 인덱스가 현재 도서 리스트의 안에 있는지 확인하는 메서드
    //메서드 미사용시 리스트에 없는 번호 수정할 시 에러 발생 (IndexOutOfBoundsException)
    private boolean isValidIndex(int index) {//인덱스 번호가 도서 리스트 범위에 있는지 확인하기 위한 메소드
        return index >= 0 && index < books.size();
    }
}
