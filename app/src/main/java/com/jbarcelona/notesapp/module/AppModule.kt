package com.jbarcelona.notesapp.module

import android.content.Context
import androidx.room.Room
import com.jbarcelona.notesapp.constants.Constants
import com.jbarcelona.notesapp.database.NoteDb
import com.jbarcelona.notesapp.database.dao.NoteDao
import com.jbarcelona.notesapp.repository.BaseRepository
import com.jbarcelona.notesapp.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDb {
        return Room.databaseBuilder(
            context,
            NoteDb::class.java,
            Constants.DB_NAME
        ).allowMainThreadQueries().build()
    }

    @Singleton
    @Provides
    fun provideMainRepository(
        noteDao: NoteDao,
    ) = MainRepository(noteDao) as BaseRepository

    @Singleton
    @Provides
    fun provideNotDao(noteDb: NoteDb) = noteDb.noteDao()
}