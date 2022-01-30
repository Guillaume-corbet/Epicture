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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class ProfileFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profil_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.accImgDelete).visibility = View.GONE

        getAccountImage()

    }


    /* CLASSES */

    class PhotoVH(itemView: View?) : RecyclerView.ViewHolder(
        itemView!!
    ) {
        var photo: ImageView? = null
        var title: TextView? = null
        var delete: Button? = null

        fun PhotoVH(itemView: View?) {
            super.itemView
        }
    }

    class Photo {
        var id: String? = null
        var title: String? = null
        var url: String? = null
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
                        R.layout.profil_layout,
                        null
                    )
                )

                vh.photo = vh.itemView.findViewById<View>(R.id.accountPhoto) as ImageView
                vh.title = vh.itemView.findViewById(R.id.accTitle) as TextView
                vh.delete = vh.itemView.findViewById(R.id.accImgDelete) as Button
                return vh
            }

            override fun onBindViewHolder(holder: PhotoVH, position: Int) {
                Picasso.with(activity?.applicationContext)
                    .load(photos[position].url.toString())
                    .into(holder.photo)
                holder.title?.text = photos[position].title

                holder.itemView.findViewById<Button>(R.id.accImgDelete).setOnClickListener {
                    var id = photos[position].id.toString()
                    deleteImage(id)
                }
            }

            override fun getItemCount(): Int {
                return photos.size
            }

        }

    fun render(photos: List<Photo>) {

        val rv = view!!.findViewById(R.id.accRecycler) as? RecyclerView
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


    private fun getAccountImage() {
        val imgurApi = Retrofit().createRetrofitBuilder()
        val token = arguments?.get("access_token").toString()
        val call = imgurApi.getAccountImage("Bearer $token")
        call.enqueue(object : Callback<ModelImgur.ImageAccount> {
            override fun onFailure(call: Call<ModelImgur.ImageAccount>, t: Throwable) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ModelImgur.ImageAccount>,
                response: Response<ModelImgur.ImageAccount>
            ) {
                try {
                    if (response.isSuccessful) {
                        val body = response.body()
                        val data = body!!.data

                        for (i in data.indices) {

                            val item = data[i]
                            val photo = Photo()

                            photo.id = item.deletehash
                            photo.title = item.title
                            photo.url = item.link
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

    private fun deleteImage(id: String) {
        val client = OkHttpClient()
        val token = arguments?.get("access_token").toString()
        val mediaType: MediaType? = MediaType.parse("text/plain")
        val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")
        val body = RequestBody.create(JSON, "{}")
        val request: Request = Request.Builder()
            .url("https://api.imgur.com/3/image/$id")
            .method("DELETE", body)
            .addHeader("Authorization", "Bearer $token")
            .build()
        GlobalScope.launch() {
            client.newCall(request).execute().use {response ->
                if (!response.isSuccessful) {
                    throw IOException("Error in connection !!! : $response")
                }
                println(response)
            }
        }
    }
}