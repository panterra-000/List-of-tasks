package uz.rdo.projects.listoftasks.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import uz.rdo.projects.listoftasks.data.repositories.AllTasksRepository
import uz.rdo.projects.listoftasks.data.repositories.CompletedTasksRepository
import uz.rdo.projects.listoftasks.data.repositories.InProgressTasksRepository
import uz.rdo.projects.listoftasks.data.repositories.MainRepository
import uz.rdo.projects.listoftasks.ui.fragments.alltasks.AllTasksRepositoryImpl
import uz.rdo.projects.listoftasks.ui.fragments.completedtasks.CompletedTasksRepositoryImpl
import uz.rdo.projects.listoftasks.ui.fragments.inprogresstasks.InProgressTasksRepositoryImpl
import uz.rdo.projects.listoftasks.ui.mainactivity.MainRepositoryImpl
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getMainActivityRepository(repository: MainRepositoryImpl):MainRepository

    @Binds
    @Singleton
    fun getAllTasksRepository(repository: AllTasksRepositoryImpl):AllTasksRepository


    @Binds
    @Singleton
    fun getCompletedTasksRepository(repository: CompletedTasksRepositoryImpl):CompletedTasksRepository


    @Binds
    @Singleton
    fun getInProgressRepository(repository: InProgressTasksRepositoryImpl):InProgressTasksRepository
}