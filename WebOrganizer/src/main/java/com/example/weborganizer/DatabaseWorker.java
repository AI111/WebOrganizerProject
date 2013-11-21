package com.example.weborganizer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.list2.R;
import com.example.weborganizer.Containers.Filter;
import com.example.weborganizer.Containers.Task;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sasha on 04.11.13.
 */
public class DatabaseWorker extends SQLiteOpenHelper{

    private Context context;
    //PERSON_TABLE
    static  final String tableUser="User";
    static  final String collId = "id";
    static final String collEmail="email";
    static final String collPass="pass";
    //TASKS
    static  final String tableTask="Task";
    static  final  String collUserId="id_User";
    static final String collTaskTitle="Task_title";
    static  final  String collTaskText="text";
    static  final  String collTaskTime="task_time";
    static final String collTaskDate="task_date";
    static  final  String collTaskLast_Editing="Last_Editing";
    static final String collTaskEditType="Editing_Type";
    static  final  String collTaskFilter="Filter_Id";
    //FILTERS
    static final  String tableFilter="Filters";
    static  final String collFilterName="Filter_Name";
    static final String collFiltrId="id";
    static  final  String collFilterUserId="UserId";
    //Contacts
    static  final String tableContacts="Contacts";
    static  final String collContactName="Name";
    static  final String collContactId="Contact_id";
    //USER_OPTIONS
    static  final  String tableUserOptions="UserOptions";
    static final String collUserOptionsId="User_id";
    static final String collUserOptionsLastDateSync="Last_Sync_Date";

    private final static String PERSON_TABLE="CREATE TABLE "+tableUser+" ("+collId+" INTEGER PRIMARY KEY NOT NULL, "+
            collEmail+" CHAR(50),"+collPass+" char(20), "+collFilterUserId+" INTEGER , FOREIGN KEY ("+
            collFilterUserId+") REFERENCES "+tableUser+" ("+collId+"));";

    private static final String FILTERS="CREATE TABLE "+tableFilter+" ("+collFilterName+" VARCHAR(40), "
            +collFiltrId+" INTEGER PRIMARY KEY  AUTOINCREMENT, "+collFilterUserId+" INTEGER , FOREIGN KEY ("
            +collFilterUserId+") REFERENCES "+tableUser+" ("+collId+"));";

    private final static String TASKS="CREATE TABLE "+tableTask+" ("+collTaskTitle+" VARCHAR(80), "+collTaskText+" Text, "+collTaskTime
            +" TIME, "+collTaskDate+" DATE, "+collTaskLast_Editing+" TIME, "+collTaskEditType+" TINYINT, "+
            collTaskFilter +" INTEGER  NOT NULL, "+collUserId+" INTEGER NOT NULL, FOREIGN KEY ("
            +collUserId+") REFERENCES "+tableUser+" ("+collId+"), "+" FOREIGN KEY ("+collTaskFilter+
            ") REFERENCES "+tableFilter+" ("+collFiltrId+"));";

    private  static final String USER_OPTIONS="CREATE TABLE "+tableUserOptions+" ("+collUserOptionsId+
            " INTEGER NOT NULL, "+collUserOptionsLastDateSync+" DATETIME);";

    static final String DATABASE_NAME="ClientDB";
    public DatabaseWorker(Context context) {
        super(context, DATABASE_NAME, null,1);
        this.context=context;
    }

//    @Override
//    public void onConfigure(SQLiteDatabase db) {
//        db.execSQL("PRAGMA foreign_keys=ON");
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     //   db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL(PERSON_TABLE);
        db.execSQL(TASKS);

        db.execSQL(FILTERS);
        db.execSQL(USER_OPTIONS);
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
                "1"
                +"');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+PERSON_TABLE);
        db.execSQL("DROP TABLE "+TASKS);
//        db.execSQL("DROP TABLE "+CONTACTS);
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
//    public LinkedList<Task> getTasks()
//        {
//            LinkedList<Task>ans=new LinkedList<Task>();
//            SQLiteDatabase database= this.getReadableDatabase();
//         //Cursor cursor  = database.rawQuery("SELECT *  FROM "+tableTask,null);
//            Cursor cursor  = database.rawQuery("SELECT *  FROM "+tableTask+" INNER JOIN "+tableFilter+
//                " ON "+tableTask+"."+collTaskFilter+" = "+tableFilter+"."+collFiltrId+"",null);
//            for(String i:cursor.getColumnNames()){
//            Log.d("COLLS",i);
//        }
//
//        int col1 =cursor.getColumnIndex(collTaskTitle);
//        int col2=cursor.getColumnIndex(collTaskText);
//        int col3 =cursor.getColumnIndex(collTaskTime);
//        int col4 =cursor.getColumnIndex(collTaskDate);
//        int col5=cursor.getColumnIndex(collTaskLast_Editing);
//        int col6 =cursor.getColumnIndex(collTaskEditType);
//        int col7 =cursor.getColumnIndex(collFiltrId);
//        int col8=cursor.getColumnIndex(collFilterName);
//        int col9=cursor.getColumnIndex(collUserId);
//        if(cursor.moveToFirst()){
//            do{
//
//                ans.add(new Task(cursor.getString(col1),cursor.getString(col2),cursor.getString(col3),cursor.getString(col4),
//                        cursor.getString(col5),(byte)cursor.getShort(col6),cursor.getInt(col7),cursor.getString(col8),cursor.getInt(col8)));
//
//            }while(cursor.moveToNext());
//
//        }
//        database.close();
//        return ans;
//    }
public ArrayList<Task> getTasks()
            {
            ArrayList<Task> ans=new ArrayList<Task>();
            SQLiteDatabase database= this.getReadableDatabase();
         Cursor cursor  = database.rawQuery("SELECT *  FROM "+tableTask,null);

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
    public void insertFilter(int user_id,String filterName)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        try{

            database.execSQL("INSERT INTO "+tableFilter+" ("+collFilterName+", "+collFilterUserId+")"+
                    " VALUES ('"+filterName+"', '"+ 0 +"');");
        }catch (Exception e){

        }
        finally {
            database.close();
        }
    }
    public void insertTask(Task task)
    {

        SQLiteDatabase database= this.getWritableDatabase();
        try{

        database.execSQL("INSERT INTO "+tableTask+" ("+collTaskTitle+", "+collTaskText+", "+collTaskTime+", "+collTaskDate+", "+
                collTaskLast_Editing+", "+collTaskEditType+", "+collTaskFilter+", "+collUserId+")"+
                " VALUES ('"+task.taskTitle+"', '"+task.taskText+"', '"+task.taskTime+"', '"+task.taskDate
                +"', "+"strftime('%s', 'now')"+", '"+"0"+"', '"+task.taskFilterId+"', '"+task.userId+"');");
        }catch (Exception e){

        }
        finally {
            database.close();
        }
    }
    public ArrayList<ArrayList<Task>>  getLists(int filterGroupe)
    {
        ArrayList<ArrayList<Task>> ans = new ArrayList<ArrayList<Task>>();
        SQLiteDatabase database= this.getReadableDatabase();
        String[] querys={   "SELECT *  FROM "+tableTask+" WHERE "+collTaskFilter+" = '"+filterGroupe+"' AND "+collTaskDate+" < DATE('now') ORDER BY "+collTaskTime,
                            "SELECT *  FROM "+tableTask+" WHERE "+collTaskFilter+" = '"+filterGroupe+"' AND "+collTaskDate+" = DATE('now') ORDER BY "+collTaskTime,
                            "SELECT *  FROM "+tableTask+" WHERE "+collTaskFilter+" = '"+filterGroupe+"' AND "+collTaskDate+" = DATE('now', '+1 day') ORDER BY "+collTaskTime,
                            "SELECT *  FROM "+tableTask+" WHERE "+collTaskFilter+" = '"+filterGroupe+"' AND "+collTaskDate+" > DATE('now', '+1 day') ORDER BY "+collTaskTime
        };
//+collTaskFilter+" = "+filterGroupe+" AND "+collTaskDate

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
                collTaskFilter+" = '"+task.taskFilterId+"', "+collUserId+" = '"+task.userId+"' WHERE "+
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
}
