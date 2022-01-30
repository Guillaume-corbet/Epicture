package com.example.epicture

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavorisFragment: Fragment() {

    var access_token = arguments?.get("access_token")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.favoris_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.likedUnlikeBtn).visibility = View.GONE

        getAccountFavoriteImage()

    }


    /* CLASSES */

    class PhotoVH(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var photo: ImageView? = null
        var title: TextView? = null
        var unlike: Button? = null

        fun PhotoVH(itemView: View?) {
            super.itemView
        }
    }

    class Photo {
        var id: String? = null
        var title: String? = null
        var url: String? = null
        var favoris: Boolean? = null
    }


    private val photos: MutableList<Photo> = ArrayList<Photo>()

    private var adapter: RecyclerView.Adapter<PhotoVH> =
        object : RecyclerView.Adapter<PhotoVH>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): PhotoVH {
                val vh = PhotoVH(
                    layoutInflater.inflate(
                        R.layout.favoris_fragment,
                        null
                    )
                )

                vh.photo = vh.itemView.findViewById<View>(R.id.accountPhotoFavoris) as ImageView
                vh.title = vh.itemView.findViewById(R.id.accTitleFavoris) as TextView
                vh.unlike = vh.itemView.findViewById(R.id.likedUnlikeBtn) as Button
                return vh
            }

            override fun onBindViewHolder(holder: PhotoVH, position: Int) {
                Picasso.with(activity?.applicationContext)
                    .load(photos[position].url.toString())
                    .into(holder.photo)
                holder.title?.text = photos[position].title

                if (photos[position].favoris == true) {
                    holder.itemView.findViewById<Button>(R.id.likedUnlikeBtn).text = "Unlike"
                } else {
                    holder.itemView.findViewById<Button>(R.id.likedUnlikeBtn).text = "Like"
                }

                holder.itemView.findViewById<Button>(R.id.likedUnlikeBtn).setOnClickListener {
                    var id = photos[position].id.toString()
                    favoriteImage(id)
                    if (holder.itemView.findViewById<Button>(R.id.likedUnlikeBtn).text == "Unlike") {
                        holder.itemView.findViewById<Button>(R.id.likedUnlikeBtn).text = "Like"
                    } else {
                        holder.itemView.findViewById<Button>(R.id.likedUnlikeBtn).text = "Unlike"
                    }
                }
            }

            override fun getItemCount(): Int {
                return photos.size
            }

        }

    fun render(photos: List<Photo>) {

        val rv = view!!.findViewById(R.id.accRecyclerFavoris) as? RecyclerView
        rv?.layoutManager = LinearLayoutManager(activity?.applicationContext)
        rv?.adapter = adapter

        rv?.addItemDecoration(object : RecyclerView.ItemDecoration() {
            fun getItemOffset(
                outRect: Rect,
                view: View?,
                parent: RecyclerView?,
                state: RecyclerView.State?
            ) {
                outRect.bottom = 16
            }
        })
    }

    private fun getAccountFavoriteImage() {
        println("Fav")
        val imgurApi = Retrofit().createRetrofitBuilder()
        val token = arguments?.get("access_token")
        val call = imgurApi.getAccountFavoriteImage(
            "Bearer $token",
            arguments?.get("account_username").toString(),
            0,
            "newest"
        )
        call.enqueue(object : Callback<ModelImgur.FavoriteData> {
            override fun onFailure(call: Call<ModelImgur.FavoriteData>, t: Throwable) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ModelImgur.FavoriteData>,
                response: Response<ModelImgur.FavoriteData>
            ) {
                try {
                    if (response.isSuccessful) {
                        val body = response.body()
                        val data = body!!.data



                        for (i in data.indices) {

                            val item = data[i]
                            val photo = Photo()

                            photo.id = item.id
                            photo.title = item.title
                            photo.url = item.images[0].link
                            photo.favoris = item.favorite
                            photos.add(photo)
                        }

                        render(photos)
                    } else {
                        println(response.errorBody())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    fun favoriteImage(id: String) {
        val imgurApi = Retrofit().createRetrofitBuilder()
        val call = imgurApi.favoriteImage("Bearer $access_token", id)
        call.enqueue(object : Callback<ModelImgur.FavotiteImageData> {
            override fun onFailure(call: Call<ModelImgur.FavotiteImageData>, t: Throwable) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ModelImgur.FavotiteImageData>,
                response: Response<ModelImgur.FavotiteImageData>
            ) {
                try {
                    if (response.isSuccessful) {
                        print("success: favorite image")
                    } else {
                        println("error: " + response.errorBody())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }
}