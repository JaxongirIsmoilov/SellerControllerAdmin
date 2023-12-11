package uz.gita.jaxongir.sellmanageradmin.presenter.editProduct

import uz.gita.jaxongir.sellmanageradmin.utills.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface EditProductDirection {
    suspend fun back()
}

@Singleton
class EditProductDirectionImpl @Inject constructor(
    val appNavigator: AppNavigator,
) : EditProductDirection {
    override suspend fun back() {
        appNavigator.back()
    }
}
