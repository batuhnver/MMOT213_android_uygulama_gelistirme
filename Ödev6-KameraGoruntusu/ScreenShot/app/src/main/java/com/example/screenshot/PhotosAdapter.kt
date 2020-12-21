package com.example.screenshot

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.photos_main.view.*

class PhotosAdapter(): BaseAdapter()
{
    var images : List<ByteArray> = emptyList()
    var context: Context?=null

    constructor(context: Context, imagesList: List<ByteArray>) : this() {
        this.context = context
        this.images = imagesList
    }
    @SuppressLint("ServiceCast", "ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        val img= BitmapFactory.decodeByteArray(images[position], 0, images[position].size)
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var view = inflator.inflate(R.layout.photos_main, null)
        view.imageView.setImageBitmap(img)

        return view
    }

    override fun getItem(position: Int): Any {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return images.size
    }

}