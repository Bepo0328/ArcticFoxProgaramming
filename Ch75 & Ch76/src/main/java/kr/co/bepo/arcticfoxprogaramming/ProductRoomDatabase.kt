package kr.co.bepo.arcticfoxprogaramming

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductRoomDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao


    companion object {
        private var INSTANCE: ProductRoomDatabase? = null

        internal fun getDatabase(context: Context): ProductRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(ProductRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            Room.databaseBuilder<ProductRoomDatabase>(
                                context.applicationContext,
                                ProductRoomDatabase::class.java,
                                "product_database").build()
                    }
                }
            }

            return INSTANCE
        }
    }

}