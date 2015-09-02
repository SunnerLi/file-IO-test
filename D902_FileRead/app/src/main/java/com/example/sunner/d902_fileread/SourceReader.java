package com.example.sunner.d902_fileread;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sunner on 2015/9/2.
 */
public class SourceReader {
    //隨時變動參數
    String placeName;
    double placeX, placeY;

    Context mContext = null;

    //建構式
    public SourceReader(Context context) {
        mContext = context;
    }

    public void sourceProcess() {
        Resources resources;    //資源檔物件
        InputStream inputStream; //輸入串流物件

        //藉由資源檔初始化輸入串流物件
        resources = mContext.getResources();

        //讀取test檔案
        inputStream = resources.openRawResource(R.raw.test);
        readFile(inputStream);

        //最後必定做檔案關閉
        try {
            inputStream.close();
            Log.i("main", "檔案成功關閉");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //定義讀取檔案
    public void readFile(InputStream inputStream) {
        String s;

        //讀取檔案
        try {
            //估計有幾個字
            byte[] buffer = new byte[inputStream.available()];

            //讀取
            inputStream.read(buffer);

            //byte[]轉字串
            s = new String(buffer);

            Log.i("main", "讀取內容：" + s);

            //字串細部處理
            stringSplit(s);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //字串細部處理
    public void stringSplit(String string) {
        int emptyPosition = 0, commaPosition = 0;

        //轉換參考：http://examples.javacodegeeks.com/core-java/character/how-to-convert-character-to-string-and-a-string-to-character-array-in-java/
        char[] stringToChar = string.toCharArray();

        //判斷第一個空白位置
        for (; stringToChar[emptyPosition] != ' '; emptyPosition++) ;

        //決定地點
        placeName = string.substring(0, emptyPosition);

        //判斷逗號位置
        for (; stringToChar[commaPosition] != ','; commaPosition++) ;

        //決定座標
        placeX = Double.valueOf(string.substring(emptyPosition + 1, commaPosition));
        placeY = Double.valueOf(string.substring(commaPosition + 1, string.length()));

        Log.i("字串分解", "\n地點:" + placeName + "\n座標：" + " ( " + placeX + " , " + placeY + " ) ");
    }
}
