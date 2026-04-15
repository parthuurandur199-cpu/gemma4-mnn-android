package com.tiggy.gemma4mnn.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatMessageDao {

    @Query("SELECT * FROM chat_message WHERE sessionId = :sessionId ORDER BY orderIndex ASC")
    fun getMessagesForSession(sessionId: Long): Flow<List<ChatMessageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: ChatMessageEntity): Long

    // CHANGE: Added : List<Long>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<ChatMessageEntity>): List<Long>

    // CHANGE: Added : Int
    @Query("DELETE FROM chat_message WHERE sessionId = :sessionId")
    suspend fun deleteMessagesForSession(sessionId: Long): Int

    // CHANGE: Added : Int
    @Query("UPDATE chat_message SET content = :content WHERE id = :messageId")
    suspend fun updateMessageContent(messageId: Long, content: String): Int
}
