package com.udovyk.users.ui.userdetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import com.udovyk.users.R
import com.udovyk.users.bus.RxBus
import com.udovyk.users.bus.events.UserClicked
import com.udovyk.users.ext.getViewModelOfType
import com.udovyk.users.network.model.ResultsItem
import com.udovyk.users.ui.base.BaseFragment
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.user_detail_fragment.*

class UserDetailFragment : BaseFragment() {

    private lateinit var viewModel: UserDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).getViewModelOfType()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.user_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenEvents()
    }

    private fun updateUI(resultsItem: ResultsItem) {

        if (resultsItem != null) {
            container.visibility = View.VISIBLE

            Glide.with(this)
                .load(resultsItem.picture!!.medium)
                .apply(RequestOptions().circleCrop())
                .into(imDetailAvatar)

            val name = resultsItem.name!!.first.plus(" ").plus(resultsItem.name.last)
            tvDetailName.text = name
            tvGender.text = resultsItem.gender
            val originDate = resultsItem.dob!!.date
            val newDate = originDate!!.substring(0, 10)
            tvDate.text = newDate
            tvPhone.text = resultsItem.phone
            tvAddress.text = resultsItem.location!!.city

        }
    }

    private fun listenEvents() {
        disposable += RxBus.listen(UserClicked::class.java).subscribe {
            updateUI(it.item)
        }
    }

}
