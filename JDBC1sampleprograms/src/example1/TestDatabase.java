package example1;


public class TestDatabase {
    public static void main(String[] args) {
        Database db = new Database();
        db.openDB();
        db.queryDB();
        db.closeDB();
    }
}
