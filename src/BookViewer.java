public class BookViewer {
    //책 정보 입출력 상호작용
    public static void printBookAdmin(Book book) {
        String status = book.isLoan() ? "[대출가능]" : "[대출중]";
        System.out.printf("  현재 정보: [%s | %s | %s]\n", book.getTitle(), book.getAuthor(), status);
    }

    public static void printShowAll(int id, Book book){
        String status = book.isLoan() ? "[대출가능]" : "[대출중]";
        System.out.println(id + " | " + book.getTitle() + " | " + book.getAuthor() + " | " + status);
    }
}
