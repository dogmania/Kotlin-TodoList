package com.example.todolist

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todolist.databinding.ActivityCareOnBinding

class CareOnActivityVideo : AppCompatActivity(), OnVideoItemClickListener {
    private lateinit var binding: ActivityCareOnBinding
    private lateinit var adapter: CareOnRecyclerViewAdapter
    private lateinit var videoList: ArrayList<Video>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCareOnBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        videoList = arrayListOf(
            Video("Video1", "https://cvws.icloud-content.com/B/ASbhXQVczIHdeXcoQcmXX7qOGhZ-ATfp72hrHd4_EnPh-1FYZJMIbQKL/59.JPEG?o=AnZ8P_9zF6_1CqyeqljTSuu3qgoF7A0BCMdbSMXPvKRe&v=1&x=3&a=CAog1DdwD945lFEiypiscjwvPdZkkFrl_ShqhAUHhTdp0zESbxCd4IHj6TAYnb3d5OkwIgEAUgSOGhZ-WgQIbQKLaieQLQmneilvsg8XHLcHNBf7UGRDHP80y1pXGvLd-JCGrxZs6bNwdvlyJwyUUjvgFEiwQu8F_VNjyUfU3KyJWSr8FWB1zKq6T8P2ds1kY9Dsiw&e=1677664411&fl=&r=845aeb73-8cb7-4113-9281-f9c854886015-1&k=hY5fCCUlfQWpYbTTqoLW4A&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=48&s=OzT7sW52DYn7ofkx0DF0xCjDsb4&cd=i", "dqtGRf9Jewc"),
            Video("Video2", "https://cvws.icloud-content.com/B/ASbhXQVczIHdeXcoQcmXX7qOGhZ-ATfp72hrHd4_EnPh-1FYZJMIbQKL/59.JPEG?o=AnZ8P_9zF6_1CqyeqljTSuu3qgoF7A0BCMdbSMXPvKRe&v=1&x=3&a=CAog1DdwD945lFEiypiscjwvPdZkkFrl_ShqhAUHhTdp0zESbxCd4IHj6TAYnb3d5OkwIgEAUgSOGhZ-WgQIbQKLaieQLQmneilvsg8XHLcHNBf7UGRDHP80y1pXGvLd-JCGrxZs6bNwdvlyJwyUUjvgFEiwQu8F_VNjyUfU3KyJWSr8FWB1zKq6T8P2ds1kY9Dsiw&e=1677664411&fl=&r=845aeb73-8cb7-4113-9281-f9c854886015-1&k=hY5fCCUlfQWpYbTTqoLW4A&ckc=com.apple.clouddocs&ckz=com.apple.CloudDocs&p=48&s=OzT7sW52DYn7ofkx0DF0xCjDsb4&cd=i", "-T8v8zhUI4A"),
            Video("Video3", "", "yEHZUeArfcw"),
            Video("Video4", "", "XsfdWHItAM4"),
            Video("Video5", "", "pW0sXdsC24Y")
        )

        binding.recyclerview.addItemDecoration(
            VideoItemDecoration(
                this,
                2,
                20,
                true
            )
        )

        start()
    }

    override fun onItemClick(video: Video) {
        val youtubeIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:${video.videoId}"))
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=${video.videoId}"))
        try {
            startActivity(youtubeIntent)
        } catch (ex: ActivityNotFoundException) {
            startActivity(webIntent)
        }
    }

    private fun start() {
        Thread{
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView() {
        runOnUiThread{
            adapter = CareOnRecyclerViewAdapter(videoList, this)
            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = GridLayoutManager(this, 2)
        }
    }

    override fun onRestart() {
        super.onRestart()
        start()
    }
}