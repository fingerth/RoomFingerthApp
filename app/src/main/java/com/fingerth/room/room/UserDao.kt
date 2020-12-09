package com.fingerth.room.room

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface UserDao {
    /**
     *      1. OnConflictStrategy.REPLACE：冲突策略是取代旧数据同时继续事务。
     *      2. OnConflictStrategy.ROLLBACK：冲突策略是回滚事务。
     *      3. OnConflictStrategy.ABORT：冲突策略是终止事务。
     *      4. OnConflictStrategy.FAIL：冲突策略是事务失败。
     *      5. OnConflictStrategy.IGNORE：冲突策略是忽略冲突。
     */

    //  当@Insert注解的方法只有一个参数的时候，这个方法也可以返回一个long，当@Insert注解的方法有多个参数的时候则可以返回long[]或者r List<Long>。long都是表示插入的rowId。
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(vararg users: User)

    //@Update注解的方法也可以返回int变量。表示更新了多少行。
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUsers(vararg users: User)

    // @Delete对应的方法也是可以设置int返回值来表示删除了多少行。
    @Delete
    fun deleteUsers(vararg users: User?)


    //返回结果可以是数组，也可以是List。
    @Query("SELECT * FROM user")
    fun loadAllUsers(): Array<User>

    @Query("SELECT * FROM user WHERE firstName == :name")
    fun loadAllUsersByFirstName(name: String): List<User>

    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
    fun loadAllUsersBetweenAges(minAge: Int, maxAge: Int): Array<User>

    @Query("SELECT * FROM user WHERE firstName LIKE :search OR lastName LIKE :search")
    fun findUserWithName(search: String): List<User>

    @Query("SELECT firstName, lastName FROM user")
    fun loadFullName(): List<NameTuple>

//    @Query("SELECT firstName, lastName FROM user WHERE region IN (:regions)")
//    fun loadUsersFromRegions(regions: List<String>): List<NameTuple?>?
//
//
//    @Query("SELECT firstName, lastName FROM user WHERE region IN (:regions)")
//    fun loadUsersFromRegionsSync(regions: List<String?>?): LiveData<List<NameTuple?>?>?

    //多表查询
//    @Query("SELECT * FROM book INNER JOIN loan ON loan.book_id = book.id INNER JOIN user ON user.id = loan.user_id WHERE user.name LIKE :userName")
//    fun findBooksBorrowedByNameSync(userName: String?): MutableList<Book?>?
//
//    @Query("SELECT user.name AS userName, loan.name AS loanName FROM user, loan WHERE user.id = loan.user_id")
//    fun loadUserAndPetNames(): LiveData<List<UserPet?>?>?
}

class NameTuple {
    var firstName: String? = null
    var lastName: String? = null
}

class UserPet {
    var userName: String? = null
    var loanName: String? = null
}