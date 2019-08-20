package com.tdm2serie2ex4.master.projet_tdm2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tdm2serie2ex4.master.projet_tdm2.Adapter.OffLineListAdapter
import com.tdm2serie2ex4.master.projet_tdm2.Model.AnnonceSaved

import com.tdm2serie2ex4.master.projet_tdm2.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListOffLine.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListOffLine.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ListOffLine : androidx.fragment.app.Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    internal lateinit var recycler_view: androidx.recyclerview.widget.RecyclerView
    internal  lateinit var adapter:OffLineListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_list_off_line, container, false)
        recycler_view = itemView.findViewById(R.id.annonce_recyclerview_off_line) as androidx.recyclerview.widget.RecyclerView
        recycler_view.setHasFixedSize(true)

        recycler_view.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 1)
        adapter = OffLineListAdapter(activity!!,AnnonceSaved.ans)
        recycler_view.adapter = adapter
        return itemView;
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
