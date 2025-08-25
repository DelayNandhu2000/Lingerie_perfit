package com.genxlead.lingeriePer_FitApp.ui.base.core

import android.util.Log
import androidx.core.content.edit
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

interface FireBaseDatabaseRepository {
    suspend fun getAppUpdateConfig(): AppUpdateData
}

data class Update(
    val update: AppUpdateData = AppUpdateData()
)

data class AppUpdateData(
    val forceUpdate: Boolean = false,
    val link: String = "",
    val minimum_required_version: Long = 0L,
    val update_message: String = ""
)

class FireBaseDatabaseRepositoryImpl(
    private val database: FirebaseDatabase
) : FireBaseDatabaseRepository {

    override suspend fun getAppUpdateConfig(): AppUpdateData {
        return suspendCancellableCoroutine { continuation ->
            val reference = database.getReference("LingeriePerfit_iOS")
            reference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val config = snapshot.getValue(Update::class.java)
                    continuation.resume(config?.update ?: Update().update)
                }

                override fun onCancelled(error: DatabaseError) {
                    continuation.resumeWithException(error.toException())
                }
            })
        }
    }
}



