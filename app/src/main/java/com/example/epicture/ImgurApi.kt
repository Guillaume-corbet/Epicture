package com.example.epicture

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ImgurApi {

    @GET("/3/gallery/{section}/{sort}/{pages}/week")
    fun getGallery(
        @Header("Authorization") authHeader: String,
        @Path("section") section: String,
        @Path("sort") sort: String,
        @Path("pages") pages: Int,
        @Query("showViral") showViral: Boolean,
        @Query("mature") mature: Boolean,
        @Query("album_previews") album_previews: Boolean
    ): Call<ModelImgur.SearchData>

    @POST("/post/v1/posts/{imageHash}/favorite")
    fun favoriteImage(
        @Header("Authorization") authHeader: String,
        @Path("imageHash") imageId: String
    ): Call<ModelImgur.FavotiteImageData>

    @DELETE("/3/image/{{imageDeleteHash}}")
    fun deleteImage(
        @Header("Authorization") authHeader: String,
        @Path("imageDeleteHash") imageId: String
    ): Call<ModelImgur.FavotiteImageData>

    @GET("/3/account/me/images")
    fun getAccountImage(@Header("Authorization") authHeader: String): Call<ModelImgur.ImageAccount>

    @GET("/3/account/{username}/gallery_favorites/{page}/{favoriteSort}")
    fun getAccountFavoriteImage(
        @Header("Authorization") authHeader: String,
        @Path("username") username: String,
        @Path("page") page: Int,
        @Path("favoriteSort") favoriteSort: String
    ): Call<ModelImgur.FavoriteData>

    @Multipart
    @POST("3/upload")
    fun uploadImage(
        @Header("Authorization") authHeader: String,
        @Part image: MultipartBody.Part,
        @PartMap queries: Map<String, @JvmSuppressWildcards RequestBody>
    ): Call<ModelImgur.Upload>

    @GET("/3/gallery/search/{page}")
    fun GetGallerySearch(@Header("Authorization") authHeader: String,
                      @Path("page") page: Int,
                      @Query("q") query: String): Call<ModelImgur.SearchData>
}