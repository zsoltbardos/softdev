/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softdevlab6;

/**
 *
 * @author x00157506
 */
public class TestContacts {
    public static void main(String[] args) {
        ContactsDB db = new ContactsDB();
        db.openDB();
        db.dropTables();
        db.buildContactsTable();
        db.showDB();
        db.queryDB();
        db.insert();
        db.showDB();
        db.closeDB();
    }
}
