package com.example.weborganizer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.list2.R;
import com.example.weborganizer.Containers.Contact;
import com.example.weborganizer.Containers.Filter;
import com.example.weborganizer.Containers.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sasha on 04.11.13.
 */
public class DatabaseWorker extends SQLiteOpenHelper{

    private Context context;
    private int USER_ID=0;
    //PERSON_TABLE
    static final String tableUser="User";
    static final String collId = "id";
    static final String collEmail="email";
    static final String collPass="pass";
    static final String collUserLastSync="Last_Sync_Date";
    static final String collUserBirth="user_birth";
    //TASKS
    static final String tableTask="Task";
    static final String collUserId="id_User";
    static final String collTaskTitle="Task_title";
    static final String collTaskText="text";
    static final String collTaskTime="task_time";
    static final String collTaskDate="task_date";
    static final String collTaskLast_Editing="Last_Editing";
    static final String collTaskEditType="Editing_Type";
    static final String collTaskFilter="Filter_Id";
    //FILTERS
    static final String tableFilter="Filters";
    static final String collFilterName="Filter_Name";
    static final String collFiltrId="id";
    static final String collFilterUserId="UserId";
    static final String collFilterLast_Editing="Last_Editing";
    static final String collFilterEditType="Editing_Type";

    //USER_OPTIONS
    static final String tableUserOptions="UserOptions";
    static final String collUserOptionsId="User_id";
    static final String collUserOptionsLanguage="User_Language";

    //USER CONTACTS
    static final String tableUserContacts="Contacts";
    static final String collContactId="contact_id";
    static final String collContactName="contact_name";
    static final String collContactSurname="contact_surname";
    static final String collContactPhone="contact_phone";
    static final String collConTactEmail="contact_email";
    static final String collContactBirth="contact_birth";
    static final String collContactUserId="user_id";
    static final String collContactLast_Editing="Last_Editing";
    static final String collContactEditType="Editing_Type";

    public static final int EDIT_TYPE_INSERT=0;
    public static final int EDIT_TYPE_UPDATE=1;
    public static final int EDIT_TYPE_DELETE=2;

    private final static String PERSON_TABLE="CREATE TABLE "+tableUser+" ("+collId+" INTEGER PRIMARY KEY NOT NULL, "+
            collEmail+" CHAR(50),"+collPass+" char(50), "+collUserBirth+"DATE, "+collUserLastSync+" TIME);";

    private static final String FILTERS="CREATE TABLE "+tableFilter+" ("+collFilterName+" VARCHAR(40), "+
            collFilterEditType+" TINYINT, "+collFilterLast_Editing+" TIME, "
            +collFiltrId+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+collFilterUserId+" INTEGER , FOREIGN KEY ("
            +collFilterUserId+") REFERENCES "+tableUser+" ("+collId+"));";

    private final static String TASKS="CREATE TABLE "+tableTask+" ("+collTaskTitle+" VARCHAR(80), "+collTaskText+" Text, "+collTaskTime
            +" TIME, "+collTaskDate+" DATE, "+collTaskLast_Editing+" TIME, "+collTaskEditType+" TINYINT, "+
            collTaskFilter +" INTEGER  NOT NULL, "+collUserId+" INTEGER NOT NULL, FOREIGN KEY ("
            +collUserId+") REFERENCES "+tableUser+" ("+collId+"), "+" FOREIGN KEY ("+collTaskFilter+
            ") REFERENCES "+tableFilter+" ("+collFiltrId+"));";

    private  static final String USER_OPTIONS="CREATE TABLE "+tableUserOptions+" ("+collUserOptionsId+
            " INTEGER NOT NULL, "+collUserOptionsLanguage+" VARCHAR(80));";

    private static final String CONTACTS = "CREATE TABLE "+tableUserContacts+" ("+collContactId+
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+collContactName+" VARCHAR(40), "+collContactSurname
            +" VARCHAR(40), "+collContactPhone+" VARCHAR(20), "+collConTactEmail+" VARCHAR(254), "
            +collContactBirth+" DATE, "+collContactUserId+" INTEGER NOT NULL, "+collContactLast_Editing+" TIME, "
            +collContactEditType+" TINYINT);";

    static final String DATABASE_NAME="ClientDB";
    public DatabaseWorker(Context context) {
        super(context, DATABASE_NAME, null,1);
        this.context=context;
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
     //   db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL(PERSON_TABLE);
        db.execSQL(TASKS);
        db.execSQL(FILTERS);
        db.execSQL(USER_OPTIONS);
        db.execSQL(CONTACTS);
        db.execSQL("INSERT INTO "+tableUserOptions+"  VALUES ('"+0+"', 'RUSSIAN');");
        db.execSQL("INSERT INTO "+tableUser+" VALUES ('"+0+"', 'email@mail.com','1993-04-07', '1111', strftime('%s', 'now'));");
        db.execSQL("INSERT INTO "+tableUser+" VALUES ('"+1+"', 'email@gmail.com','1993-04-14', '1212', strftime('%s', 'now'));");
        db.execSQL("INSERT INTO "+tableFilter+" ("+collFilterName+")"+" VALUES ('"+
                context.getResources().getString(R.string.title_section1)+"');");
        db.execSQL("INSERT INTO "+tableFilter+" ("+collFilterName+")"+" VALUES ('"+
                context.getResources().getString(R.string.title_section2)+"');");
        db.execSQL("INSERT INTO "+tableFilter+" ("+collFilterName+")"+" VALUES ('"+
                context.getResources().getString(R.string.title_section3)+"');");
        db.execSQL("INSERT INTO "+tableTask+" ("+collTaskTitle+", "+collTaskText+", "+collTaskTime+", "+collTaskDate+", "+
                collTaskLast_Editing+", "+collTaskEditType+", "+collTaskFilter+", "+collUserId+")"+
                " VALUES ('"+
                "Купить хлеб"
                +"', '"+
                "Купить белый"
                +"', "
                +"time('12:00', 'localtime')"
                +", "+
                "date('now')"
                +", "+
                "strftime('%s', 'now')"
                +", '"+
                "1"
                +"', '"+
                "1"
                +"', '"+
                USER_ID
                +"');");
        db.execSQL("INSERT INTO "+tableUserContacts+" ("+collContactName+", "+collContactSurname+", "
                +collContactPhone+", "+collConTactEmail+", "+collContactBirth+", "+collContactUserId
                +", "+collContactLast_Editing+", "+collContactEditType+
                ") VALUES ( '"+"SASHA"+"', '"+"ANDREEV"+"' ,'"+"098454656"+"', '"+"mail.com"+"', '"+"1993-11-24"+"', '"
                +USER_ID+"', strftime('%s', 'now'), '0');");
        db.execSQL("INSERT INTO "+tableUserContacts+" ("+collContactName+", "+collContactSurname+", "
                +collContactPhone+", "+collConTactEmail+", "+collContactBirth+", "+collContactUserId
                +", "+collContactLast_Editing+", "+collContactEditType+
                ") VALUES ( '"+"Vasia"+"', '"+"Petrenko"+"' ,'"+"098454656"+"', '"+"mail.com"+"', '"+"1993-12-12"+"', '"
                +USER_ID+"', strftime('%s', 'now'), '0');");
        db.execSQL("INSERT INTO "+tableUserContacts+" ("+collContactName+", "+collContactSurname+", "
                +collContactPhone+", "+collConTactEmail+", "+collContactBirth+", "+collContactUserId
                +", "+collContactLast_Editing+", "+collContactEditType+
                ") VALUES ( '"+"Petia"+"', '"+"Petrov"+"' ,'"+"098454656"+"', '"+"mail.com"+"', '"+"1993-04-1"+"', '"
                +USER_ID+"', strftime('%s', 'now'), '0');");
        db.execSQL("INSERT INTO "+tableUserContacts+" ("+collContactName+", "+collContactSurname+", "
                +collContactPhone+", "+collConTactEmail+", "+collContactBirth+", "+collContactUserId
                +", "+collContactLast_Editing+", "+collContactEditType+
                ") VALUES ( '"+"Dima"+"', '"+"Sidorov"+"' ,'"+"098454656"+"', '"+"mail.com"+"', '"+"1993-04-28"+"', '"
                +USER_ID+"', strftime('%s', 'now'), '0');");
        db.execSQL("INSERT INTO "+tableUserContacts+" ("+collContactName+", "+collContactSurname+", "
                +collContactPhone+", "+collConTactEmail+", "+collContactBirth+", "+collContactUserId
                +", "+collContactLast_Editing+", "+collContactEditType+
                ") VALUES ( '"+"Dima"+"', '"+"Sidorov"+"' ,'"+"098454656"+"', '"+"mail.com"+"', '"+"1993-04-28"+"', '"
                +USER_ID+"', strftime('%s', 'now'), '1');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+PERSON_TABLE);
        db.execSQL("DROP TABLE "+TASKS);
        db.execSQL("DROP TABLE "+CONTACTS);
        db.execSQL("DROP TABLE "+FILTERS);
        db.execSQL("DROP TABLE"+USER_OPTIONS);
        onCreate(db);

    }
    public ArrayList<Filter> getFilters()
    {
        ArrayList<Filter>ans=new ArrayList<Filter>();
        SQLiteDatabase database= this.getReadableDatabase();
        Cursor cursor  = database.rawQuery("SELECT *  FROM "+DatabaseWorker.tableFilter,null);
        int col1 =cursor.getColumnIndex(DatabaseWorker.collFiltrId);
        int col2=cursor.getColumnIndex(DatabaseWorker.collFilterName);
        if(cursor.moveToFirst()){
            do{

                ans.add(new Filter(cursor.getInt(col1),cursor.getString(col2)));

            }while(cursor.moveToNext());

        }

        return ans;
    }

public ArrayList<Task> getTasks()
            {
            ArrayList<Task> ans=new ArrayList<Task>();
            SQLiteDatabase database= this.getReadableDatabase();
         Cursor cursor  = database.rawQuery("SELECT *  FROM "+tableTask+" WHERE "+collUserId+" = "+USER_ID,null);

            for(String i:cursor.getColumnNames()){
            Log.d("COLLS",i);
        }

        int col1 =cursor.getColumnIndex(collTaskTitle);
        int col2=cursor.getColumnIndex(collTaskText);
        int col3 =cursor.getColumnIndex(collTaskTime);
        int col4 =cursor.getColumnIndex(collTaskDate);
        int col5=cursor.getColumnIndex(collTaskLast_Editing);
        int col6 =cursor.getColumnIndex(collTaskEditType);
        int col7 =cursor.getColumnIndex(collTaskFilter);
        int col8=cursor.getColumnIndex(collUserId);
        if(cursor.moveToFirst())
        {
            do
            {

                ans.add(new Task(cursor.getString(col1),cursor.getString(col2),cursor.getString(col3),cursor.getString(col4),
                        cursor.getString(col5),(byte)cursor.getShort(col6),cursor.getInt(col7),cursor.getInt(col8)));

            }while(cursor.moveToNext());

        }
        database.close();
        return ans;
    }
    public void insertFilter(String filterName)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        try{

            database.execSQL("INSERT INTO "+tableFilter+" ("+collFilterName+", "+collFilterUserId+")"+
                    " VALUES ('"+filterName+"', '"+ USER_ID +"');");
        }catch (Exception e){

        }
        finally {
            database.close();
        }
    }
   public void deleteFilters(Filter f){

           SQLiteDatabase database =this.getWritableDatabase();
       try{
           String query ="DELETE FROM "+tableFilter+" WHERE "+collFiltrId+" = '"+f.filterId+"' ";
           Log.d("MESSEGE",query);
           database.execSQL(query);
       }catch (Exception e){
           e.printStackTrace();
       }
       finally {
          database.close();
       }
   }
    public void insertTask(Task task)
    {

        SQLiteDatabase database= this.getWritableDatabase();
        String query="INSERT INTO "+tableTask+" ("+collTaskTitle+", "+collTaskText+", "+collTaskTime+", "+collTaskDate+", "+
                collTaskLast_Editing+", "+collTaskEditType+", "+collTaskFilter+", "+collUserId+")"+
                " VALUES ('"+task.taskTitle+"', '"+(task.taskText==null?"NULL":task.taskText)+"', '"+task.taskTime+"', '"+task.taskDate
                +"', "+"strftime('%s', 'now')"+", '"+"0"+"', '"+task.taskFilterId+"', '"+USER_ID+"');";
        try{

        database.execSQL(query);
        }catch (Exception e){
            e.printStackTrace();
            Log.d("ROW_ADD",query);
        }
        finally {
            database.close();
        }
    }
    public ArrayList<ArrayList<Task>>  getLists(int filterGroupe)
    {
        ArrayList<ArrayList<Task>> ans = new ArrayList<ArrayList<Task>>();
        SQLiteDatabase database= this.getReadableDatabase();
        String[] querys={   "SELECT *  FROM "+tableTask+" WHERE "+collUserId+" = "+USER_ID+" AND "
                +collTaskFilter+" = '"+filterGroupe+"' AND "+collTaskDate+" < DATE('now') ORDER BY "+collTaskTime,
                            "SELECT *  FROM "+tableTask+" WHERE "+collUserId+" = "+USER_ID+" AND "
                                    +collTaskFilter+" = '"+filterGroupe+"' AND "+collTaskDate+" = DATE('now') ORDER BY "+collTaskTime,
                            "SELECT *  FROM "+tableTask+" WHERE "+collUserId+" = "+USER_ID+" AND "
                                    +collTaskFilter+" = '"+filterGroupe+"' AND "+collTaskDate+" = DATE('now', '+1 day') ORDER BY "+collTaskTime,
                            "SELECT *  FROM "+tableTask+" WHERE "+collUserId+" = "+USER_ID+" AND "
                                    +collTaskFilter+" = '"+filterGroupe+"' AND "+collTaskDate+" > DATE('now', '+1 day') ORDER BY "+collTaskTime
        };


        for(String query:querys){
            Cursor cursor  = database.rawQuery(query,null);
            ArrayList<Task> groupe = new ArrayList<Task>();


                int col1 =cursor.getColumnIndex(collTaskTitle);
                int col2=cursor.getColumnIndex(collTaskText);
                int col3 =cursor.getColumnIndex(collTaskTime);
                int col4 =cursor.getColumnIndex(collTaskDate);
                int col5=cursor.getColumnIndex(collTaskLast_Editing);
                int col6 =cursor.getColumnIndex(collTaskEditType);
                int col7 =cursor.getColumnIndex(collTaskFilter);
                int col8=cursor.getColumnIndex(collUserId);
                if(cursor.moveToFirst()){
                    do{
                        Log.d("ADD","");
                        groupe.add(new Task(cursor.getString(col1),cursor.getString(col2),cursor.getString(col3),cursor.getString(col4),
                        cursor.getString(col5),(byte)cursor.getShort(col6),cursor.getInt(col7),cursor.getInt(col8)));
                        Log.d("COLL_ADD",cursor.getString(col4));
                      }while(cursor.moveToNext());

             }

            ans.add(groupe);

        }
        return ans;
    }

    public void printTable(String table){
        SQLiteDatabase database= this.getReadableDatabase();
        Cursor cursor  = database.rawQuery("SELECT *  FROM "+table,null);


        String[] cools =cursor.getColumnNames();
        int[] collsNum=new int[cools.length];


        for(int i=0;i<cools.length;i++)
        {
            collsNum[i]=cursor.getColumnIndex(cools[i]);
        }
        Log.d("ARR", Arrays.toString(collsNum)+" "+cools);
        StringBuilder stringBuilder = new StringBuilder();

        if(cursor.moveToFirst()){
            do{
                for(int i:collsNum){
                stringBuilder.append(cursor.getString(i)).append(" | ");
                }
                stringBuilder.append("/n");


            }while(cursor.moveToNext());
            Log.d(table,stringBuilder.toString());
    }
        database.close();
    }
    public void update(Task task){
        SQLiteDatabase database= this.getWritableDatabase();
        String query ="UPDATE  "+tableTask+" SET "+collTaskTitle+" = '"+task.taskTitle +"', "+collTaskText+
                " = '"+task.taskText+"', "+collTaskTime+" = '"+task.taskTime+"', "+collTaskDate+" = '"+task.taskDate+"', "+
                collTaskLast_Editing+" = strftime('%s', 'now')"+", "+collTaskEditType+" = '1', "+
                collTaskFilter+" = '"+task.taskFilterId+"', "+collUserId+" = '"+USER_ID+"' WHERE "+
                collTaskLast_Editing+" = '"+task.lastTaskLastEditing+"'";

        try{
            Log.d("QUERY",query);
            database.execSQL(query);
        }catch (Exception e){
            Log.d("QUERY",query);
            e.printStackTrace();
        }
        finally {
            database.close();
        }
    }
    public void deleteTaskRow(String changrTime)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        String query ="DELETE FROM  "+tableTask+" WHERE "+collUserId+" = "+USER_ID+" AND "
                +collTaskLast_Editing+" = '"+changrTime+"'";

        try{
            Log.d("QUERY",query);
            database.execSQL(query);
        }catch (Exception e){
            Log.d("QUERY",query);
            e.printStackTrace();
        }
        finally {
            database.close();
        }
    }
    public ArrayList<Contact> getContacts(){
        ArrayList<Contact> ans=new ArrayList<Contact>();
        SQLiteDatabase database= this.getReadableDatabase();
        Cursor cursor  = database.rawQuery("SELECT *  FROM "+tableUserContacts+" WHERE "
                +collContactUserId+" = "+USER_ID+" ORDER BY "+collContactName,null);

        for(String i:cursor.getColumnNames()){
            Log.d("COLLS",i);
        }
        int col=cursor.getColumnIndex(collContactId);
        int col1 =cursor.getColumnIndex(collContactName);
        int col2=cursor.getColumnIndex(collContactSurname);
        int col3 =cursor.getColumnIndex(collContactPhone);
        int col4 =cursor.getColumnIndex(collConTactEmail);
        int col5=cursor.getColumnIndex(collContactBirth);

        if(cursor.moveToFirst())
        {
            do
            {

                ans.add(new Contact(cursor.getInt(col),cursor.getString(col1),cursor.getString(col2),
                        cursor.getInt(col3),cursor.getString(col4),
                        cursor.getString(col5)));

            }while(cursor.moveToNext());

        }
        database.close();
        Log.d("CONTACTS",ans.toString());
        return ans;
    }
    public ArrayList<Contact> searchContacts(String query){
        ArrayList<Contact> ans=new ArrayList<Contact>();
        final String QUERY="SELECT *  FROM "+tableUserContacts+" WHERE "
                +collContactUserId+" = "+USER_ID+" AND "+collContactName+" like '%"+query+"%'";
        SQLiteDatabase database= this.getReadableDatabase();
        Cursor cursor  = database.rawQuery(QUERY,null);

        for(String i:cursor.getColumnNames()){
            Log.d("COLLS",i);
        }
        int col=cursor.getColumnIndex(collContactId);
        int col1 =cursor.getColumnIndex(collContactName);
        int col2=cursor.getColumnIndex(collContactSurname);
        int col3 =cursor.getColumnIndex(collContactPhone);
        int col4 =cursor.getColumnIndex(collConTactEmail);
        int col5=cursor.getColumnIndex(collContactBirth);

        if(cursor.moveToFirst())
        {
            do
            {

                ans.add(new Contact(cursor.getInt(col),cursor.getString(col1),cursor.getString(col2),
                        cursor.getInt(col3),cursor.getString(col4),
                        cursor.getString(col5)));

            }while(cursor.moveToNext());

        }
        database.close();
        Log.d("CONTACTS",ans.toString());
        return ans;
    }

    //SYNC FUNCTIONS//
    public void getNotSyncTask(int editType){
        ArrayList<Task> ans=new ArrayList<Task>();
        SQLiteDatabase database= this.getReadableDatabase();
        final String query="SELECT *  FROM "+tableTask+" INNER JOIN "+tableUser+" ON "+
                tableTask+"."+collUserId+"="+tableUser+"."+collId+" WHERE "+collUserId+
                " = '"+USER_ID+"' AND "+collUserLastSync+" < "+tableTask+"."+collTaskLast_Editing+" AND "+
                tableTask+"."+collTaskEditType+" = '"+editType+"'"+" ORDER BY "+tableTask+"."+collTaskEditType;

        Cursor cursor = database.rawQuery(query,null);
        String[] cools =cursor.getColumnNames();
        int[] collsNum=new int[cools.length];


        for(int i=0;i<cools.length;i++)
        {
            collsNum[i]=cursor.getColumnIndex(cools[i]);
        }
        Log.d("ARR", Arrays.toString(collsNum)+" "+cools);
        StringBuilder stringBuilder = new StringBuilder();

        if(cursor.moveToFirst()){
            do{
                for(int i:collsNum){
                    stringBuilder.append(cursor.getString(i)).append(" | ");
                }
                stringBuilder.append("/n");


            }while(cursor.moveToNext());
            Log.d("QUERY",query);
            Log.d("TASK",stringBuilder.toString());
        database.close();

        }
    }
     //*********TEST METHODS*********************//
    public String between(){

        SQLiteDatabase database= this.getReadableDatabase();
        final String query="SELECT *  FROM "+tableUserContacts+" INNER JOIN "+tableUser+" ON "+
                tableUserContacts+"."+collContactUserId+"="+tableUser+"."+collId+" WHERE strftime('%m', `"+collContactBirth+"`) BETWEEN "
                +"'06' AND '12';";

        Cursor cursor = database.rawQuery(query,null);
        String[] cools =cursor.getColumnNames();
        int[] collsNum=new int[cools.length];
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<cools.length;i++)
        {
            collsNum[i]=cursor.getColumnIndex(cools[i]);
            Log.d("COLL", cools[i]);
            stringBuilder.append(cools[i]);
            stringBuilder.append("\t\t");
        }
        Log.d("ARR", Arrays.toString(collsNum)+" "+cools.toString());
        stringBuilder.append("\n");

        if(cursor.moveToFirst()){
            do{
                for(int i:collsNum){
                    stringBuilder.append(cursor.getString(i)).append("\t\t");
                }
                stringBuilder.append("\n");
            }while(cursor.moveToNext());
            Log.d("QUERY",query);
            Log.d("TASK",stringBuilder.toString());
            database.close();

        }
        return stringBuilder.toString();
}
    public String contactCount(int user_id){

        SQLiteDatabase database= this.getReadableDatabase();
        final String query="SELECT '"+user_id+"', COUNT("+collContactName+") FROM "+tableUserContacts+" WHERE "+
                collContactUserId+" = '"+user_id+"';";


        Cursor cursor = database.rawQuery(query,null);
        String[] cools =cursor.getColumnNames();
        int[] collsNum=new int[cools.length];
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<cools.length;i++)
        {
            collsNum[i]=cursor.getColumnIndex(cools[i]);
            Log.d("COLL", cools[i]);
            stringBuilder.append(cools[i]);
            stringBuilder.append("\t\t");
        }
        Log.d("ARR", Arrays.toString(collsNum)+" "+cools.toString());
        stringBuilder.append("\n");

        if(cursor.moveToFirst()){
            do{
                for(int i:collsNum){
                    stringBuilder.append(cursor.getString(i)).append("\t\t");
                }
                stringBuilder.append("\n");
            }while(cursor.moveToNext());
            Log.d("QUERY",query);
            Log.d("TASK",stringBuilder.toString());
            database.close();

        }
        return stringBuilder.toString();
    }
    public String  taskCount(){

        SQLiteDatabase database= this.getReadableDatabase();
        final String query="SELECT id, email,(SELECT COUNT("+collTaskLast_Editing+") FROM "+tableTask+" WHERE "+
                tableUser+"."+collId+" = "+tableTask+"."+collUserId+")AS summ FROM "+tableUser;


        Cursor cursor = database.rawQuery(query,null);
        String[] cools =cursor.getColumnNames();
        int[] collsNum=new int[cools.length];
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<cools.length;i++)
        {
            collsNum[i]=cursor.getColumnIndex(cools[i]);
            Log.d("COLL", cools[i]);
            stringBuilder.append(cools[i]);
            stringBuilder.append("\t\t");
        }
        Log.d("ARR", Arrays.toString(collsNum)+" "+cools.toString());
        stringBuilder.append("\n");

        if(cursor.moveToFirst()){
            do{
                for(int i:collsNum){
                    stringBuilder.append(cursor.getString(i)).append("\t\t");
                }
                stringBuilder.append("\n");
            }while(cursor.moveToNext());
            Log.d("QUERY",query);
            Log.d("TASK",stringBuilder.toString());
            database.close();

        }
        return stringBuilder.toString();
    }
    public String maxContacts(){

        SQLiteDatabase database= this.getReadableDatabase();
        final String query="SELECT id, email,(SELECT COUNT("+collTaskLast_Editing+") FROM "+tableTask+" WHERE "+
                tableUser+"."+collId+" = "+tableTask+"."+collUserId+")AS summ FROM "+tableUser+" ORDER BY summ LIMIT 1";


        Cursor cursor = database.rawQuery(query,null);
        String[] cools =cursor.getColumnNames();
        int[] collsNum=new int[cools.length];
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<cools.length;i++)
        {
            collsNum[i]=cursor.getColumnIndex(cools[i]);
            Log.d("COLL", cools[i]);
            stringBuilder.append(cools[i]);
            stringBuilder.append("\t\t");
        }
        Log.d("ARR", Arrays.toString(collsNum)+" "+cools.toString());
        stringBuilder.append("\n");

        if(cursor.moveToFirst()){
            do{
                for(int i:collsNum){
                    stringBuilder.append(cursor.getString(i)).append("\t\t");
                }
                stringBuilder.append("\n");
            }while(cursor.moveToNext());
            Log.d("QUERY",query);
            Log.d("TASK",stringBuilder.toString());
            database.close();

        }
        return stringBuilder.toString();
    }
    public String maxContacts(String mail){

        SQLiteDatabase database= this.getReadableDatabase();
        final String query=
                //"SELECT t1."+collContactUserId+ ", MAX (("+tableUserContacts+"."+collContactUserId+
                //") FROM IN ("+
                "SELECT *, MAX ("+collContactUserId+") FROM (SELECT "+
                collContactUserId+", COUNT ("+collConTactEmail+") FROM "+tableUserContacts+
               " WHERE "+tableUserContacts+"."+collConTactEmail+" LIKE '%"+mail+"') AS t2" ;

        Log.d("QUERY",query);
        Cursor cursor = database.rawQuery(query,null);
        String[] cools =cursor.getColumnNames();
        int[] collsNum=new int[cools.length];
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<cools.length;i++)
        {
            collsNum[i]=cursor.getColumnIndex(cools[i]);
            Log.d("COLL", cools[i]);
            stringBuilder.append(cools[i]);
            stringBuilder.append("\t\t");
        }
        Log.d("ARR", Arrays.toString(collsNum)+" "+cools.toString());
        stringBuilder.append("\n");

        if(cursor.moveToFirst()){
            do{
                for(int i:collsNum){
                    stringBuilder.append(cursor.getString(i)).append("\t\t");
                }
                stringBuilder.append("\n");
            }while(cursor.moveToNext());

            Log.d("TASK",stringBuilder.toString());
            database.close();

        }
        return stringBuilder.toString();

    }
    public String notNull(int type){

        SQLiteDatabase database= this.getReadableDatabase();
        final String query="SELECT "+collEmail+", "+collId+" FROM "+tableUser+" WHERE NOT  EXISTS (SELECT "+
                collContactUserId+" FROM "+tableUserContacts+" WHERE "+
               tableUserContacts+"."+collContactUserId+" = "+tableUser+"."+collId+")";
                //
                // "SELECT "+collEmail+", "+collId+" FROM "+tableUser+"  WHERE "+collId+" NOT IN (SELECT "+collContactUserId+" FROM "+tableUserContacts+")";

//                "SELECT "+collEmail+", "+collId+" FROM "+tableUser+" LEFT JOIN "+tableUserContacts+" ON "+tableUser+"."+collId+" = "+
//                        tableUserContacts+"."+collContactUserId+" WHERE "+tableUserContacts+"."+collContactUserId+" IS NULL";

        Log.d("QUERY",query);
        Cursor cursor = database.rawQuery(query,null);
        String[] cools =cursor.getColumnNames();
        int[] collsNum=new int[cools.length];
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<cools.length;i++)
        {
            collsNum[i]=cursor.getColumnIndex(cools[i]);
            Log.d("COLL", cools[i]);
            stringBuilder.append(cools[i]);
            stringBuilder.append("\t\t");
        }
        Log.d("ARR", Arrays.toString(collsNum)+" "+cools.toString());
        stringBuilder.append("\n");

        if(cursor.moveToFirst()){
            do{
                for(int i:collsNum){
                    stringBuilder.append(cursor.getString(i)).append("\t\t");
                }
                stringBuilder.append("\n");
            }while(cursor.moveToNext());

            Log.d("TASK",stringBuilder.toString());
            database.close();

        }
        return stringBuilder.toString();

    }
    public String updatePar(){

        SQLiteDatabase database= this.getReadableDatabase();
        final String query=
                "UPDATE "+tableTask+" SET "+collTaskText+
                " = 'САМАЯ РАННЯЯ ЗАПИСЬ ПОЛЬЗОВАТЕЛЯ С МАКСИМАЛЬНЫМ КОЛИЧЕСТВОМ  КОНТАКТОВ' WHERE "+collTaskLast_Editing+" = "+
                        " ( SELECT MIN ("+collTaskLast_Editing+") FROM "+tableTask+" WHERE "+collUserId+" IN "+
                "( SELECT MAX ("+collContactUserId+") FROM (SELECT "+
                collContactUserId+", COUNT ("+collConTactEmail+") FROM "+tableUserContacts+
                ") AS t2 ))" ;
        // "SELECT "+collEmail+", "+collId+" FROM "+tableUser+"  WHERE "+collId+" NOT IN (SELECT "+collContactUserId+" FROM "+tableUserContacts+")";

//                "SELECT "+collEmail+", "+collId+" FROM "+tableUser+" LEFT JOIN "+tableUserContacts+" ON "+tableUser+"."+collId+" = "+
//                        tableUserContacts+"."+collContactUserId+" WHERE "+tableUserContacts+"."+collContactUserId+" IS NULL";

        Log.d("QUERY",query);
        Cursor cursor = database.rawQuery(query,null);
        String[] cools =cursor.getColumnNames();
        int[] collsNum=new int[cools.length];
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<cools.length;i++)
        {
            collsNum[i]=cursor.getColumnIndex(cools[i]);
            Log.d("COLL", cools[i]);
            stringBuilder.append(cools[i]);
            stringBuilder.append("\t\t");
        }
        Log.d("ARR", Arrays.toString(collsNum)+" "+cools.toString());
        stringBuilder.append("\n");

        if(cursor.moveToFirst()){
            do{
                for(int i:collsNum){
                    stringBuilder.append(cursor.getString(i)).append("\t\t");
                }
                stringBuilder.append("\n");
            }while(cursor.moveToNext());

            Log.d("TASK",stringBuilder.toString());
            database.close();

        }
        return stringBuilder.toString();

    }
    public String union(){

        SQLiteDatabase database= this.getReadableDatabase();
        final String query=
               // " SELECT('МАКСИМУМ') UNION "+
                "SELECT "+collContactUserId+" FROM "+tableUserContacts+" GROUP BY " +collContactUserId+
                        " HAVING count(*)=( SELECT"/*+collContactUserId*/+" MAX (c) FROM (SELECT "+
                collContactUserId+", COUNT ("+collConTactEmail+") AS c FROM "+tableUserContacts+
                        ") AS t2 )";
                       // "UNION SELECT('МИНИМУМ')  "

//               " SELECT MIN * ,(c) FROM (SELECT "+
//        collContactUserId+", COUNT ("+collConTactEmail+") FROM "+tableUserContacts+
//                ") AS t1 "+" ";
        // "SELECT "+collEmail+", "+collId+" FROM "+tableUser+"  WHERE "+collId+" NOT IN (SELECT "+collContactUserId+" FROM "+tableUserContacts+")";

//                "SELECT "+collEmail+", "+collId+" FROM "+tableUser+" LEFT JOIN "+tableUserContacts+" ON "+tableUser+"."+collId+" = "+
//                        tableUserContacts+"."+collContactUserId+" WHERE "+tableUserContacts+"."+collContactUserId+" IS NULL";

        Log.d("QUERY",query);
        Cursor cursor = database.rawQuery(query,null);
        String[] cools =cursor.getColumnNames();
        int[] collsNum=new int[cools.length];
        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i<cools.length;i++)
        {
            collsNum[i]=cursor.getColumnIndex(cools[i]);
            Log.d("COLL", cools[i]);
            stringBuilder.append(cools[i]);
            stringBuilder.append("\t\t");
        }
        Log.d("ARR", Arrays.toString(collsNum)+" "+cools.toString());
        stringBuilder.append("\n");

        if(cursor.moveToFirst()){
            do{
                for(int i:collsNum){
                    stringBuilder.append(cursor.getString(i)).append("\t\t");
                }
                stringBuilder.append("\n");
            }while(cursor.moveToNext());

            Log.d("TASK",stringBuilder.toString());
            database.close();

        }
        return stringBuilder.toString();

    }
}