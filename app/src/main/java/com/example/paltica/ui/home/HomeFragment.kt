package com.example.paltica.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.paltica.R
import com.example.paltica.databinding.CardItemBinding
import com.example.paltica.databinding.FragmentHomeBinding
import com.google.android.material.card.MaterialCardView

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var cardAdapter: SimpleCardAdapter<CardItem>
    val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.items.apply {
            cardAdapter = SimpleCardAdapter(getDummyData()) { view, item ->
                CardItemBinding.bind(view).apply {
                    imgContainer.setImageResource(item.imageResourceId)
                    view.context.resources.getStringArray(item.stringResourceId).let {
                        txtTitle.text = it[0]
                        txtDescription.text = it[1]
                    }
                }
            }
            adapter = cardAdapter

        }
    }

    private fun getDummyData() = mutableListOf(CardItem(R.array.item_1_title_desc, R.mipmap.img_01),
        CardItem(R.array.item_2_title_desc, R.mipmap.img_02),
        CardItem(R.array.item_3_title_desc, R.mipmap.img_03))

}

data class CardItem(@ArrayRes val stringResourceId: Int,
                    @DrawableRes val imageResourceId: Int )