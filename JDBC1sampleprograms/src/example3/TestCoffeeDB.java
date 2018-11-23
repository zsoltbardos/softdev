package example3;

public class TestCoffeeDB {

    public static void main(String[] args) {
        CoffeeDB cdb = new CoffeeDB();
        cdb.openDB();
        cdb.dropTables();
        cdb.buildCoffeeTable();
        cdb.queryDB();
        cdb.queryStrings();
        cdb.queryLIKE();
        cdb.queryANDOR();
        cdb.querySORT();
        cdb.queryMATHFNS();
        cdb.insert("Honduran Dark","22-001",8.65);
        cdb.update();
        cdb.queryDB();
        cdb.delete();
        cdb.queryDB();
        cdb.closeDB();
    }
}
