package com.tdm2serie2ex4.master.projet_tdm2

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.tdm2serie2ex4.master.projet_tdm2.Adapter.AnnonceListAdapter
import com.tdm2serie2ex4.master.projet_tdm2.Model.RSSObject

import com.tdm2serie2ex4.master.projet_tdm2.R
import com.tdm2serie2ex4.master.projet_tdm2.Reader.HTTPDataHandler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_list_annonce.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListAnnonce.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListAnnonce.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListAnnonce : androidx.fragment.app.Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    internal lateinit var recycler_view: androidx.recyclerview.widget.RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_list_annonce, container, false)

        recycler_view = itemView.findViewById(R.id.annonce_recyclerview) as androidx.recyclerview.widget.RecyclerView
        recycler_view.setHasFixedSize(true)

        recycler_view.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 1)

        loadRSS(recycler_view)
        return itemView;
    }
    private val  RSS_link ="http%3A%2F%2Fwww.annonce-algerie.com%2Fupload%2Fflux%2Frss_1.xml&api_key=murmsbbpp53y8kdgb9df5jjly9xyhepv0xjhuh2z&count=30"
    private val  RSS_to_JSON_API ="https://api.rss2json.com/v1/api.json?rss_url="


    private fun loadRSS(annonce_recyclerview: androidx.recyclerview.widget.RecyclerView) {
        val loadRSSAsyn = object : AsyncTask<String, String, String>(){

            internal var mDialog = ProgressDialog(getActivity())
            override fun onPostExecute(result: String?) {
                mDialog.dismiss()
                var rssObject: RSSObject
                rssObject = Gson().fromJson<RSSObject>(result, RSSObject::class.java!!)
                val adapter = AnnonceListAdapter(activity!!,rssObject)
                annonce_recyclerview.adapter=adapter
              //   test.text = rssObject.items.toString()
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
        loadRSSAsyn.execute(url_get_data.toString())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

}
