package com.example.vesta.data.models.product

import com.example.vesta.data.models.OctStickers

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
class ProductResponse(
    @SerialName("attributes") val attributes: List<Attribute>?,
    @SerialName("category") val category: Category?,
    @SerialName("date_added") val dateAdded: String?,
    @SerialName("date_available") val dateAvailable: String?,
    @SerialName("date_modified") val dateModified: String?,
    @SerialName("description") val description: List<ProductDescription>?,
    @SerialName("ean") val ean: String?,
    @SerialName("height") val height: String?,
    @SerialName("image") val image: String?,
    @SerialName("images") val images: List<Image>?,
    @SerialName("isbn") val isbn: String?,
    @SerialName("jan") val jan: String?,
    @SerialName("last_sync_at") val lastSyncAt: String?,
    @SerialName("length") val length: String?,
    @SerialName("length_class_id") val lengthClassId: Int?,
    @SerialName("location") val location: String?,
    @SerialName("manufacturer") val manufacturer: Manufacturer?,
    @SerialName("manufacturer_id") val manufacturerId: Int?,
    @SerialName("minimum") val minimum: Int?,
    @SerialName("model") val model: String?,
    @SerialName("mpn") val mpn: String?,
    @SerialName("multistore_product") val multistoreProduct: MultistoreProduct?,
    @SerialName("oct_stickers") val octStickers: OctStickers?,
    @SerialName("ones_uuid") val onesUuid: String?,
    @SerialName("points") val points: Int?,
    @SerialName("product_id") val productId: Int?,
    @SerialName("quantity_len117") val quantityLen117: Int?,
    @SerialName("related") val related: List<Related>?,
    @SerialName("reviews") val reviews: List<Review>?,
    @SerialName("shipping") val shipping: Int?,
    @SerialName("sku") val sku: String?,
    @SerialName("sort_order") val sortOrder: Int?,
    @SerialName("status") val status: Int?,
    @SerialName("stock_status_id") val stockStatusId: Int?,
    @SerialName("store") val store: List<Store>?,
    @SerialName("subtract") val subtract: Int?,
    @SerialName("tax_class_id") val taxClassId: Int?,
    @SerialName("upc") val upc: String?,
    @SerialName("viewed") val viewed: Int?,
    @SerialName("weight") val weight: String?,
    @SerialName("weight_class_id") val weightClassId: Int?,
    @SerialName("width") val width: String?
)

@Serializable
class MultistoreProduct(
    @SerialName("date_added") val dateAdded: String?,
    @SerialName("date_modified") val dateModified: String?,
    @SerialName("id") val id: Int?,
    @SerialName("last_sync_at") val lastSyncAt: String?,
    @SerialName("price") val price: Int?,
    @SerialName("pricem") val priceM: Int?,
    @SerialName("pricep") val priceP: String?,
    @SerialName("pricer") val pricer: Int?,
    @SerialName("product_id") val productId: Int?,
    @SerialName("quantity") val quantity: Int?,
    @SerialName("spb1") val spb1: Int?,
    @SerialName("spb2") val spb2: Int?,
    @SerialName("spb3") val spb3: Int?,
    @SerialName("spb4") val spb4: Int?,
    @SerialName("spb5") val spb5: Int?,
    @SerialName("spb6") val spb6: Int?,
    @SerialName("spb7") val spb7: Int?,
    @SerialName("spb8") val spb8: Int?,
    @SerialName("spb9") val spb9: Int?,
    @SerialName("spb10") val spb10: Int?,
    @SerialName("spb11") val spb11: Int?,
    @SerialName("spb12") val spb12: Int?,
    @SerialName("spb13") val spb13: Int?,
    @SerialName("spb14") val spb14: Int?,
    @SerialName("spb15") val spb15: Int?,
    @SerialName("spb16") val spb16: Int?,
    @SerialName("stock_status_id") val stockStatusId: Int?,
    @SerialName("store_id") val storeId: Int?
)