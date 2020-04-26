package com.mr2.mvvm_test.sample;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mr2.mvvm_test.sample.live_data_by_room.ExampleDao;
import com.mr2.mvvm_test.sample.live_data_by_room.ExampleData;
import com.mr2.mvvm_test.sample.room_sample.Item;
import com.mr2.mvvm_test.sample.room_sample.ItemDao;

import java.util.List;

/**
 * ・RoomDatabase を拡張する抽象クラスであること。
 * ・データベースに関連付けられているエンティティのリストをアノテーション内に含ること。
 * ・引数が 0 で、@Dao アノテーション付きのクラスを返す抽象メソッドを含むこと。
 * 以上三つの条件を満たす必要がある。
 * 実行時に Room.databaseBuilder() または Room.inMemoryDatabaseBuilder() を呼び出すことで、
 * Database のインスタンスを取得できる。
 *
 * (version = 1): テーブル(Entity)の設計を変更した場合にバージョンを上げる。
 *
 * (exportSchema = true): データベースのスキーマの履歴をプロジェクトのサブフォルダにエクスポートさせることができる。
 * trueに設定する場合、モジュールレベルのbuild.gradleの、 android -> defaultConfig -> javaCompileOptions
 * -> annotationProcessorOptions 内に以下の行を追加する。
 *  arguments = ["room.schemaLocation": "$projectDir/schemas".toString()]
 */
@Database(entities = {Item.class, ExampleData.class}, version = 1, exportSchema = true)
public abstract class MyDatabase extends RoomDatabase { //TODO: version上げないとかも
    private static MyDatabase instance;

    /**
     * DBのインスタンスは基本的にシングルトンパターンにしたほうがよい。
     */
    public static MyDatabase getInstance(Context context){
        if (null != instance) return instance;
        instance = Room.databaseBuilder(context,
                MyDatabase.class,
                "my_database").build();
        return instance;
    }

    /**
     * Daoインターフェースの実装を受け取る抽象メソッド。
     * メソッドの実装はRoomが生成してくれる。
     * @return Dao
     */
    public abstract ItemDao itemDao();
    public abstract ExampleDao exampleDao();

    /**
     * RoomDatabase:
     * シングルトンパターンでインスタンスを取得し、Daoを取り出す。
     * Daoのメソッドはワーカースレッドで叩かないと怒られる。
     *
     * tips:
     * ワーカーからUIの絡むコールバック等を行いたい場合は
     * Activity::runOnUiThread(Runnable)等を使う。
     * （ActivityやFragmentは不定期に死ぬことを忘れない事）
     * ちゃんとやりたいならLoaderを使ったほうがいい。
     */
    public void setupDefaultData(Context context){
        ItemDao dao = getInstance(context).itemDao();
        new Thread(() -> {
            List<Item> list = dao.getAll();
            if (0 == list.size()){
                for (int i = 0; 30 > i; i++){
                    dao.insert(new Item("sample item: " + i));
                }
            }
        }).start();
    }
}
