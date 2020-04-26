package com.mr2.mvvm_test.sample.room_sample;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/**
 * Room用データモデルの作成にはクラス定義の頭に@Entityアノテーションを付ける。
 * (tableName = "tableName"): SQLiteに作成されるテーブル名の指定。指定しない場合Entity名がそのまま使われる。
 * (primaryKeys = {"firstName", "lastName"}): PrimaryKey複数指定
 */
@Entity
public class Item {
    /**
     * PrimaryKeyの設定は必須。@PrimaryKeyアノテーションを付ける。
     * (autoGenerate = true): サロゲートキーとしてオートインクリメント
     */
    @PrimaryKey (autoGenerate = true)
    public int _id;
    /**
     * \@ColumnInfo(name = "name"): SQLiteに作成されるカラム名の指定。指定しない場合フィールド名がそのまま使われる。
     * 基本的にSQLiteで使用可能なデータ型のみ対応している。TEXT、INTEGER、
     * 独自クラスを指定したい場合、@TypeConverterの設定が必要になる。
     *
     * エンティティに永続化したくないフィールドがある場合は、@Ignoreアノテーションを付ける。
     */
    @NonNull
    public String name;

    public Item(@NonNull String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return _id == item._id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_id);
    }
}
