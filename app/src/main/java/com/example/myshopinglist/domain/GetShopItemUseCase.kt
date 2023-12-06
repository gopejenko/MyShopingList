package com.example.myshopinglist.domain

class GetShopItemUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopItem(shopItem: ShopItem){
        return shopListRepository.getShopItem(shopItem)
        //TODO()
    }
}