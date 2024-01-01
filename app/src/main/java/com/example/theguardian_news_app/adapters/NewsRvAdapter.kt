package com.example.theguardian_news_app.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theguardian_news_app.R
import com.example.theguardian_news_app.models.Article
import okhttp3.internal.http2.Http2Connection
import java.util.concurrent.CompletableFuture.AsynchronousCompletionTask


class NewsRvAdapter: RecyclerView.Adapter<NewsRvAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    lateinit var articleImage: ImageView
    lateinit var articleTitle: TextView
    lateinit var articleDateTime: TextView
    lateinit var articleDescription: TextView
    lateinit var articleSource: TextView

    private val differCallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        //check if the objects items are the same
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    //determine the difference between two listes on thread (BackList)
    val diff = AsyncListDiffer(this, differCallback)

    private var onItemClickListener : ((Article) -> Unit)? = null
    override fun getItemCount(): Int {
        return diff.currentList.size
    }

    override fun onBindViewHolder(holder: NewsRvAdapter.ArticleViewHolder, position: Int) {
        val article = diff.currentList[position]

        articleImage = holder.itemView.findViewById(R.id.articleImage)
        articleTitle = holder.itemView.findViewById(R.id.articleTitle)
        articleSource = holder.itemView.findViewById(R.id.articleSource)
        articleDateTime = holder.itemView.findViewById(R.id.articleDateTime)
        articleDescription = holder.itemView.findViewById(R.id.articleDescription)

        holder.itemView.apply{
            Glide.with(this).load(article.urlToImage).into(articleImage)
            articleSource.text = article.source.name
            articleTitle.text = article.title
            articleDescription.text = article.description
            articleDateTime.text = article.publishedAt

            setOnClickListener {
                onItemClickListener?.let{
                    it(article)
                }
            }
        }

        //for external classes
        fun onItemClickListener(listener: (Article) -> Unit){
            onItemClickListener = listener
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder{
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent,  false)
        return ArticleViewHolder(layout)
    }

}