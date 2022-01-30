package com.example.epicture

class ModelImgur {

    data class DataSearch(
        val id: String,
        val title: String,
        val description: String,
        val datetime: Int,
        val type: String,
        val animated: Boolean,
        val width: Int,
        val height: Int,
        val size: Int,
        val views: Int,
        val bandwidth: Long,
        val vote: Int,
        val favorite: Boolean,
        val nsfw: Boolean,
        val section: String,
        val account_url: String,
        val account_id: String,
        val is_ad: Boolean,
        val in_most_viral: Boolean,
        val has_sound: Boolean,
        val tags: List<DataTags>,
        val images: List<ImageData>,
        val link: String
    )

    data class DataTags(
        val name: String,
        val display_name: String,
        val followers: Int,
        val total_items: Int,
        val following: Boolean,
        val background_hash: String,
        val thumbnail_hash: String,
        val accent: String,
        val background_is_animated: Boolean,
        val thumbnail_is_animated: Boolean,
        val is_promoted: Boolean,
        val description: String,
        val logo_hash: String,
        val logo_destination_url: String
    )

    data class ImageData(
        val id: String,
        val title: String,
        val description: String,
        val datetime: Long,
        val type: String,
        val animated: Boolean,
        val width: Int,
        val height: Int,
        val size: Long,
        val views: Int,
        val bandwidth: Long,
        val vote: String,
        val favorite: Boolean,
        val nsfw: String,
        val section: String,
        val account_url: String,
        val account_id: Long,
        val is_ad: Boolean,
        val has_sound: Boolean,
        val tags: List<String>,
        val in_gallery: Boolean,
        val deletehash: String,
        val name: String,
        val link: String,
        val comment_count: Int,
        val favorite_count: Int,
        val ups: Int,
        val downs: Int,
        val score: Int
    )

    data class DataFavorite(
        val id: String,
        val title: String,
        val description: String,
        val datetime: Int,
        val type: String,
        val animated: Boolean,
        val width: Int,
        val height: Int,
        val size: Int,
        val views: Int,
        val bandwidth: Long,
        val vote: Int,
        val favorite: Boolean,
        val nsfw: Boolean,
        val section: String,
        val account_url: String,
        val account_id: String,
        val is_ad: Boolean,
        val has_sound: Boolean,
        val tags: List<DataTags>,
        val images: List<ImageData>,
        val link: String,
        val in_gallery: Boolean,
        val comment_count: Int,
        val favorite_count: Int,
        val ups: Int,
        val downs: Int,
        val points: Int,
        val is_album: Boolean
    )

    data class Data(
        val id: String,
        val title: String,
        val description: String,
        val datetime: Long,
        val type: String,
        val animated: Boolean,
        val width: Int,
        val height: Int,
        val size: Long,
        val views: Int,
        val bandwidth: Long,
        val vote: String,
        val favorite: Boolean,
        val nsfw: String,
        val section: String,
        val account_url: String,
        val account_id: Long,
        val is_ad: Boolean,
        val has_sound: Boolean,
        val is_most_viral: Boolean,
        val tags: List<String>,
        val in_gallery: Boolean,
        val deletehash: String,
        val name: String,
        val link: String
    )

    data class uploadData(
        val id: String
    )

    data class SearchData (val data: List<DataSearch>, val success: Boolean, val status: String)

    data class SearchDataOne (val data: DataSearch)

    data class FavoriteData (val data: List<DataFavorite>, val success: Boolean, val status: String)

    data class FavoriteDataOneItem (val data: DataFavorite)

    data class FavotiteImageData (val data: String, val success: Boolean, val status: String)

    data class ImageAccount (val data: List<Data>, val success: Boolean, val status: String)

    data class ImageAccountOneItem (val data: Data)

    data class Upload(val data: uploadData, val success: Boolean, val status: String)

}