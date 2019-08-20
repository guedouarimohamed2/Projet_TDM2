package com.tdm2serie2ex4.master.projet_tdm2.Adapter

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tdm2serie2ex4.master.projet_tdm2.Database.DataBase
import com.tdm2serie2ex4.master.projet_tdm2.Interface.IItemClickListener
import com.tdm2serie2ex4.master.projet_tdm2.MainActivity
import com.tdm2serie2ex4.master.projet_tdm2.Model.Annonce
import com.tdm2serie2ex4.master.projet_tdm2.Model.AnnonceSaved
import com.tdm2serie2ex4.master.projet_tdm2.Model.RSSObject
import com.tdm2serie2ex4.master.projet_tdm2.R
import com.tdm2serie2ex4.master.projet_tdm2.VideoActivity
import java.util.ArrayList

class MyViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
    internal var text_wilaya: TextView
    internal var text_type: TextView
    internal var text_nom: TextView
    internal lateinit var btn : Button
    internal lateinit var btn_v : Button

    internal var itemClickListener: IItemClickListener?=null

    fun setItemClickListener(itemClickListener:IItemClickListener)
    {
        this.itemClickListener = itemClickListener
    }
    init {
        text_nom = itemView.findViewById(R.id.nom) as TextView
        text_type = itemView.findViewById(R.id.type) as TextView
        text_wilaya = itemView.findViewById(R.id.wilaya) as TextView
        btn =  itemView.findViewById(R.id.btn_save) as Button
        btn_v =  itemView.findViewById(R.id.btn_video) as Button
        itemView.setOnClickListener{view -> itemClickListener!!.onclick(view,adapterPosition)}

    }
}

class AnnonceListAdapter (private val context: Context,
                          private val rssObject: RSSObject): androidx.recyclerview.widget.RecyclerView.Adapter<MyViewHolder>(){
    private val inflater:LayoutInflater;
    init {
        inflater = LayoutInflater.from(context)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = inflater.inflate(R.layout.annonce_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return rssObject.items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text_nom.text= rssObject.items[position].title
        holder.text_type.text=rssObject.items[position].content
        holder.text_wilaya.text=rssObject.items[position].pubDate

        holder.setItemClickListener(object :IItemClickListener{

            override fun onclick(view: View, position: Int) {
//                LocalBroadcastManager.getInstance(context)
//                        .sendBroadcast(Intent("position").putExtra("title",rssObject.items[position].title))
                Toast.makeText(context, "Hi there! This is a Toast.", Toast.LENGTH_SHORT).show()
            }
        })
        lateinit var db: RoomDatabase
        var annonces: List<Annonce> = ArrayList<Annonce>()

        holder.btn.setOnClickListener {
            val activity: MainActivity = context as MainActivity
            // LocalBroadcastManager.getInstance(context)
            Toast.makeText(context, holder.text_nom.text, Toast.LENGTH_SHORT).show();

            db = Room.databaseBuilder(
                    context,
                    DataBase::class.java, "annonce.db"
            ).allowMainThreadQueries().build()

            val a1 = Annonce(0,rssObject.items[position].title,rssObject.items[position].content,rssObject.items[position].pubDate)
            AnnonceSaved.ans.clear()
            (db as DataBase).annonceDao().insertAll(a1)
            annonces = (db as DataBase).annonceDao().getAll()
            for(i in annonces){
                AnnonceSaved.add_annonce(i)
            }

            // Toast.makeText(Activity(), "Click!", Toast.LENGTH_SHORT).show();

        }

        holder.btn_v.setOnClickListener {
            Toast.makeText(context,"efzerfze",Toast.LENGTH_SHORT).show()

            val intent = Intent(context, VideoActivity::class.java)
            context.startActivity(intent)
        }
    }

}