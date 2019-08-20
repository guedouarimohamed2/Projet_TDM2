package com.tdm2serie2ex4.master.projet_tdm2

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import com.tdm2serie2ex4.master.projet_tdm2.Adapter.AnnonceListAdapter
import com.tdm2serie2ex4.master.projet_tdm2.Database.DataBase
import com.tdm2serie2ex4.master.projet_tdm2.Model.Annonce
import com.tdm2serie2ex4.master.projet_tdm2.Model.AnnonceSaved
import com.tdm2serie2ex4.master.projet_tdm2.Model.RSSObject
import com.tdm2serie2ex4.master.projet_tdm2.R.id.annonce_recyclerview
import com.tdm2serie2ex4.master.projet_tdm2.R.id.toolbar
import com.tdm2serie2ex4.master.projet_tdm2.Reader.HTTPDataHandler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_annonce.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val  RSS_link ="http://www.annonce-algerie.com/upload/flux/rss_1.xml"
    private val  RSS_to_JSON_API ="https://api.rss2json.com/v1/api.json?rss_url="

    var list_off_line:ListOffLine? = ListOffLine()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title="Annonces"
        setSupportActionBar(toolbar)


        loadFrag1(ListAnnonce())
        initial()
/*
        val LinearLayoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
        annonce_recyclerview.layoutManager = LinearLayoutManager*/
      //  loadRSS();

    }
    var annonces: List<Annonce> = ArrayList<Annonce>()

    lateinit var db: RoomDatabase
    fun initial(){
        db = Room.databaseBuilder(
                this.applicationContext,
                DataBase::class.java, "annonce.db"
        ).allowMainThreadQueries().build()
        annonces = (db as DataBase).annonceDao().getAll()

        if(!annonces.isEmpty()) {
            for(i in annonces){
                AnnonceSaved.add_annonce(i)
            }
        }else{

        }
    }


    fun show_list_annonce(view : View){
        loadFrag1(ListAnnonce())
        // test_text.text= Annonces.ans[0].nom
    }

    fun show_list_off_line(view : View){
        loadFrag2(ListOffLine())
    }
    private fun loadFrag1(f1:ListAnnonce){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,f1)


        ft.addToBackStack(null)

        ft.commit()
    }

    private fun loadFrag2(f2:ListOffLine){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment,f2)
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun loadRSS() {/*
                val loadRSSAsyn = object : AsyncTask<String, String, String>(){

                internal var mDialog = ProgressDialog(this@MainActivity)
                override fun onPostExecute(result: String?) {
                    mDialog.dismiss()
                    var rssObject: RSSObject
                    rssObject = Gson().fromJson<RSSObject>(result,RSSObject::class.java!!)
                    val adapter = AnnonceListAdapter(baseContext,rssObject)
                    annonce_recyclerview.adapter=adapter
                   // test.text = rssObject.items.toString()
                    adapter.notifyDataSetChanged()
                }

                override fun doInBackground(vararg params: String?): String {
                    val result:String
                    val http: HTTPDataHandler = HTTPDataHandler()
                    result = http.getHTTPDataHandler(params[0]);
                   // test.text=result.toString()
                    return result
                }

                override fun onPreExecute() {
                    //Toast.makeText(applicationContext, "jniubni", Toast.LENGTH_SHORT).show();
                    mDialog.setMessage("please wait")
                    mDialog.show()
                }
            }
        var url_get_data = StringBuilder(RSS_to_JSON_API)
        url_get_data.append(RSS_link)
        loadRSSAsyn.execute(url_get_data.toString())*/
    }
}
