package br.com.harpa_crista.Helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelperHarpa extends SQLiteOpenHelper {

    public static String DBPATH ="";
    //"/data/data/br.com.nazareno/databases/";
    public static String DBNOME = "harpa.sqlite";
    private static final int VERSION = 1;
    public SQLiteDatabase myDataBase;
    private final Context myContext;
    private boolean mNeedUpdate = false;
    public static final String TAB_HINOS = "hinos";


    public DbHelperHarpa(Context context) {
        super(context, DBNOME, null, VERSION);
        if(android.os.Build.VERSION.SDK_INT >= 17){
            DBPATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DBPATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;

        copyDataBase();

        this.getReadableDatabase();;
    }

    public void updateDataBase() throws IOException {
        if (mNeedUpdate) {
            File dbFile = new File(DBPATH + DBNOME);
            if (dbFile.exists())
                dbFile.delete();

            copyDataBase();

            mNeedUpdate = false;
        }
    }

    private boolean checkDataBase() {
        File dbFile = new File(DBPATH + DBNOME);
        return dbFile.exists();
    }

    private void copyDataBase() {
        if (!checkDataBase()) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDBFile();
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    private void copyDBFile() throws IOException {
        InputStream mInput = myContext.getAssets().open(DBNOME);
        OutputStream mOutput = new FileOutputStream(DBPATH + DBNOME);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0)
            mOutput.write(mBuffer, 0, mLength);
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDataBase() throws SQLException {
        myDataBase = SQLiteDatabase.openDatabase(DBPATH + DBNOME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return myDataBase != null;
    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            mNeedUpdate = true;
    }

}
