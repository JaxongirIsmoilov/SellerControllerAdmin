package uz.gita.jaxongir.sellmanageradmin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.jaxongir.sellmanageradmin.presenter.addProduct.AddProductDirection
import uz.gita.jaxongir.sellmanageradmin.presenter.addProduct.AddProductDirectionImpl
import uz.gita.jaxongir.sellmanageradmin.presenter.addSeller.AddSellerDirection
import uz.gita.jaxongir.sellmanageradmin.presenter.addSeller.AddSellerScreenDirectionImpl
import uz.gita.jaxongir.sellmanageradmin.presenter.editSeller.EditDirection
import uz.gita.jaxongir.sellmanageradmin.presenter.editSeller.EditDirectionImpl
import uz.gita.jaxongir.sellmanageradmin.presenter.login.LoginDirection
import uz.gita.jaxongir.sellmanageradmin.presenter.login.LoginDirectionImpl
import uz.gita.jaxongir.sellmanageradmin.presenter.products.ProductsDirection
import uz.gita.jaxongir.sellmanageradmin.presenter.products.ProductsDirectionImpl
import uz.gita.jaxongir.sellmanageradmin.presenter.seller.SellerDirection
import uz.gita.jaxongir.sellmanageradmin.presenter.seller.SellerDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {
    @Binds
    fun bindsLoginDirection(impl: LoginDirectionImpl): LoginDirection

    @Binds
    fun bindsSellerDirection(impl: SellerDirectionImpl): SellerDirection

    @Binds
    fun bindsAddSellerDirection(impl: AddSellerScreenDirectionImpl): AddSellerDirection

    @Binds
    fun bindsEditSellerDirection(impl : EditDirectionImpl) : EditDirection

    @Binds
    fun bindsAddProductDirection(impl : AddProductDirectionImpl) : AddProductDirection

    @Binds
    fun bindsProductsDirection(impl : ProductsDirectionImpl) : ProductsDirection
}