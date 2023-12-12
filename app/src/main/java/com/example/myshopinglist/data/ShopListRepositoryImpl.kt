package com.example.myshopinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myshopinglist.domain.ShopItem
import com.example.myshopinglist.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl : ShopListRepository {

    private val shopListLD = MutableLiveData<List<ShopItem>>()
  //  private val shopList = mutableListOf<ShopItem>()
    private val shopList = sortedSetOf<ShopItem>({ o1, o2 ->
      o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0

    init {
        for (i in 0 until  1000){
            val item = ShopItem("Name $i", i, Random.nextBoolean())
            addShopItem(item)
        }
    }

    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
        updateList()
        //TODO("Not yet implemented")
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
        updateList()
        //TODO("Not yet implemented")
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
      //  TODO("Not yet implemented")
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): LiveData<List<ShopItem>> {
        return shopListLD
        //TODO("Not yet implemented")
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}