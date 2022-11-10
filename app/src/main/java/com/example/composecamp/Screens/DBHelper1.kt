package com.example.composecamp.Screens

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper1(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                PASS_COl + " TEXT," +
                NAME_COL + " TEXT" + ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)

    }
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    fun addUser(email:String,name:String,passwd:String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID_COL,email)
        values.put(NAME_COL,name)
        values.put(PASS_COl, passwd)
        // Inserting Row
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    fun checkUser(email: String, password: String): Boolean {
        // array of columns to fetch
        val columns = arrayOf(ID_COL);
        val db = this.readableDatabase;
        // selection criteria
        val selection = "$ID_COL = ? AND $PASS_COl = ?";
        // selection arguments
        val selectionArgs = arrayOf(email, password);
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        val cursor = db.query(TABLE_NAME, //Table to query
            columns, //columns to return
            selection, //columns for the WHERE clause
            selectionArgs, //The values for the WHERE clause
            null,  //group the rows
            null, //filter by row groups
            null); //The sort order
        val cursorCount = cursor.count;
        cursor.close();
        db.close();
        if (cursorCount > 0)
            return true;
        return false;
    }
    // This method is for adding data in our database
    fun addName(email : String, password : String ) : Long{

        // below we are creating
        // a content values variable
        val values = ContentValues();

        // we are inserting our values
        // in the form of key-value pair
        values.put(ID_COL, email);
        values.put(PASS_COl, password);

        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase;

        // all values are inserted into database

        val isval : Long = db.insert(TABLE_NAME, null, values)
        return isval;
        // at last we are
//        // closing our database
//        db.close()
    }

    // below method is to get
    // all data from our database
    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase;

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

    }

    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "DocTest.db";

        // below is the variable for database version
        private val DATABASE_VERSION = 1;

        // below is the variable for table name
        val TABLE_NAME = "Doctors";

        // below is the variable for id column
        val ID_COL = "usr_id";

        // below is the variable for name column
        val PASS_COl = "password";

        //         below is the variable for age column
        val NAME_COL = "Name"
    }
}
