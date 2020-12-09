package com.fingerth.room

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fingerth.room.room.AppDatabase


class App : Application() {

    var mAppDatabase: AppDatabase? = null
        private set


    override fun onCreate() {
        super.onCreate()

        //1.最正确的做法
        mAppDatabase =
            Room.databaseBuilder(applicationContext, AppDatabase::class.java, "android_room_dev.db")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()

        //2.不推荐的做法
//        mAppDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "android_room_dev.db")
//            .allowMainThreadQueries()
//            .fallbackToDestructiveMigration()
//            .build()

    }

    /**
     * 数据库版本 1->2 user表格新增了age列
     */
    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE User ADD COLUMN age integer")
        }
    }

    /**
     * 数据库版本 2->3 新增book表格
     */
    val MIGRATION_2_3: Migration = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `book` (`uid` INTEGER PRIMARY KEY autoincrement, `name` TEXT , `userId` INTEGER, 'time' INTEGER)")
        }
    }


    /**
     *     当数据库里面表有变化的时候(不管你是新增了表，还是改变了某个表)有如下几个场景。
     *     如果database的版本号不变。app操作数据库表的时候会直接crash掉。(错误的做法)
     *     如果增加database的版本号。但是不提供Migration。app操作数据库表的时候会直接crash掉。（错误的做法）
     *     如果增加database的版本号。同时启用fallbackToDestructiveMigration。这个时候之前的数据会被清空掉。如下fallbackToDestructiveMigration()设置。(不推荐的做法)
     *     增加database的版本号，同时提供Migration。这要是Room数据迁移的重点。(最正确的做法)
     */
}