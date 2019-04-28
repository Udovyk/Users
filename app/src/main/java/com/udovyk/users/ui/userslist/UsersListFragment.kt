package com.udovyk.users.ui.userslist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.udovyk.users.R
import com.udovyk.users.bus.RxBus
import com.udovyk.users.bus.events.UserClicked
import com.udovyk.users.ext.getViewModelOfType
import com.udovyk.users.network.model.ResultsItem
import com.udovyk.users.ui.base.BaseFragment
import com.udovyk.users.ui.userslist.paging.ItemAdapter
import com.udovyk.users.ui.userslist.paging.RecyclerTouchListener
import kotlinx.android.synthetic.main.users_list_fragment.*

class UsersListFragment : BaseFragment() {

    private lateinit var viewModel: UsersListViewModel
    private val adapter = ItemAdapter()
    private var touchListener: RecyclerTouchListener? = null
    private var wasInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).getViewModelOfType()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.users_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUsers()
        viewModel.progressLiveData.observe(this, Observer<Boolean> { onProgress(it) })
        viewModel.itemPagedList.observe(this, Observer<PagedList<ResultsItem>> { adapter.submitList(it) })

        initRecyclerView()

    }

    private fun onProgress(value: Boolean) {
        if (value) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
            sendFirstUser()
        }
    }

    private fun sendFirstUser() {
        if (!wasInitialized && adapter.currentList!!.isNotEmpty()) {
            val firstUser = adapter.currentList!![0]
            RxBus.publish(UserClicked(firstUser!!))
            wasInitialized = true
        }
    }

    private fun initRecyclerView() {
        rvUsers.setHasFixedSize(true)
        rvUsers.layoutManager = LinearLayoutManager(context!!)
        rvUsers.adapter = adapter

        touchListener = RecyclerTouchListener(activity!!)

        touchListener!!.listener = object : RecyclerTouchListener.ClickListener {
            override
            fun onPress(position: Int, view: View) {
                val userClicked = adapter.currentList!![position]
                RxBus.publish(UserClicked(userClicked!!))
            }

            override
            fun onLongPress(position: Int, view: View) {
            }
        }

        rvUsers.addOnItemTouchListener(touchListener!!)

    }
}
