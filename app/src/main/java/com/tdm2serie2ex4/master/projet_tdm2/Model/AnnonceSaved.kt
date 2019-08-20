package com.tdm2serie2ex4.master.projet_tdm2.Model

import android.content.ContentResolver
import android.net.Uri
import java.util.ArrayList

object AnnonceSaved {
    var tests = ArrayList<Annonce>()
    var ans:MutableList<Annonce> = ArrayList()
    val t = System.currentTimeMillis()
    //Log.i("TAG", "SERIAL: " + Build.SERIAL);
    val KEY_ENABLE_HOME = "position"
    var type_recherche = 1
    fun initial(){


    }
    fun add_annonce(annonce: Annonce){
        ans.add(annonce)
    }
    fun remove_annonce(annonce: Annonce){
        ans.remove(annonce)
    }

    fun findAnnonceByTitre(titre: String?): Annonce? {
        for (a in AnnonceSaved.ans)
            if (a.nom.equals(titre))
                return a
        return null
    }

    fun findAnnonceByPosition(pos: Int?): Annonce? {
        if(AnnonceSaved.ans.size > pos!!)
             return AnnonceSaved.ans[pos]
        return null
    }

}