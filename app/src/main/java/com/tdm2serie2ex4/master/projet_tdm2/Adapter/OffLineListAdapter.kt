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
import androidx.room.Room
import com.tdm2serie2ex4.master.projet_tdm2.Database.DataBase
import com.tdm2serie2ex4.master.projet_tdm2.Interface.IItemClickListener
import com.tdm2serie2ex4.master.projet_tdm2.MainActivity
import com.tdm2serie2ex4.master.projet_tdm2.Model.Annonce
import com.tdm2serie2ex4.master.projet_tdm2.Model.AnnonceSaved
import com.tdm2serie2ex4.master.projet_tdm2.R

class OffLineListAdapter(internal var context: Context,
                         internal var annonceList:MutableList<Annonce>): androidx.recyclerview.widget.RecyclerView.Adapter<OffLineListAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.annonce_off_line_item,parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return annonceList.size
    }

    override fun onBindViewHolder(holder: OffLineListAdapter.MyViewHolder, position: Int) {
        holder.text_nom.text= annonceList[position].nom
        holder.text_type.text=annonceList[position].type
        holder.text_wilaya.text=annonceList[position].wilaya


        holder.setItemClickListener(object :IItemClickListener{

            override fun onclick(view: View, position: Int) {
//                LocalBroadcastManager.getInstance(context)
//                        .sendBroadcast(Intent(AnnonceSaved.KEY_ENABLE_HOME).putExtra("nom",annonceList[position].nom))
                Toast.makeText(context, annonceList[position].nom, Toast.LENGTH_SHORT).show()
            }
        })


        holder.btn_delete.setOnClickListener {
            val activity: MainActivity = context as MainActivity
            // LocalBroadcastManager.getInstance(context)
            val db = Room.databaseBuilder(
                    context,
                    DataBase::class.java, "annonce.db"
            ).allowMainThreadQueries().build()

            //   Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show();
          //  activity.list_off_line!!.recycler_view.adapter = activity.list_off_line!!.adapter
            db.annonceDao().delete(AnnonceSaved.findAnnonceByTitre(annonceList[position].nom.toString())!!)
            AnnonceSaved.remove_annonce(AnnonceSaved.findAnnonceByTitre(annonceList[position].nom.toString())!!)
            this.notifyDataSetChanged()
        }
    }

    inner class MyViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView){
        internal var text_wilaya: TextView
        internal var text_type: TextView
        internal var text_nom: TextView
        internal var btn_delete : Button
        internal var itemClickListener: IItemClickListener?=null

        fun setItemClickListener(itemClickListener: IItemClickListener)
        {
            this.itemClickListener = itemClickListener
        }

        init {
            text_nom = itemView.findViewById(R.id.nom) as TextView
            text_type = itemView.findViewById(R.id.type) as TextView
            text_wilaya = itemView.findViewById(R.id.wilaya) as TextView
            btn_delete =  itemView.findViewById(R.id.btn_delete) as Button
            itemView.setOnClickListener{view -> itemClickListener!!.onclick(view,adapterPosition)}
        }
    }
}