package com.example.agrotech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class DBAdapter extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ATHARV2";

    // User table name
    private static final String TABLE_USER = "Farmer";

    // User Table Columns names
    private static final String COLUMN_USER_ID= "user_id";
    private static final String COLUMN_FIRST_NAME="first_name";
    private static final String COLUMN_LAST_NAME="last_name";
    private static final String COLUMN_MOBILE_NUMBER= "mobile_number";
    private static final String COLUMN_USER_NAME= "user_name";
    private static final String COLUMN_LOCAL_ADDRESS= "local_address";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRST_NAME + " TEXT,"
            + COLUMN_LAST_NAME + " TEXT," + COLUMN_MOBILE_NUMBER + " TEXT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_LOCAL_ADDRESS + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_CROP_TABLE = "DROP TABLE IF EXISTS  CROP_TABLE";
    private String DROP_CUSTOMER_TABLE = "DROP TABLE IF EXISTS  CUSTOMER";

    /**
     * Constructor
     *
     * @param context
     */
    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_USER_TABLE);
            String price="CREATE TABLE cropprice( cropid INTEGER PRIMARY KEY AUTOINCREMENT,farmer_mobile TEXT,crop_name Text not null,crop_price integer not null, pdate Text DEFAULT (datetime('now','localtime')))";
            Log.d("price", price);
            db.execSQL(price);
            String cust="CREATE TABLE customer( cid INTEGER PRIMARY KEY AUTOINCREMENT,cname TEXT, mobile TEXT,email TEXT,address TEXT,password TEXT)";
            Log.d("cust", cust);
            db.execSQL(cust);
            String s = "CREATE TABLE if not exists CROP_TABLE (crop_id INTEGER PRIMARY KEY AUTOINCREMENT, crop_name text not null, crop_price integer not null)";
            Log.d("s", s);
            db.execSQL(s);
            String ins = "insert into CROP_TABLE(crop_name,crop_price) values('Onion',110);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Potato',50);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Wheat',100);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Maize',101);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Millet',99);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Sorghum',98);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Barley',87);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Tomato',32);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Potato',25);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Vegetables',123);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Alfalfa',121);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Dates',234);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Citrus',200);";
            db.execSQL(ins);
            ins = "insert into CROP_TABLE(crop_name,crop_price) values('Grapes',230);";
            db.execSQL(ins);

        } catch (Exception e1) {
            e1.printStackTrace();
            Log.e("Exception", e1.getMessage());

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_CROP_TABLE);
        db.execSQL(DROP_CUSTOMER_TABLE);

        // Create tables again
        onCreate(db);

    }
    public void addCustomer(CustomerBean user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("cname", user.getcustomer_firstname());
        values.put("mobile", user.getcustomer_mobilenumber());
        values.put("email", user.getcustomer_email());
        values.put("address", user.getcustomer_address());
        values.put("password", user.getcustomer_password());

        // Inserting Row
        db.insert("Customer", null, values);
        db.close();
    }
    public void updatePrice(String cropname,int price)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            String sql="update crop_table set crop_price="+price +" where crop_name='"+cropname+"'";
        Log.d("sql", sql);
        db.execSQL(sql);
        } catch (Exception e1) {
            e1.printStackTrace();
            Log.e("Exception", e1.getMessage());

        }
    }
    public void addPrice(String m, String cropname,int price ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("farmer_mobile", m);
        values.put("crop_name", cropname);
        values.put("crop_price",price);

        // Inserting Row
        db.insert("cropprice", null, values);
        db.close();
    }
    public void addUser(Farmerbean user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, user.getfarmer_firstname());
        values.put(COLUMN_LAST_NAME, user.getfarmer_lastname());
        values.put(COLUMN_MOBILE_NUMBER, user.getfarmer_mobilenumber());
        values.put(COLUMN_LOCAL_ADDRESS, user.getfarmer_address());
        values.put(COLUMN_USER_NAME, user.getfarmer_username());
        values.put(COLUMN_USER_PASSWORD, user.getfarmer_password());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Farmerbean> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_FIRST_NAME,
                COLUMN_LAST_NAME,
                COLUMN_LOCAL_ADDRESS,
                COLUMN_LOCAL_ADDRESS,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<Farmerbean> userList = new ArrayList<Farmerbean>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Farmerbean user = new Farmerbean();
                user.setfarmer_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setfarmer_firstname(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST_NAME)));
                user.setfarmer_lastname(cursor.getString(cursor.getColumnIndex(COLUMN_LAST_NAME)));
                user.setfarmer_mobilenumber(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE_NUMBER)));
                user.setfarmer_address(cursor.getString(cursor.getColumnIndex(COLUMN_LOCAL_ADDRESS)));
                user.setfarmer_username(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID)));
                user.getfarmer_password();
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(Farmerbean user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, user.getfarmer_id());
        values.put(COLUMN_FIRST_NAME, user.getfarmer_firstname());
        values.put(COLUMN_LAST_NAME, user.getfarmer_lastname());
        values.put(COLUMN_MOBILE_NUMBER, user.getfarmer_mobilenumber());
        values.put(COLUMN_LOCAL_ADDRESS, user.getfarmer_address());
        values.put(COLUMN_USER_NAME, user.getfarmer_username());
        values.put(COLUMN_USER_PASSWORD, user.getfarmer_password());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getfarmer_id())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(Farmerbean user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getfarmer_id())});
        db.close();
    }


    public Cursor getCropDetails(String cr) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from CROP_TABLE where crop_name='"+cr+"'",null);
        return res;
    }
    public Cursor getAllCropDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from CROP_TABLE order by crop_id ",null);
        return res;
    }
    public Cursor getAllCropPriceDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from cropprice order by cropid desc ",null);
        return res;
    }
    public Cursor getFarmerDetails(String m) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from user where mobile_number='"+m+"'",null);
        return res;
    }
    public Cursor getAllFarmerDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from farmer order by user_id ",null);
        return res;
    }
    public Cursor getAllCustomerDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from Customer order by cid ",null);
        return res;
    }
    public boolean checkCustomer(String m, String password) {

        // array of columns to fetch
        String[] columns = {"cid"};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = "mobile = ? AND password = ?";

        // selection arguments
        String[] selectionArgs = {m, password};


        Cursor cursor = db.query("customer", //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
    public boolean checkUser(String username, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_MOBILE_NUMBER + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {username, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE username = 'ash' AND user_password = '123';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
